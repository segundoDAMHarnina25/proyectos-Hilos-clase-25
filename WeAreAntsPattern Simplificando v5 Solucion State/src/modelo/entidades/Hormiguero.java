package modelo.entidades;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import modelo.insectos.*;
import modelo.soporte.*;
import utiles.Limpiador;

public class Hormiguero implements PropertyChangeListener {
    private ZonaDefensa zonaDefensa;
    Statistics statistics;
    public final int cantidadHormigasPorSector = 10;
    public final int cantidadHormigasTotal = 30;

    private boolean atacada = false,retirar=false;

    private List<Hormiga> hormigas;
    private static long id = 1;
    private boolean tareaAcabada = false;

    public Hormiguero() {
        super();
        hormigas = new ArrayList<Hormiga>();
        zonaDefensa = new ZonaDefensa("territorio");
        statistics=new Statistics();
    }

    public void funciona() {
        int cantidad = 0;
        do {
            tareaGlobal();
            List<Hormiga> retiradas;
            if (atacada)
                tareaDefensa();
            if(retirar){
                retiradas=zonaDefensa.retirada();
                System.out.println("repatriando");
                convertirARecolectoras(retiradas);
                comprobarRecolectoras();
                retirar=false;
            }
            if (cantidad++ == 1) {
                System.out.println("under attack");
                atacada = true;
            }
            if (cantidad == 4) {
                System.out.println("end war");
                atacada = false;
            }
            if (cantidad == 10)
                setTareaAcabada(true);
        } while (!isTareaAcabada());
        System.out.println("hormiga totales "+hormigas.size());
        //mostrar estadisticas
        System.out.println(statistics.getCurrentMediaAlimento());
        System.out.println(statistics.getCurrentIndiceGlobal());
    }

    private void comprobarRecolectoras() {
        hormigas.forEach((hormiga -> {
            System.out.println("recolectora "+(hormiga.getComportamiento().getClass()==Recolectora.class));
        }));
    }

    private void convertirARecolectoras(List<Hormiga> retiradas) {
        retiradas.forEach((hormiga -> {
            hormiga.setComportamiento(new Recolectora(hormiga));
        }));
    }
    private void tareaGlobal() {
        hormigas.forEach((hormiga) -> {
            hormiga.hacerTarea();
        });
        System.out.println("hormigas muertas globales " + new Limpiador<Hormiga>().enterrarHormigas(hormigas));
        crearHormigas();
    }

    private void tareaDefensa() {
        zonaDefensa.insertaHormigas(cantidadHormigasTotal, cantidadHormigasPorSector, hormigas);
        zonaDefensa.recorrer();
    }

    private void crearHormigas() {
        int contador=0;
        for (int i = hormigas.size(); i < cantidadHormigasTotal; i++) {
            Hormiga hormiga = new Hormiga(id++);
            hormiga.addPropertyChangeListener(this);
            hormigas.add(hormiga);
            contador++;
        }
        System.out.println("nuevas hormigas "+contador);
    }


    private boolean isTareaAcabada() {
        return tareaAcabada;
    }

    private void setTareaAcabada(boolean tareaAcabada) {
        this.tareaAcabada = tareaAcabada;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HormigaData data = (HormigaData) evt.getNewValue();
        statistics.addData(data);
    }
}

package modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import modelo.insectos.Comportamiento;
import modelo.insectos.Guerrera;
import modelo.insectos.Hormiga;
import modelo.insectos.Obrera;
import modelo.soporte.AreaConstruccion;
import modelo.soporte.SoporteHormiga;
import modelo.soporte.ZonaDefensa;

public class Hormiguero {
    private AreaConstruccion areaConstruccion;
    private ZonaDefensa zonaDefensa;
    private final int cantidadHormigasPorSector = 100;
    private final int totalHormigas = 400;

    private List<Hormiga> hormigas;
    private static long id = 1;
    private boolean tareaAcabada = false;

    public Hormiguero() {
        super();
        hormigas = new ArrayList<Hormiga>();
        zonaDefensa = new ZonaDefensa("territorio");
        areaConstruccion = new AreaConstruccion("global");
    }

    public void funciona() {
        int cantidad = 0;
        do {
            tareaGlobal();
            tareaDefensa();
            tareaConstruccion();
            if (cantidad++ == 10)
                setTareaAcabada(true);
        } while (isTareaAcabada());
    }

    private void tareaGlobal() {
        hormigas.forEach((hormiga) -> {
            hormiga.hacerTarea();
        });
    }

    private void tareaDefensa() {
        do {
            do {
                zonaDefensa.recorrer();
            } while (zonaDefensa.isFinalGuardia());
            insertaHormigasGuerrera(zonaDefensa);
        } while (!isTareaAcabada());
    }

    private void tareaConstruccion() {
        int reparar = 0;
        do {
            reparar = areaConstruccion.reparar(reparar);
            insertaHormigasObrera(areaConstruccion);
        } while (!isTareaAcabada());
    }


    private void insertaHormigasGuerrera(SoporteHormiga elemento) {
        for (int i = elemento.getHormigasSize(); i <= cantidadHormigasPorSector; i++) {
            insertarHormiga(elemento, new Guerrera());
        }
    }

    private void insertaHormigasObrera(SoporteHormiga elemento) {
        for (int i = elemento.getHormigasSize(); i <= cantidadHormigasPorSector; i++) {
            insertarHormiga(elemento, new Obrera(areaConstruccion));
        }
    }

    private void insertarHormiga(SoporteHormiga elemento, Comportamiento comportamiento) {
        Hormiga hormiga = new Hormiga(id++);
        hormiga.setComportamiento(comportamiento);
        hormigas.add(hormiga);
        elemento.insertar(hormiga);
    }


    private boolean isTareaAcabada() {
        return tareaAcabada;
    }

    private void setTareaAcabada(boolean tareaAcabada) {
        this.tareaAcabada = tareaAcabada;
    }

}

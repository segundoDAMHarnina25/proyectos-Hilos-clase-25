package modelo.soporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.insectos.Hormiga;
import modelo.insectos.Obrera;
import utiles.Limpiador;

public class AreaConstruccion implements SoporteHormiga {
    private String nombre;
    List<Hormiga> constructoras;
    private boolean crecimientoAcabado = false;

    public AreaConstruccion(String nombre) {
        super();
        this.nombre = nombre;
        constructoras = new ArrayList<>();
    }


    @Override
    public void insertar(Hormiga hormigaObrera) {
        Obrera obrera = new Obrera(this);
        hormigaObrera.setComportamiento(obrera);
        constructoras.add(hormigaObrera);
    }

    public int reparar(int pendiente) {
        if (pendiente == 0) {
            pendiente = new Random().nextInt(1000);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        do {
            for (Hormiga hormiga : constructoras) {
                hormiga.hacerTarea();
                pendiente--;
            }
            new Limpiador<Hormiga>().enterrarHormigas(constructoras);
        } while (pendiente != 0 && !constructoras.isEmpty());
        return pendiente;

    }

    @Override
    public String toString() {
        return nombre;
    }

    private boolean isCrecimientoAcabado() {
        return crecimientoAcabado;
    }

    private void setCrecimientoAcabado(boolean crecimientoAcabado) {
        this.crecimientoAcabado = crecimientoAcabado;
    }

    @Override
    public int getHormigasSize() {
        return constructoras.size();
    }
}

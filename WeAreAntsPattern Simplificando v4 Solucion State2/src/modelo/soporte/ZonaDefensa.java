package modelo.soporte;

import java.util.ArrayList;
import java.util.List;

import modelo.insectos.Guerrera;
import modelo.insectos.Hormiga;
import utiles.Limpiador;

public class ZonaDefensa implements SoporteHormiga {
    private String nombre;
    private List<Hormiga> defensoras;
    private boolean finalGuardia;

    public ZonaDefensa(String name) {
        super();
        this.nombre = name;
        defensoras = new ArrayList<>();
    }

    @Override
    public void insertar(Hormiga hormiga) {
        hormiga.setComportamiento(new Guerrera());
        defensoras.add(hormiga);
    }

    public void recorrer() {
        finalGuardia = false;
        defensoras.forEach((defensora) -> {
            defensora.hacerTarea();
        });
        new Limpiador<Hormiga>().enterrarHormigas(defensoras);
        if (defensoras.isEmpty()) finalGuardia = true;
    }

    public boolean isFinalGuardia() {
        return finalGuardia;
    }

    protected void setFinalGuardia(boolean finalGuardia) {
        this.finalGuardia = finalGuardia;
    }


    @Override
    public int getHormigasSize() {
        return defensoras.size();
    }
}

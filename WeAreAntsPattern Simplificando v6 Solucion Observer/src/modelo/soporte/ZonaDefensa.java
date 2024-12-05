package modelo.soporte;

import java.util.ArrayList;
import java.util.List;

import modelo.insectos.Guerrera;
import modelo.insectos.Hormiga;
import utiles.Limpiador;

public class ZonaDefensa{
    private String nombre;
    private List<Hormiga> defensoras;
    private boolean finalGuardia;

    public ZonaDefensa(String name) {
        super();
        this.nombre = name;
        defensoras = new ArrayList<>();
    }

    public void insertar(Hormiga hormiga) {
        hormiga.setComportamiento(new Guerrera(hormiga));
        defensoras.add(hormiga);
    }

    public void recorrer() {
        finalGuardia = false;
        defensoras.forEach((defensora) -> {
            defensora.hacerTarea();
        });
        System.out.println("hormigas guerrreras muertas "+new Limpiador<Hormiga>().enterrarHormigas(defensoras));
        if (defensoras.isEmpty()) finalGuardia = true;
    }

    public boolean isFinalGuardia() {
        return finalGuardia;
    }

    protected void setFinalGuardia(boolean finalGuardia) {
        this.finalGuardia = finalGuardia;
    }


    public int getHormigasSize() {
        return defensoras.size();
    }

    public boolean contains(Hormiga hormiga) {
        return defensoras.contains(hormiga);
    }
    public List<Hormiga> retirada() {
        List<Hormiga> retirada=defensoras;
        defensoras=new ArrayList<>();
        return retirada;
    }
    public void insertaHormigas(int cantidadHormigasTotal,int cantidadHormigasPorSector,List<Hormiga> hormigas) {
        int j=0;
        int reclutadas=0;
        for (int i = getHormigasSize(); i < cantidadHormigasPorSector; i++) {
            boolean insertada=false;
            do{
                Hormiga hormiga = hormigas.get(j++);
                if(!contains(hormiga)) {
                    insertar(hormiga);
                    insertada = true;
                    reclutadas++;
                }
            }while(!insertada);
        }
        System.out.println("rclutadas "+reclutadas);
    }
}

package modelo.soporte;

import modelo.insectos.Hormiga;

public interface SoporteHormiga extends Hormigeable,Insertable{
    boolean contains(Hormiga hormiga);
}

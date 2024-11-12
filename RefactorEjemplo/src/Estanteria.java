
// Clase interna para organizar los libros en una estantería

import java.util.ArrayList;
import java.util.List;

public class Estanteria {
    private List<Libro> libros;

    public Estanteria() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("La estantería está vacía.");
        } else {
            System.out.println("Libros en la estantería:");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }
}
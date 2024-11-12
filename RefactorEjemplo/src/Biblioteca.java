public class Biblioteca {

    // Método para crear un libro y agregarlo a una estantería
    public void agregarLibroAEstanteria(String titulo, String autor) {
        Estanteria estanteria = new Estanteria();
        Libro libro = new Libro(titulo, autor);
        estanteria.agregarLibro(libro);
        estanteria.mostrarLibros();
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.agregarLibroAEstanteria("Cien Años de Soledad", "Gabriel García Márquez");
        biblioteca.agregarLibroAEstanteria("Don Quijote de la Mancha", "Miguel de Cervantes");
    }
}


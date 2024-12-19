package modelo.oms;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import modelo.data.Cardumen;
import modelo.data.Coordenada;
import modelo.enums.EspeciePez;

public class CardumenOM {

    /**
     * Genera una lista de card�menes con datos predefinidos.
     *
     * @param dimension Dimensi�n del �rea donde se ubicar�n los card�menes.
     * @param cantidadCardumenes N�mero de card�menes a generar.
     * @return Lista de card�menes generados seg�n los datos predefinidos.
     */
    public static List<Cardumen> getAll(Dimension dimension, int cantidadCardumenes) {
        List<Cardumen> cardumenes = new ArrayList<>();

        // Datos predefinidos para las pruebas
        EspeciePez[] especies = { EspeciePez.ATUN, EspeciePez.SARDINA, EspeciePez.MERLUZA };
        int[] pesos = { 500, 400, 400 };
        double[] velocidadesRegeneracion = { 50.0, 25.0, 53.5 };
        Coordenada[] posiciones = {
            new Coordenada(10, 10),
            new Coordenada(20, 20),
            new Coordenada(30, 30)
        };

        // Generar los card�menes usando los datos predefinidos
        for (int i = 0; i < cantidadCardumenes; i++) {
            int index = i % especies.length; // Rotar entre los datos predefinidos
            Cardumen cardumen = new Cardumen(
                especies[index],
                posiciones[index],
                pesos[index],
                velocidadesRegeneracion[index],
                dimension
            );
            cardumenes.add(cardumen);
        }

        return cardumenes;
    }
}


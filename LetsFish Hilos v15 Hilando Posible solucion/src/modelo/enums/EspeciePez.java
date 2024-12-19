package modelo.enums;

public enum EspeciePez {
    ATUN(500,TipoBarco.PALANGRE),     // Tama�o en kilogramos
    SARDINA(1,TipoBarco.CERCO),
    MERLUZA(3,TipoBarco.CERCO),
    BACALAO(15,TipoBarco.CERCO),
    PEZ_ESPADA(300,TipoBarco.PALANGRE);

    private final int tamano; // Tama�o del pez en kilogramos
    private TipoBarco tipoBarco;
   
    private EspeciePez(int tamano, TipoBarco tipoBarco) {
		this.tamano = tamano;
		this.tipoBarco = tipoBarco;
	}
	
    public int getTamano() {
        return tamano;
    }

    @Override
    public String toString() {
        return name() + " (Tama�o: " + tamano + " kg)";
    }

	public boolean isSuitable(TipoBarco tipo) {
		return this.tipoBarco.equals(tipo);
	}
}

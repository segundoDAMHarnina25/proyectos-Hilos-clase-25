package modelo;

public class Puerto {
	private Long id;
	private String nombre;
	private Coordenada sitio;
	
	public Puerto(Long id, String nombre, Coordenada sitio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sitio = sitio;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Coordenada getSitio() {
		return sitio;
	}
		
}

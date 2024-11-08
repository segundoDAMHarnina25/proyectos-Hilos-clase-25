package modelo.data;

import java.util.Objects;

public class Articulo {
	private String barCode;
	private String nombre;

	private float precio;
	private int stock = 10;

	public Articulo(String barCode, String nombre, float precio) {
		super();
		this.barCode = barCode;
		this.nombre = nombre;
		setPrecio(precio);
	}

	private float getPrecio() {
		return precio;
	}

	@Override
	public Articulo clone() throws CloneNotSupportedException {
		return new Articulo(new String(this.barCode), new String(this.nombre), this.precio);
	}

	private void setPrecio(float precio) {
		assert precio > 0;
		this.precio = precio;
	}

	private int getStock() {
		return stock;
	}

	private void setStock(int stock) {
		assert stock > 0;
		this.stock = stock;
	}

	private String getBarCode() {
		return barCode;
	}

	private String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barCode, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		return Objects.equals(barCode, other.barCode) && Objects.equals(nombre, other.nombre);
	}

	public boolean gestionarStock(int cantidadSolicitada) {
		if (comprobarStock(cantidadSolicitada)) {
			decrementaStock(cantidadSolicitada);
			return true;
		}
		;
		return false;
	}

	private void decrementaStock(int cantidad) {
		stock -= cantidad;
	}

	boolean comprobarStock(int cantidad) {
		return stock > cantidad;
	}

	public int getCantidad() {
		return stock;
	}

	public boolean comprar(Integer value) {
//		System.out.println("me piden el articulo "+this.nombre+" en esta cantidad "+value+" y el stock es de "+stock);
		boolean comprobarStock = comprobarStock(value);
//		System.out.println("la comprabacion es "+comprobarStock);
//		System.out.println("stock antes de decrementar "+stock);
		if (comprobarStock) decrementaStock(value);
//		System.out.println("stock despues de decrementar "+stock);
		return comprobarStock;
	}

}

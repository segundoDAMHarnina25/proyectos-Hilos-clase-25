package modelo.repos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

import modelo.data.Articulo;

public class ArticulosEnStrockRepositorio {
	private List<Articulo> lista;
	private int total=0;
	public ArticulosEnStrockRepositorio() throws CloneNotSupportedException {
		super();
		lista=new ArticuloEnStockOM().getAll();
	}
	
	
	public ArticulosEnStrockRepositorio(List<Articulo> lista) {
		super();
		this.lista = lista;
	}


	@Override
	public int hashCode() {
		return Objects.hash(lista);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticulosEnStrockRepositorio other = (ArticulosEnStrockRepositorio) obj;
		return Objects.equals(lista, other.lista);
	}


	@Override
	public ArticulosEnStrockRepositorio clone() throws CloneNotSupportedException {
		List<Articulo> clon=new ArrayList<>();
		lista.forEach((articulo)->{try {
			clon.add(articulo.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}});
		return new ArticulosEnStrockRepositorio(clon);
	}


	public int getCantidad(Articulo articulo) {
		return lista.get(lista.indexOf(articulo)).getCantidad();
	}


	public boolean comprar(Articulo key, Integer value) {
		return lista.get(lista.indexOf(key)).comprar(value);
	}
	public int getTotalVendido() {
		total=0;
		lista.stream().forEach((articulo)->{
			int cantidad = articulo.getCantidad();
			total+=Articulo.stockInicial-cantidad;
			if(cantidad<10) {
				System.out.println();
			}
		});
		return total;
	}


	public void devolverCompra(List<Entry<Articulo, Integer>> list) {
		list.forEach(item->{
			lista.get(lista.indexOf(item.getKey())).incrementarStock(item.getValue());
		});
	}
}

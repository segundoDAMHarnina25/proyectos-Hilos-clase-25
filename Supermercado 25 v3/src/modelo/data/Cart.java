package modelo.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Cart {
	private final Map<Articulo, Integer> cesta;

	public Cart() {
		super();
		cesta = new HashMap<>();
	}

	public boolean agregar(Articulo articulo, int cantidad) {
		if (!cesta.containsKey(articulo))
			if (articulo.gestionarStock(cantidad)) {
				cesta.put(articulo, cantidad);
				return true;
			}
		return false;
	}

	public Entry<Articulo, Integer> getFirst() {
		return (Entry<Articulo, Integer>) cesta.entrySet().toArray()[0];
	}

	public boolean isEmpty() {
		return cesta.isEmpty();
	}

	public void remove(Entry<Articulo, Integer> first) {
		cesta.remove(first.getKey());

	}

	public Set<Entry<Articulo, Integer>> get() {
		return cesta.entrySet();
	}

	public int size() {
		return cesta.size();
	}

	public int getTotal() {
		return cesta.entrySet().stream().mapToInt((entry)->{return entry.getValue();}).sum();
	}
	
	@Override
	public String toString() {
		 	StringBuilder sb = new StringBuilder();
		    cesta.forEach((articulo, cantidad) -> {
		        sb.append("Artículo: ").append(articulo.toString())
		          .append(", Cantidad: ").append(cantidad)
		          .append("\n");
		    });
		    return sb.toString();
	}
}

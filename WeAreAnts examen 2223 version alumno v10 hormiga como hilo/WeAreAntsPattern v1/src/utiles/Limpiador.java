package utiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.insectos.Vivible;

public class Limpiador<T extends Vivible> {
	public int enterrarHormigas(List<T> hormigas) {
		int muertas=0;
		for (Iterator iterator = hormigas.iterator(); iterator.hasNext();) {
			T hormiga = (T) iterator.next();
			if(!hormiga.isAlive()) {
				iterator.remove();
				muertas++;
			}
		}
		return muertas;
	}
//	public List<T> enterrarHormigas(List<T> hormigas) {
//		ArrayList<T> muertas=new ArrayList<T>();
//		for (Iterator iterator = hormigas.iterator(); iterator.hasNext();) {
//			T hormiga = (T) iterator.next();
//			if(!hormiga.isAlive()) {
//				muertas.add(hormiga);
//				iterator.remove();
//			}
//		}
//		return muertas;
//	}
}

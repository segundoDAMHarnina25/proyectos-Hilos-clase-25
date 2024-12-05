package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import objectsmothers.HormigasOM;

public class Hormiguero implements Runnable {
	List<Hormiga> hormigas;
	Map<Alimento, Integer> despensa;

	public Hormiguero() {
		super();
		hormigas = HormigasOM.getHormigasInicio(5, "AA", this);
		despensa = new HashMap<Alimento, Integer>();
	}

	@Override
	public void run() {
		List<IdoneidadADN> idoneidades = new ArrayList();
		ExecutorService executorService = Executors.newCachedThreadPool();
		try {
			inciarHormiguero(idoneidades, executorService);
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		do {
			Criadero criadero = new Criadero(despensa, hormigas, idoneidades, this);
//			System.out.println("comenzamos la cria");
			try {
				hormigas=executorService.submit(criadero).get();
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			} catch (ExecutionException e2) {
				e2.printStackTrace();
			}
//			System.out.println("recibimos la cria");
			idoneidades = new ArrayList();
			for (Iterator iterator = hormigas.iterator(); iterator.hasNext();) {
				Hormiga hormiga = (Hormiga) iterator.next();
				Future<IdoneidadADN> submit = executorService.submit(hormiga);
				try {
					idoneidades.add(submit.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			System.out.println("somos " + hormigas.size() + " hormigas");
		} while (true);
	}

	private void inciarHormiguero(List<IdoneidadADN> idoneidades, ExecutorService executorService)
			throws InterruptedException, ExecutionException {
		ArrayList<Future<IdoneidadADN>> submits = new ArrayList<Future<IdoneidadADN>>();
		for (Hormiga hormiga : hormigas) {
			// antes de morir la hormiga va a donar su adn
			submits.add(executorService.submit(hormiga));
//			idoneidades.add((IdoneidadADN) executorService.submit(hormiga).get());
		}
		while (!allSubmitsArrived(submits)) {
		}
		;
		for (Future<IdoneidadADN> future : submits) {
			idoneidades.add(future.get());
		}
	}

	private boolean allSubmitsArrived(ArrayList<Future<IdoneidadADN>> submits) {
		for (Future<IdoneidadADN> future : submits) {
			if (!future.isDone())
				return false;
		}
		return true;
	}

	public void descargar(Alimento alimento) {
		if (despensa.containsKey(alimento)) {
			Integer cantidad = despensa.get(alimento);
			despensa.put(alimento, ++cantidad);
		} else {
			despensa.put(alimento, 1);
		}
	}

}

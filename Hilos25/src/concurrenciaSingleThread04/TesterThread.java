package concurrenciaSingleThread04;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class TesterThread {
	public static void main(String[] args) {
		ArrayList<Element> elements = new ArrayList<>();
		int size = 1000;
		for (int i = 0; i < size; i++) {
			elements.add(new Element());
		}
		Instant now = Instant.now();
		elements.forEach((element) -> {
			new Thread(new TransformerThread(element)).start();
		});
		Instant later = Instant.now();
		Duration duration = Duration.between(now, later);
		System.out.println("la diferencia " + duration.toMillis());
	}
}

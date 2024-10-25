package prioridades06;
import java.time.Duration;
import java.time.Instant;

import prioridades05.Utiles;

public class Element extends Thread {

	public int value;
	Instant before;
	
	public Element(String string,Instant instant) {
		super(string);
		before=instant;
	}

	public void run() {
		int times=10;
		for (int i = 0; i < times; i++) {
			value++;
//			try {
//				sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			Utiles.wasteTime(this.getName(),10000);
		}
		System.out.println("terminando el proceso "+getName()+" con tiempo "+Duration.between(before, Instant.now()));
	};
}

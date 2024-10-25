package prioridades06sleep;
import java.time.Instant;

public class Element extends Thread {

	public int value;
	Instant now;
	
	public Element(String string,Instant instant) {
		super(string);
		now=instant;
	}

	public void run() {
		System.out.println("soy el "+getName()+" y mi prioridad "+getPriority());
		int times=1000;
		for (int i = 0; i < times; i++) {
			value++;
//			if(i%10==0)
				System.out.println("interaccionando del "+getName());
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
}

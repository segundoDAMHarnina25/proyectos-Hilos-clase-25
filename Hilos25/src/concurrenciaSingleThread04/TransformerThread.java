package concurrenciaSingleThread04;

public class TransformerThread implements Runnable{
	
	private Element element;
	
	public TransformerThread(Element element) {
		super();
		this.element = element;
	}

	@Override
	public void run() {
		element.value++;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package nohilos;


import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class GPSSubscriber implements Subscriber<GeographicalPoint> {

	private Subscription subscription;
	private String name;
	private int requested=0;
	
	public GPSSubscriber(String name,int requested) {
		super();
		this.name = name;
		this.requested=requested;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription=subscription;
		//si request es 2. onNext será llamado 2 veces
		this.subscription.request(requested);
	}
	
	@Override
	public void onNext(GeographicalPoint item) {
//		subscription.request(1);
		System.out.println(name+ " recibe "+item.toString());
	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}
	
}

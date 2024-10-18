package sincronizacion02problema;
public class ProductorConsumidor {
	//En este ejemplo presentamos el problema de la sincronizacion y vemos como
	//El consumidor no va a poder acceder a todos los datos que genera productor
	//La forma ideal seria hacer que consumidor esperase un nuevo valor de 
	//Productor y que este esperase que consumidor obtuviese el valor para 
	//generar uno nuevo
	public static void main(String[] args) {
		Contenedor c = new Contenedor();
		Productor produce = new Productor(c);
		Consumidor consume = new Consumidor(c);
		produce.start();
		consume.start();
//		System.out.println("contenedor antes de pausa"+c.valor);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("contenedor despues de pausa"+c.valor);
		
		System.out.println("fin del main");
	}
}

package synchro06v7BlockinQueue;

public class Escritor {
	public static boolean  on=false;
	public static void escribe(String mensaje) {
		if(on)System.out.println(mensaje);
	}
	public static boolean isOn() {
		return on;
	}
	public static void setOn(boolean on) {
		Escritor.on = on;
	}
	
}

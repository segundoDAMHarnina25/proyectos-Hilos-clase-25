package prioridades07;
//algo confuso 

//Demostraci�n de prioridades en hilos
class PrioridadHilos implements Runnable {
  int contar;
  Thread hilo;

  static boolean stop=false;
  static String actualNombre;

  //Construye un nuevo hilo.
  PrioridadHilos(String nombre){
      hilo= new Thread(this,nombre);
      contar=0;
      actualNombre=nombre;
  }

  //Punto de entrada de hilo.
  public void run(){
      System.out.println(hilo.getName()+" iniciando.");
      do {
          contar++;
          if (actualNombre.compareTo(hilo.getName())!=0){
              actualNombre=hilo.getName();
              if(contar%100000==0)System.out.println("En "+actualNombre);
          }
      }while (stop==false&&contar<10000000);
      stop=true;
     System.out.println("\n"+ hilo.getName()+" terminado.");
  }
}
public class DemoPrioridad{
    public static void main(String[] args) {
    PrioridadHilos ph1= new PrioridadHilos("Prioridad Alta");
    PrioridadHilos ph2= new PrioridadHilos("Prioridad Baja");
    PrioridadHilos ph3= new PrioridadHilos("Prioridad Normal #1");
    PrioridadHilos ph4= new PrioridadHilos("Prioridad Normal #2");
    PrioridadHilos ph5= new PrioridadHilos("Prioridad Normal #3");

    //Establecer prioridades
        ph1.hilo.setPriority(Thread.NORM_PRIORITY+2);
        ph2.hilo.setPriority(Thread.NORM_PRIORITY-2);
     //Deje ph3, ph4 y ph5 en el nivel de prioridad normal predeterminado

     //Comenzar los hilos
        ph1.hilo.start();
        ph2.hilo.start();
        ph3.hilo.start();
        ph4.hilo.start();
        ph5.hilo.start();
//    try {
//    	//esto estropea todo el ejemplo
//        ph1.hilo.join();
//        ph2.hilo.join();
//        ph3.hilo.join();
//        ph4.hilo.join();
//        ph5.hilo.join();
//    }catch (InterruptedException exc){
//        System.out.println("Hilo principal interrumpido.");
//    }
        System.out.println("\nHilo de alta prioridad cont� hasta "+ph1.contar);
        System.out.println("Hilo de baja prioridad cont� hasta "+ph2.contar);
        System.out.println("Hilo de normal prioridad #1 cont� hasta "+ph3.contar);
        System.out.println("Hilo de normal prioridad #2 cont� hasta "+ph4.contar);
        System.out.println("Hilo de normal prioridad #3 cont� hasta "+ph5.contar);
    }
}
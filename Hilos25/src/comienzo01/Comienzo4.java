package comienzo01;

class Meollo3 implements Runnable{
     @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("buena la hermos hecho");
     }
}
public class Comienzo4 {
    public static void main(String[] args) {
        
    Meollo3 cosa=new Meollo3();
    //cosa.run();
    cosa.run();
    //Como veis lo anterior no tiene sentido pero hemos de
    //imaginar que no podemos usar extends pero necesitamos
    //que sea un hilo
    //Ahora es cuando construimos el hilo usando el objeto
    //runnable que ya tiene el metodo run definido
    Thread otro=new Thread (new Meollo3(),"noseyo");
    otro.start();
    System.out.println(otro.getName());
    
 }
    
}

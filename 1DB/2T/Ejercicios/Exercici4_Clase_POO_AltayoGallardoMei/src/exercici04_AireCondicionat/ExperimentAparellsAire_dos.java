package exercici04_AireCondicionat;

public class ExperimentAparellsAire_dos {

    public static void main(String[] args) throws ExcepcioUtilitzacio{

        AparellAire ap1 = null;

        System.out.println("\nSEGON EXPERIMENT\n");


        // creaci� d'un aparell viable
        try {
	        ap1 = new AparellAire("Fragor", 18, 30);
	        System.out.println("OK. Aparell amb l�mits " + 18 + ", " +
	                30 + " fabricat");
	        System.out.println("L'aparell es: " + ap1);

        }
        catch (ExcepcioFabricacio a){
	        System.out.println("Excepció llançada " + a);

        }
        System.out.println();


        // recuperem el valor memoritzat de f�brica
        try {
        	ap1.recuperarMemoria();
        	System.out.println("ERROR.Mem�ria d'aparell apagat recuperada!");

        } catch (ExcepcioEstatIncorrecte a) {
	        System.out.println("Excepció llançada " + a);
        }
        
        System.out.println();

        // primer hem de posar l'aparell en marxa...
        ap1.pulsarIterruptor();
        System.out.print("Interruptor polsat.");
        
        if (ap1.isOn()) System.out.println(" Aparell en marxa...");
        else System.out.println(" Aparell apagat (????? ERROR ???)");

        // Ara s� que hauriem de poder recuperar el valor memoritzat
        
        try {
        ap1.recuperarMemoria();
        System.out.println("memoria recuperada!");
        }
        catch (ExcepcioEstatIncorrecte a) {
	        System.out.println("Excepció llançada " + a);
        }

        System.out.println();

        //pujem la temperatura, a veure fins on podem arribar...
        for (int i = 1; i < 100; i++) {
        	try {
        		ap1.pujar();
        		System.out.println("  --> temperatura pujada fins a " 
	                               + ap1.getTempActual() + "�" +
	                               " sense problemes");
        	} catch (ExcepcioOperacioNoPermesa a) {
    	        System.out.println("Latemperatura actual és: " + ap1.getTempActual());
    	        System.out.println("El limit superior és: " + ap1.getTempMax());
    	        System.out.println("El problema detectat és: " + a);
    	        
    	        System.exit(0);

        	}
        }

        System.out.println("\nFINAL DEL SEGON EXPERIMENT\n");
    }
}

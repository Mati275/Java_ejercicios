package exercici04_AireCondicionat;

public class ExperimentAparellsAire_tres {

    public static void main(String[] args) throws ExcepcioUtilitzacio {

        AparellAire ap1 = null, ap2 = null;

        System.out.println("\nTERCER EXPERIMENT\n");

        // creaci� d'un aparell no viable
        try {
	        ap2 = new AparellAire("FugiuTots", 0, 32);
	        System.out.println("OK. Aparell amb l�mits " + 0 + ", " +
	                32 + " fabricat");
	        System.out.println("L'aparell es: " + ap1);
        }
        catch (ExcepcioFabricacio a) {
	        System.out.println("Excepció llançada " + a);
        }

        System.out.println();

        // creaci� d'un aparell viable
        try {
	        ap1 = new AparellAire("FugiuTots", 20, 32);
	        System.out.println("OK. Aparell amb l�mits " + 20 + ", " +
	                32 + " fabricat");
	        System.out.println("L'aparell es: " + ap1);
        }	
        catch (ExcepcioFabricacio a) {
	        System.out.println("Excepció llançada " + a);

        }
        System.out.println();

        // El posem en marxa
        ap1.pulsarIterruptor();
        System.out.print("Interruptor polsat.");
        
        if (ap1.isOn()) System.out.println(" Aparell en marxa...");
        else System.out.println(" Aparell apagat (????? ERROR ???)");
        

        // Recurperem el valor memoritzat
        try {
	        ap1.recuperarMemoria();
	        System.out.println("mem�ria recuperada!");
        }
        catch (ExcepcioEstatIncorrecte a) {
	        System.out.println("Excepció llançada " + a);
        }
        System.out.println();

        // fem un nou experiment de pujar la temperatura...
        for (int i = 1; i <= 10; i++) {
        	try {
	            ap1.pujar();
	            System.out.println("  --> (" + i + ") temperatura pujada fins a " 
	                                + ap1.getTempActual() + "�" +
	                                " sense problemes");
        	}
        	catch (ExcepcioOperacioNoPermesa a) {
    	        System.out.println("Excepció llançada " + a);
        	}
        }
        
        System.out.println("\nFINAL DEL TERCER EXPERIMENT\n");
    }
}

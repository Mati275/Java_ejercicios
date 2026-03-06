package exercici04_AireCondicionat;

public class ExperimentAparellsAire_un {

    public static void main (String args []) throws ExcepcioUtilitzacio{
        
        AparellAire ap = null;
        
        System.out.println("\nINICI DE L'EXPERIMENT\n");
        
        // Provem de fabricar un aparell amb l�mit superior incorrecte. 
        // Esperem que l'error es detecti i que es llenci una excepci�.
        try {
            ap = new AparellAire("ElGiCul", 20, AparellAire.LIMIT_SUPERIOR+1);
            System.out.println("ERROR. Aparell amb límits "+20+", "+
                               (AparellAire.LIMIT_SUPERIOR+1)+" fabricat!");
            System.exit(0);
        }
        catch(ExcepcioFabricacio a) {
        	System.out.print ("Excepció Llançada" + a);
        	
        }
        System.out.println();
        
        // fabriquem un aparell amb l�mits correctes.
        // Esperem que no passi res i que no es llanci cap excepci�
        try {
            ap = new AparellAire("ElGiCul", 20, 26);
            System.out.println("OK. Aparell amb límits "+20+", "+
                               26+" fabricat");
        }
        catch(ExcepcioFabricacio a) {
        	System.out.print ("Excepció Llançada" + a);        
        }
        System.out.println();
        
        // Provem de recuperar la mem�ria d'un aparell que est� OFF 
        // perqu� s'acaba de crear. Esperem que l'error es detecti i que
        // es llanci una excepci�
        try {
            ap.recuperarMemoria();
            System.out.println("ERROR. Memória recuperada d'un aparell apagat!");
        }
        catch(ExcepcioEstatIncorrecte a) {
        	System.out.print ("Excepció Llançada" + a);
        }
        System.out.println();
        
        // ara ho farem b�. Primer posarem l'aparell en marxa i
        // despr�s recuperem la mem�ria. No s'hauria de produir cap error
        ap.pulsarIterruptor();
        System.out.println("Aparell funcionant...");
        try {
            ap.recuperarMemoria();
            System.out.println("OK. Memoria recuperada!");
        }
        catch(ExcepcioEstatIncorrecte a) {
        	System.out.print ("Excepció Llançada" + a);
        }
        
        System.out.println();
        
        // Portar l'aparell al seu l�mit inferior. At�s que l'aparell est� ON
        // i que no baixarem per sota del l�mit inferior, no esperem
        // que es llenci cap mena d'excepci�
        try {
            while (ap.getTempActual()>ap.getTempMin()){
                ap.baixar();
            }
            System.out.println("OK. Assolida la temperatura mínima");
        }
        catch(ExcepcioEstatIncorrecte a) {
        	System.out.print ("Excepció Llançada" + a);
        }
        System.out.println();
        
        // provar de portar l'aparell encara per sota de la temperatura m�nima
        // en aquest cas si que esperem que es llanci una excepci�
        try {
            ap.baixar();
            System.out.println("ERROR la temperatura ha baixat per sota del mínim");
        }
        catch(ExcepcioUtilitzacio a) {
        	System.out.print ("Excepció Llançada" + a);
        }
        
        System.out.println("\nFINAL DE L'EXPERIMENT\n");
        
    }
           
}

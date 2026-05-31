package strings;

public class Ex02_CountCodeDefuse {

    /**
     *
     * @param text text cannot be null. It may be an empty ("") String.
     * @return Number of code and defuse ocurrences in text
     */
    public static int countCodeDefuse(String text){

        // Cas base
        if(text.isEmpty()){
            return 0;
        }

        if( text.startsWith("code") || text.startsWith("defuse") ){

            return 1 + countCodeDefuse(text.substring(1));

        }

        if( text.startsWith("codefuse") ){
            return 2 + countCodeDefuse(text.substring(1));
        }

        // No se ha encontrado en esta iteración la palabra al principio
        return countCodeDefuse( text.substring(1) );

    }

}

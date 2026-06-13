package numbers;

public class Ex03_RemoveDuplicateDigits {

    /**
     * DO NOT CONVERT THE NUMBER TO STRING
     * @param number number>=1
     * @return Returns the original number where all equal consecutive digits are substituted for one
     */
    public static int removeDuplicateDigits(int number){



        if( number < 10 ){
            return number;
        }

        // Ej: 11111
        // 1ra iteración

        int resta = removeDuplicateDigits( number / 10 ); // 1111 sense duplicats
        int num1 = number % 10;
        int num2 = resta % 10;

        if( num1 == num2 ){
            return resta;
        }

        return resta * 10 + num1;










//        // Cas base: es desmonta la pila stack (cuan es queda amb 1 digit el numero)
//        if( number < 10 ){
//            return number;
//        }
//
//        int resta =  removeDuplicateDigits( number/10 );
//        int ultimDigit = number % 10;
//        int digitActual = resta % 10;
//
//        // Començant desd de l'esquerra, si el numero que té a la dreta és el mateix --> 11233: num1 = 11, resta = 1 ; num1 = 112 , resta = 1 ; num1 = 1123 , resta = 12 ; num1 = 11233 , resta = 123
//        if( ultimDigit == digitActual ){
//            //
//            return resta;
//        }
//
//        // No és duplicat (s'ha d'enmagatzemar)
//        return resta * 10 + ultimDigit; // Hacerle un hueco al numero

    }

}

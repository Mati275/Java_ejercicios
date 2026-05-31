package numbers;

public class Ex01_TripleOccurrences {

    /**
     * DO NOT CONVERT NUMBER TO STRING
     * @param n Must be n>=1
     * @param d Must be 0<=d<=9
     * @return Number of coocurrences of d in n. If d has two more d to its left, then it counts as 3 and not 1.
     */
    public static int countDigit(int n, int d) {
        int number;

        if( n == 0 ){
            return 0;
        }

        number = n % 10;

        // Si el digit és el mateix que el nombre i els dos de la seva esquerra tenen el mateix digit, suma 3
        if( d == number && number == (n / 10) % 10 && number == (n / 100) % 10 ){
            return 3 + countDigit( n / 10, d );
        } // Comprovant que no és un triple ocurrència

        // Si el digit és el mateix que el nombre, suma 1
        if( d == number ){
            return 1 + countDigit( n / 10, d );
        }

        // El digit no encaixa amb el numero
        return countDigit( n / 10, d );

    }

}

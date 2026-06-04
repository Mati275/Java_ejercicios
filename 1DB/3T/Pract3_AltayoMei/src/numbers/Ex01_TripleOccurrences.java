package numbers;

public class Ex01_TripleOccurrences {

    /**
     * DO NOT CONVERT NUMBER TO STRING
     * @param n Must be n>=1
     * @param d Must be 0<=d<=9
     * @return Number of coocurrences of d in n. If d has two more d to its left, then it counts as 3 and not 1.
     */
    public static int countDigit(int n, int d) {
        //TODO: Write code

        int num1 = (n%10);
        int num2 = (n/10)%10;
        int num3 = (n/100)%10;

        if (n == 0) {
            return 0;
        }

        // Mismo numero que los dos de su izquierda, suma 3
        if ( (num2 == d && num2 == num1 )&& num3 == d ) {
            return 3+ countDigit(n/10, d);
        }


        if ( d == num1 ) {
            return 1+countDigit(n/10, d);
        }

        return countDigit(n/10, d);

    }

}

package arrays;

import java.util.Arrays;

public class Ex05_CountDecreasingPairs {

    /**
     *
     * @param num Array of int. It may have size 0.
     * @return
     */
    public static int countDecreasingPairs(int[] num){

        return countDecreasingPairsIdx(num, 0);

    }

    public static int countDecreasingPairsIdx(int[] numbers, int idx){

        // Cas base --> Es procesa fins al penúltim element
        if( numbers.length - 1 == idx ){
            return 0;
        }

        // it's a decreasing pair
        if( numbers[idx] > numbers[idx + 1] ){
            return 1 + countDecreasingPairsIdx(numbers, idx + 1);
        }

        return countDecreasingPairsIdx(numbers, idx + 1);

    }


}

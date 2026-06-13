package arrays;

import java.util.Arrays;

public class Ex04_AlternateArraySum {

    /**
     *
     * @param numbers Array of int whose numbers should be summed alternatively. Array may have 0 elements.
     * @return Result of the alternate sum
     */
    public static int alternateArraySum(int[] numbers){
        return alternateArraySumIdx( numbers, 0 );
    }

    public static int alternateArraySumIdx(int[] numbers, int idx){

        // Cas base
        if( idx == numbers.length ){
            return 0;
        }

        return numbers[idx] - alternateArraySumIdx( numbers, idx + 1 );

    }


}

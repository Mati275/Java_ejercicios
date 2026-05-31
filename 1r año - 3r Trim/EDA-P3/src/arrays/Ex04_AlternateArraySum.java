package arrays;

import java.util.Arrays;

public class Ex04_AlternateArraySum {

    /**
     *
     * @param numbers Array of int whose numbers should be summed alternatively. Array may have 0 elements.
     * @return Result of the alternate sum
     */
    public static int alternateArraySum(int[] numbers){
        if( numbers == null || numbers.length == 0 ){
            return 0;
        }

        return numbers[0] - alternateArraySum(Arrays.stream(numbers).skip(1).toArray());
        // 1-( 2 -( 3 - (4) ) ) --> 1 - 2 +3 -4

    }


}

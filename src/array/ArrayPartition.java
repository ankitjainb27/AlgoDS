package array;

import java.util.Arrays;

/**
 * Created by ankit.ppe on 23/04/17.
 */
public class ArrayPartition {
    public static void main(String[] args) {
        ArrayPartition arrayPartition = new ArrayPartition();
        int[] nums = {1,3,2,4,5};
        System.out.println(arrayPartition.arrayPairSum(nums));

    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int total = 0;
        for (int i = 0; i < nums.length; i = i+2) {
            total = nums[i];
        }
        return total;
    }
}

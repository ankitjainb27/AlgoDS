package array;

import java.util.Arrays;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 */
public class WiggleSort {
    public static void main(String[] args) {
        WiggleSort wiggleSort = new WiggleSort();
//        int[] nums = {3, 5, 2, 1, 6, 4};
        int[] nums = {3, 5, 5, 5, 6, 1};
        wiggleSort.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i&1) == 0 && nums[i] > nums[i + 1]) || ((i&1) != 0 && nums[i] < nums[i + 1])) {
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }
        }
    }
}

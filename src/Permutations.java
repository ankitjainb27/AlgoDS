import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date 04/06/17
 *
 * @author Ankit Jain
 *
 * https://leetcode.com/problems/permutations-ii/
 */
public class Permutations {
    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = {2, 2,3,3,3};
        System.out.println(permutations.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List list = new ArrayList();
        while (true) {
            int i;
            ArrayList<Integer> integers = new ArrayList<Integer>();
            for (int k = 0; k < nums.length; k++) {
                integers.add(nums[k]);
            }
            list.add(integers);
            for (i = nums.length - 2; i >= 0; --i) {
                if (nums[i + 1] > nums[i])
                    break;
            }
            if (i == -1) {
                break;
            }
            int ceilIndex = findCeil(nums, i + 1);
            swap(nums, i, ceilIndex);
            reverse(nums, i + 1);
        }
        return list;
    }

    private void reverse(int[] nums, int i) {
        for (int j = i; j < i+(nums.length-i) / 2; j++) {
            swap(nums, j, nums.length - (j - i) - 1);
        }
    }

    private int findCeil(int[] nums, int i) {
        int ceil = i;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i - 1] && nums[j] <= nums[ceil])
                ceil = j;
        }
        return ceil;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

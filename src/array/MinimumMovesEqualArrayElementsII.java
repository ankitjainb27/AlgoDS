package array;

import java.util.Arrays;

/**
 * Created on 17/08/17 at 9:47 AM
 *
 * @author Ankit Jain
 */
public class MinimumMovesEqualArrayElementsII {
    public static void main(String[] args) {
        System.out.println(new MinimumMovesEqualArrayElementsII().minMoves21(new int[]{1, 2, 5, 8, 0}));
    }

    public int minMoves21(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Arrays.sort(nums);
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int diff = 0;
        int diff1 = 0;
        for (int i : nums) {
            diff += Math.abs(i - min);
            diff1 += Math.abs(i - max);

        }
        int minDiff = Math.min(diff, diff1);
        System.out.println(diff);
        for (int j = min + 1; j < max; j++) {
            int v1 = leftBinarySearch(0, nums.length - 1, j, nums);
            int v2 = rightBinarySearch(0, nums.length - 1, j, nums);
            System.out.println("j:  " + j + " -- " + v1 + " -- " + v2);
//            System.out.println(j + " -- " + (Arrays.binarySearch(nums, j) >= 0 ? -1 : 0));
            diff += (Arrays.binarySearch(nums, j) >= 0 ? -1 : 0) + leftBinarySearch(0, nums.length - 1, j, nums) - rightBinarySearch(0, nums.length - 1, j, nums);
            System.out.println(diff);
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

    private int leftBinarySearch(int l, int r, int x, int[] nums) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= x && nums[mid - 1] < x) return mid;
            else if (nums[mid] >= x)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return -1;
    }

    private int rightBinarySearch(int l, int r, int x, int[] nums) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= x && nums[mid + 1] > x) return nums.length - mid;
            else if (nums[mid] <= x)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }

    public int minMoves22(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int leftDiff = 0;
            for (int j : nums) {
                leftDiff += Math.abs(j - i);
            }
            ans = Math.min(ans, leftDiff);
//            System.out.println(leftDiff);
        }
        return ans;
    }

}

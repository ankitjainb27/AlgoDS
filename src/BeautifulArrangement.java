import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Date 05/06/17
 *
 * @author Ankit Jain
 */
public class BeautifulArrangement {
    public static void main(String[] args) {
        BeautifulArrangement beautifulArrangement = new BeautifulArrangement();
        System.out.println(beautifulArrangement.countArrangement(4));
    }


    public int countArrangement(int N) {
        int[] nums = new int[N];
        boolean[] included = new boolean[N];
        int count = 0;
        count = countArrangementUtil(nums, 0, included, N, count);
        return count;
    }

    private int countArrangementUtil(int[] nums, int i, boolean[] included, int n, int count) {
        if (i == n) {
            return count+1;
        }
        for (int j = 1; j <= n; j++) {
            if (isSafe(nums, included, j, i, n)) {
                included[j - 1] = true;
                nums[i] = j;
                count = countArrangementUtil(nums, i + 1, included, n, count);
                nums[i] = 0;
                included[j - 1] = false;
            }
        }
        return count;
    }

    private boolean isSafe(int[] nums, boolean[] included, int j, int i, int n) {
        if (!included[j - 1] && (j % (i + 1) == 0 || (i + 1) % j == 0))
            return true;
        else
            return false;
    }


    private void permute(int[] nums) {
        permuteUtil(nums, nums.length, 0);
    }

    /**
     * The number at the ith position is divisible by i.
     * i is divisible by the number at the ith position.
     *
     * @param nums
     * @param length
     * @param i
     */

    int count = 0;
    private void permuteUtil(int[] nums, int length, int i) {
        if (i == length) {
            System.out.println(Arrays.toString(nums));
            int c = 0;
            for (int j = 1; j <= length; j++) {
                if (nums[j - 1] % (j) == 0 || j % nums[j - 1] == 0)
                    c++;
            }
            if (c == length) {
                //System.out.println("==>" + Arrays.toString(nums));
                count++;
            }
        }
        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            permuteUtil(nums, length, i + 1);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

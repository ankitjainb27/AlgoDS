package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 16/08/17 at 1:13 PM
 *
 * @author Ankit Jain
 */
public class LargestDivisibleSubset {
    public static void main(String[] args) {

    }

    public List<Integer> largestDivisibleSubset1(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] pre = new int[n];
        Arrays.sort(nums);
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] pre = new int[n];
        List<Integer> list = new ArrayList<>();
        if (nums.length == 0) return list;
        dp[0] = 1;
        pre[0] = -1;
        int max = 1;
        int index = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            pre[i] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + dp[j] > dp[i]) {
                        dp[i] = 1 + dp[j];
                        pre[i] = j;
                    }

                }
            }
            if (max <= dp[i]) {
                max = dp[i];
                index = i;
            }
        }
        while (index != -1) {
            list.add(nums[index]);
            index = pre[index];
        }
        return list;
    }
}

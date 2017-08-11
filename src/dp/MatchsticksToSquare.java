package dp;

import java.util.Arrays;

/**
 * Date 08/08/17
 *
 * @author Ankit Jain
 */
public class MatchsticksToSquare {
    public static void main(String[] args) {
//        System.out.println(new MatchsticksToSquare().makesquare(new int[]{3,3,3,3,4}));
//        System.out.println(new MatchsticksToSquare().makesquare(new int[]{3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5}));
        System.out.println(new MatchsticksToSquare().makesquare1(new int[]{2, 2, 2, 3, 4, 4, 4, 5, 6}));
//        System.out.println(new MatchsticksToSquare().makesquare(new int[]{1,1,2,2,2}));
    }

    public boolean makesquare1(int[] nums) {
        int sum = 0;
        for (int i : nums)
            sum += i;
        if (sum == 0 || sum % 4 != 0) return false;
        int side = sum / 4;
        System.out.println(side);
        int n = nums.length;
        Arrays.sort(nums);
        int[][] dp = new int[n + 1][side + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= side; j++) {
                int val = nums[i - 1];
                if (j - val >= 0) {
                    System.out.println("i: " + i + "  j: " + j);
                    System.out.println(j - val);
                    System.out.println(dp[i - 1][j]);
                    System.out.println(dp[i - 1][j - val]);
                    System.out.println(dp[i - 1][j - val] > dp[i - 1][j]);

                    int max = Integer.MIN_VALUE;
                    for (int k = 0; k <= dp[i - 1][j]; k++) {
                        max = Math.max(max, (((j - val) * dp[i - 1][j - val]) - (k * j)));
                        if (i == 6 && j == 6) System.out.println("---------" + max);
                    }

                    dp[i][j] = dp[i - 1][j] + (((j - val) != 0 && (((j - val) * dp[i - 1][j - val]) - (dp[i - 1][j] * j)) > dp[i - 1][j]) ? 1 : 0) + ((j - val) == 0 ? 1 : 0);
                    System.out.println(dp[i][j]);
                    for (int l = 0; l <= nums.length; l++) {
                        System.out.println(Arrays.toString(dp[l]));
                    }
                } else
                    dp[i][j] = dp[i - 1][j];

            }
        }
        for (int i = 0; i <= nums.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[n][side] == 4;
    }

    public boolean makesquare(int[] nums) {
        int sum = 0;
        for (int i : nums)
            sum += i;
        if (sum == 0 || sum % 4 != 0) return false;
        int side = sum / 4;
        int n = nums.length;
        Arrays.sort(nums);
        int[][] dp = new int[n + 1][side + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= side; j++) {
                int val = nums[i - 1];
                if (j - val >= 0) {
                    dp[i][j] = dp[i - 1][j] + (((j - val) != 0 && dp[i - 1][j - val] > dp[i - 1][j]) ? 1 : 0) + ((j - val) == 0 ? 1 : 0);
                } else
                    dp[i][j] = dp[i - 1][j];

            }
        }
        return dp[n][side] == 4;
    }
}

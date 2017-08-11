package dp;

/**
 * Date 03/08/17
 *
 * @author Ankit Jain
 */
public class PredictWinner {

    public static void main(String[] args) {
        System.out.println(new PredictWinner().PredictTheWinner4(new int[]{1, 5, 233, 7}));
    }

    /**
     * Note - When length of array is even, the first player will always win
     * https://www.youtube.com/watch?v=Tw1k46ywN6E&feature=youtu.be&list=PLUl4u3cNGP6317WaSNfmCvGym2ucw3oGp&t=3622
     * @param nums
     * @return
     */
    //My Code
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        dp[0][0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
            dp[i][i] = nums[i];
        }
        for (int i = 2; i <= nums.length; i++) {
            for (int l = 0; l < nums.length - i + 1; l++) {
                int r = i + l - 1;
                dp[l][r] = (sum[r] - (l == 0 ? 0 : sum[l - 1])) - Math.min(dp[l][r - 1], dp[l + 1][r]);
            }
        }
        return 2 * dp[0][nums.length - 1] >= sum[nums.length - 1];
    }

    //My Code
    public boolean PredictTheWinner1(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int val = ptwUtil(nums, 0, nums.length - 1, sum);
        return 2 * val >= sum;
    }

    private int ptwUtil(int[] nums, int l, int r, int sum) {
        if (r - l == 0) return nums[l];
        if (r - l == 1) return Math.max(nums[l], nums[r]);
        int val1 = sum - ptwUtil(nums, l + 1, r, sum - nums[l]);
        int val2 = sum - ptwUtil(nums, l, r - 1, sum - nums[r]);
        return Math.max(val1, val2);
    }

    public boolean PredictTheWinner2(int[] nums) {
        return winner(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int winner(int[] nums, int s, int e, int turn) {
        if (s == e)
            return turn * nums[s];
        int a = turn * nums[s] + winner(nums, s + 1, e, -turn);
        int b = turn * nums[e] + winner(nums, s, e - 1, -turn);
        return turn * Math.max(turn * a, turn * b);
    }

    public boolean PredictTheWinner3(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length];
        return winner(nums, 0, nums.length - 1, memo) >= 0;
    }

    public int winner(int[] nums, int s, int e, Integer[][] memo) {
        if (s == e)
            return nums[s];
        if (memo[s][e] != null)
            return memo[s][e];
        int a = nums[s] - winner(nums, s + 1, e, memo);
        int b = nums[e] - winner(nums, s, e - 1, memo);
        memo[s][e] = Math.max(a, b);
        return memo[s][e];
    }

    public boolean PredictTheWinner4(int[] nums) {
        int[][] dp = new int[nums.length + 1][nums.length];
        for (int s = nums.length; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[s + 1][e];
                int b = nums[e] - dp[s][e - 1];
                dp[s][e] = Math.max(a, b);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }

    public boolean PredictTheWinner5(int[] nums) {
        int[] dp = new int[nums.length];
        for (int s = nums.length; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[e];
                int b = nums[e] - dp[e - 1];
                dp[e] = Math.max(a, b);
            }
        }
        return dp[nums.length - 1] >= 0;
    }

    public boolean PredictTheWinner6(int[] nums) {
        return helper(nums, 0, nums.length - 1) >= 0;
    }

    private int helper(int[] nums, int s, int e) {
        return s == e ? nums[e] : Math.max(nums[e] - helper(nums, s, e - 1), nums[s] - helper(nums, s + 1, e));
    }

    public boolean PredictTheWinner7(int[] nums) {
        return helper1(nums, 0, nums.length - 1, new Integer[nums.length][nums.length]) >= 0;
    }

    private int helper1(int[] nums, int s, int e, Integer[][] mem) {
        if (mem[s][e] == null)
            mem[s][e] = s == e ? nums[e] : Math.max(nums[e] - helper1(nums, s, e - 1, mem), nums[s] - helper1(nums, s + 1, e, mem));
        return mem[s][e];
    }
}

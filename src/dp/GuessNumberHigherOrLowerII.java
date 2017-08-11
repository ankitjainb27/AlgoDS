package dp;

/**
 * Date 07/08/17
 *
 * @author Ankit Jain
 */
public class GuessNumberHigherOrLowerII {
    public static void main(String[] args) {
        System.out.println(new GuessNumberHigherOrLowerII().getMoneyAmount(6));
    }

    public int getMoneyAmount(int n) {
        if (n <= 3) return n - 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                int r = j + i - 1;
                int min = Integer.MAX_VALUE;
                for (int k = j + 1; k < r; k++) {
                    min = Math.min(min, k + Math.max(dp[j][k - 1], dp[k + 1][r]));
                }
                dp[j][r] = j + 1 == r ? j : min;
            }
        }
        return dp[1][n];
    }

    public int getMoneyAmount1(int n) {
        int[][] table = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i > 0; i--) {
                int globalMin = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int localMax = k + Math.max(table[i][k - 1], table[k + 1][j]);
                    globalMin = Math.min(globalMin, localMax);
                }
                table[i][j] = i + 1 == j ? i : globalMin;
            }
        }
        return table[1][n];
    }
}

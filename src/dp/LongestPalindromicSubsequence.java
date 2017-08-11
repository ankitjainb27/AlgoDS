package dp;

import java.util.Arrays;

/**
 * Date 04/08/17
 *
 * @author Ankit Jain
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubsequence().longestPalindromeSubseq("bbbab"));
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int prev = dp[i];
            dp[i] = 1;
            for (int l = i + 1; l < n; l++) {
                int current = dp[l];
                if (s.charAt(i) == s.charAt(l)) dp[l] = prev + 2;
                else dp[l] = Math.max(dp[l - 1], dp[l]);
                prev = current;
            }
        }
        return dp[n - 1];
    }

    public int longestPalindromeSubseq2(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= s.length(); i++) {
            for (int l = 0; l < s.length() - i + 1; l++) {
                int r = i + l - 1;
                if (s.charAt(l) == s.charAt(r)) dp[r] = (r - l == 1) ? 2 : dp[r - 1] + 2;
                else dp[r] = Math.max(dp[r - 1], dp[r]);
            }
        }
        return dp[s.length() - 1];
    }

    public int longestPalindromeSubseq3(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++)
            dp[i][i] = 1;
        for (int i = 2; i <= s.length(); i++) {
            for (int l = 0; l < s.length() - i + 1; l++) {
                int r = i + l - 1;
                if (s.charAt(l) == s.charAt(r)) dp[l][r] = dp[l + 1][r - 1] + 2;
                else dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
            }
        }
        return dp[0][s.length() - 1];
    }

    public int longestPalindromeSubseq4(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int l = i + 1; l < s.length(); l++) {
                if (s.charAt(i) == s.charAt(l)) dp[i][l] = dp[i + 1][l - 1] + 2;
                else dp[i][l] = Math.max(dp[i][l - 1], dp[i + 1][l]);
            }
        }
        return dp[0][s.length() - 1];
    }

}

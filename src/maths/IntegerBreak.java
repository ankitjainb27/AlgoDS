package maths;

import java.util.HashMap;

/**
 * Date 02/08/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/integer-break/description/
 */
public class IntegerBreak {

    //Mathematical solution - O(n)
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        product *= n;

        return product;
    }

    //Bottom-up DP Solution - O(n^2)
    public int integerBreak1(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])));
            }
        }
        return dp[n];
    }

    //Top-down/Memoization DP Solution, without using HashMap - O(n^2)
    public int integerBreak2(int n) {
        int[] map = new int[n + 1];
        map[1] = 1;
        return ibUtil(n, map);
    }

    public int ibUtil(int n, int[] map) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n / 2; i++) {
            int val1 = Math.max(i, map[i] != 0 ? map[i] : ibUtil(i, map));
            int val2 = Math.max(n - i, map[n - i] != 0 ? map[n - i] : ibUtil(n - i, map));
            max = Math.max(max, val1 * val2);
        }
        map[n] = max;
        return max;
    }

    //Top-down/Memoization DP Solution, using HashMap - O(n^2)
    public int integerBreak3(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        return ibUtil1(n, map);
    }

    public int ibUtil1(int n, HashMap<Integer, Integer> map) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n / 2; i++) {
            int ib1 = map.containsKey(i) ? map.get(i) : ibUtil1(i, map);
            int ib2 = map.containsKey(n - i) ? map.get(n - i) : ibUtil1(n - i, map);

            int val1 = Math.max(i, ib1);
            int val2 = Math.max(n - i, ib2);
            max = Math.max(max, val1 * val2);
        }
        map.put(n, max);
        return max;
    }
}

package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Date 01/08/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/maximum-length-of-pair-chain/description/
 */
public class MaxLengthPairChain {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 1;
        int[] prev = pairs[0];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > prev[1]) {
                count++;
                prev = pairs[i];
            }
        }
        return count;
    }

    //DP Solution
    public int findLongestChain1(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < pairs.length; i++)
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++)
            max = Math.max(max, dp[i]);

        return max;
    }
}

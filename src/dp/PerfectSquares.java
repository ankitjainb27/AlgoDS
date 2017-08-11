package dp;

import java.util.*;

/**
 * Date 07/08/17
 *
 * @author Ankit Jain
 */
public class PerfectSquares {
    public static void main(String[] args) {
        PerfectSquares perfectSquares = new PerfectSquares();
        System.out.println(perfectSquares.numSquares3(5));
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        int limit = (int) Math.sqrt(n);
        for (int i = 1; i <= limit; i++) {
            for (int j = i * i; j < dp.length; j++) {
                dp[j] = Math.min(i == 1 ? Integer.MAX_VALUE : dp[j], 1 + dp[j - i * i]);
            }
        }
        return dp[n];
    }

    public int numSquares1(int n) {
        int[] dp = new int[n + 1];
        int limit = (int) Math.sqrt(n);
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= limit; i++) {
            for (int j = i * i; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], 1 + dp[j - i * i]);
            }
        }
        return dp[n];
    }

    public int numSquares2(int n) {
        if (n == (int) Math.sqrt(n) * (int) Math.sqrt(n)) return 1;
        List<Integer> perfectSquares = new ArrayList<>();
        for (int i = 1; i * i < n; i++) {
            perfectSquares.add(i * i);
        }
//        System.out.println(perfectSquares);
        boolean[] canPerfectSquare = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = perfectSquares.size() - 1; i >= 0; i--) {
            canPerfectSquare[perfectSquares.get(i) - 1] = true;
        }
//        System.out.println(Arrays.toString(canPerfectSquare));
        int count = 1;
        queue.addAll(perfectSquares);
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int val = queue.poll();
                for (int j : perfectSquares) {
                    if (val + j == n) return count;
                    else if (val + j < n && !canPerfectSquare[val + j - 1]) {
                        queue.add(val + j);
                        canPerfectSquare[val + j - 1] = true;
                    } else if (val + j > n) break;
                }
            }
        }

        return n;
    }

    public int numSquares3(int n) {
        if (n == (int) Math.sqrt(n) * (int) Math.sqrt(n)) return 1;
        List<Integer> perfectSquares = new ArrayList<>();
        for (int i = 1; i * i < n; i++) {
            perfectSquares.add(i * i);
        }
        boolean[] canPerfectSquare = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        queue.add(n);
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int val = queue.poll();
                for (int j : perfectSquares) {
                    if (val - j == 0) return count;
                    else if (val - j > 0 && !canPerfectSquare[val - j - 1]) {
                        queue.add(val - j);
                        canPerfectSquare[val - j - 1] = true;
                    }
                }
            }
        }

        return n;
    }
}

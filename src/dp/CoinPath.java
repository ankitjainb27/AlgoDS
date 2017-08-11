package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Date 06/08/17
 *
 * @author Ankit Jain
 */
public class CoinPath {
    public static void main(String[] args) {
        CoinPath coinPath = new CoinPath();
//        System.out.println(coinPath.cheapestJump(new int[]{1, 2, 4, -1, 2}, 2));
//        System.out.println(coinPath.cheapestJump(new int[]{0, 0, 0, 0, 0, 0}, 3));
        int[] val = new int[]{33, 90, 57, 39, 42, 45, 29, 90, 81, 87, 88, 37, 58, 97, 80, 2, 77, 64, 82, 41, 2, 33, 34, 95, 85, 77, 92, 3, 8, 15, 71, 84, 58, 65, 46, 48, 3, 74, 4, 83, 23, 12, 15, 77, 33, 65, 17, 86, 21, 62, 71, 55, 80, 63, 75, 55, 11, 34, -1, 64, 81, 18, 77, 82, 8, 12, 14, 6, 46, 39, 71, 14, 6, 46, 89, 37, 88, 70, 88, 33, 89, 92, 60, 0, 78, 10, 88, 99, 20};
        int[] val1 = new int[]{33, 90, 57, 39, 42, 45, 29, 90, 81, 87, 88, 37, 58, 97, 80, 2, 77, 64, 82, 41, 2, 33, 34, 95, 85, 77, 92, 3, 8, 15, 71, 84, 58, 65, 46, 48, 3, 74, 4, 83, 23, 12, 15, 77, 33, 65, 17, 86, 21, 62, 71, 55, 80, 63, 75, 55, 11, 34, -1, 64, 81, 18, 77, 82, 8, 12, 14, 6, 46, 39, 71, 14, 6, 46, 89, 37, 88, 70, 88, 33, 89, 92, 60, 0, 78, 10, 88, 99, 20};
//        System.out.println(coinPath.cheapestJump1(val, 74));
        System.out.println(coinPath.cheapestJump2(val1, 74));
    }

    private boolean[][] connect;

    public List<Integer> cheapestJump(int[] A, int B) {
        long[] cost = new long[A.length];
        long INF = Long.MAX_VALUE / 10;
        for (int i = 1; i < A.length; i++) {
            cost[i] = INF;
            if (A[i] != -1) {
                for (int j = Math.max(0, i - B); j < i; j++) {
                    cost[i] = Math.min(cost[i], cost[j] + A[i]);
                }
            }
        }
        if (cost[A.length - 1] >= INF) {
            return Collections.emptyList();
        }

        connect = new boolean[A.length][A.length];
        boolean[] done = new boolean[A.length];
        dfs(A, B, cost, A.length - 1, done);
        List<Integer> answer = new ArrayList<>();
        int cur = 0;
        answer.add(cur + 1);
        while (cur < A.length - 1) {
            for (int next = cur + 1; next <= cur + B && next < A.length; next++) {
                if (connect[cur][next]) {
                    cur = next;
                    answer.add(cur + 1);
                    break;
                }
            }
        }
        return answer;
    }

    private void dfs(int[] A, int B, long[] cost, int i, boolean[] done) {
        if (i == 0) {
            return;
        }
        if (done[i]) {
            return;
        }
        if (A[i] != -1) {
            for (int j = Math.max(0, i - B); j < i; j++) {
                if (cost[i] == cost[j] + A[i]) {
                    connect[j][i] = true;
                    dfs(A, B, cost, j, done);
                }
            }
        }
        done[i] = true;
    }


    public List<Integer> cheapestJump1(int[] A, int B) {
        int[] dp = new int[A.length];
        int[] val = new int[A.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = A[0];
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = B; j >= 1; j--) {
                int index = i + j;
                if (index < A.length && A[index] != -1 && dp[i] != Integer.MAX_VALUE && j + A[index] <= dp[index]) {
                    dp[index] =  j + A[index];
                    val[index] = i + 1;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(val));
        List<Integer> list = new ArrayList<>();
        if (dp[dp.length - 1] == Integer.MAX_VALUE) return list;
        int index = val.length - 1;
        list.add(index + 1);
        while (index != 0) {
            list.add(val[index]);
            index = val[index] - 1;
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> cheapestJump2(int[] A, int B) {
        int n = A.length;
        int[] dp = new int[A.length];
        int[] path = new int[A.length];
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            if (A[i] < 0) continue;
            for (int j = 1; j <= B && i + j < n; j++) {
                if (A[i + j] < 0) continue;
                if (dp[i + j] + A[i] < dp[i]) {
                    dp[i] = dp[i + j] + A[i];
                    path[i] = i + j;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(path));
        List<Integer> list = new ArrayList<>();
        if (dp[0] == Integer.MAX_VALUE) return list;
        list.add(1);
        int cur = 0;
        while (cur != n - 1) {
            //cout << "cur=" << cur << endl;
            list.add(path[cur] + 1);
            cur = path[cur];
        }
        return list;
    }
}

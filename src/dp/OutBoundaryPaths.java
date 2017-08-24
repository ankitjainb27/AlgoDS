package dp;

/**
 * Created on 18/08/17 at 11:23 AM
 *
 * @author Ankit Jain
 */
public class OutBoundaryPaths {
    public static void main(String[] args) {
        System.out.println(new OutBoundaryPaths().findPaths1(1, 3, 3, 0, 1));
    }

    public int findPaths1(int m, int n, int N, int i, int j) {
        int rem = (int) Math.pow(10, 9) + 7;
        int ans = 0;
        if (N == 0) return ans;
        long[][] dp1 = new long[m][n];
        long[][] dp = new long[m][n];
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int l = 0; l < m; l++) {
            for (int o = 0; o < n; o++) {
                for (int[] d : dir) {
                    int x = l + d[0];
                    int y = o + d[1];
                    if (x < 0 || x >= m || y < 0 || y >= n)
                        dp1[l][o]++;
                }
            }
        }
        for (int k = 1; k < N; k++) {
            for (int l = m - 1; l >= 0; l--) {
                for (int o = n - 1; o >= 0; o--) {
                    dp[l][o] = dp1[l][o];
                    for (int[] d : dir) {
                        int x = l + d[0];
                        int y = o + d[1];
                        if (x >= 0 && x < m && y >= 0 && y < n)
                            dp[l][o] = (dp[l][o] + dp[x][y] % rem) % rem;
                    }
                }
            }
        }
        return (int) (dp[i][j] % rem);
    }

    public int findPaths(int m, int n, int N, int i, int j) {
        int rem = (int) Math.pow(10, 9) + 7;
        int ans = 0;
        if (N == 0) return ans;
        long[][][] dp = new long[m][n][N];
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int l = 0; l < m; l++) {
            for (int o = 0; o < n; o++) {
                for (int[] d : dir) {
                    int x = l + d[0];
                    int y = o + d[1];
                    if (x < 0 || x >= m || y < 0 || y >= n)
                        dp[l][o][0]++;
                }
            }
        }
        for (int k = 1; k < N; k++) {
            for (int l = 0; l < m; l++) {
                for (int o = 0; o < n; o++) {
                    dp[l][o][k] = dp[l][o][0];
                    for (int[] d : dir) {
                        int x = l + d[0];
                        int y = o + d[1];
                        if (x >= 0 && x < m && y >= 0 && y < n)
                            dp[l][o][k] = (dp[l][o][k] + dp[x][y][k - 1] % rem) % rem;
                    }
                }
            }
        }
        return (int) (dp[i][j][N - 1] % rem);
    }
}

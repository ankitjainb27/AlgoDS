package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created on 21/08/17 at 8:59 PM
 *
 * @author Ankit Jain
 */
public class LongestIncreasingPathMatrix {
    public static void main(String[] args) {
        int[][] mat = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        int[][] mat1 = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        int[][] mat2 = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(System.currentTimeMillis());
        System.out.println(new LongestIncreasingPathMatrix().longestIncreasingPath(mat2));
        System.out.println(System.currentTimeMillis());
//        System.out.println(new LongestIncreasingPathMatrix().longestIncreasingPath1(mat1));
    }

    public int longestIncreasingPath1(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                {
                    max = Math.max(max, bfs(matrix, dp, i, j, m, n));
                    System.out.println("[" + i + "," + j + "]  " + dp[i][j]);
                }
            }
        }
        return max;
    }

    private int bfs(int[][] matrix, int[][] dp, int i, int j, int m, int n) {
        System.out.println("[" + i + "," + j + "]  ");
        if (dp[i][j] != 0)
            return dp[i][j];
        dp[i][j] = 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        int count = 0;
        int temp = 0;
        while (!queue.isEmpty()) {
            count++;
//            printQueue(queue);
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] val = queue.poll();
                for (int[] dir : dirs) {
                    int x = val[0] + dir[0];
                    int y = val[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[val[0]][val[1]] < matrix[x][y]) {
                        if (dp[x][y] == 0)
                            queue.add(new int[]{x, y});
                        else
                            temp = Math.max(temp, 1 + dp[x][y]);
                    }
                }
            }
        }
        dp[i][j] = Math.max(dp[i][j], count);
        dp[i][j] = Math.max(dp[i][j], temp);
        return dp[i][j];
    }

    private void printQueue(LinkedList<int[]> queue) {
        LinkedList<int[]> queue1 = (LinkedList<int[]>) queue.clone();
        while (!queue1.isEmpty()) {
            System.out.print(Arrays.toString(queue1.poll()) + " -- ");
        }
        System.out.println();
    }

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                {
                    max = Math.max(max, dfs(matrix, dp, i, j, m, n));
//                    System.out.println("[" + i + "," + j + "]  " + dp[i][j]);
                }
            }
        }
        return max;
    }

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    private int dfs(int[][] matrix, int[][] dp, int i, int j, int m, int n) {
        if (dp[i][j] != 0)
            return dp[i][j];
        int max = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[i][j] < matrix[x][y]) {
                max = Math.max(max, dfs(matrix, dp, x, y, m, n) + 1);
            }
        }
        dp[i][j] = max;
        return dp[i][j];
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int longestIncreasingPath3(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        int n = matrix.length, m = matrix[0].length, count = m * n, ans = 0;
        while (count > 0) {
            HashSet<Point> remove = new HashSet<Point>();
            // each round, remove the peak number.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == Integer.MIN_VALUE)
                        continue;
                    boolean up = (i == 0 || matrix[i][j] >= matrix[i - 1][j]);
                    boolean bottom = (i == n - 1 || matrix[i][j] >= matrix[i + 1][j]);
                    boolean left = (j == 0 || matrix[i][j] >= matrix[i][j - 1]);
                    boolean right = (j == m - 1 || matrix[i][j] >= matrix[i][j + 1]);
                    if (up && bottom && left && right)
                        remove.add(new Point(i, j));
                }
            }
            for (Point point : remove) {
                matrix[point.x][point.y] = Integer.MIN_VALUE;
                count--;
            }
            ans++;
        }
        return ans;
    }

    //topological sort
    private final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private boolean ispeak(int[][] matrix, boolean[][] marked, int i, int j) {
        if (i > 0 && !marked[i - 1][j] && matrix[i - 1][j] > matrix[i][j]) return false;
        if (i < matrix.length - 1 && !marked[i + 1][j] && matrix[i + 1][j] > matrix[i][j]) return false;
        if (j > 0 && !marked[i][j - 1] && matrix[i][j - 1] > matrix[i][j]) return false;
        if (j < matrix[0].length - 1 && !marked[i][j + 1] && matrix[i][j + 1] > matrix[i][j]) return false;
        return true;
    }

    public int longestIncreasingPath4(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int len = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] marked = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (ispeak(matrix, marked, i, j)) queue.add(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                marked[p[0]][p[1]] = true;
                for (int j = 0; j < 4; j++) {
                    int r = p[0] + dirs[j][0], c = p[1] + dirs[j][1];
                    if (r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length && !marked[r][c] && ispeak(matrix, marked, r, c)) {
                        if (matrix[r][c] != matrix[p[0]][p[1]]) queue.add(new int[]{r, c});
                    }
                }
            }
        }
        return len;
    }
}

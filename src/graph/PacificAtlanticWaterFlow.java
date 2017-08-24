package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created on 17/08/17 at 9:34 PM
 *
 * @author Ankit Jain
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        int[][] mat = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        System.out.println(new PacificAtlanticWaterFlow().pacificAtlantic2(mat));
    }

    public List<int[]> pacificAtlantic2(int[][] matrix) {
        int m = matrix.length;
        List<int[]> list = new ArrayList<>();
        if (m == 0) return list;

        int n = matrix[0].length;
        boolean[][] atl = new boolean[m][n];
        Queue<int[]> atlQueue = new LinkedList<>();
        boolean[][] pac = new boolean[m][n];
        Queue<int[]> pacQueue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            atl[i][n - 1] = true;
            atlQueue.add(new int[]{i, n - 1});
            pac[i][0] = true;
            pacQueue.add(new int[]{i, 0});
        }
        for (int i = 0; i < n; i++) {
            atl[m - 1][i] = true;
            atlQueue.add(new int[]{m - 1, i});
            pac[0][i] = true;
            pacQueue.add(new int[]{0, i});
        }
        bfs(matrix, atl, atlQueue);
        bfs(matrix, pac, pacQueue);
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(atl[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(pac[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();*/
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j])
                    list.add(new int[]{i, j});
            }
        }

        return list;
    }

    private void bfs(int[][] matrix, boolean[][] atl, Queue<int[]> queue) {
        int[][] val = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] num = queue.poll();
            int i = num[0];
            int j = num[1];
            atl[i][j] = true;
            for (int[] val1 : val) {
                int newI = i + val1[0];
                int newJ = j + val1[1];
                if (newI >= 0 && newI < matrix.length && newJ >= 0 && newJ < matrix[0].length && !atl[newI][newJ] && matrix[newI][newJ] >= matrix[i][j]) {
                    queue.add(new int[]{newI, newJ});
                }
            }
        }
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        int m = matrix.length;
        List<int[]> list = new ArrayList<>();
        if (m == 0) return list;

        int n = matrix[0].length;
        boolean[][] atl = new boolean[m][n];
        boolean[][] pac = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs1(matrix, atl, i, n - 1, Integer.MIN_VALUE);
            dfs1(matrix, pac, i, 0, Integer.MIN_VALUE);
        }
        for (int i = 0; i < n; i++) {
            dfs1(matrix, atl, m - 1, i, Integer.MIN_VALUE);
            dfs1(matrix, pac, 0, i, Integer.MIN_VALUE);
        }
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(atl[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(pac[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();*/
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j])
                    list.add(new int[]{i, j});
            }
        }

        return list;
    }

    private void dfs1(int[][] matrix, boolean[][] atl, int i, int j, int height) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || atl[i][j] || height > matrix[i][j])
            return;
        atl[i][j] = true;
        int[][] val = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] val1 : val) {
            dfs1(matrix, atl, i + val1[0], j + val1[1], matrix[i][j]);
        }
    }

    public List<int[]> pacificAtlantic1(int[][] matrix) {
        int m = matrix.length;
        List<int[]> list = new ArrayList<>();
        if (m == 0) return list;

        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean[][] visited1 = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        boolean[][] pac = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            atl[i][n - 1] = true;
            pac[i][0] = true;

            visited[i][n - 1] = true;
            visited1[i][0] = true;
        }
        for (int i = 0; i < n; i++) {
            atl[m - 1][i] = true;
            pac[0][i] = true;

            visited[m - 1][i] = true;
            visited1[0][i] = true;
        }
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(pac[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();
        */
        for (int i = 0; i <= m - 2; i++) {
            for (int j = 0; j <= n - 2; j++) {
                if (!visited[i][j]) {
                    atl[i][j] = dfs(matrix, atl, visited, i, j, m, n);
                }
            }
        }
        for (int i = m - 1; i >= 1; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (!visited1[i][j]) {
                    pac[i][j] = dfs(matrix, pac, visited1, i, j, m, n);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j])
                    list.add(new int[]{i, j});
            }
        }
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(atl[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(pac[i][j] + " -- ");
            }
            System.out.println();
        }*/
        return list;
    }

    private boolean dfs(int[][] matrix, boolean[][] atl, boolean[][] visited, int i, int j, int m, int n) {
        visited[i][j] = true;
        if (valid(i + 1, j, m, n) && matrix[i + 1][j] <= matrix[i][j]) {
            if (!visited[i + 1][j])
                atl[i + 1][j] = dfs(matrix, atl, visited, i + 1, j, m, n);
            if (visited[i + 1][j] && atl[i + 1][j]) return true;
        }
        if (valid(i, j + 1, m, n) && matrix[i][j + 1] <= matrix[i][j]) {
            if (!visited[i][j + 1])
                atl[i][j + 1] = dfs(matrix, atl, visited, i, j + 1, m, n);
            if (visited[i][j + 1] && atl[i][j + 1]) return true;
        }
        if (valid(i - 1, j, m, n) && matrix[i - 1][j] <= matrix[i][j]) {
            if (!visited[i - 1][j])
                atl[i - 1][j] = dfs(matrix, atl, visited, i - 1, j, m, n);
            if (visited[i - 1][j] && atl[i - 1][j]) return true;
        }
        if (valid(i, j - 1, m, n) && matrix[i][j - 1] <= matrix[i][j]) {
            if (!visited[i][j - 1])
                atl[i][j - 1] = dfs(matrix, atl, visited, i, j - 1, m, n);
            if (visited[i][j - 1] && atl[i][j - 1]) return true;
        }
        return false;
    }

    private boolean valid(int i, int j, int m, int n) {
        return (i >= 0 && i < m && j >= 0 && j < n);
    }

}

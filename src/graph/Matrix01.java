package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 17/08/17 at 11:34 PM
 *
 * @author Ankit Jain
 */
public class Matrix01 {
    public static void main(String[] args) {
        //[[0,1,0,1,1],[1,1,0,0,1],[0,0,0,1,0],[1,0,1,1,1],[1,0,0,0,1]]
        int[][] mat = {{1, 1, 0, 0, 1, 0, 0, 1, 1, 0}, {1, 0, 0, 1, 0, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 0, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 0, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 0, 0, 1, 1, 1}, {0, 1, 0, 1, 1, 0, 1, 1, 1, 1}, {1, 1, 1, 0, 1, 0, 1, 1, 1, 1}};
        new Matrix01().updateMatrix3(mat);
    }

    public int[][] updateMatrix3(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = Integer.MAX_VALUE;
                    if (i - 1 >= 0 && matrix[i - 1][j] != Integer.MAX_VALUE)
                        matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i - 1][j]);
                    if (j - 1 >= 0 && matrix[i][j - 1] != Integer.MAX_VALUE)
                        matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i][j - 1]);
                }
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i + 1 < row && matrix[i + 1][j] != Integer.MAX_VALUE)
                    matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i + 1][j]);
                if (j + 1 < col && matrix[i][j + 1] != Integer.MAX_VALUE)
                    matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i][j + 1]);
            }
        }
        /*for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();*/
        return matrix;
    }

    public int[][] updateMatrix2(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return matrix;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    if (i == 0 && j == 0)
                        matrix[i][j] = Integer.MAX_VALUE - 1;
                    if (i == 0 && j != 0)
                        matrix[i][j] = 1 + matrix[i][j - 1];
                    if (i != 0 && j == 0)
                        matrix[i][j] = 1 + matrix[i - 1][j];
                    if (i != 0 && j != 0)
                        matrix[i][j] = 1 + Math.min(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] != 0) {
                    if (i == m - 1 && j != n - 1)
                        matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i][j + 1]);
                    if (i != m - 1 && j == n - 1)
                        matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i + 1][j]);
                    if (i != m - 1 && j != n - 1)
                        matrix[i][j] = Math.min(matrix[i][j], 1 + Math.min(matrix[i][j + 1], matrix[i + 1][j]));
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " -- ");
            }
            System.out.println();
        }
        return matrix;
    }


    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return matrix;
        int n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    visited[i][j] = true;
                    queue.add(new int[]{i, j});
                }
            }
        }
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] val = queue.poll();
            int i = val[0];
            int j = val[1];
            for (int[] d : dir) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                    visited[x][y] = true;
                    matrix[x][y] = matrix[i][j] + 1;
                    queue.add(new int[]{x, y});
                }
            }
        }
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " -- ");
            }
            System.out.println();
        }*/
        return matrix;
    }

    public int[][] updateMatrix1(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return matrix;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    dfs(matrix, visited, i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " -- ");
            }
            System.out.println();
        }
        return matrix;
    }

    private int dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length)
            return 0;
        if (visited[i][j])
            return matrix[i][j] + 1;
        visited[i][j] = true;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int min = Integer.MAX_VALUE;
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            matrix[x][y] = dfs(matrix, visited, x, y);
            min = Math.min(min, matrix[x][y] + 1);
        }
        return min;
    }
}

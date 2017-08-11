package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Date 08/08/17
 *
 * @author Ankit Jain
 */
public class SurroundedRegions {
    public static void main(String[] args) {
        String[] vals = {"XOOOOOOOOOOOOOOOOOOO", "OXOOOOXOOOOOOOOOOOXX", "OOOOOOOOXOOOOOOOOOOX", "OOXOOOOOOOOOOOOOOOXO", "OOOOOXOOOOXOOOOOXOOX", "XOOOXOOOOOXOXOXOXOXO", "OOOOXOOXOOOOOXOOXOOO", "XOOOXXXOXOOOOXXOXOOO", "OOOOOXXXXOOOOXOOXOOO", "XOOOOXOOOOOOXXOOXOOX", "OOOOOOOOOOXOOXOOOXOX", "OOOOXOXOOXXOOOOOXOOO", "XXOOOOOXOOOOOOOOOOOO", "OXOXOOOXOXOOOXOXOXOO", "OOXOOOOOOOXOOOOOXOXO", "XXOOOOOOOOXOXXOOOXOO", "OOXOOOOOOOXOOXOXOXOO", "OOOXOOOOOXXXOOXOOOXO", "OOOOOOOOOOOOOOOOOOOO", "XOOOOXOOOXXOOXOXOXOO"};
//        String[] vals = {"OO", "OO"};
        char[][] mat = new char[vals.length][vals[0].length()];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = vals[i].charAt(j);
            }
        }
        new SurroundedRegions().solve(mat);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " -- ");
            }
            System.out.println();
        }
    }

    // BFS
    public void solve(char[][] board) {
        int rows = board.length;
        if (rows == 0) return;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') bfs(board, i, 0, rows, cols);
            if (board[i][cols - 1] == 'O') bfs(board, i, cols - 1, rows, cols);
        }
        for (int i = 1; i < cols - 1; i++) {
            if (board[0][i] == 'O') bfs(board, 0, i, rows, cols);
            if (board[rows - 1][i] == 'O') bfs(board, rows - 1, i, rows, cols);
        }
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '-') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
    }

    private void bfs(char[][] board, int i, int j, int rows, int cols) {
        int[] rowDiff = {-1, 0, 0, 1};
        int[] colDiff = {0, -1, 1, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] val = queue.poll();
            board[val[0]][val[1]] = '-';
            for (int k = 0; k < 4; k++) {
                int m = val[0] + rowDiff[k];
                int n = val[1] + colDiff[k];
                if (m >= 0 && m < rows && n >= 0 && n < cols && board[m][n] == 'O') {
                    board[m][n] = '-';
                    queue.add(new int[]{m, n});
                }
            }
        }
    }

    // Iterative DFS
    public void solve1(char[][] board) {
        int rows = board.length;
        if (rows == 0) return;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') dfsRecursive(board, i, 0, rows, cols);
            if (board[i][cols - 1] == 'O') dfsRecursive(board, i, cols - 1, rows, cols);
        }
        for (int i = 1; i < cols - 1; i++) {
            if (board[0][i] == 'O') dfsRecursive(board, 0, i, rows, cols);
            if (board[rows - 1][i] == 'O') dfsRecursive(board, rows - 1, i, rows, cols);
        }
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '-') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }

    }

    //Iteraitve DFS
    private void dfs(char[][] board, int i, int j, int rows, int cols) {
        int[] rowDiff = {-1, 0, 0, 1};
        int[] colDiff = {0, -1, 1, 0};
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});
        while (!stack.isEmpty()) {
            int[] val = stack.pop();
            board[val[0]][val[1]] = '-';
            for (int k = 0; k < 4; k++) {
                int m = val[0] + rowDiff[k];
                int n = val[1] + colDiff[k];
                if (m >= 0 && m < rows && n >= 0 && n < cols && board[m][n] == 'O') {
                    stack.push(new int[]{m, n});
                }
            }
        }
    }

    //Recursive DFS
    public void solve2(char[][] board) {
        int rows = board.length;
        if (rows == 0) return;
        int cols = board[0].length;
        System.out.println(rows);
        System.out.println(cols);
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0, rows, cols);
            if (board[i][cols - 1] == 'O') dfs(board, i, cols - 1, rows, cols);
        }
        for (int i = 1; i < cols - 1; i++) {
            if (board[0][i] == 'O') dfs(board, 0, i, rows, cols);
            if (board[rows - 1][i] == 'O') dfs(board, rows - 1, i, rows, cols);
        }
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '-') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
    }

    private void dfsRecursive(char[][] board, int i, int j, int rows, int cols) {
        board[i][j] = '-';
        if (isValid(board, i - 1, j, rows, cols) && i > 1) {
            dfsRecursive(board, i - 1, j, rows, cols);
        }
        if (isValid(board, i + 1, j, rows, cols) && i < rows - 2) {
            dfsRecursive(board, i + 1, j, rows, cols);
        }
        if (isValid(board, i, j - 1, rows, cols) && j > 1) {
            dfsRecursive(board, i, j - 1, rows, cols);
        }
        if (isValid(board, i, j + 1, rows, cols) && j < cols - 2) {
            dfsRecursive(board, i, j + 1, rows, cols);
        }
    }

    private boolean isValid(char[][] board, int i, int j, int rows, int cols) {
        return (i >= 0 && i < rows && j >= 0 && j < cols && board[i][j] == 'O');
    }

}

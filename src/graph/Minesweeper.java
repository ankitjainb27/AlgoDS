package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Date 31/07/17
 *
 * @author Ankit Jain
 */
public class Minesweeper {

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        String[] strings = {"EEEEE", "EEMEE", "EEEEE", "EEEEE"};
        char[][] board = new char[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = strings[i].charAt(j);
            }
        }
        board = minesweeper.updateBoard(board, new int[]{3, 0});
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + " -- ");
            }
            System.out.println();
        }
    }


    //DFS
    int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] col = {-1, 0, 1, -1, 1, -1, 0, 1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int rows = board.length;
        int cols = board[0].length;
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else if (board[x][y] == 'E') {
            int mineCount = 0;
            for (int i = 0; i < row.length; i++) {
                int m = x + row[i];
                int n = y + col[i];
                if (isValid(rows, cols, m, n) && board[m][n] == 'M')
                    mineCount++;
            }
            if (mineCount > 0) {
                board[x][y] = (char) (mineCount + '0');
            } else {
                board[x][y] = 'B';
                int[] newClick = click.clone();
                for (int i = 0; i < row.length; i++) {
                    int m = x + row[i];
                    int n = y + col[i];
                    if (isValid(rows, cols, m, n) && board[m][n] == 'E') {
                        newClick[0] = m;
                        newClick[1] = n;
                        updateBoard(board, newClick);
                    }
                }
            }
        }
        return board;
    }

    private boolean isValid(int rows, int cols, int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    //BFS
    public char[][] updateBoard1(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];

            if (board[row][col] == 'M') { // Mine
                board[row][col] = 'X';
            } else { // Empty
                // Get number of mines first.
                int count = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = row + i, c = col + j;
                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                        if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                    }
                }

                if (count > 0) { // If it is not a 'B', stop further DFS.
                    board[row][col] = (char) (count + '0');
                } else { // Continue BFS to adjacent cells.
                    board[row][col] = 'B';
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i == 0 && j == 0) continue;
                            int r = row + i, c = col + j;
                            if (r < 0 || r >= m || c < 0 || c >= n) continue;
                            if (board[r][c] == 'E') {
                                queue.add(new int[]{r, c});
                                board[r][c] = 'B'; // Avoid to be added again.
                            }
                        }
                    }
                }
            }
        }

        return board;
    }

}

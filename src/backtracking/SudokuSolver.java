package backtracking;

/**
 * Date 04/06/17
 *
 * @author Ankit Jain
 *         <p>
 *         <p>
 *         https://leetcode.com/problems/sudoku-solver/#/description
 */
public class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        String[] strings = {"..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.", "........6", "...2759.."};
        char[][] chars = new char[strings.length][strings.length];
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                chars[i][j] = strings[i].charAt(j);
            }
        }
        sudokuSolver.solveSudoku(chars);
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                System.out.print(chars[i][j] + "-");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        solveSudoku(board, board.length);
    }

    private boolean solveSudoku(char[][] board, int n) {
        int i = -1;
        int j = -1;
        boolean flag = false;
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                if (board[k][l] == '.') {
                    flag = true;
                    i = k;
                    j = l;
                    break;
                }
            }
            if (flag)
                break;
        }
        if (i == -1 && j == -1)
            return true;
        for (int k = 1; k <= 9; k++) {
            if (isSafe(board, i, j, (char) ('0' + k), n)) {
                board[i][j] = (char) ('0' + k);
                if (solveSudoku(board, n))
                    return true;
                else {
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

    private boolean isSafe(char[][] board, int i, int j, char k, int n) {
        int rowStart = 3 * (i / 3);
        int colStart = 3 * (j / 3);
        for (int l = rowStart; l < rowStart + 3; l++) {
            for (int m = colStart; m < colStart + 3; m++) {
                if (board[l][m] == k)
                    return false;
            }
        }
        for (int l = 0; l < n; l++) {
            if (board[i][l] == k || board[l][j] == k)
                return false;
        }
        return true;
    }

}

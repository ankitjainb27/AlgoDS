package graph.dfs;

/**
 * Date 04/06/17
 *
 * @author Ankit Jain
 *
 * https://leetcode.com/problems/word-search/#/description
 */
public class WordSearch {
    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(wordSearch.exist(board, "SEE"));
    }

    public boolean exist(char[][] board, String word) {
        if(board.length == 0)
        {
            if(word.length() == 0)
                return true;
            else
                return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (existUtil(board, visited, word, 1, i, j))
                        return true;
                    else
                        visited[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean existUtil(char[][] board, boolean[][] visited, String word, int n, int l, int m) {
        if (word.length() == n)
            return true;
        if (isValid(board, l + 1, m, word.charAt(n),visited)) {
            visited[l + 1][m] = true;
            if (existUtil(board, visited, word, n + 1, l + 1, m))
                return true;
            else {
                visited[l + 1][m] = false;
            }
        }
        if (isValid(board, l - 1, m, word.charAt(n), visited)) {
            visited[l - 1][m] = true;
            if (existUtil(board, visited, word, n + 1, l - 1, m))
                return true;
            else {
                visited[l - 1][m] = false;
            }
        }
        if (isValid(board, l , m+1, word.charAt(n), visited)) {
            visited[l][m+1] = true;
            if (existUtil(board, visited, word, n + 1, l, m+1))
                return true;
            else {
                visited[l][m+1] = false;
            }
        }
        if (isValid(board, l, m-1, word.charAt(n), visited)) {
            visited[l][m-1] = true;
            if (existUtil(board, visited, word, n + 1, l, m-1))
                return true;
            else {
                visited[l][m-1] = false;
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int i, int m, char c, boolean[][] visited) {
        if (i >= 0 && i < board.length && m >= 0 && m < board[0].length && board[i][m] == c && !visited[i][m])
            return true;
        else
            return false;
    }
}

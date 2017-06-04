import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Date 04/06/17
 *
 * @author Ankit Jain
 *         <p>
 *         https://leetcode.com/problems/word-search-ii/#/description
 *
 *
 */
public class WordSearchII {
    public static void main(String[] args) {
        WordSearchII wordSearch = new WordSearchII();
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] strings = {"oath", "pea", "eat", "rain"};
        System.out.println(wordSearch.findWords(board, strings));
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if(!list.contains(words[i]) && exist(board,words[i]))
                list.add(words[i]);
        }
        return list;
    }

    public boolean exist(char[][] board, String word) {
        if (board.length == 0) {
            if (word.length() == 0)
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
        if (isValid(board, l + 1, m, word.charAt(n), visited)) {
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
        if (isValid(board, l, m + 1, word.charAt(n), visited)) {
            visited[l][m + 1] = true;
            if (existUtil(board, visited, word, n + 1, l, m + 1))
                return true;
            else {
                visited[l][m + 1] = false;
            }
        }
        if (isValid(board, l, m - 1, word.charAt(n), visited)) {
            visited[l][m - 1] = true;
            if (existUtil(board, visited, word, n + 1, l, m - 1))
                return true;
            else {
                visited[l][m - 1] = false;
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

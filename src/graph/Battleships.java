package graph;

/**
 * Date 28/07/17
 * @author Ankit Jain
 * https://leetcode.com/problems/battleships-in-a-board/tabs/description
 */
public class Battleships {
    public static void main(String[] args) {
        char[][] board = {{'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'
                }};
        System.out.println(new Battleships().countBattleships(board));
    }

    public int countBattleships(char[][] board) {
        int count = 0;
        if(board.length == 0) return count;
        for(int i = 0; i< board.length;i++)
            for(int j = 0; j < board[0].length;j++)
                if(board[i][j] == 'X' && (i==0 || board[i-1][j]=='.') && (j==0 || board[i][j-1] == '.'))
                    count++;
        return count;
    }
}

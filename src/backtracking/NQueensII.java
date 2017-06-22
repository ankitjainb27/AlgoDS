package backtracking;

import java.util.ArrayList;

/**
 * Date 04/06/17
 *
 * @author Ankit Jain
 *         <p>
 *         N-Queen II Problem
 *         https://leetcode.com/problems/n-queens-ii/#/description
 */
public class NQueensII {
    public static void main(String[] args) {
        NQueensII nQueens = new NQueensII();
        System.out.println(nQueens.solveNQueens(5));
    }

    public int solveNQueens(int n) {
        ArrayList<String> listS = new ArrayList<>();
        char[] ch = new char[n];
        for (int i = 0; i < n; i++) {
            ch[i] = '.';
        }
        String line = new String(ch);
        for (int j = 0; j < n; j++) {
            listS.add(line);
        }
        int val = solveNQueensUtil(listS, 0, n, 0);
//        System.out.println(list);
        return val;
    }

    private int solveNQueensUtil(ArrayList<String> list, int rowIndex, int n, int count) {
        if (rowIndex == n) {
            count++;
            return count;
        }
        String row = list.get(rowIndex);
        for (int i = 0; i < n; i++) {
            if (isSafe(list, rowIndex, i, n)) {
                list.set(rowIndex, row.substring(0, i) + "Q" + row.substring(i + 1, n));
                count = solveNQueensUtil(list, rowIndex + 1, n, count);
                list.set(rowIndex, row.substring(0, i) + "." + row.substring(i + 1, n));
            }
        }
        return count;
    }

    private boolean isSafe(ArrayList<String> list, int rowIndex, int i, int n) {
        for (int j = 0; j < n; j++) {
            if (j != rowIndex && list.get(j).charAt(i) == 'Q')
                return false;
        }
        for (int j = i + 1, row = rowIndex - 1; j < n && row >= 0; j++, row--) {
            if (list.get(row).charAt(j) == 'Q')
                return false;
        }

        for (int j = i - 1, row = rowIndex - 1; j >= 0 && row >= 0; j--, row--) {
            if (list.get(row).charAt(j) == 'Q')
                return false;
        }
        return true;
    }
}



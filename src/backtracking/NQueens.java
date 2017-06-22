package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 04/06/17
 *
 * @author Ankit Jain
 *
 * N-Queen Problem
 * https://leetcode.com/problems/n-queens/#/description
 */
public class NQueens {
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        nQueens.solveNQueens(5);
    }

    public List<List<String>> solveNQueens(int n) {
        List list = new ArrayList();
        ArrayList<String> listS = new ArrayList<>();
        char[] ch = new char[n];
        for (int i = 0; i < n; i++) {
            ch[i] = '.';
        }
        String line = new String(ch);
        for (int j = 0; j < n; j++) {
            listS.add(line);
        }
        solveNQueensUtil(list, listS, 0, n);
//        System.out.println(list);
        return list;
    }

    private void solveNQueensUtil(List list1, ArrayList<String> list, int rowIndex, int n) {
        if (rowIndex == n) {
            ArrayList<String> solList = new ArrayList<>();
            solList.addAll(list);
            list1.add(solList);
            return;
        }
        String row = list.get(rowIndex);
        for (int i = 0; i < n; i++) {
            if (isSafe(list, rowIndex, i, n)) {
                list.set(rowIndex, row.substring(0, i) + "Q" + row.substring(i + 1, n));
                solveNQueensUtil(list1, list, rowIndex + 1, n);
                list.set(rowIndex, row.substring(0, i) + "." + row.substring(i + 1, n));
            }
        }
        return;
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



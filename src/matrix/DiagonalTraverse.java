package matrix;

import java.util.Arrays;

/**
 * Date 01/08/17
 *
 * @author Ankit Jain
 */
public class DiagonalTraverse {
    public static void main(String[] args) {
        DiagonalTraverse diagonalTraverse = new DiagonalTraverse();
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(diagonalTraverse.findDiagonalOrder3(mat)));
    }

    //Uses the diagonal property of matrix, the same of index of matrix is equal.
    public int[] findDiagonalOrder3(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int h = matrix.length, w = matrix[0].length, id = 0;
        int[] res = new int[h * w];
        for (int i = 0; i < h + w; i++) {
            // find lower bound and upper bound
            int lb = (int) Math.max(0, i - w + 1), ub = (int) Math.min(i, h - 1);
            if (i % 2 == 0)
                for (int j = ub; j >= lb; j--)
                    res[id++] = matrix[j][i - j];
            else
                for (int j = lb; j <= ub; j++)
                    res[id++] = matrix[j][i - j];
        }
        return res;
    }

    //Doesn't use boolean
    public int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int r = 0, c = 0, m = matrix.length, n = matrix[0].length, arr[] = new int[m * n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[r][c];
            if ((r + c) % 2 == 0) { // moving up
                if (c == n - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            } else {                // moving down
                if (r == m - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
            }
        }
        return arr;
    }

    //Using boolean
    public int[] findDiagonalOrder(int[][] matrix) {
        int M = matrix.length;
        if (M == 0) return new int[]{};
        int N = matrix[0].length;
        int[] res = new int[M * N];
        int k = 0;
        boolean flag = true;
        int i = 0;
        int j = 0;
        while (k < M * N) {
            while (i < M && i >= 0 && j < N && j >= 0) {
                if (flag)
                    res[k++] = matrix[i--][j++];
                else
                    res[k++] = matrix[i++][j--];
            }
            if (flag) {
                if (j == N) {
                    j = N - 1;
                    i = i + 2;
                } else
                    i = 0;
            } else {
                if (i == M) {
                    i = M - 1;
                    j = j + 2;
                } else
                    j = 0;
            }
            flag = !flag;
        }
        return res;
    }


}

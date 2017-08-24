package dp;

import java.util.Arrays;

/**
 * Created on 15/08/17 at 3:23 PM
 *
 * @author Ankit Jain
 */
public class MaximalSquare {

    public static void main(String[] args) {
        String[] strings = {"10100", "10111", "11111", "10010"};
        char[][] mat = new char[strings.length][strings[0].length()];
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[0].length(); j++) {
                mat[i][j] = strings[i].charAt(j);
            }
        }
        System.out.println(new MaximalSquare().maximalSquare4(mat));
    }

    public int maximalSquare4(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int r = matrix.length, c = matrix[0].length;
        int[] dp = new int[c];
        int size = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                dp[j] = (matrix[i][j] == '1') ? dp[j] + 1 : 0;
            System.out.println(Arrays.toString(dp));
            int count = 0;
            for (int j = 0; j < c; j++) {
                if (dp[j] > size) {
                    count++;
                    if (count > size) {
                        size = count;
                        break;//important! for only increase one for each line
                    }
                } else
                    count = 0;
            }
            System.out.println(Arrays.toString(dp));
        }
        return size * size;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int maxSide = 0;
        int prev;
        int[] dp = new int[matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            prev = i == 0 ? 0 : matrix[i - 1][0] - '0';
            for (int j = 0; j < matrix[0].length; j++) {
                int val = -1;
                if (matrix[i][j] == '1') {
                    val = Math.min(prev, Math.min(dp[j], dp[j + 1]));
                    maxSide = Math.max(maxSide, 1 + val);
                }
                prev = dp[j + 1];
                dp[j + 1] = 1 + val;
            }
        }
        return maxSide * maxSide;
    }

    public int maximalSquare2(char[][] a) {
        if (a.length == 0) return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }


    public int maximalSquare1(char[][] matrix) {
        int maxSide = 0;
        boolean flag = false;

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    flag = true;
                    int val = Math.min(matrix[i - 1][j - 1] - '0', Math.min(matrix[i - 1][j] - '0', matrix[i][j - 1] - '0'));
                    if (val != 0) {
                        matrix[i][j] = (char) ((1 + val) + '0');
                        maxSide = Math.max(maxSide, 1 + val);
                    }

                }
            }
        }
        if (flag && maxSide == 0) maxSide = 1;
        if (maxSide == 0) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] == '1') {
                    maxSide = 1;
                    break;
                }
            }
        }
        if (maxSide == 0 && matrix.length > 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[0][i] == '1') {
                    maxSide = 1;
                    break;
                }
            }
        }
        return maxSide * maxSide;
    }

}

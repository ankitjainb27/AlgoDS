package dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Date 11/08/17
 *
 * @author Ankit Jain
 */
public class OnesAndZeros {
    public static void main(String[] args) {
        OnesAndZeros onesAndZeros = new OnesAndZeros();
//        System.out.println(onesAndZeros.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
//        System.out.println(onesAndZeros.findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
        System.out.println(onesAndZeros.findMaxForm(new String[]{"0", "11", "1000", "01", "0", "101", "1", "1", "1", "0", "0", "0", "0", "1", "0", "0110101", "0", "11", "01", "00", "01111", "0011", "1", "1000", "0", "11101", "1", "0", "10", "0111"}, 12, 80));
    }

    int result = 0;

    public int findMaxForm(String[] strs, int m, int n) {

        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                int diff = s1.length() - s2.length();
                if (diff != 0) return diff;
                return s1.compareTo(s2);
            }

        });
        System.out.println(Arrays.toString(strs));
        for (int i = 0; i < strs.length; i++)
            findMax(strs, m, n, i, 0);
        return result;
    }

    public void findMax(String[] strs, int m, int n, int index, int currentMax) {
        //can't match any more, return
        if (index >= strs.length || strs[index].length() > m + n) {
            result = Math.max(result, currentMax);
            return;
        }
        boolean canAdd = true;
        int newM = m, newN = n;

        for (char c : strs[index].toCharArray()) {
            if (c == '0' && newM > 0) {
                newM--;
            } else if (c == '1' && newN > 0) {
                newN--;
            } else { //can't add this new result
                canAdd = false;
                break;
            }
        }
        if (canAdd) {
            findMax(strs, newM, newN, index + 1, currentMax + 1);
        } else {
            findMax(strs, m, n, index + 1, currentMax);
        }
    }

    public int findMaxForm3(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String st : strs) {
            int[] count = find01WithMemo(st);
            for (int i = m; i >= count[0]; i--) {
                for (int j = n; j >= count[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - count[0]][j - count[1]]);
                }
            }
        }
        return dp[m][n];
    }

    public int findMaxForm2(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int k = 1; k <= strs.length; k++) {
            int[] count = find01WithMemo(strs[k - 1]);
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (count[0] > i || count[1] > j)
                        dp[k][i][j] = dp[k - 1][i][j];
                    else dp[k][i][j] = Math.max(dp[k - 1][i][j], 1 + dp[k - 1][i - count[0]][j - count[1]]);
                }
            }
            //print(dp, k, m, n);
        }
        return dp[strs.length][m][n];
    }

    private void print(int[][][] dp, int k, int m, int n) {
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[k][i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int[] find01WithMemo(String st) {
        HashMap<String, int[]> map = new HashMap<>();
        if (!map.containsKey(st)) {
            int[] num = find01(st);
            map.put(st, num);
        }
        return map.get(st);
    }

    public int findMaxForm1(String[] strs, int m, int n) {
        return findMaxFormUtil(strs, m, n, 0);
    }

    private int findMaxFormUtil(String[] strs, int m, int n, int index) {
        if (index >= strs.length || (strs.length < m + n)) return 0;
        int[] num;
        num = find01(strs[index]);
        int max = Integer.MIN_VALUE;
        if (m >= num[0] && n >= num[1])
            max = Math.max(max, 1 + findMaxFormUtil(strs, m - num[0], n - num[1], index + 1));
        return Math.max(max, findMaxFormUtil(strs, m, n, index + 1));
    }

    private int[] find01(String st) {
        int[] count = new int[2];
        for (char ch : st.toCharArray())
            count[ch - '0']++;
        return count;
    }
}

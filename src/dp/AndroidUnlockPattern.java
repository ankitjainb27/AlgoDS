package dp;

/**
 * Date 13/07/17
 *
 * @author Ankit Jain
 *         <p>
 *         Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 *         <p>
 *         Rules for a valid pattern:
 *         Each pattern must connect at least m keys and at most n keys.
 *         All the keys must be distinct.
 *         If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 *         The order of keys used matters.
 */
public class AndroidUnlockPattern {
    public static void main(String[] args) {
        AndroidUnlockPattern androidUnlockPattern = new AndroidUnlockPattern();
        System.out.println(androidUnlockPattern.numberOfPatterns(3, 3));
    }

    private void numberOfPatternsUtil(int m, int n, boolean[] list, int i, int len) {
        if (len >= m && len <= n) {
            count++;
        }
        if (len == n) return;
        for (int j = 1; j <= 9; j++) {
            int jump = req[i][j];
            if (!list[j] && (jump == 0 || list[jump])) {
                list[j] = true;
                numberOfPatternsUtil(m, n, list, j, len + 1);
                list[j] = false;
            }
        }
    }

    int count = 0;
    int[][] req;

    public int numberOfPatterns(int m, int n) {
        req = new int[10][10];
        req[1][3] = 2;
        req[3][1] = 2;
        req[1][7] = 4;
        req[7][1] = 4;
        req[1][9] = 5;
        req[9][1] = 5;
        req[2][8] = 5;
        req[8][2] = 5;
        req[4][6] = 5;
        req[6][4] = 5;
        req[3][7] = 5;
        req[7][3] = 5;
        req[3][9] = 6;
        req[9][3] = 6;
        req[7][9] = 8;
        req[9][7] = 8;
        boolean[] visited = new boolean[10];
        for (int i = 1; i <= 5; i++) {
            if (i == 1 || i == 2 || i == 5) {
                visited[i] = true;
                int prevCount = count;
                numberOfPatternsUtil(m, n, visited, i, 1);
                if (i == 1 || i == 2)
                    count += (count - prevCount) * 3;
                visited[i] = false;
            }
        }
        return count;
    }

}

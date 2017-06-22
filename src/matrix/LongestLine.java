package matrix;

/**
 * Created by ankit.ppe on 23/04/17.
 */
public class LongestLine {
    public static void main(String[] args) {
        LongestLine longestLine = new LongestLine();
        int[][] M = {{0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 1}};
        System.out.println(longestLine.longestLine(M));
    }

    public int longestLine(int[][] M) {
        int maxLen = 0;
        int m = M.length;
        if (m == 0)
            return 0;
        int n = M[0].length;
        for (int i = 0; i < m; i++) {
            int len = 0;
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1)
                    len++;
                else {
                    maxLen = Math.max(len, maxLen);
                    len = 0;
                }
            }
            maxLen = Math.max(len, maxLen);
        }
        for (int i = 0; i < n; i++) {
            int len = 0;
            for (int j = 0; j < m; j++) {
                if (M[j][i] == 1)
                    len++;
                else {
                    maxLen = Math.max(len, maxLen);
                    len = 0;
                }
            }
            maxLen = Math.max(len, maxLen);
        }
        for (int i = 0; i <= m + n - 2; i++) {
            int len = 0;
            int range;
            if (i < m)
                range = 0;
            else
                range = m;
            for (int j = range; j < m; j++) {
                if (M[j][i] == 1)
                    len++;
                else {
                    maxLen = Math.max(len, maxLen);
                    len = 0;
                }
            }
            maxLen = Math.max(len, maxLen);
        }


        return maxLen;
    }
}

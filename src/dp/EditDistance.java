package dp;

/**
 * Created on 21/08/17 at 6:38 PM
 *
 * @author Ankit Jain
 */
public class EditDistance {
    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance("apple", "apdly"));
        System.out.println(editDistance.minDistance("apple", "apdlyt"));
        System.out.println(editDistance.minDistance("apple", "aplyt"));
        System.out.println(editDistance.minDistance("apple", "alye"));
        System.out.println(editDistance.minDistance("apple", "appye"));
        System.out.println(editDistance.minDistance("apple", "appe"));
        System.out.println(editDistance.minDistance("sea", "eat"));
    }

    public int minDistance(String word1, String word2) {
        int[][] cache = new int[word1.length()][word2.length()];
        return match(word1, word2, 0, 0, cache);
    }

    private int match(String word1, String word2, int i, int j, int[][] cache) {
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int res = 0;
        if (word1.charAt(i) == word2.charAt(j)) {
            res = match(word1, word2, i + 1, j + 1, cache);
        } else {
            int insert = match(word1, word2, i, j + 1, cache);
            int replace = match(word1, word2, i + 1, j + 1, cache);
            int delete = match(word1, word2, i + 1, j, cache);
            res = Math.min(Math.min(insert, replace), delete) + 1;
        }
        cache[i][j] = res;
        return res;
    }

    public int minDistance2(String word1, String word2) {
        int[] dp = new int[word2.length() + 1];
        for (int i = 0; i <= word2.length(); i++) {
            dp[i] = i;
        }
//        System.out.println(Arrays.toString(dp));
        for (int i = 0; i < word1.length(); i++) {
            int prev = dp[0];
            dp[0] = i + 1;
            for (int j = 0; j < word2.length(); j++) {
                int next = dp[j + 1];
                if (word1.charAt(i) == word2.charAt(j))
                    dp[j + 1] = prev;
                else
                    dp[j + 1] = Math.min(prev, Math.min(dp[j + 1], dp[j])) + 1;
                prev = next;
            }
//            System.out.println(Arrays.toString(dp));
        }
        return dp[word2.length()];
    }

    public int minDistance1(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
//        System.out.println(Arrays.toString(dp[0]));
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
            }
//            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[word1.length()][word2.length()];
    }
}

package string;

/**
 * Date 28/07/17
 *
 * @author Ankit Jain
 */
public class PalindromeCounting {
    public static void main(String[] args) {
        PalindromeCounting palindromeCounting = new PalindromeCounting();
        System.out.println(palindromeCounting.countSubstrings2("aaa"));
//        System.out.println(palindromeCounting.countSubstrings1("aaa"));
    }

    public int countSubstrings2(String s) {
        int res = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; i - j >= 0 && i + j < n && s.charAt(i - j) == s.charAt(i + j); j++)
                res++; //substring s[i-j, ..., i+j]
            for (int j = 0; i - 1 - j >= 0 && i + j < n && s.charAt(i - 1 - j) == s.charAt(i + j); j++)
                res++; //substring s[i-1-j, ..., i+j]
        }
        return res;
    }

    //Method - Traversal
    public int countSubstrings(String s) {
        int len = s.length();
        int count = len;
        for (int i = 0; i < len - 1; i++) {
            count += getPalCount(i - 1, i + 1, s);
            count += getPalCount(i, i + 1, s);
        }
        return count;
    }

    private int getPalCount(int l, int r, String s) {
        int count = 0;
        int len = s.length();
        while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
            count++;
        }
        return count;
    }

    //Method - Dynamic Programming
    public int countSubstrings1(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = len;
        for (int i = 0; i < len; i++)
            dp[i][i] = true;
        for (int i = 2; i <= len; i++) {
            for (int j = 0; j < len - i + 1; j++) {
                int k = i + j - 1;
                if (s.charAt(j) == s.charAt(k) && (i == 2 || dp[j + 1][k - 1])) {
                    dp[j][k] = true;
                    count++;
                }
            }
        }
        return count;
    }
}

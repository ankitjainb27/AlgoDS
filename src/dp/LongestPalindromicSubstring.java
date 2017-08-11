package dp;

/**
 * Date 04/08/17
 *
 * @author Ankit Jain
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindromeSubstring("bbbab"));
    }


    public int longestPalindromeSubstring(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int maxLen = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int l = i - 1;
            int r = i + 1;
            int len = 1;
            while (l >= 0 && r < n) {
                if (chars[l--] == chars[r++])
                    len += 2;
                else break;
            }
            maxLen = Math.max(maxLen, len);
            l = i - 1;
            r = i;
            len = 0;
            while (l >= 0 && r < n) {
                if (chars[l--] == chars[r++])
                    len += 2;
                else break;
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

}

package array;

/**
 * Created on 17/08/17 at 3:21 PM
 *
 * @author Ankit Jain
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(new LongestRepeatingCharacterReplacement().characterReplacement("AAAB", 0));
        System.out.println(new LongestRepeatingCharacterReplacement().characterReplacement("AABA", 0));
        System.out.println(new LongestRepeatingCharacterReplacement().characterReplacement("AABABBA", 2));
        System.out.println(new LongestRepeatingCharacterReplacement().characterReplacement("AABABBAAABBBBAAAAAABBA", 2));
    }

    public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    public int characterReplacement1(String s, int k) {
        char[] chars = s.toCharArray();
        int l = 0;
        int r = 0;
        int max = 1;
        int n = k;
        while (l < chars.length && r < chars.length) {
            if (k > 0 || chars[r] == chars[l]) {
                if (chars[r] != chars[l])
                    k--;
                r++;
            } else {
                max = Math.max(max, r - l);
                char ch = chars[l];
                l++;
                while (l < chars.length && chars[l] == ch) {
                    l++;
                }
                l++;
                if (r < l)
                    r = l;
                if (k + 1 <= n)
                    k++;
            }
        }
        max = Math.max(max, r - l);
        return max;
    }
}
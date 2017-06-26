package string;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/valid-word-abbreviation/#/description
 */
public class ValidAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        int wl = word.length();
        int al = abbr.length();
        while (i < wl && j < al) {
            char ch = abbr.charAt(j);
            if (ch >= '0' && ch <= '9') {
                int digit = 0;
                while (j < al && Character.isDigit(abbr.charAt(j))) {
                    digit = digit * 10 + abbr.charAt(j++) - '0';
                    if (digit == 0)
                        return false;
                }
                i += digit;
            } else {
                if (word.charAt(i++) != abbr.charAt(j++))
                    return false;
            }
        }
        return i == wl && j == al;
    }
}

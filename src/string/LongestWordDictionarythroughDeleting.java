package string;

import java.util.*;

/**
 * Date 04/08/17
 *
 * @author Ankit Jain
 */
public class LongestWordDictionarythroughDeleting {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>(Arrays.asList("ale", "apple", "monkey", "aple", "plea"));
        List<String> list = new ArrayList<>(Arrays.asList("ale", "apple", "monkey", "aple", "plea"));
//        List<String> list = new ArrayList<>(Arrays.asList("a","b","c"));
        System.out.println(new LongestWordDictionarythroughDeleting().findLongestWord("abpcplea", list));
    }

    /**
     * We sort the input dictionary by longest length and lexicography. Then, we iterate through the dictionary exactly once. In the process, the first dictionary word in the sorted dictionary which appears as a subsequence in the input string s must be the desired solution.
     *
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, (a, b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) : a.compareTo(b));
        for (String dictWord : d) {
            int i = 0;
            for (char c : s.toCharArray())
                if (i < dictWord.length() && c == dictWord.charAt(i)) i++;
            if (i == dictWord.length()) return dictWord;
        }
        return "";
    }

    /**
     * more efficient solution which avoids sorting the dictionary:
     *
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord1(String s, List<String> d) {
        String longest = "";
        for (String dictWord : d) {
            int i = 0;
            for (char c : s.toCharArray())
                if (i < dictWord.length() && c == dictWord.charAt(i)) i++;

            if (i == dictWord.length() && dictWord.length() >= longest.length())
                if (dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0)
                    longest = dictWord;
        }
        return longest;
    }


    public String findLongestWord2(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String st1, String st2) {
                if (st1.length() == st2.length()) {
                    return st1.compareTo(st2);
                } else
                    return st2.length() - st1.length();
            }
        });
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            chars[ch - 'a']++;
        }
        for (int j = 0; j < d.size(); j++) {
            String st = d.get(j);
            char[] val = new char[26];
            for (int i = 0; i < st.length(); i++) {
                char ch = st.charAt(i);
                val[ch - 'a']++;
            }
            int len = 0;
            for (int i = 0; i < 26; i++) {
                if (val[i] != 0) {
                    if (val[i] <= chars[i]) {
                        len += val[i];
                    } else break;
                }
            }
            if (len == st.length()) {
                int d1 = 0;
                int w1 = 0;
                while (d1 < st.length() && w1 < s.length()) {
                    if (st.charAt(d1) == s.charAt(w1)) {
                        d1++;
                    }
                    w1++;
                }
                if (d1 == st.length()) {
                    return st;
                }
            }
        }
        return "";
    }
}

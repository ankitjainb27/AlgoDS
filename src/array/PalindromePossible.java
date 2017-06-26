package array;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/palindrome-permutation/#/description
 */
public class PalindromePossible {

    public boolean canPermutePalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (!set.add(ch)) set.remove(ch);
        }
        return set.size() < 2;
    }

    public static void main(String[] args) {
        PalindromePossible palindromePossible = new PalindromePossible();
        System.out.println(palindromePossible.canPermutePalindrome("aab"));
        System.out.println(palindromePossible.canPermutePalindrome("code"));
        System.out.println(palindromePossible.canPermutePalindrome("carerace"));
    }

}

package bit;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;

/**
 * Date 03/08/17
 *
 * @author Ankit Jain
 */
public class MaximumProductWordLengths {
    public static void main(String[] args) {
        MaximumProductWordLengths maximumProductWordLengths = new MaximumProductWordLengths();
        System.out.println(maximumProductWordLengths.maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
    }

    public int maxProduct(String[] words) {
        int[] set1 = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int temp = 0;
            for (char ch : words[i].toCharArray()) {
                temp |= 1 << (ch - 'a');
            }
            set1[i] = temp;
        }
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i; j < words.length; j++) {
                if ((set1[i] & set1[j]) == 0)
                    max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }

    public int maxProduct1(String[] words) {
        int max = 0;

        Arrays.sort(words, new Comparator<String>() {
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });

        int[] masks = new int[words.length]; // alphabet masks

        for (int i = 0; i < masks.length; i++) {
            for (char c : words[i].toCharArray()) {
                masks[i] |= 1 << (c - 'a');
            }
        }

        for (int i = 0; i < masks.length; i++) {
            if (words[i].length() * words[i].length() <= max) break; //prunning
            for (int j = i + 1; j < masks.length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                    break; //prunning
                }
            }
        }

        return max;
    }

    public int maxProduct2(String[] words) {
        BitSet[] set1 = new BitSet[words.length];
        for (int i = 0; i < words.length; i++) {
            set1[i] = new BitSet(26);
            for (char ch : words[i].toCharArray()) {
                set1[i].set(ch - 'a' + 1);
            }
        }
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i; j < words.length; j++) {
                BitSet temp = (BitSet) set1[i].clone();
                temp.and(set1[j]);
                if (temp.cardinality() == 0)
                    max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }
}

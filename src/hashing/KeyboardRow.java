package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created on 24/08/17 at 3:58 PM
 *
 * @author Ankit Jain
 */
public class KeyboardRow {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new KeyboardRow().findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }

    public String[] findWords(String[] words) {
        HashSet<Character>[] rows = new HashSet[3];
        String[] vals = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new HashSet<>();
            for (int j = 0; j < vals[i].length(); j++) {
                rows[i].add(vals[i].charAt(j));
                rows[i].add((char) (vals[i].charAt(j) - 32));
            }
            System.out.println(rows[i]);
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            char firstCh = words[i].charAt(0);
            int k = 0;
            for (int j = 0; j < 3; j++) {
                if (rows[j].contains(firstCh)) {
                    k = j;
                    break;
                }
            }
            list.add(words[i]);
            for (char ch : words[i].toCharArray()) {
                if (!rows[k].contains(ch)) {
                    list.remove(list.size() - 1);
                    break;
                }
            }
        }
        return list.toArray(new String[0]);
    }
}

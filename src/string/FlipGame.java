package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/flip-game/#/description
 */
public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chars[i] == chars[i + 1] && chars[i] == '+') {
                chars[i] = chars[i + 1] = '-';
                list.add(String.valueOf(chars));
                chars[i] = chars[i + 1] = '+';
            }
        }
        return list;
    }

    public List<String> generatePossibleNextMoves1(String s) {
        List list = new ArrayList();
        for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; )
            list.add(s.substring(0, i) + "--" + s.substring(i + 2));
        return list;
    }

    public static void main(String[] args) {
        FlipGame flipGame = new FlipGame();
        System.out.println(flipGame.generatePossibleNextMoves("++"));
    }
}

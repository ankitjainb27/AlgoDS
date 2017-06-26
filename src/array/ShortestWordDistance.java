package array;

import java.util.Map;

/**
 * Date 23/06/17
 * @author Ankit Jain
 * https://leetcode.com/problems/shortest-word-distance/#/description
 */
public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (index != -1 && !words[index].equals(words[i]))
                    min = Math.min(min, i - index);
                index = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ShortestWordDistance shortestWordDistance = new ShortestWordDistance();
        System.out.println(shortestWordDistance.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"));
    }
}

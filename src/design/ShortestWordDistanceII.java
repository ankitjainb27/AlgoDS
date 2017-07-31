package design;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Date 01/07/17
 * @author Ankit Jain
 * https://leetcode.com/problems/shortest-word-distance-ii/#/description
 */
public class ShortestWordDistanceII {

    public static void main(String[] args) {
        ShortestWordDistanceII obj = new ShortestWordDistanceII(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        int param_1 = obj.shortest("coding", "practice");
        System.out.println(param_1);
        int param_2 = obj.shortest("makes", "coding");
        System.out.println(param_2);
    }

    //Method 1
    HashMap<String, TreeSet<Integer>> map;
    public ShortestWordDistanceII(String[] words) {
        map = new HashMap<>();
        int i = 0;
        for(String word : words)
        {
            if(!map.containsKey(word))
                map.put(word, new TreeSet<Integer>());
            map.get(word).add(i++);
        }
    }

    public int shortest(String word1, String word2) {
        int min = Integer.MAX_VALUE;
        TreeSet<Integer> set1 = map.get(word1);
        TreeSet<Integer> set2 = map.get(word2);
        for(Integer i : set1)
        {
            Integer floor = set2.floor(i);
            min = floor!= null?Math.min(min, Math.abs(i-floor)):min;
            Integer ceiling = set2.ceiling(i);
            min = ceiling!= null?Math.min(min, Math.abs(i-ceiling)):min;
        }
        return min;
    }

    //Method 2, but leads to TLE
    HashMap<String, Integer> map1;
    public ShortestWordDistanceII(String[] words, boolean b) {
        map1 = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (!words[i].equals(words[j])) {
                    String st = "";
                    if (words[i].compareTo(words[j]) < 0)
                        st = words[i] + words[j];
                    else
                        st = words[j] + words[i];
                    if (map1.containsKey(st))
                        map1.put(st, Math.min(map1.get(st), Math.abs(i - j)));
                    else
                        map1.put(st, Math.abs(i - j));
                }
            }
        }
    }

    public int shortest1(String word1, String word2) {
        if (word1.compareTo(word2) < 0)
            return map1.get(word1 + word2);
        else
            return map1.get(word2 + word1);
    }
}
package graph.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date 05/06/17
 *
 * @author Ankit Jain
 */
public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII wordLadderII = new WordLadderII();
        String beginWord = "hot";
        String endWord = "dog";
        List<String> wordList = Arrays.asList(new String[]{"hot","tot","dog","cog","hog","hop","pot","dot"});

        System.out.println(wordLadderII.findLadders(beginWord, endWord, wordList));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> list = new ArrayList();
        List<String> strings = new ArrayList<>();
        boolean[] used = new boolean[wordList.size()];
        strings.add(beginWord);
        findLaddersUtil(beginWord, endWord, wordList, used, list, strings);
        return list;
    }

    private void findLaddersUtil(String beginWord, String endWord, List<String> wordList, boolean[] used, List<List<String>> list, List<String> strings) {
        if (beginWord.equals(endWord)) {
            List<String> strings1 = new ArrayList<>(strings);
            if (list.size() > 0) {
                if (strings.size() < list.get(0).size()) {
                    list.clear();
                    list.add(strings1);
                } else if (strings.size() == list.get(0).size()) {
                    list.add(strings1);
                }
            } else {
                list.add(strings1);
            }
            return;
        }
        for (int i = 0; i < wordList.size(); i++) {
            if (isSafe(beginWord, wordList.get(i), used, i)) {
                strings.add(wordList.get(i));
                used[i] = true;
                findLaddersUtil(wordList.get(i), endWord, wordList, used, list, strings);
                used[i] = false;
                strings.remove(strings.size() - 1);
            }
        }
    }

    private boolean isSafe(String beginWord, String s, boolean[] used, int i) {
        if (!used[i] && isOneCharDiff(beginWord, s))
            return true;
        else
            return false;
    }

    private boolean isOneCharDiff(String beginWord, String s) {
        int diff = 0;
        for (int i = 0; i < beginWord.length(); i++) {
            if(beginWord.charAt(i) != s.charAt(i))
                diff++;
        }
        return diff==1?true:false;
    }
}

package graph.bfs;

import java.util.*;

/**
 * Date 08/08/17
 *
 * @author Ankit Jain
 */
public class WordLadder {
    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println(wordLadder.ladderLength2("a", "c", list));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                if (endWord.equals(word)) return count;

                char[] chars = word.toCharArray();
                for (int j = 0; j < beginWord.length(); j++) {
                    char oldChar = word.charAt(j);
                    for (int k = 0; k < 26; k++) {
                        chars[j] = (char) (k + 'a');
                        String st = new String(chars);
                        if (!visited.contains(st) && set.contains(st)) {
                            visited.add(st);
                            queue.add(st);
                        }
                    }
                    chars[j] = oldChar;
                }

            }
            count++;
        }
        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int count = 1;
        Set<String> set = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        if (!set.contains(endWord)) return 0;
        set.remove(endWord);
        Set<String> visited = new HashSet<>();
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set temp = endSet;
                endSet = beginSet;
                beginSet = temp;
            }
            Set<String> nextLevel = new HashSet<>();
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                for (int j = 0; j < beginWord.length(); j++) {
                    char oldChar = word.charAt(j);
                    for (int k = 0; k < 26; k++) {
                        chars[j] = (char) (k + 'a');
                        String st = new String(chars);
                        if (endSet.contains(st)) {
                            return ++count;
                        }
                        if(!set.contains(st))
                            continue;
                        if (!visited.contains(st)) {
                            visited.add(st);
                            nextLevel.add(st);
                        }
                    }
                    chars[j] = oldChar;
                }
                beginSet = nextLevel;
            }
            count++;
        }
        return 0;
    }


    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) {
            return 0;
        }

        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        return bfsHelper(beginWord, endWord, wordSet);
    }

    public int bfsHelper(String beginWord, String endWord, Set<String> wordSet) {
        int steps = 0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            steps++;
            // always start from a smaller one
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }
            Set<String> nextLevel = new HashSet<>();
            for (String curStr : beginSet) {
                for (int i = 0; i < curStr.length(); i++) {
                    for (char j = 'a'; j <= 'z'; j++) {
                        String toStr = replace(curStr, i, j);
                        if (!wordSet.contains(toStr)) {
                            continue;
                        }
                        if (endSet.contains(toStr)) {
                            return ++steps;
                        }
                        if (!visited.contains(toStr)) {
                            visited.add(toStr);
                            nextLevel.add(toStr);
                        }
                    }
                }
            }
            beginSet = nextLevel;
        }
        return 0;
    }

    public String replace(String str, int i, char c) {
        char[] chs = str.toCharArray();
        chs[i] = c;
        return new String(chs);
    }
}

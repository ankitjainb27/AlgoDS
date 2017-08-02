package string.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Date 31/07/17
 *
 * @author Ankit Jain
 */
public class ReplaceWords {
    public static void main(String[] args) {
        ReplaceWords replaceWords = new ReplaceWords();
        List<String> list = new ArrayList<>(Arrays.asList("cat", "bat", "rat"));
        System.out.println(list);
        System.out.println(replaceWords.replaceWords(list, "the cattle was rattled by the battery"));
    }

    //Solution using HashMap for TrieNode
    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for (String st : dict) {
            trie.insert(st);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String pref = trie.searchPrefix(words[i]);
            if (pref != null)
                words[i] = pref;
        }
        return String.join(" ", words);
    }

    class Trie {
        TrieNode head;

        public Trie() {
            head = new TrieNode();
        }

        public void insert(String st) {
            TrieNode node = head;
            for (char ch : st.toCharArray()) {
                if (!node.map.containsKey(ch)) {
                    node.map.put(ch, new TrieNode());
                }
                node = node.map.get(ch);
            }
            node.isEnd = true;
        }

        public String searchPrefix(String st) {
            TrieNode node = head;
            StringBuilder sb = new StringBuilder();
            for (char ch : st.toCharArray()) {
                if (node.map.containsKey(ch)) {
                    sb.append(ch);
                    node = node.map.get(ch);
                    if (node.isEnd)
                        return sb.toString();
                } else {
                    return null;
                }
            }
            return null;
        }
    }

    class TrieNode {
        boolean isEnd;
        HashMap<Character, TrieNode> map;

        public TrieNode() {
            map = new HashMap<>();
        }
    }

}

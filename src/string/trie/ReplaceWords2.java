package string.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date 31/07/17
 *
 * @author Ankit Jain
 */
public class ReplaceWords2 {
    public static void main(String[] args) {
        ReplaceWords replaceWords = new ReplaceWords();
        List<String> list = new ArrayList<>(Arrays.asList("cat", "bat", "rat"));
        System.out.println(list);
        System.out.println(replaceWords.replaceWords(list, "the cattle was rattled by the battery"));
    }

    //Solution using Array for TrieNode
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
                int chInt = ch-'a';
                if (node.map[chInt] != null) {
                    node = node.map[chInt];
                } else {
                    TrieNode newNode = new TrieNode();
                    node.map[chInt]  = newNode;
                    node = newNode;
                }
            }
            node.isEnd = true;
        }

        public String searchPrefix(String st) {
            TrieNode node = head;
            StringBuilder sb = new StringBuilder();
            for (char ch : st.toCharArray()) {
                int chInt = ch-'a';
                if (node.map[chInt] != null) {
                    sb.append(ch);
                    node = node.map[chInt];
                } else {
                    return null;
                }
                if (node.isEnd)
                    return sb.toString();
            }
            return null;
        }
    }

    class TrieNode {
        boolean isEnd;
        TrieNode[] map;

        public TrieNode() {
            map = new TrieNode[26];
        }
    }

}

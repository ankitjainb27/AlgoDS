package string;

import java.util.*;

/**
 * Date 31/07/17
 *
 * @author Ankit Jain
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {
        System.out.println(new SortCharactersByFrequency().frequencySort4("his s he a ha he  ha ae"));
    }

    //Fastest - Uses Bucket sort and doesn't use Collection API
    public String frequencySort(String s) {
        int[] chars = new int[256];
        int max = Integer.MIN_VALUE;
        for (char ch : s.toCharArray()) {
            chars[(int) ch]++;
            max = Math.max(max, chars[(int) ch]);
        }
        String[] buckets = new String[max + 1];
        for (int i = 0; i < 256; i++) {
            String str = buckets[chars[i]];
            if (chars[i] > 0)
                buckets[chars[i]] = str == null ? "" + (char) i : str + (char) i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = max; i >= 0; i--) {
            String str = buckets[i];
            if (str != null) {
                for (char ch : str.toCharArray()) {
                    for (int j = 0; j < i; j++) {
                        sb.append(ch);
                    }
                }
            }
        }
        return sb.toString();
    }

    //Faster, but creates another class to store values
    public String frequencySort2(String s) {
        Ele[] chars = new Ele[256];
        for (int i = 0; i < 256; i++) {
            chars[i] = new Ele(' ', 0);
        }
        for (char ch : s.toCharArray()) {
            chars[(int) ch].freq++;
            chars[(int) ch].ch = ch;
        }
        Arrays.sort(chars, new Comparator<Ele>() {
            @Override
            public int compare(Ele e1, Ele e2) {
                return e2.freq - e1.freq;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Ele e : chars) {
            if (e.freq > 0) {
                for (int i = 0; i < e.freq; i++)
                    sb.append(e.ch);
            } else
                break;
        }
        return sb.toString();
    }

    class Ele implements Comparable<Ele> {
        char ch;
        int freq;

        public Ele(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        @Override
        public int compareTo(Ele o) {
            return o.freq - freq;
        }
    }

    //Faster - Uses HashMap and Sort by value.
    public String frequencySort1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch))
                map.put(ch, map.get(ch) + 1);
            else
                map.put(ch, 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> me : list) {
            for (int i = 0; i < me.getValue(); i++)
                sb.append(me.getKey());
        }
        return sb.toString();
    }

    public String frequencySort4(String s) {
        if (s.equals("")) return "";
        int[] map = new int[256];
        for (char c : s.toCharArray()) map[c]++;
        Queue<Character> priorityQ = new PriorityQueue<Character>(new Comparator<Character>() {
            public int compare(Character a, Character b) {
                int countA = map[a], countB = map[b];
                if (countA > countB) return -1;
                else if (countA == countB) return 0;
                else return 1;
            }
        });
        for (int Char = 0; Char < 256; Char++) {
            if (map[Char] > 0)
                priorityQ.add((char) Char);
        }
        StringBuilder result = new StringBuilder();
        while (priorityQ.size() > 0) {
            char c = priorityQ.poll();
            for (int count = 1; count <= map[c]; count++) result.append(c);
        }
        return result.toString();
    }

    public String frequencySort5(String s) {
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        PriorityQueue<Char> pq = new PriorityQueue<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                Char ch = new Char((char) i, count[i]);
                pq.offer(ch);
            }
        }

        char[] str = new char[s.length()];
        int i = 0;
        while (pq.size() > 0) {
            Char ch = pq.poll();
            for (int j = 0; j < ch.freq; j++) {
                str[i++] = ch.c;
            }
        }
        return String.valueOf(str);
    }

    class Char implements Comparable<Char> {
        char c;
        int freq;

        public Char(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }

        public int compareTo(Char ch) {
            return ch.freq - freq;
        }
    }

}

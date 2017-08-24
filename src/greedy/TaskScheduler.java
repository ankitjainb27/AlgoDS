package greedy;

import java.util.*;

/**
 * Created on 17/08/17 at 7:06 PM
 *
 * @author Ankit Jain
 */
public class TaskScheduler {
    public static void main(String[] args) {
        System.out.println(new TaskScheduler().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }

    public int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for (char t : tasks) {
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while (i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }


    public int leastInterval1(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<Map.Entry<Character, Integer>>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        queue.addAll(map.entrySet());
        int count = 0;
        List<Map.Entry<Character, Integer>> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int i = 0;
            for (i = 0; i <= n && !queue.isEmpty(); i++) {
                count++;
                Map.Entry<Character, Integer> me = queue.poll();
                me.setValue(me.getValue() - 1);
                if (me.getValue() > 0)
                    list.add(me);
            }
            count += list.isEmpty() || !queue.isEmpty() ? 0 : n - i + 1;
            queue.addAll(list);
            list.clear();
        }
        return count;
    }
}

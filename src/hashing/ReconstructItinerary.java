package hashing;

import java.util.*;

/**
 * Created on 19/08/17 at 10:17 PM
 *
 * @author Ankit Jain
 */
public class ReconstructItinerary {
    public static void main(String[] args) {
        System.out.println(new ReconstructItinerary().findItinerary(new String[][]{{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}}));
    }

    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(String[] ticket : tickets) {
            String start = ticket[0];
            String end = ticket[1];
            if(!map.containsKey(start)) {
                PriorityQueue<String> orders = new PriorityQueue<>();
                orders.offer(end);
                map.put(start, orders);
            } else {
                map.get(start).offer(end);
            }
        }
        System.out.println(map);
        List<String> result = new ArrayList<>();
        helper(map, result, "JFK");
        return result;
    }
    private void helper(Map<String, PriorityQueue<String>> map, List<String> result, String cur) {
        PriorityQueue<String> arrivals = map.get(cur);

        while(arrivals != null && !arrivals.isEmpty()) {
            String arrival = arrivals.poll();
            helper(map, result, arrival);
        }

        result.add(0, cur);
    }

    public List<String> findItinerary1(String[][] tickets) {
        List<String> list = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            if (!map.containsKey(ticket[0]))
                map.put(ticket[0], new ArrayList<String>());
            map.get(ticket[0]).add(ticket[1]);
        }

        list.add("JFK");
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String st1, String st2) {
                return st1.compareTo(st2);
            }
        };
        System.out.println(map);
        String prev = "JFK";
        for (Map.Entry<String, List<String>> me : map.entrySet()) {
            if (me.getValue().size() > 1)
                Collections.sort(me.getValue(), comp);
        }
        dfs(map, list, prev, tickets.length + 1);
        return list;
    }

    private boolean dfs(HashMap<String, List<String>> map, List<String> list, String prev, int n) {
        if (list.size() == n)
            return true;
        if (map.containsKey(prev)) {
            List<String> ls = map.get(prev);
            for (int i = 0; i < ls.size(); i++) {
                String st = ls.get(i);
                list.add(st);
                ls.remove(i);
                if(dfs(map, list, st, n)) return true;
                ls.add(i, st);
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
}

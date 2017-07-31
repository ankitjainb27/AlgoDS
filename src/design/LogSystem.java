package design;

import java.util.*;

/**
 * Date 02/07/17
 *
 * @author Ankit Jain
 */
public class LogSystem {

    public static void main(String[] args) {
        LogSystem logSystem = new LogSystem();
//        [1,"2017:01:01:23:59:59"],[2,"2017:01:02:23:59:59"],["2017:01:01:23:59:58","2017:01:02:23:59:58","Second"]]
        logSystem.put(1, "2017:01:01:23:59:59");
        logSystem.put(2, "2017:01:02:23:59:59");
//        logSystem.put(3, "2016:01:01:00:00:00");
        System.out.println(logSystem.retrieve("2017:01:01:23:59:58","2017:01:02:23:59:58","Second"));
//        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Month"));
//        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Day"));
//        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"));
//        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Minute"));
//        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Second"));

    }

    TreeMap<Integer, List<Integer>>[] map;
    String[] gran;

    LogSystem() {
        map = new TreeMap[6];
        for (int i = 0; i < 6; i++) {
            map[i] = new TreeMap<>();
        }
        gran = new String[6];
        gran = "Year:Month:Day:Hour:Minute:Second".split(":");
    }

    public void put(int id, String timestamp) {
        String[] strs = timestamp.split(":");
        for (int i = 0; i < strs.length; i++) {
            Integer val = Integer.valueOf(strs[i]);
            if (!map[i].containsKey(val)) {
                map[i].put(val, new ArrayList<>());
            }
            map[i].get(val).add(id);
        }
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> list = new ArrayList<>();
        int i;
        for (i = 0; i < gran.length; i++) {
            if (gra.equals(gran[i])) break;
        }
        Integer b = Integer.valueOf(s.split(":")[i]);
        Integer end = Integer.valueOf(e.split(":")[i]);
        if(b>end)
        {
            Integer temp = b;
            b = end;
            end = temp;
        }
        Map<Integer, List<Integer>> treeMap = map[i].subMap(b, true, end, true);
        Set<Integer> set = treeMap.keySet();
        for (Integer j : set) {
            list.addAll(treeMap.get(j));
        }
        Collections.sort(list);
        return list;
    }
}

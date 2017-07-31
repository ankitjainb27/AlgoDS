package design;

import java.util.*;

/**
 * Date 02/07/17
 *
 * @author Ankit Jain
 */
public class LogSystem1 {
    public static void main(String[] args) {
        LogSystem1 logSystem = new LogSystem1();
//        [1,"2017:01:01:23:59:59"],[2,"2017:01:02:23:59:59"],["2017:01:01:23:59:58","2017:01:02:23:59:58","Second"]]
        logSystem.put(1, "2017:01:01:23:59:59");
        logSystem.put(2, "2017:01:01:22:59:59");
        logSystem.put(3, "2016:01:01:00:00:00");
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"));
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"));
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Day"));
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"));
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Minute"));
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Second"));

    }

    TreeMap<Long, Integer> map;
    String[] gran;
    String[] min = {"0", "0", "0", "0", "0", "0"};
    String[] max = {"0", "12", "31", "23", "59", "59"};
    long[] vals = {31536000, 259200, 86400, 3600, 60, 1};

    public LogSystem1() {
        map = new TreeMap<>();
        gran = new String[6];
        gran = "Year:Month:Day:Hour:Minute:Second".split(":");
    }

    public void put(int id, String timestamp) {
        String[] strs = timestamp.split(":");
        long l = 0;
        for (int i = 0; i < strs.length; i++) {
            l += vals[i] * Integer.valueOf(strs[i]);
        }
        map.put(l, id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        int i;
        long l1 = 0;
        long l2 = 0;
        String[] str1 = s.split(":");
        String[] str2 = e.split(":");
        boolean flag = false;
        for (i = 0; i < gran.length; i++) {
            if (!flag) {
                l1 += vals[i] * Integer.valueOf(str1[i]);
                l2 += vals[i] * Integer.valueOf(str2[i]);
            } else {
                l1 += vals[i] * Integer.valueOf(min[i]);
                l2 += vals[i] * Integer.valueOf(max[i]);

            }
            if (gra.equals(gran[i])) {
                flag = true;
            }
        }
        Map<Long, Integer> treeMap = map.subMap(l1, true, l2, true);
        List<Integer> list = new ArrayList<>(treeMap.values());
        Collections.sort(list);
        return list;
    }
}

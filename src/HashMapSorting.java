import java.util.*;

/**
 * Date 11/06/17
 *
 * @author Ankit Jain
 */
public class HashMapSorting {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(43, 54);
        map.put(23, 23);
        map.put(3, 98);
        map.put(89, 3);
        map.put(10, 0);
        map.put(200, 2);
        sortByKey(map);
        sortByValue(map);
    }

    private static void sortByValue(HashMap<Integer, Integer> map) {
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        HashMap<Integer,Integer> map1 = new LinkedHashMap<>();
        Iterator it=  list.iterator();
        while (it.hasNext())
        {
            Map.Entry<Integer,Integer> me = (Map.Entry<Integer, Integer>) it.next();
            map1.put(me.getKey(),me.getValue());
        }
        System.out.println(map1);
    }

    private static void sortByKey(HashMap<Integer, Integer> map) {
        //Method 1 - Use TreeMap
        TreeMap treeMap = new TreeMap(map);
        Iterator it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> me = (Map.Entry<Integer, Integer>) it.next();
            System.out.println(me.getKey() + "--" + me.getValue());
        }

        //Method 2 - Use keySet()
        List list = new ArrayList(map.keySet());
        Collections.sort(list);
        HashMap<Integer,Integer> map1 = new LinkedHashMap<>();
        Iterator it1=  list.iterator();
        while (it1.hasNext())
        {
            Integer me = (Integer) it1.next();
            map1.put(me,map.get(me));
        }
        System.out.println(map1);
    }
}

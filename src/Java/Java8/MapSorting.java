package Java.Java8;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Date 26/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-how-to-sort-a-map/
 */
public class MapSorting {
    public static void main(String[] args) {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        Map<String, Integer> sortedMap1 = unsortMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(o, n)->o, LinkedHashMap::new ));
        System.out.println("Sorted...");
        System.out.println(sortedMap1);

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        unsortMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));
        System.out.println("Sorted...");
        System.out.println(sortedMap);

    }
}

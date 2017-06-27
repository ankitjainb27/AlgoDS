package Java.Java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Date 26/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
 */
public class GroupingBy {
    public static void main(String[] args) {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        Map<String, Long> map = items.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        System.out.println(map);
        Map<String, Long> sortedMap = new LinkedHashMap<>();
        Map<String, Long> finalSortedMap = sortedMap;
        map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue()
                .reversed()).forEachOrdered(e -> finalSortedMap.put(e.getKey(), e.getValue()));
        System.out.println(sortedMap);

    }
}

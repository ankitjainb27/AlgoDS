package Java.Java8;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Date 27/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-filter-a-map-examples/
 */
public class FilterMapExample {
    public static void main(String[] args) {
        FilterMapExample filterMapExample = new FilterMapExample();
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 9);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        unsortMap.entrySet().stream().filter(e -> e.getValue().equals(9)).map(e -> e.getKey()).collect(Collectors.toList()).forEach(System.out::println);
        String result = unsortMap.entrySet().stream().filter(e -> e.getValue().equals(9)).map(e -> e.getKey()).collect(Collectors.joining());
        System.out.println(result);
        Map<String, Integer> filterMap = unsortMap.entrySet().stream().filter(e -> e.getValue().equals(9)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(filterMap);
    }
}

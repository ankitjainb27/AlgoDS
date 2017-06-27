package Java.Java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Date 27/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-convert-map-to-list/
 */
public class ConvertMapToList {
    public static void main(String[] args) {
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
        List<String> keys = unsortMap.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(keys);
        List<String> keys1 = unsortMap.keySet().stream().collect(Collectors.toList());
        System.out.println(keys1);
        List<Integer> values = unsortMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        System.out.println(values);
        List<Integer> values1 = unsortMap.values().stream().collect(Collectors.toList());
        System.out.println(values1);
        List<Integer> values2 = unsortMap.values().stream().filter(integer -> (integer & 1) == 0).collect(Collectors.toList());
        System.out.println(values2);


        Map<Integer, String> map = new HashMap<>();
        map.put(20, "apple");
        map.put(30, "orange");
        map.put(30, "banana");
        map.put(60, "watermelon");
        map.put(50, "dragonfruit");

        List<Integer> keysM = new ArrayList<>(map.keySet());
        keysM.forEach(e -> System.out.print(e + ", "));

        List<String> keysV = new ArrayList<String>(map.values());
        keysV.forEach(e -> System.out.println(e + ",  "));

        List<Integer> resultKey = new ArrayList<>();
        List<String> resultValue = map.entrySet().stream().sorted(Map.Entry.<Integer, String>comparingByKey().reversed()).peek(e -> resultKey.add(e.getKey())).map(x -> x.getValue()).collect(Collectors.toList());
        System.out.println(resultKey);
        System.out.println(resultValue);
    }
}

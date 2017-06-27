package Java.Java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date 26/06/17
 *
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-foreach-examples/
 */
public class ForEachExample {
    public static void main(String[] args) {
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        items.forEach((k,v)-> System.out.println("Item: " + k + "   Count: " + v));

        List<String> items1 = new ArrayList<>();
        items1.add("A");
        items1.add("B");
        items1.add("C");
        items1.add("D");
        items1.add("E");
        System.out.println("Using Lambda");
        items1.forEach(i -> System.out.println("item: " + i));
        System.out.println("Using Method Reference");
        items1.forEach(System.out::println);
        System.out.println("Using streams");
        items1.stream().forEach(System.out::println);
    }
}

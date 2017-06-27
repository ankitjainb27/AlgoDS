package Java.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Date 26/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-streams-map-examples/
 * http://www.mkyong.com/java8/java-8-streams-filter-examples/
 */
public class StreamExamples {
    public static void main(String[] args) {
        Stream<String> language = Stream.of("java", "python", "node", null, "ruby", null, "php");
//        List<String> list = language.filter(i -> i != null).collect(Collectors.toList());
        List<String> list = language.filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(list);

        String[] array = {"a", "b", "c", "d", "e"};
        System.out.println("Using Arrays.stream");
        Stream<String> stringStream = Arrays.stream(array);
        stringStream.forEach(System.out::println);

        System.out.println("Using Stream.of");
        Stream<String> stringStream1 = Stream.of(array);
        stringStream1.forEach(System.out::println);

        int[] intArray = {1, 2, 3, 4, 5};

        // 1. Arrays.stream -> IntStream
        System.out.println("Using Arrays.stream");
        IntStream intStream1 = Arrays.stream(intArray);
        intStream1.forEach(x -> System.out.println(x));

        // 2. Stream.of -> Stream<int[]>
        Stream<int[]> temp = Stream.of(intArray);

        // Cant print Stream<int[]> directly, convert / flat it to IntStream
        System.out.println("Using Stream.of");
        IntStream intStream2 = temp.flatMapToInt(x -> Arrays.stream(x));
        intStream2.forEach(x -> System.out.println(x));


    }
}

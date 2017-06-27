package Java.Java8;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Date 26/06/17
 *
 * @author Ankit Jain
 */
public class StreamReuseExample {
    public static void main(String[] args) {
        String[] array = {"a", "b", "c", "d", "e"};
        Stream<String> stream = Arrays.stream(array);

        // loop a stream
        stream.forEach(x -> System.out.println(x));

        // reuse it to filter again! throws IllegalStateException
/*
        long count = stream.filter(x -> "b".equals(x)).count();
        System.out.println(count);
*/

        System.out.println("Using Stream supplier");
        Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);

        //get new stream
        streamSupplier.get().forEach(System.out::println);

        //get another new stream
        long count1 = streamSupplier.get().filter(x -> !"b".equals(x)).count();
        System.out.println(count1);
    }
}





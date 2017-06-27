package Java.Java8;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Date 27/06/17
 *
 * @author Ankit Jain
 *         http://www.mkyong.com/java/java-check-if-array-contains-a-certain-value/
 */
public class ContainsInArray {
    public static void main(String[] args) {
        String[] alphabet = new String[]{"A", "B", "C"};
        System.out.println(Arrays.stream(alphabet).filter(e -> e.equals("A")).count() > 0);

        System.out.println(Arrays.stream(alphabet).anyMatch(e -> e.equals("A")));

        System.out.println(Arrays.stream(alphabet).filter(e -> e.charAt(0) > 'A').findAny());

        int[] number = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(IntStream.of(number).anyMatch(x -> x == 4));
    }
}

package Java.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date 27/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java/java-how-to-convert-a-primitive-array-to-list/
 */
public class PrimitiveArrayToList {
    public static void main(String[] args) {
        int[] number = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list = Arrays.stream(number).boxed().collect(Collectors.toList());
        System.out.println(list);

        int[] number1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
// No, the return type is not what we want
        List<int[]> ints = Arrays.asList(number1);
        System.out.println(number1);
    }
}

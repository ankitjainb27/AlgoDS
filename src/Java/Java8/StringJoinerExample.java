package Java.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Date 27/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-stringjoiner-example/
 */
public class StringJoinerExample {
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(", ");
        sj.add("asdasd");
        sj.add("2");
        sj.add("dsdsadas");
        sj.add("asdasd");
        System.out.println(sj.toString());

        sj = new StringJoiner(", ", "{", "}");
        sj.add("asdasd");
        sj.add("2");
        sj.add("dsdsadas");
        sj.add("asdasd");
        System.out.println(sj.toString());

        System.out.println(String.join("-", "5", "10", "2017"));

        List<String> list = Arrays.asList("dsdsa", "331", "ccccd");
        System.out.println(String.join(", ", list));

        List<String> list1 = Arrays.asList(
                "Dragon Blaze",
                "Angry Bird",
                "Candy Crush");

        //{Dragon Blaze, Angry Bird, Candy Crush}
        String result = list1.stream()
                .collect(Collectors.joining(", ", "{", "}"));
        System.out.println(result);
    }
}

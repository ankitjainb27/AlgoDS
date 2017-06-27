package Java.Java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 */
public class Example1 {
    public static void main(String[] args) {
        Example1 example1 = new Example1();
        List<Person> people = Arrays.asList(
                new Person("Ankit", "Jain", 12),
                new Person("Sal", "Alsa", 32),
                new Person("clkdms", "klm", 532),
                new Person("rlw", "aslkd", 32)
        );

        //Java 7
        System.out.println("Java 7");
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastname.toLowerCase().compareTo(o2.lastname.toLowerCase());
            }
        });
        System.out.println(people);
        for (Person p : people) {
            if (p.lastname.toLowerCase().startsWith("a"))
                System.out.println(p);
        }

        System.out.println();
        System.out.println("Java 8");

        people.sort(Comparator.comparing(o -> o.lastname.toLowerCase()));
        people.forEach(System.out::println);
        System.out.println("----------");
        List<Person> people1 = people.stream().filter(person -> person.lastname.toLowerCase().startsWith("a")).collect(Collectors.toList());
        people1.forEach(System.out::println);
        System.out.println("----------");
        perform(people, p -> true, p -> System.out.println(p.lastname));
        System.out.println("----------");
        perform(people, p -> p.lastname.toLowerCase().startsWith("a"), p -> System.out.println(p.lastname));
    }

    private static void perform(List<Person> people, Predicate<Person> cond, Consumer<Person> consumer) {
        for (Person p : people) {
            if (cond.test(p))
                consumer.accept(p);
        }
    }
}

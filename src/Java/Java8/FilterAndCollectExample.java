package Java.Java8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date 26/06/17
 *
 * @author Ankit Jain
 */
public class FilterAndCollectExample {
    public static void main(String[] args) {
        FilterAndCollectExample filterAndCollectExample = new FilterAndCollectExample();
        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        lines.stream().filter(i -> !i.equals("mkyong")).forEach(System.out::println);

        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
        Person jack = persons.stream().filter(i -> i.name.equals("jack")).findAny().orElse(null);
        String jack1 = persons.stream().filter(i -> i.name.equals("jack")).map(i -> i.name).findAny().orElse(null);
        List<String> names = persons.stream().map(i -> i.name).collect(Collectors.toList());
        List<String> namesUpprercase = persons.stream().map(i -> i.name.toUpperCase()).collect(Collectors.toList());

        System.out.println(jack);
        System.out.println(jack1);
        System.out.println("\nAll Names");
        names.forEach(System.out::println);
        namesUpprercase.forEach(System.out::println);

        List<String> alpha = Arrays.asList("a", "b", "c", "d");
        List<String> alphaUpper = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(alphaUpper);

        List<Staff> staff = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );

        List<StaffPublic> result = staff.stream().map(i -> new StaffPublic(i.name, i.age, null)).collect(Collectors.toList());
        System.out.println(result);
    }


    public static class Person {

        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "name: " + name + "   " + "age: " + age;
        }

        //gettersm setters, toString
    }

    public static class Staff {

        private String name;
        private int age;
        private BigDecimal salary;

        public Staff(String name, int age, BigDecimal salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
        //...
    }

    public static class StaffPublic {

        private String name;
        private int age;
        private String extra;

        public StaffPublic(String name, int age, String st) {
            this.name = name;
            this.age = age;
            this.extra = st;
        }

        @Override
        public String toString() {

            return
                    "name: " + name + "  " +
                            "age: " + age + "  " +
                            "extra: " + extra;
        }

        //...
    }
}

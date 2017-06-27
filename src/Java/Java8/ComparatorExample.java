package Java.Java8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Date 26/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-lambda-comparator-example/
 */
public class ComparatorExample {
    public static void main(String[] args) {
        List<Developer> listDevs = getDevelopers();
        System.out.println("Before Sort");
        listDevs.forEach(System.out::println);
        Comparator<Developer> developerComparator = (o1, o2) -> o1.age - o2.age;
        listDevs.sort(developerComparator);
        System.out.println("After Sort Ascending");
        listDevs.forEach(System.out::println);
        System.out.println("After Sort Descending");
        listDevs.sort(developerComparator.reversed());
        listDevs.forEach(System.out::println);
    }

    private static List<Developer> getDevelopers() {

        List<Developer> result = new ArrayList<Developer>();

        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));

        return result;

    }

    public static class Developer {
        public String name;
        public BigDecimal salary;
        public int age;

        public Developer(String name, BigDecimal salary, int age) {
            this.name = name;
            this.salary = salary;
            this.age = age;
        }

        @Override
        public String toString() {
            return "name: " + name + "  " +
                    "salary: " + salary + "  " +
                    "age: " + age;
        }
    }
}

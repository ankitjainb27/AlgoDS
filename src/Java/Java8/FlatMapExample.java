package Java.Java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Date 27/06/17
 *
 * @author Ankit Jain
 *         http://www.mkyong.com/java8/java-8-flatmap-example/
 */
public class FlatMapExample {
    public static void main(String[] args) {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        Stream<String[]> temp = Arrays.stream(data);
        Stream<String> stream = temp.flatMap(e -> Arrays.stream(e));
        Stream<String> result = stream.filter(e -> e.equals("a"));
        result.forEach(System.out::println);

        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);

        List<String> books = list.stream().map(e -> e.getBook()).flatMap(e -> e.stream()).distinct().collect(Collectors.toList());
        System.out.println(books);


        int[] intArray = {1, 2, 3, 4, 5, 6};
        Stream<int[]> stream1 = Stream.of(intArray);
        IntStream intStream = stream1.flatMapToInt(Arrays::stream);
        intStream.forEach(System.out::println);

    }

    public static class Student {

        private String name;
        private Set<String> book;

        public void addBook(String book) {
            if (this.book == null) {
                this.book = new HashSet<>();
            }
            this.book.add(book);
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<String> getBook() {
            return book;
        }

        //getters and setters

    }
}

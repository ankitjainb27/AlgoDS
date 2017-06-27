package Java.Java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Date 26/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java8/java-8-convert-list-to-map/
 */
public class ListToMap {
    public static void main(String[] args) {
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "mkyong.com", 1));

        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getName));
        System.out.println(map);

        list.add(new Hosting(6, "linode.com", 100000)); // new line
        /*Note
                (oldValue, newValue) -> oldValue ==> If the key is duplicated, do you prefer oldKey or newKey?
        */
        Map<Integer, String> mapWithDuplicate = list.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getName, (o, n) -> o));
        System.out.println(mapWithDuplicate);

        Map sortedMap = list.stream()
                .sorted(Comparator.comparingLong(Hosting::getWebsites).reversed())
                .collect(Collectors.toMap(
                                Hosting::getName, Hosting::getWebsites, // key = name, value = websites
                                (oldValue, newValue) -> oldValue,       // if same key, take the old key
                                LinkedHashMap::new                      // returns a LinkedHashMap, keep order
                        ));
        System.out.println("Sorted : " + sortedMap);
    }

    public static class Hosting {

        private int Id;
        private String name;
        private long websites;

        public Hosting(int id, String name, long websites) {
            Id = id;
            this.name = name;
            this.websites = websites;
        }

        public int getId() {
            return Id;
        }

        public String getName() {
            return name;
        }

        public long getWebsites() {
            return websites;
        }

        //getters, setters and toString()
    }
}

package Java.Java8;

import java.util.Optional;

/**
 * Date 27/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/tutorials/java-8-tutorials/
 */
public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> gender = Optional.of("MALE");
        Optional<String> emptyGender = Optional.empty();
        String ans1 = "Yes";
        String ans2 = null;

        System.out.println("Non-Empty Optional: " + gender);
        System.out.println("Non-Empty Optional: " + gender.get());
        System.out.println("Non-Empty Optional: " + Optional.empty());

        System.out.println(Optional.ofNullable(ans1));
        System.out.println(Optional.ofNullable(ans2));

//        System.out.println(Optional.of(ans2));
        System.out.println(Optional.ofNullable(ans2).orElse("daads"));
        System.out.println(emptyGender.isPresent()?emptyGender:"NOT PRESENT");
        Optional.ofNullable(ans2).ifPresent(a-> System.out.println(a));
        System.out.println(gender.filter(g->g.equals("male")));
        System.out.println(gender.filter(g->g.equals("MALE")));
        System.out.println(gender.filter(g->g.equalsIgnoreCase("MALE")));
        System.out.println(emptyGender.filter(g->g.equalsIgnoreCase("MALE")));

        if (gender.isPresent()) {
            System.out.println("Value available.");
        } else {
            System.out.println("Value not available.");
        }

        gender.ifPresent(g -> System.out.println("In gender Option, value available."));

        //condition failed, no output print
        emptyGender.ifPresent(g -> System.out.println("In emptyGender Option, value available."));


        System.out.println(gender.orElse("<N/A>")); //MALE
        System.out.println(emptyGender.orElse("<N/A>")); //<N/A>

        System.out.println(gender.orElseGet(() -> "<N/A>")); //MALE
        System.out.println(emptyGender.orElseGet(() -> "<N/A>")); //<N/A>


    }
}

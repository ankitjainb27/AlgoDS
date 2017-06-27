package Java.Java8;

import java.util.Random;

/**
 * Date 27/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java/java-generate-random-integers-in-a-range/
 */
public class RandomNumberGenerator {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println(getRandomNumberInRange(5, 10));
            System.out.println(5 + new Random().nextInt(6));
            System.out.println(getRandomNumberInRange1(5, 10));
            System.out.println(new Random().ints(5,10+1).findFirst().getAsInt());
            System.out.println();
        }

        new Random().ints(10,5,11).forEach(System.out::println);
    }

    private static int getRandomNumberInRange1(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}

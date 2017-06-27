package Java.Java8;

/**
 * Date 27/06/17
 * @author Ankit Jain
 * http://www.mkyong.com/java/convert-string-to-char-array-in-java/
 */
public class StringToCharArray {
    public static void main(String[] args) {
        String password = "password123";
        password.chars().mapToObj(e -> (char)e).forEach(System.out::println);
    }
}

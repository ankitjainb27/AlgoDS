package Java.Java8;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 */
public class Person {
    public String firstname;
    public String lastname;
    public int age;

    public Person(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    @Override
    public String toString() {
        return
                "firstname: " + firstname + "\n" +
                        "lastname: " + lastname + "\n" +
                        "age: " + age;
    }
}

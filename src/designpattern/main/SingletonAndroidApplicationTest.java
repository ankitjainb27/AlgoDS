package designpattern.main;

/**
 * Date 07/07/17
 *
 * @author Ankit Jain
 */

public class SingletonAndroidApplicationTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(SingletonAndroidApplication.getInstance().toString());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(SingletonAndroidApplication1.getInstance().toString());
        }
    }
}

//Uses Lazy instantiation
class SingletonAndroidApplication {

    private static SingletonAndroidApplication singletonAndroidApplication;

    private SingletonAndroidApplication() {
    }

    public static SingletonAndroidApplication getInstance() {
        if (singletonAndroidApplication == null)
            singletonAndroidApplication = new SingletonAndroidApplication();
        return singletonAndroidApplication;
    }
}

//Uses Early instantiation
class SingletonAndroidApplication1 {

    private static final SingletonAndroidApplication1 singletonAndroidApplication = new SingletonAndroidApplication1();

    private SingletonAndroidApplication1() {
    }

    public static SingletonAndroidApplication1 getInstance() {
        return singletonAndroidApplication;
    }
}

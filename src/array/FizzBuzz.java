package array;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25/08/17 at 10:27 AM
 *
 * @author Ankit Jain
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<String>(n);
        int num3 = 3;
        int num5 = 5;
        for (int i = 1; i <= n; i++) {
            if (i == num3 && num3 == num5) {
                list.add("FizzBuzz");
                num3 += 3;
                num5 += 5;
            } else if (i == num3) {
                list.add("Fizz");
                num3 += 3;
            } else if (i == num5) {
                list.add("Buzz");
                num5 += 5;
            } else
                list.add(String.valueOf(i));
        }
        return list;
    }

    List<String> answer = new AbstractList<String>() {
        public String get(int i) {
            i++;
            return i % 15 == 0 ? "FizzBuzz" :
                    i % 5 == 0 ? "Buzz" :
                            i % 3 == 0 ? "Fizz" :
                                    Integer.toString(i);
        }

        public int size() {
            return Integer.MAX_VALUE;
        }
    };

    public List<String> fizzBuzz1(int n) {
        return answer.subList(0, n);
    }
}

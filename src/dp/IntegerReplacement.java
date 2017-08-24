package dp;

import java.util.HashMap;

/**
 * Created on 18/08/17 at 2:30 PM
 *
 * @author Ankit Jain
 */
public class IntegerReplacement {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new IntegerReplacement().integerReplacement(2147483646));
    }



    public int integerReplacement(int n) {
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(1L, 0);
        return irUtil(n, map);
    }

    public int irUtil(long n, HashMap<Long, Integer> map) {
        if (map.containsKey(n)) return map.get(n);
        int min = Integer.MAX_VALUE;
        if (n % 2 == 0) {
            min = Math.min(min, 1 + irUtil(n / 2, map));
        } else {
            min = Math.min(min, 1 + irUtil(n + 1, map));
            min = Math.min(min, 1 + irUtil(n - 1, map));
        }
        map.put(n, min);
        return min;
    }
}

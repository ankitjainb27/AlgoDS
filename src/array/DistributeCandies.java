package array;

import java.util.HashSet;

/**
 * Created on 25/08/17 at 10:20 AM
 *
 * @author Ankit Jain
 */
public class DistributeCandies {
    public int distributeCandies(int[] candies) {
        boolean[] set = new boolean[200001];
        int count = 0;
        for (int i : candies)
            if(!set[i+100000])
            {
                count++;
                set[i+100000] = true;
            }
        return Math.min(count, candies.length / 2);
    }

    public int distributeCandies1(int[] candies) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : candies)
            set.add(i);
        return Math.min(set.size(), candies.length / 2);
    }
}

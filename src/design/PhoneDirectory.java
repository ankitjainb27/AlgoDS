package design;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Date 01/07/17
 *
 * @author Ankit Jain
 */
public class PhoneDirectory {

    //My Solution
    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    LinkedList<Integer> list;
    HashSet<Integer> set;
    int max;
    int i = 0;

    public PhoneDirectory(int maxNumbers) {
        list = new LinkedList<>();
        set = new HashSet<>();
        max = maxNumbers;
        if (maxNumbers > 0)
            list.add(i++);
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (list.size() == 0) return -1;
        Integer val = list.pollFirst();
        set.add(val);
        if (i < max)
            list.add(i++);
        return val;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return !set.contains(number);
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        if (!check(number)) {
            set.remove(number);
            list.add(number);

        }
    }
}

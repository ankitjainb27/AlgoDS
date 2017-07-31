package design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Date 01/07/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/insert-delete-getrandom-o1/#/description
 */
public class RandomizedSet {

    /**
     * Initialize your data structure here.
     */
    HashMap<Integer, Integer> map;
    LinkedList<Integer> list;
    Random rand;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new LinkedList<>();
        rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        int i = list.size();
        list.add(val);
        map.put(val, i);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int i = map.get(val);
            map.remove(val);
            if (i != list.size() - 1) {
                list.set(i, list.get(list.size() - 1));
                map.put(list.get(list.size() - 1), i);

            }
            list.remove(list.size() - 1);

            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int i = (int) (rand.nextInt(list.size()));
        return list.get(i);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
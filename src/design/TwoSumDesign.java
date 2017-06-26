package design;

import java.util.HashMap;

/**
 * Date 25/06/17
 * @author Ankit Jain
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/#/description
 */
public class TwoSumDesign {

    public static void main(String[] args) {
        TwoSum obj = new TwoSum();
        obj.add(0);
        boolean param_2 = obj.find(0);
        System.out.println(param_2);
    }

    public static class TwoSum {

        /**
         * Initialize your data structure here.
         */
        HashMap<Integer, Integer> map;

        public TwoSum() {
            map = new HashMap<>();
        }

        /**
         * Add the number to an internal data structure..
         */
        public void add(int number) {
            if (map.containsKey(number))
                map.put(number, map.get(number) + 1);
            else
                map.put(number, 1);
        }

        /**
         * Find if there exists any pair of numbers which sum is equal to the value.
         */
        public boolean find(int value) {
            int right;
            for (Integer key : map.keySet()) {
                right = value - key;
                if ((key == right && map.get(key) > 1) || (key != right && map.containsKey(right)))
                    return true;
            }
            return false;
        }
    }
}

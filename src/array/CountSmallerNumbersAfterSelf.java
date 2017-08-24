package array;

import java.util.*;

/**
 * Created on 22/08/17 at 5:20 AM
 *
 * @author Ankit Jain
 */
public class CountSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        System.out.println(new CountSmallerNumbersAfterSelf().countSmaller(new int[]{3, 2, 2, 6, 1}));
    }

    public List<Integer> countSmaller(int[] nums) {
        TreeSet<Integer> map = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nums[o1] == nums[o2] ? -1 : nums[o1] - nums[o2];
            }
        });
        Integer[] list = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            list[i] = map.headSet(i).size();
            map.add(i);
        }
        return Arrays.asList(list);
    }

}

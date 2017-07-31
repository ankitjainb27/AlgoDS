package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Date 28/07/17
 *
 * @author Ankit Jain
 */
public class FindDuplicate {
    public static void main(String[] args) {
        FindDuplicate findDuplicate = new FindDuplicate();
        System.out.println(findDuplicate.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    //Fastest
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] set = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            set[nums[i] - 1]++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (set[i] > 1) list.add(i + 1);
        }
        return list;
    }

    //Faster
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                if (!set.contains(Math.abs(nums[i]))) {
                    list.add(Math.abs(nums[i]));
                    set.add(Math.abs(nums[i]));
                }
            } else
                nums[index] = nums[index] * -1;
        }
        return list;
    }

    //Faster
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                list.add(Math.abs(nums[i]));
            } else
                nums[index] = nums[index] * -1;
        }
        return list;
    }


}

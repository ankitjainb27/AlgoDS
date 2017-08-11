package recursion;

import java.util.HashMap;

/**
 * Date 03/08/17
 *
 * @author Ankit Jain
 */
public class TargetSum {
    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
        System.out.println(1<<1);
        System.out.println(targetSum.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public int findTargetSumWays(int[] nums, int S) {
        return ftswUtil(nums, S, 0);
    }

    private int ftswUtil(int[] nums, int S, int index) {
        if (index == nums.length) {
            if (S == 0) {
                return 1;
            } else return 0;
        }
        int sum = 0;
        sum += ftswUtil(nums, S - nums[index], index + 1);
        sum += ftswUtil(nums, S + nums[index], index + 1);
        return sum;
    }

    public int findTargetSumWays1(int[] nums, int S) {
        int[] val = new int[nums.length];
        HashMap<String, Integer> map = new HashMap<>();
        return ftswUtil1(nums, S, 0, map);
    }

    private int ftswUtil1(int[] nums, int S, int index, HashMap<String, Integer> map) {
        if (index == nums.length) {
            if (S == 0) {
                return 1;
            } else return 0;
        }
        int sum = 0;
        String key1 = (S - nums[index]) + "-" + index;
        String key2 = (S + nums[index]) + "-" + index;
        if (!map.containsKey(key1)) {
            map.put(key1, ftswUtil1(nums, S - nums[index], index + 1, map));
        }
        sum += map.get(key1);
        if (!map.containsKey(key2)) {
            map.put(key2, ftswUtil1(nums, S + nums[index], index + 1, map));
        }
        sum += map.get(key2);
        return sum;
    }
}

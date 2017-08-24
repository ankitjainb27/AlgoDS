package dp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created on 14/08/17 at 10:21 AM
 *
 * @author Ankit Jain
 */
public class CombinationSumIV {

    //Brute Force - TLE
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        return dfs(nums, target);
    }

    private int dfs(int[] nums, int target) {
        if (target < 0) return 0;
        if (target == 0)
            return 1;
        int count = 0;
        for (int i = 0; i < nums.length; i++)
            count += dfs(nums, target - nums[i]);
        return count;
    }

    //Memoization with sorting - AC
    public int combinationSum41(int[] nums, int target) {
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<>();
        return dfs(nums, target, map);
    }

    private int dfs(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (target < 0) return 0;
        if (target == 0)
            return 1;
        if (map.containsKey(target)) return map.get(target);
        int count = 0;
        for (int i = 0; i < nums.length; i++)
            count += dfs(nums, target - nums[i], map);
        map.put(target, count);
        return count;
    }

    //Memoization w/o sorting - AC
    public int combinationSum42(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return dfs(nums, target, map);
    }

    public int combinationSum43(int[] nums, int target) {
        Arrays.sort(nums);
        int[] map = new int[target + 1];
        Arrays.fill(map, -1);
        return dfs(nums, target, map);
    }

    private int dfs(int[] nums, int target, int[] map) {
        if (target < 0) return 0;
        if (target == 0)
            return 1;
        if (map[target] != -1) return map[target];
        int count = 0;
        for (int i = 0; i < nums.length; i++)
            count += dfs(nums, target - nums[i], map);
        map[target] = count;
        return count;
    }

    //Bottom-Up DP Approach
    public int combinationSum44(int[] nums, int target) {
        Arrays.sort(nums);
        int[] map = new int[target+1];
        map[0] = 1;
        for(int j = 1; j<=target;j++)
            for(int i = 0; i<nums.length;i++)
                if(nums[i]<=j)
                    map[j]+=map[j-nums[i]];
                else break;
        return map[target];
    }
}

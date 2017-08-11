package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date 06/08/17
 *
 * @author Ankit Jain
 */
public class IncreasingSubsequences {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        System.out.println(new IncreasingSubsequences().findSubsequences(nums));
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> out = new ArrayList<List<Integer>>();
        calculate(nums, out, new ArrayList<Integer>(), 0, Integer.MIN_VALUE);
        return out;

    }

    public void calculate(int[] nums, List<List<Integer>> out, List<Integer> temp, int start, int min) {
        if (temp.size() >= 2)
            out.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            if (!isDuplicate(nums, start, i) && nums[i] >= min) {
                temp.add(nums[i]);
                calculate(nums, out, temp, i + 1, nums[i]);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public boolean isDuplicate(int[] nums, int s, int e) {

        for (int i = s; i < e; i++) {
            if (nums[i] == nums[e])
                return true;
        }

        return false;
    }

    public List<List<Integer>> findSubsequences1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> sub = new ArrayList<Integer>();
        fsUtil1(nums, list, sub, 0);
        return list;
    }

    private void fsUtil1(int[] nums, List<List<Integer>> list, List<Integer> sub, int index) {
        if (sub.size() > 1)
            list.add(new ArrayList(sub));
        HashSet<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i])) continue;
            if (sub.size() == 0 || (sub.size() > 0 && nums[i] >= sub.get(sub.size() - 1))) {
                used.add(nums[i]);
                sub.add(nums[i]);
                fsUtil1(nums, list, sub, i + 1);
                sub.remove(sub.size() - 1);
            }
        }
    }

    public List<List<Integer>> findSubsequences2(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> sub = new ArrayList<Integer>();
        fsUtil(nums, res, sub, 0);
        list.addAll(res);
        return list;
    }

    private void fsUtil(int[] nums, Set<List<Integer>> list, List<Integer> sub, int index) {
        if (sub.size() > 1)
            list.add(new ArrayList(sub));
        for (int i = index; i < nums.length; i++) {
            if (sub.size() == 0 || (sub.size() > 0 && nums[i] >= sub.get(sub.size() - 1))) {
                sub.add(nums[i]);
                fsUtil(nums, list, sub, i + 1);
                sub.remove(sub.size() - 1);
            }
        }
    }
}

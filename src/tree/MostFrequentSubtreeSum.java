package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date 09/08/17
 *
 * @author Ankit Jain
 */
public class MostFrequentSubtreeSum {
    int max = Integer.MIN_VALUE;

    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        postOrder(root, map, list);
        int[] res = new int[list.size()];
        int j = 0;
        for (Integer i : list)
            res[j++] = i;
        return res;
    }

    private int postOrder(TreeNode root, HashMap<Integer, Integer> map, List<Integer> list) {
        if (root == null)
            return 0;
        int left = postOrder(root.left, map, list);
        int right = postOrder(root.right, map, list);
        int sum = root.val + left + right;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        int freq = map.get(sum);
        if (freq >= max) {
            if (freq != max) {
                max = freq;
                list.clear();
            }
            list.add(sum);
        }
        return sum;
    }
}

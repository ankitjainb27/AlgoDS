package tree.bst;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 06/08/17
 *
 * @author Ankit Jain
 * https://leetcode.com/contest/leetcode-weekly-contest-44/problems/two-sum-iv-input-is-a-bst/
 */
public class TwoSumIV {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            long sum = list.get(l) + list.get(r);
            if (sum == k) return true;
            else if (sum < k) l++;
            else r--;
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}

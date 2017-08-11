package tree;

/**
 * Date 06/08/17
 *
 * @author Ankit Jain
 * https://leetcode.com/contest/leetcode-weekly-contest-44/problems/maximum-binary-tree/
 */
public class MaximumBinaryTree {
    public static void main(String[] args) {

    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return cmbtUtil(nums, 0, nums.length - 1);
    }

    private TreeNode cmbtUtil(int[] nums, int l, int r) {
        if (l > r) return null;
        int i = findMax(nums, l, r);
        TreeNode node = new TreeNode(nums[i]);
        node.left = cmbtUtil(nums, l, i - 1);
        node.right = cmbtUtil(nums, i + 1, r);
        return node;
    }

    private int findMax(int[] nums, int l, int r) {
        int max = Integer.MIN_VALUE;
        int val = l;
        for (int i = l; i <= r; i++) {
            if (nums[i] > max) {
                val = i;
                max = nums[i];
            }
        }
        return val;
    }
}

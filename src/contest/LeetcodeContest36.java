package contest;

import tree.TreeNode;

import java.util.Arrays;

/**
 * Date 11/06/17
 *
 * @author Ankit Jain
 */
public class LeetcodeContest36 {
    public static void main(String[] args) {
        LeetcodeContest36 contest36 = new LeetcodeContest36();
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(3);
        head1.right = new TreeNode(2);
        head1.left.left = new TreeNode(5);

        TreeNode head2 = new TreeNode(2);
        head2.left = new TreeNode(1);
        head2.right = new TreeNode(3);
        head2.left.right = new TreeNode(4);
        head2.right.right = new TreeNode(7);

//        TreeNode a = contest36.mergeTrees(head1, head2);
//        System.out.println(a);

        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(contest36.triangleNumber(nums));
    }

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            count = findCount(i, l, r, nums, count);
        }
        return count;
    }

    private int findCount(int i, int l, int r, int[] nums, int count) {
        if (l >= r)
            return count;
        if (nums[i] + nums[l] > nums[r]) {
            System.out.println(nums[i] + "--" + nums[l] + "--" + nums[r]);
            count++;
        } else {
            count = findCount(i, l + 1, r, nums, count);
            count = findCount(i, l, r - 1, nums, count);
        }
        return count;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode head;
        if (t1 == null && t2 == null)
            return null;
        if (t1 != null && t2 != null) {
            head = new TreeNode(t1.val + t2.val);
        } else if (t1 != null)
            head = new TreeNode(t1.val);
        else
            head = new TreeNode(t2.val);
        if (t1 != null && t2 != null) {
            preorderUtil(t1.left, t2.left, head, true);
            preorderUtil(t1.right, t2.right, head, false);
        } else if (t1 != null) {
            preorderUtil(t1.left, null, head, true);
            preorderUtil(t1.right, null, head, false);
        } else {
            preorderUtil(null, t2.left, head, true);
            preorderUtil(null, t2.right, head, false);
        }
        return head;
    }

    private void preorderUtil(TreeNode t1, TreeNode t2, TreeNode parent, boolean isLeft) {
        if (t1 == null && t2 == null)
            return;
        TreeNode treeNode;
        if (t1 != null && t2 != null)
            treeNode = new TreeNode(t1.val + t2.val);
        else if (t1 != null)
            treeNode = new TreeNode(t1.val);
        else
            treeNode = new TreeNode(t2.val);
        if (isLeft)
            parent.left = treeNode;
        else
            parent.right = treeNode;
        if (t1 != null && t2 != null) {
            preorderUtil(t1.left, t2.left, treeNode, true);
            preorderUtil(t1.right, t2.right, treeNode, false);
        } else if (t1 != null) {
            preorderUtil(t1.left, null, treeNode, true);
            preorderUtil(t1.right, null, treeNode, false);
        } else {
            preorderUtil(null, t2.left, treeNode, true);
            preorderUtil(null, t2.right, treeNode, false);
        }
    }
}



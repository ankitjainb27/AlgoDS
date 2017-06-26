package tree.bst;

import tree.TreeNode;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/closest-binary-search-tree-value/#/description
 */
public class ClosestBSTValue {
    public static void main(String[] args) {
        ClosestBSTValue closestBSTValue = new ClosestBSTValue();
        TreeNode treeNode1 = new TreeNode(0);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(8);
//        treeNode4.left = treeNode2;
//        treeNode4.right = treeNode6;
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;
        treeNode6.left = treeNode5;
        treeNode6.right = treeNode7;
        System.out.println(closestBSTValue.closestValue(treeNode4, 1));

    }

    public int closestValue(TreeNode root, double target) {
        int succ = -1;
        int pre = -1;
        while (root != null) {
            if (root.val > target) {
                succ = root.val;
                root = root.left;
            } else {
                pre = root.val;
                root = root.right;
            }
        }
        return ((succ == -1 && pre != -1) || (succ != -1 && pre != -1 && succ - target > target - pre)) ? pre : succ;
    }

}

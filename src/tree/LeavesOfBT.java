package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/find-leaves-of-binary-tree/#/description
 */
public class LeavesOfBT {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(5);
        LeavesOfBT leavesOfBT = new LeavesOfBT();
        System.out.println(leavesOfBT.findLeaves(root));

        TreeNode treeNode1 = new TreeNode(0);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(8);
        treeNode4.left = treeNode2;
//        treeNode4.right = treeNode6;
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;
        treeNode6.left = treeNode5;
        treeNode6.right = treeNode7;

        System.out.println(leavesOfBT.findLeaves(treeNode4));

    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> list = new ArrayList();
        postOrder(root, list);
        return list;
    }

    private int postOrder(TreeNode root, List<List<Integer>> list) {
        if (root == null)
            return 0;
        int l = postOrder(root.left, list);
        int r = postOrder(root.right, list);
        int index = Math.max(l, r);
        if (list.size() <= index) list.add(new ArrayList<>());
        list.get(index).add(root.val);
        return index + 1;
    }
}

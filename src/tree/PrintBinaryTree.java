package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 06/08/17
 *
 * @author Ankit Jain
 */
public class PrintBinaryTree {
    public static void main(String[] args) {
        PrintBinaryTree printBinaryTree = new PrintBinaryTree();
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        node.left = node1;
        node.right = node4;
        node.left.left = node2;
        node.left.left.left = node3;
        System.out.println(printBinaryTree.printTree(node));
    }

    private int findHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(findHeight(root.left), findHeight(root.right));
    }

    public List<List<String>> printTree(TreeNode root) {
        int height = findHeight(root);
        int n = (int) Math.pow(2, height) - 1;
        List<List<String>> list = new ArrayList<>();
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < n; i++)
            nums.add("");
        for (int i = 0; i < height; i++)
            list.add(new ArrayList(nums));
        ptUtil(root, list, height, 0, n, 0);
        return list;
    }

    private void ptUtil(TreeNode root, List<List<String>> list, int height, int widthStart, int widthEnd, int currHeight) {
        if (root == null) return;
        List<String> nums = list.get(currHeight);
        int mid = widthStart + (widthEnd - widthStart) / 2;
        nums.set(mid, String.valueOf(root.val));
        ptUtil(root.left, list, height, widthStart, mid - 1, currHeight + 1);
        ptUtil(root.right, list, height, mid + 1, widthEnd, currHeight + 1);
    }
}

package tree.bst;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 09/08/17
 *
 * @author Ankit Jain
 */
public class UniqueBST {

    // Catalan Number - Count the number of possible Binary Search Trees with n keys
    public int numTrees(int n) {
        long val = 1;
        for (int i = 0; i < n; i++)
            val = ((2 * n - i) * val) / (i + 1);
        return (int) (val / (n + 1));
    }

    public int numTrees1(int n) {
        int[] dp = new int[n + 1];
        if (n < 2) return 1;
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int l = 0;
            int r = i - 1;
            int sum = 0;
            while (l < r)
                sum += 2 * dp[l++] * dp[r--];
            if (l == r) sum += l != 1 ? dp[l] * dp[l] : dp[l];
            dp[i] = sum;
        }
        return dp[n];
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return generateTree(1, n);
    }

    private List<TreeNode> generateTree(int l, int r) {
        List<TreeNode> list = new ArrayList<>();
        if (l > r) list.add(null);
        for (int i = l; i <= r; i++) {
            List<TreeNode> lefts = generateTree(l, i - 1);
            List<TreeNode> rights = generateTree(i + 1, r);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    list.add(node);
                }
            }
        }
        return list;
    }

    private List<TreeNode> generateTree1(int l, int r) {
        List<TreeNode>[] dp = new ArrayList[r + 1];
        dp[0] = new ArrayList<TreeNode>();
        if(r == 0) return dp[0];
        dp[0].add(null);
        for (int i = l; i <= r; i++) {
            dp[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<TreeNode> left = dp[j];
                List<TreeNode> right = dp[i - j - 1];
                for (int k = 0; k < left.size(); k++) {
                    for (int m = 0; m < right.size(); m++) {
                        TreeNode node = new TreeNode(j + 1);
                        node.left = left.get(k);
                        node.right = clone(right.get(m), j + 1);
                        dp[i].add(node);
                    }
                }
            }
        }
        return dp[r];
    }

    private TreeNode clone(TreeNode treeNode, int i) {
        if (treeNode == null) return null;
        TreeNode node = new TreeNode(treeNode.val + i);
        node.left = clone(treeNode.left, i);
        node.right = clone(treeNode.right, i);
        return node;
    }
}

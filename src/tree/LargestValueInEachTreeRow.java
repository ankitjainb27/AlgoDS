package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date 28/07/17
 *
 * @author Ankit Jain
 */
public class LargestValueInEachTreeRow {
    public static void main(String[] args) {

    }

    //Fastest and optimized for both Time and Space - Uses Preorder
    //Note In preorder, at least one level for upper level is always traversed before that the lower level
    public List<Integer> largestValue(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list, 0);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list, int level) {
        if (root == null) return;
        if (list.size() <= level) {
            list.add(Integer.MIN_VALUE);
        }
        list.set(level, Math.max(root.val, list.get(level)));
        preorder(root.left, list, level + 1);
        preorder(root.right, list, level + 1);
    }

    //Faster - Uses BFS
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        int size = queue.size();
        while (size > 0) {
            int max = Integer.MIN_VALUE;
            while (size-- != 0) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                max = Math.max(max, node.val);
            }
            list.add(max);
            size = queue.size();
        }
        return list;
    }

}

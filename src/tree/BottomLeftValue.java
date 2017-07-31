package tree;

/**
 * Date 28/07/17
 *
 * @author Ankit Jain
 */
public class BottomLeftValue {
    public static void main(String[] args) {
        BottomLeftValue bottomLeftValue = new BottomLeftValue();
        System.out.println();
    }

    // Solution 1 - Uses global variables
    int maxLevel = 0;
    int val = 0;

    public int findBottomLeftValue(TreeNode root) {
        inorder(root, 1);
        return val;
    }

    private void inorder(TreeNode root, int level) {
        if (root == null) return;
        inorder(root.left, level + 1);
        if (maxLevel < level) {
            val = root.val;
            maxLevel = level;
        }
        inorder(root.right, level + 1);
    }


    // Solution 2 - w/o uses of global variables
    public int findBottomLeftValue1(TreeNode root) {
        int[] val = {0,0};
        inorder(root, 1, val);
        return val[0];
    }

    private void inorder(TreeNode root, int level, int[] res) {
        if (root == null) return;
        inorder(root.left, level + 1, res);
        if (res[1] < level) {
            res[0] = root.val;
            res[1] = level;
        }
        inorder(root.right, level + 1, res);
    }
}

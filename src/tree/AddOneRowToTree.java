package tree;

/**
 * Date 18/06/17
 * <p>
 * 4
 * /   \
 * 2     6
 * / \   /
 * 3   1 5
 *
 * @author Ankit Jain
 */
public class AddOneRowToTree {
    public static void main(String[] args) {
        AddOneRowToTree addOneRowToTree = new AddOneRowToTree();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(5);
        addOneRowToTree.preorder(root);
        TreeNode a = addOneRowToTree.addOneRow(root, 1, 1);
        System.out.println("---");
        addOneRowToTree.preorder(a);
    }

    private void preorder(TreeNode a) {
        if(a == null)
            return;
        System.out.println(a.val);
        preorder(a.left);
        preorder(a.right);
    }

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        addOneRowUtil(root, v, d - 1);
        return root;
    }

    public void addOneRowUtil(TreeNode root, int v, int d) {
        if (root == null || d < 1)
            return;
        if (d == 1) {
            TreeNode left = new TreeNode(v);
            TreeNode right = new TreeNode(v);
            left.left = root.left;
            right.right = root.right;
            root.left = left;
            root.right = right;
            return;
        }
        addOneRowUtil(root.left, v, d - 1);
        addOneRowUtil(root.right, v, d - 1);
    }
}

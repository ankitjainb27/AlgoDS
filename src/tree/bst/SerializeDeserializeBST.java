package tree.bst;

import tree.TreeNode;

/**
 * Date 09/08/17
 *
 * @author Ankit Jain
 */
public class SerializeDeserializeBST {

    // [41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23]
    // 0,3,5,6,8,7,10,14,13,12,15,17,18,21,23,22,20,19,16,11,9,4,2,1,25,28,27,26,29,31,33,34,32,30,36,35,24,38,40,39,37,43,42,45,47,46,49,48,44,41,
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postorder(root, sb);
        return sb.toString().length() == 0 ? "" : sb.toString().substring(0, sb.toString().length() - 1);
    }

    private void postorder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(root.val);
        sb.append(",");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] vals = data.split(",");
        Count count = new Count();
        count.count = vals.length - 1;
        TreeNode root = new TreeNode(Integer.parseInt(vals[count.count]));
        count.count--;
        postOrderD(vals, count, root, null);
        return root;
    }

    private void postOrderD(String[] vals, Count c, TreeNode parent, TreeNode gP) {
        if (c.count < 0) return;
        Integer val = Integer.parseInt(vals[c.count]);
        if (val > parent.val) {
            TreeNode right = new TreeNode(val);
            parent.right = right;
            c.count--;
            postOrderD(vals, c, right, parent);
        }
        if (c.count < 0) return;
        val = Integer.parseInt(vals[c.count]);
        if (gP == null || (val < parent.val && val > gP.val)) {
            TreeNode left = new TreeNode(val);
            parent.left = left;
            c.count--;
            postOrderD(vals, c, left, parent);
        }
    }

    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString().length() == 0 ? "" : sb.toString().substring(0, sb.toString().length() - 1);
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val);
        sb.append(",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if (data == null || data.length() == 0) return null;
        String[] vals = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Count count = new Count();
        count.count = 1;
        preorderD(vals, count, root, null);
        return root;
    }

    private void preorderD(String[] vals, Count c, TreeNode parent, TreeNode gP) {
        if (vals.length == c.count) return;
        Integer val = Integer.parseInt(vals[c.count]);
        if (val < parent.val) {
            TreeNode left = new TreeNode(val);
            parent.left = left;
            c.count++;
            preorderD(vals, c, left, parent);
        }
        if (vals.length == c.count) return;
        val = Integer.parseInt(vals[c.count]);
        if (gP == null || (val > parent.val && val < gP.val)) {
            TreeNode right = new TreeNode(val);
            parent.right = right;
            c.count++;
            preorderD(vals, c, right, gP);
        }
    }

    class Count {
        int count = 0;
    }
}

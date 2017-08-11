package tree.bst;

import tree.TreeNode;

/**
 * Date 07/08/17
 *
 * @author Ankit Jain
 */
public class DeleteNodeInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode root1 = new TreeNode(3);
        TreeNode root3 = new TreeNode(2);
        TreeNode root4 = new TreeNode(4);
        TreeNode root2 = new TreeNode(6);
        TreeNode root5 = new TreeNode(7);
        root.left = root1;
        root.left.left = root3;
        root.left.right = root4;
        root.right = root2;
        root.right.right = root5;
        inorder(new DeleteNodeInBST().deleteNode2(root, 5));
    }

    private static void inorder(TreeNode treeNode) {
        if (treeNode == null) return;
        inorder(treeNode.left);
        System.out.print(treeNode.val + " -- ");
        inorder(treeNode.right);
    }

    public TreeNode deleteNode1(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val < key)
            root.right = deleteNode(root.right, key);
        else if (root.val > key)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.val = minValue(root);
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private int minValue(TreeNode node) {
        node = node.right;
        while (node.left != null)
            node = node.left;
        return node.val;
    }

    public TreeNode deleteNode2(TreeNode root, int key) {
        TreeNode node = root;
        TreeNode parent = root;
        while (node != null && node.val != key) {
            parent = node;
            if (node.val < key) node = node.right;
            else node = node.left;
        }
        if (node == null) return root;
        if (node.left == null || node.right == null) {
            if (parent.left == node) parent.left = node.left == null ? node.right : node.left;
            else if (parent.right == node) parent.right = node.left == null ? node.right : node.left;
            else return node.left == null ? node.right : node.left;
        } else {
            TreeNode succ = findSuccessor(node);
            node.val = succ.val;
            node.right = deleteNode(node.right, succ.val);
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode node = root;
        TreeNode parent = root;
        int val = 0;
        while (node != null && node.val != key) {
            parent = node;
            if (node.val < key) {
                val = 2;
                node = node.right;

            } else {
                val = 1;
                node = node.left;
            }
        }
        if (node == null) return root;
        if (node.left == null && node.right == null) {
            if (val == 1)
                parent.left = null;
            else if (val == 2) parent.right = null;
            else return null;
        } else if (node.left != null) {
            TreeNode pred = findPredessor(node);
            node.val = pred.val;
            node.left = deleteNode(node.left, pred.val);
        } else {
            TreeNode succ = findSuccessor(node);
            node.val = succ.val;
            node.right = deleteNode(node.right, succ.val);
        }
        return root;
    }

    private TreeNode findPredessor(TreeNode node1) {
        TreeNode node = node1.left;
        while (node.right != null)
            node = node.right;
        return node;
    }

    private TreeNode findSuccessor(TreeNode node1) {
        TreeNode node = node1.right;
        while (node.left != null)
            node = node.left;
        return node;
    }


    private TreeNode deleteRootNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        TreeNode next = root.right;
        TreeNode pre = null;
        for (; next.left != null; pre = next, next = next.left) ;
        next.left = root.left;
        if (root.right != next) {
            pre.left = next.right;
            next.right = root.right;
        }
        return next;
    }

    public TreeNode deleteNode4(TreeNode root, int key) {
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null && cur.val != key) {
            pre = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else if (key > cur.val) {
                cur = cur.right;
            }
        }
        if (pre == null) {
            return deleteRootNode(cur);
        }
        if (pre.left == cur) {
            pre.left = deleteRootNode(cur);
        } else {
            pre.right = deleteRootNode(cur);
        }
        return root;
    }
}

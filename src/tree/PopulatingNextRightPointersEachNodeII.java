package tree;

/**
 * Created on 14/08/17 at 8:46 PM
 *
 * @author Ankit Jain
 */
public class PopulatingNextRightPointersEachNodeII {
    public void connect1(TreeLinkNode root) {
        TreeLinkNode prev = root;
        TreeLinkNode next = prev;
        boolean flag = true;
        while (next != null) {
            if (next.right != null) {
                next.right.next = findNext(next);
                if (flag)
                    prev = next.right;
            }
            if (next.left != null) {
                if (next.right != null)
                    next.left.next = next.right;
                else
                    next.left.next = findNext(next);
                if (flag)
                    prev = next.left;
            }
            if (next.next == null) {
                next = prev;
                flag = true;
            } else {
                flag = false;
                next = next.next;
            }
        }
    }

    private TreeLinkNode findNext(TreeLinkNode root) {
        if (root == null || root.next == null) return null;
        else if (root.next.left != null) return root.next.left;
        else if (root.next.right != null) return root.next.right;
        else return findNext(root.next);
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
        val = x;
    }
}

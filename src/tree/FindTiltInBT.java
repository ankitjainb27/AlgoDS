package tree;

/**
 * Created by ankit.ppe on 23/04/17.
 */
public class FindTiltInBT {
    int total = 0;

    public static void main(String[] args) {
        FindTiltInBT findTiltInBT = new FindTiltInBT();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
        findTiltInBT.findTilt(node1);
        System.out.println(String.valueOf(findTiltInBT.total));
    }

    public int findTilt(TreeNode root) {
        if (root == null)
            return 0;
        int ls = findTilt(root.left);
        int rs = findTilt(root.right);
        int tilt = Math.abs(ls - rs);
        total += tilt;
        return ls + rs + root.val;
    }
}


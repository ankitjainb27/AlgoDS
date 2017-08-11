package linkedlist;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 */
public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if ((node = node.next) != null) System.out.print(" -> ");
        }
        System.out.println();
    }
}

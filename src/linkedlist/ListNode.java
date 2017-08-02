package linkedlist;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 */
public class ListNode {
    int val;
    ListNode next;

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

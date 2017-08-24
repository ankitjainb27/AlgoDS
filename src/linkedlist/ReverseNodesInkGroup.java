package linkedlist;

/**
 * Created on 22/08/17 at 8:34 AM
 *
 * @author Ankit Jain
 */
public class ReverseNodesInkGroup {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode.print(new ReverseNodesInkGroup().reverseKGroup(head, 3));
    }

    ListNode nextHead;

    public ListNode reverseKGroup(ListNode head, int k) {
        Count count = new Count(0);
        ListNode node = head;
        nextHead = new ListNode(0);
        ListNode newHead = reverse(node, count, k);
        if (count.val == k) {
            head.next = reverseKGroup(nextHead, k);
        } else {
            count.val = 0;
            return reverse(newHead, count, k);
        }
        return newHead;
    }

    private ListNode reverse(ListNode head, Count count, int k) {
        ListNode node = head;
        ListNode prev = null;
        ListNode next = null;
        while (node != null && count.val < k) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
            count.val++;
        }
        nextHead = next;
        return prev;
    }


    class Count {
        int val;

        public Count(int val) {
            this.val = val;
        }
    }
}

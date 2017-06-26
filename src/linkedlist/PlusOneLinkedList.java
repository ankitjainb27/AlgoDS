package linkedlist;

/**
 * Date 25/06/17
 * @author Ankit Jain
 * https://leetcode.com/problems/plus-one-linked-list/#/description
 */
public class PlusOneLinkedList {

    public static void main(String[] args) {
        PlusOneLinkedList plusOneLinkedList = new PlusOneLinkedList();
        ListNode head = new ListNode(9);
        head.next = new ListNode(2);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(9);
        head = plusOneLinkedList.plusOne(head);
        head.print(head);
    }

    // My solution - Recursive
    public ListNode plusOne(ListNode head) {
        if (head == null)
            return null;
        int carry = plusOneUtil(head);
        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            head = node;
        }
        return head;
    }

    private int plusOneUtil(ListNode head) {
        if (head == null) {
            return 1;
        }
        int carry = plusOneUtil(head.next);
        if (carry == 1) {
            head.val += 1;
            if (head.val <= 9) {
                carry = 0;
            } else {
                head.val -= 10;
            }
        }
        return carry;
    }

}

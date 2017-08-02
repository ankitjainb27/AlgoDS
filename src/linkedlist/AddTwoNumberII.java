package linkedlist;

/**
 * Date 02/08/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/add-two-numbers-ii/description/
 */
public class AddTwoNumberII {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(7);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);

        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode node = new AddTwoNumberII().addTwoNumbers(listNode, listNode4);
        ListNode.print(node);
    }

    // Using Recursion Stack
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int m = 0;
        ListNode node = l1;
        while (node != null) {
            m++;
            node = node.next;
        }
        int n = 0;
        node = l2;
        while (node != null) {
            n++;
            node = node.next;
        }
        ListNode dummy = new ListNode(1);
        int val = aTNUtil(l1, l2, m, n, dummy);
        return val == 1 ? dummy : dummy.next;
    }

    private int aTNUtil(ListNode l1, ListNode l2, int m, int n, ListNode dummy) {
        if (l1 != null && l2 != null) {
            int val = 0;
            if (m > n)
                val = l1.val + aTNUtil(l1.next, l2, m - 1, n, dummy);
            else if (n > m)
                val = l2.val + aTNUtil(l1, l2.next, m, n - 1, dummy);
            else
                val = l2.val + l1.val + aTNUtil(l1.next, l2.next, m - 1, n - 1, dummy);
            ListNode node = new ListNode(val % 10);
            node.next = dummy.next;
            dummy.next = node;
            return val / 10;
        }
        return 0;
    }

    //Reversing the LinkedList
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val = 0;
            if (l1 != null) {
                val = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }
            val += carry;
            ListNode node = new ListNode((val) % 10);
            tail.next = node;
            tail = tail.next;
            carry = val / 10;
        }
        if (carry == 1) {
            ListNode node = new ListNode(1);
            tail.next = node;
        }
        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null, next;
        ListNode curr = node;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode n0 = new ListNode(0), pcurr = n0;
        ListNode curr1 = l1, curr2 = l2;
        int cnt1 = 0, cnt2 = 0;
        while (curr1 != null) {
            curr1 = curr1.next;
            cnt1++;
        }
        while (curr2 != null) {
            curr2 = curr2.next;
            cnt2++;
        }
        for (int i = 0; i < Math.abs(cnt1 - cnt2); i++) {
            pcurr.next = new ListNode(0);
            pcurr = pcurr.next;
        }
        if (cnt1 < cnt2) {
            pcurr.next = l1;
            l1 = n0.next;
        } else {
            pcurr.next = l2;
            l2 = n0.next;
        }

        ListNode res = sumup(l1, l2);
        return res.val == 0 ? res.next : res;
    }

    private ListNode sumup(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return new ListNode(0);
        } else {
            ListNode rest = sumup(l1.next, l2.next);
            int rval = rest.val + l1.val + l2.val;
            rest.val = rval % 10;
            ListNode newRest = new ListNode(rval / 10);
            newRest.next = rest;
            return newRest;
        }
    }
}

package tree.bst;

import linkedlist.ListNode;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 07/08/17
 *
 * @author Ankit Jain
 */
public class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode node) {
        ListNode head = node;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        ListNode[] list = new ListNode[count];
        for (int i = 0; i < count; i++) {
            list[i] = node;
            node = node.next;
        }
        return SLBSTUtil1(list, 0, list.length - 1);
    }

    private TreeNode SLBSTUtil1(ListNode[] list, int l, int r) {
        if (l > r) return null;
        int midV = l + (r - l) / 2;
        TreeNode mid = new TreeNode(list[midV].val);
        mid.left = SLBSTUtil1(list, l, midV - 1);
        mid.right = SLBSTUtil1(list, midV + 1, r);
        return mid;
    }

    public TreeNode sortedListToBST2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        return SLBSTUtil(list, 0, list.size() - 1);
    }

    private TreeNode SLBSTUtil(List<ListNode> list, int l, int r) {
        if (l > r) return null;
        int midV = l + (r - l) / 2;
        TreeNode mid = new TreeNode(list.get(midV).val);
        mid.left = SLBSTUtil(list, l, midV - 1);
        mid.right = SLBSTUtil(list, midV + 1, r);
        return mid;
    }

    public TreeNode sortedListToBST1(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null)
            prev.next = null;
        TreeNode node = new TreeNode(slow.val);
        node.left = prev != null ? sortedListToBST(head) : null;
        node.right = sortedListToBST(slow.next);
        return node;
    }


    ListNode head;

    public TreeNode sortedListToBST3(ListNode node) {
        ListNode head1 = node;
        head = node;
        int count = 0;
        while (head1 != null) {
            count++;
            head1 = head1.next;
        }

        return SLBSTUtil2(0, count - 1);
    }

    private TreeNode SLBSTUtil2(int l, int r) {
        if (l > r) return null;
        int mid = l + (r - l) / 2;
        TreeNode left = SLBSTUtil2(l, mid - 1);
        TreeNode midNode = new TreeNode(head.val);
        head = head.next;
        midNode.left = left;
        midNode.right = SLBSTUtil2(mid + 1, r);
        return midNode;
    }
}

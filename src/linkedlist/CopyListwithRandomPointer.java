package linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 15/08/17 at 6:05 AM
 *
 * @author Ankit Jain
 */
public class CopyListwithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode node = head;
        while (node != null) {
            RandomListNode node1 = new RandomListNode(node.label);
            node1.next = node.next;
            node.next = node1;
            node = node1.next;
        }
        RandomListNode head1 = head.next;
        node = head;
        while (node != null) {
            if (node.random != null)
                node.next.random = node.random.next;
            node = node.next.next;
        }
        node = head;
        while (node != null) {
            RandomListNode temp = node.next.next;
            if (temp != null)
                node.next.next = temp.next;
            node.next = temp;
            node = node.next;
        }
        return head1;
    }

    public RandomListNode copyRandomList1(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode node = head;
        while (node != null) {
            RandomListNode node1 = new RandomListNode(node.label);
            map.put(node, node1);
            node = node.next;
        }

        node = head;
        while (node != null) {
            RandomListNode node1 = map.get(node);
            node1.next = map.get(node.next);
            node1.random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) return null;
        List<RandomListNode> list = new ArrayList<>();
        RandomListNode node = head;
        RandomListNode prev = null;
        while (node != null) {
            list.add(node.next);
            RandomListNode node1 = new RandomListNode(node.label);
            node.next = node1;
            node1.random = node;
            if (prev != null)
                prev.next = node1;
            prev = node1;
            node = list.get(list.size() - 1);

        }
        RandomListNode head1 = head.next;
        node = head1;
        while (node != null) {
            if (node.random.random != null)
                node.random = node.random.random.next;
            else
                node.random = null;
            node = node.next;
        }
        node = head;
        for (int i = 0; i < list.size(); i++) {
            node.next = list.get(i);
            node = node.next;
        }
        return head1;
    }
}

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
}
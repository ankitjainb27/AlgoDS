package linkedlist;

import java.util.Random;

/**
 * Date 01/08/17
 *
 * @author Ankit Jain
 */
public class GetRandomNode {
    /**
     * @param head The linked list's head.
     * Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    Random rand;
    ListNode head;

    public GetRandomNode(ListNode head) {
        rand = new Random();
        this.head = head;
    }

    /**
     * Returns a random node's value.
     */
    //Using Reservoir Sampling
    public int getRandom() {
        ListNode node = head;
        int result = node.val;
        int len = 1;
        while (node != null) {
            if (rand.nextInt(len++) == 0) result = node.val;
            node = node.next;
        }
        return result;
    }

    /*List<Integer> list;
    Random rand;
    public Solution(ListNode head) {
        list = new ArrayList<>();
        rand = new Random();
        while(head != null)
        {
            list.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }*/

}

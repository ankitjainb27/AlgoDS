package heap;

import java.util.*;

/**
 * Date 17/06/17
 *
 * @author Ankit Jain
 */
public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2-o1);

        priorityQueue.add(4);
        priorityQueue.add(43);
        priorityQueue.add(3);
        priorityQueue.add(300);
        priorityQueue.add(1);
        priorityQueue.add(90);
        priorityQueue.add(25);
        System.out.println(priorityQueue);
        Iterator it = priorityQueue.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
        while (priorityQueue.size() > 0)
        {
            System.out.println(priorityQueue.peek() + "--" + priorityQueue.poll());
        }
    }
}

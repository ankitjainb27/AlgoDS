package design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Date 25/06/17
 * @author Ankit Jain
 * https://leetcode.com/problems/design-hit-counter/#/description
 */
public class HitCounterDesign {
    public class HitCounter {

        Queue<Integer> queue;

        /**
         * Initialize your data structure here.
         */
        public HitCounter() {
            queue = new LinkedList<>();
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            queue.add(timestamp);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            while (timestamp - queue.peek() >= 300)
                queue.poll();
            return queue.size();
        }
    }

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
}

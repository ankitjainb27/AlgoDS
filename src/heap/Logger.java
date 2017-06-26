package heap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Date 22/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/logger-rate-limiter/#/description
 */
public class Logger {
    /**
     * Initialize your data structure here.
     */

    class Log {
        Integer time;
        String val;

        public Log(Integer time, String val) {
            this.time = time;
            this.val = val;
        }
    }

    PriorityQueue<Log> queue;
    HashSet<String> set;

    public Logger() {
        set = new HashSet<>();
        queue = new PriorityQueue<>(new Comparator<Log>() {
            @Override
            public int compare(Log o1, Log o2) {
                return o1.time - o2.time;
            }
        });
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (queue.size() > 0 && timestamp - queue.peek().time >= 10) {
            Log log = queue.poll();
            set.remove(log.val);
        }
        if (!set.contains(message)) {
            Log log = new Log(timestamp, message);
            queue.add(log);
            set.add(message);
            return true;
        }
        return false;
    }
}

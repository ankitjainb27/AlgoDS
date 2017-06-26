package hashing;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    /*HashMap<String, Integer> map;

    public Logger() {
        map = new HashMap();
    }

    */
    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     *//*
    public boolean shouldPrintMessage(int timestamp, String message) {
        System.out.println(map.size());
        if (map.containsKey(message)) {
            Integer time = map.get(message);
            if (timestamp - time > 10) {
                map.put(message, timestamp);
                return true;
            }
            else
                return false;
        } else {
            map.put(message, timestamp);
        }
        return true;
    }*/

    LinkedHashMap<String, Integer> map;
    int lastSecond = 0;

    public Logger() {
        map = new LinkedHashMap<String, Integer>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return lastSecond - eldest.getValue() >= 10;
            }
        };
    }


    public boolean shouldPrintMessage(int timestamp, String message) {
        lastSecond = timestamp;
        boolean res;
        if (res = !map.containsKey(message) || timestamp - map.get(message) >= 10) {
            map.put(message, timestamp);
        }
        return res;
    }
}

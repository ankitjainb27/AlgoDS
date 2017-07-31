package design;

import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Date 03/07/17
 *
 * @author Ankit Jain
 */
public class LRUCache1 {

    public static void main(String[] args) {
        LRUCache1 cache = new LRUCache1(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
/*
        LRUCache1 cache = new LRUCache1(3 */
/* capacity *//*
);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);    // evicts key 2
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));       // returns 4
*/

//        ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
//[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
    }

    HashMap<Integer, Node1> map;
    TreeMap<Long, Node1> treeMap;
    int cap;

    public LRUCache1(int capacity) {
        map = new HashMap<>();
        treeMap = new TreeMap<>(Collections.reverseOrder());
        cap = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node1 node = map.get(key);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long time  = System.currentTimeMillis();
            treeMap.remove(node.time);
            treeMap.put(time, new Node1(key, node.val, time));
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long time = System.currentTimeMillis();
        Node1 node = new Node1(key, value, time);
        if (map.containsKey(key)) {
            Node1 rNode = map.get(key);
            treeMap.remove(rNode.time);
            map.remove(key);
            map.put(key, node);
            treeMap.put(time, node);
        } else {
            if (map.size() >= cap) {
                Long oldTIme = treeMap.lastKey();
                Node1 oldNode = treeMap.get(oldTIme);
                map.remove(oldNode.key);
                treeMap.remove(oldTIme);
            }
            map.put(key, node);
            treeMap.put(time, node);
        }
    }

    private void print(Node1 node) {
        while (node != null) {
            System.out.println(node.key + "--" + node.val + "-->  ");
            node = node.next;
        }
    }
}

class Node1 {

    int key;
    int val;
    Node1 prev;
    Node1 next;
    long time;


    public Node1(int key, int val, long time) {
        this.key = key;
        this.val = val;
        this.time = time;
    }

    @Override
    public String toString() {
        return "key: " + key + "val: " + val +"time: " + time;
    }
}
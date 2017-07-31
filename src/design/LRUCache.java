package design;

import java.util.HashMap;

/**
 * Date 03/07/17
 *
 * @author Ankit Jain
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3 /* capacity */);

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

//        ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
//[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
    }

    HashMap<Integer, Node> map;
    int cap;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        cap = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            if (head != node) {
                if (tail == node) {
                    tail = tail.prev;
                }
                head.prev = node;
                node.prev.next = node.next;
                if(node.next != null)
                    node.next.prev = node.prev;
                node.next = head;
                head = node;
                node.prev = null;
//                System.out.println("++++");
//                print(head);
//                System.out.println("------");

            }
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
//        System.out.println("key: " + key + "value: " + value);

//        System.out.println(map);
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            Node node1 = map.get(key);
            if (node1 == head) {
                head = node1.next;
                node1.next = null;
            }
            if (node1 == tail) {
                tail = node1.prev;
                node1.prev = null;
                if(tail != null)
                    tail.next = null;
            }
            Node next = node1.next;
            if (node1.prev != null && node1.prev.next != null)
                node1.prev.next = node1.next;
            if (node1.prev != null)
                next.prev = node1.prev;
            Node node2 = map.get(key);
            node2 = null;
            map.remove(key);
        }
        if (head == null) {
            head = node;
            tail = node;
        } else if (map.size() < cap) {
            node.next = head;
            head.prev = node;
            head = node;
        } else {
            map.remove(tail.key);
            if (tail.prev != null) {
                tail = tail.prev;
                if(tail.next != null)
                    tail.next.prev = null;
                tail.next = null;
            }
            node.next = head;
            head.prev = node;
            head = node;
        }
        map.put(key, node);
//        print(head);
    }

    private void print(Node node) {
        while (node != null) {
            System.out.println(node.key + "--" + node.val + "-->  ");
            node = node.next;
        }
    }
}

class Node {

    int key;
    int val;
    Node prev;
    Node next;


    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
package design;

import java.util.HashMap;

/**
 * Date 04/07/17
 *
 * @author Ankit Jain
 */
public class LFUCache {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2 /* capacity */);


        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    HashMap<Integer, Node> keyMap;
    HashMap<Integer, DLL> freqMap;
    int cap;
    int minFreq = Integer.MAX_VALUE;

    public LFUCache(int capacity) {
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
        cap = capacity;
    }

    public int get(int key) {
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            DLL dll = freqMap.get(node.freq);
            if (dll.remove(node)) {
                freqMap.remove(node.freq);
                if (minFreq == node.freq)
                    minFreq = node.freq + 1;
            }
            node.freq += 1;
            if (freqMap.containsKey(node.freq)) {
                DLL dllPlus1 = freqMap.get(node.freq);
                dllPlus1.addToFront(node);
            } else {
                DLL newDll = new DLL();
                newDll.addToFront(node);
                freqMap.put(node.freq, newDll);
            }
            print();
            return node.val;
        } else {
            print();
            return -1;
        }
    }

    void print() {
        System.out.println("keyMap: " + keyMap);
        System.out.println("freqMap: " + freqMap);
    }

    public void put(int key, int value) {
        if (cap == 0) return;
        if (keyMap.size() < cap || keyMap.containsKey(key)) {
            Node node = null;
            if (keyMap.containsKey(key)) {
                node = keyMap.get(key);
                keyMap.remove(key);
                DLL dll = freqMap.get(node.freq);
                if (dll.remove(node)) {
                    freqMap.remove(node.freq);
                    if (minFreq == node.freq)
                        minFreq = node.freq + 1;
                }
                node.val = value;
                node.freq += 1;
            }
            if (node == null)
                node = new Node(key, value, 1);
            keyMap.put(key, node);
            if (freqMap.containsKey(node.freq)) {
                DLL dllPlus1 = freqMap.get(node.freq);
                dllPlus1.addToFront(node);
            } else {
                DLL newDll = new DLL();
                newDll.addToFront(node);
                freqMap.put(node.freq, newDll);
            }
            minFreq = Math.min(minFreq, node.freq);
        } else {
            DLL dll = freqMap.get(minFreq);
            Node node = dll.getLast();
            keyMap.remove(node.key);
            if (dll.remove(node)) {
                freqMap.remove(node.freq);
            }
            put(key, value);
            minFreq = 1;
        }
        print();
    }

    class Node {
        int key, val, freq;
        Node prev, next;

        public Node(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }

        public Node() {
            this.key = 0;
            this.val = 0;
            this.freq = 0;
        }

        @Override
        public String toString() {
            return "{ key: " + key + " val: " + val + " freq: " + freq + " }";
        }
    }

    class DLL {
        Node head;
        Node tail;
        int size;

        public DLL() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        boolean remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            size--;
            return size == 0;
        }

        void addToFront(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
            size++;
        }

        Node getLast() {
            return tail.prev;
        }


        @Override
        public String toString() {
            String st = "";
            Node node = head.next;
            while (node != tail) {
                st += node.toString() + " --> ";
                node = node.next;

            }
            return st.substring(0, st.length() - 4);
        }
    }

}

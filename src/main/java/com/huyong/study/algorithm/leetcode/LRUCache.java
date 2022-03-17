package com.huyong.study.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huyong
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(2));
        lruCache.put(1,1);
        lruCache.put(4,1);
        System.out.println(lruCache.get(2));
    }

    class Node {
        int value;
        int key;
        Node next;
        Node parent;

        public Node() {
        }

        public Node(int key, int value) {
            this.value = value;
            this.key = key;
        }
    }

    int capacity;

    int size;

    Map<Integer, Node> map = new HashMap<>();

    Node head = new Node();
    Node tail = new Node();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.parent = head;
    }

    public int get(int key) {
        Node item = map.get(key);
        if (item == null) {
            return -1;
        }
        Node next = head.next;
        if (next != item) {
            item.parent.next = item.next;
            item.next.parent = item.parent;

            item.next = next;
            next.parent = item;

            head.next = item;
            item.parent = head;
        }
        return item.value;
    }

    public void put(int key, int value) {
        Node item = map.get(key);
        if (item != null) {
            item.value = value;

            if (head.next == item) {
                return;
            }

            item.parent.next = item.next;
            item.next.parent = item.parent;

            head.next.parent = item;
            item.next = head.next;
            head.next = item;
            item.parent = head;
        } else {
            if (size == capacity) {
                Node parent = tail.parent;
                if (parent == head) {
                    return;
                }
                map.remove(parent.key);
                parent.parent.next = parent.next;
                parent.next.parent = parent.parent;
                parent = null;
                --size;
            }
            Node node = new Node(key, value);
            map.put(key, node);

            head.next.parent = node;
            node.next = head.next;
            head.next = node;
            node.parent = head;
            ++size;
        }
    }
}

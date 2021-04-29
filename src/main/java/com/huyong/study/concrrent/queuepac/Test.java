package com.huyong.study.concrrent.queuepac;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Test test = new Test();
        Node node = new Node("1");
        Node t = test.tail, p = t;
        Node q = p.next;
        if (p.casNext(null, node)) {
            System.out.println(test.head == test.tail);
            System.out.println(test.head.next.value);
        }
    }


    private static final long headOffset;
    private static final long tailOffset;
    private static final sun.misc.Unsafe UNSAFE;

    private static class Node {
        private String value;
        private Node next;

        public Node(String value) {
            this.value = value;
        }

        private static final long valueOffset;
        private static final long nextOffset;

        private boolean casNext(Node cmp, Node value) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, value);
        }

        static {
            try {
                Field valueField = Node.class.getDeclaredField("value");
                Field nextField = Node.class.getDeclaredField("next");
                valueOffset = UNSAFE.objectFieldOffset(valueField);
                nextOffset = UNSAFE.objectFieldOffset(nextField);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                throw new Error(e);
            }
        }

    }
    private Node head;
    private Node tail;

    public Test() {
        head = tail = new Node(null);
    }

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            Field headField = Test.class.getDeclaredField("head");
            Field tailField = Test.class.getDeclaredField("tail");
            headOffset = UNSAFE.objectFieldOffset(headField);
            tailOffset = UNSAFE.objectFieldOffset(tailField);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

}

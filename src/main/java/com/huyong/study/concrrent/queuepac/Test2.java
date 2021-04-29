package com.huyong.study.concrrent.queuepac;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Test2 {
    public static void main(String[] args) throws Exception {
        ConcurrentLinkedQueue<String> objects = new ConcurrentLinkedQueue<>();
        objects.offer("a");
        objects.offer("b");
        objects.offer("c");
        objects.peek();
        objects.poll();
        objects.remove("");
        Field tail = objects.getClass().getDeclaredField("tail");
        tail.setAccessible(true);
        Object o = tail.get(objects);
        Field item = o.getClass().getDeclaredField("item");
        item.setAccessible(true);
        Object o1 = item.get(o);
        System.out.println(o1);

        LinkedBlockingQueue<Object> objects1 = new LinkedBlockingQueue<>();
        objects1.offer(1);
        objects1.put(1);

        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        arrayBlockingQueue.add(1);
        arrayBlockingQueue.add(2);

        PriorityBlockingQueue<Object> objects2 = new PriorityBlockingQueue<>();


    }
}

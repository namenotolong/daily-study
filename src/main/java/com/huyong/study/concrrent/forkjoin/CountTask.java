package com.huyong.study.concrrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-15 5:19 下午
 */
public class CountTask extends RecursiveTask<Integer> {

    private static int HOLD = 1000000000;

    private int start;

    private int end;

    public CountTask(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) <= HOLD) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask left = new CountTask(start, middle);
            CountTask right = new CountTask(middle + 1, end);
            left.fork();
            right.fork();
            Integer leftValue = left.join();
            Integer rightValue = right.join();
            sum = rightValue + leftValue;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, 100000000);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(countTask);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Integer.MAX_VALUE);
    }

}

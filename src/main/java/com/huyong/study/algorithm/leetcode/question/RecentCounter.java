package com.huyong.study.algorithm.leetcode.question;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyong
 */
public class RecentCounter {

    private List<Integer> buff = new ArrayList<>();

    int preIndex = 0;

    public RecentCounter() {

    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(2));
        System.out.println(recentCounter.ping(3));
        System.out.println(recentCounter.ping(300));
        System.out.println(recentCounter.ping(360));
        System.out.println(recentCounter.ping(900));
        System.out.println(recentCounter.ping(2000));
        System.out.println(recentCounter.ping(3000));
        System.out.println(recentCounter.ping(4000));
        System.out.println(recentCounter.ping(5000));
        System.out.println(recentCounter.ping(6000));
        System.out.println(recentCounter.ping(7000));
    }

    public int ping(int t) {
        buff.add(t);
        return getRecentCount(t, 3000);
    }
    private int getRecentCount(int t, int num) {
        int left = t - num;
        int start = preIndex;
        int end = buff.size() - 1;
        while (true) {
            int mid = (start + end) / 2;
            Integer value = buff.get(mid);
            if (mid == start) {
                if (left <= value) {
                    int result = buff.size() - preIndex;
                    preIndex = mid;
                    return result;
                } else {
                    int result = buff.size() - end;
                    preIndex = end;
                    return result;
                }
            }
            if (value == left) {
                preIndex = mid;
                return buff.size() - preIndex;
            }
            if (value > left) {
                end = mid;
            } else {
                start = mid;
            }
        }
    }
}

package com.huyong.study.algorithm.leetcode.dinamic;

import java.util.Arrays;

/**
 * @author huyong
 * 且钢条
 */
public class CutFe {

    static int[] p = {1,5,8,9,10,17,17,20,24,30};

    /**
     * 递归子问题  效率低 可存储子问题解  这是自顶向下
     * @param len
     * @return
     */
    public static int resolve1(int len) {
        if (len < 1) {
            return 0;
        }
        if (len == 1) {
            return p[0];
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            int value = p[i] + resolve1(len - i - 1);
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    /**
     * 自底向下求解子问题
     * @param len
     * @return
     */
    public static int resolve(int len) {
        int[] p = {1,5,8,9,10,17,17,20,24,30};
        int[] f = new int[len];
        f[0] = p[0];
        for (int i = 1; i < len; i++) {
            int max = 0;
            for (int j = 0; j <= (i + 1) / 2; j++) {
                int left,right;
                if (j == 0) {
                    if (i >= p.length) {
                       continue;
                    }
                    left = 0;
                    right = p[i];
                } else {
                    left = f[j - 1];
                    right = f[i - j];
                }
                int item = left + right;
                if (item > max) {
                    max = item;
                }
            }
            f[i] = max;
        }
        System.out.println(Arrays.toString(f));
        return f[len - 1];
    }

    public static void main(String[] args) {
        System.out.println(resolve(7));;
        System.out.println(resolve1(7));;
    }
}

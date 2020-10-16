package com.huyong.study.algorithm.sort;

/**
 * 描述: 插入，稳定,时间：O(n*n)，空间：O(1）
 *
 * @author huyong
 * @date 2020-10-14 3:59 下午
 */
public class InsertSort implements Sort {
    @Override
    public void sort(final int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int t = arr[j];
            //类似于堆的下虑
            while (j > 0 && arr[j - 1] > t) {
                arr[j] = arr[j - 1];
                --j;
            }
            arr[j] = t;
        }
    }
}

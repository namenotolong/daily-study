package com.huyong.study.algorithm.sort;

/**
 * 描述: 计数排序，时间：O(n+k)，空间：O(n+k)，稳定
 *  *
 * @author huyong
 * @date 2020-10-16 10:58 上午
 */
public class CountSort implements Sort {
    @Override
    public void sort(final int[] arr) {
        int min = 0;
        int max = 0;
        for (final int i : arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int gap = max - min + 1;
        int[][] heap = new int[gap][gap];
        int[] order = new int[gap];
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int index = num - min;
            heap[index][order[index]++] = num;
        }
        int index = 0;
        for (int i = 0; i < heap.length; i++) {
            for (int k = 0; k < order[i]; k++) {
                arr[index++] = heap[i][k];
            }
        }
    }
}

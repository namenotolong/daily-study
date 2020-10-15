package com.huyong.study.algorithm.sort;

import com.huyong.study.algorithm.utils.CommonUtils;

import java.util.Arrays;

/**
 * 描述:堆排序
 *
 * @author huyong
 * @date 2020-10-15 11:04 上午
 */
public class HeapSort implements Sort {
    @Override
    public void sort(int[] arr) {
        buildHeap(arr);
        for (int i = 1; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr));
            CommonUtils.swap(arr, 0, arr.length - i);
            System.out.println(Arrays.toString(arr));
            downHeap(arr, 0, arr.length - i - 1);
        }
    }

    /**
     * 初始化堆
     * @param arr
     */
    private void buildHeap(int[] arr) {
        for (int i = arr.length / 2; i >= 0; i--) {
            downHeap(arr, i, arr.length - 1);
        }
    }

    /**
     * 向下过滤
     * @param arr
     * @param end
     */
    private void downHeap(int[] arr, int start, int end) {
        int cur = start;
        for (; cur * 2 < end;) {
            int left = cur * 2 + 1;
            int right = cur * 2 + 2;
            int t = arr[left] > arr[right] ? left : right;
            if (arr[t] > arr[cur]) {
                CommonUtils.swap(arr, t, cur);
            } else{
                break;
            }
            cur = t;
        }
    }
}

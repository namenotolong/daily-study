package com.huyong.study.algorithm.utils;

import java.util.Arrays;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-14 2:43 下午
 */
public class CommonUtils {
    public static <T> void reverse(T[] arr, int start, int end) {
        while (start < end) {
            T t = arr[end];
            arr[end--] = arr[start];
            arr[start++] = t;
        }
    }
    public static void swap(int[] arr, int src, int des) {
        int t = arr[src];
        arr[src] = arr[des];
        arr[des] = t;
    }

    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6,7};
        reverse(arr, 1, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}

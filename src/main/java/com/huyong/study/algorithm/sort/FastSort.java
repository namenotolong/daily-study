package com.huyong.study.algorithm.sort;

import com.huyong.study.algorithm.utils.CommonUtils;

import java.util.Arrays;

/**
 * 描述:快速排序，非稳定，时间：O(nlogn)，空间：O(nlogn）
 *
 * @author huyong
 * @date 2020-10-14 2:32 下午
 */
public class FastSort implements Sort {

    @Override
    public void sort(int[] arr) {
        sort1(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        FastSort sort = new FastSort();
        int[] arr = {5,4,2,1,3,9,8,7,6,4,2,3,4,5,4,3,2,3,34,5,34,34,5,2,3,123,43,5};
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start;
        int j = end;
        int template = arr[start];
        while (i < j) {
            while (i < j && template < arr[j]) {
                --j;
            }
            while (i < j && template > arr[i]) {
                ++i;
            }
            if (arr[i] == arr[j] && i < j) {
                ++i;
            } else {
                CommonUtils.swap(arr, i, j);
            }
        }
        if (start < i - 1) {
            sort(arr, start, i - 1);
        }
        if (end > j + 1) {
            sort(arr, j + 1, end);
        }
    }

    /**
     * 三路排序 思想：取三个坐标点，小于、大于、等于（最右边 + 1）
     * @param arr
     * @param start
     * @param end
     */
    private void sort1(int[] arr, int start, int end) {
        if (end <= start) {
            return;
        }
        int lt = start, gt = end;
        int v = arr[start];
        int i = start;
        while (i <= gt) {
            if (arr[i] < v) {
                CommonUtils.swap(arr, lt++, i++);
            }
            else if (arr[i] > v) {
                //为什么i不自增？因为i左边的一定比v小或者等于，i右边的有可能小于v，还需要检测一次
                CommonUtils.swap(arr, i, gt--);
            }
            else {
                //大于、小于都会交换，这样等于的始终在一起
                i++;
            }
        }
        sort1(arr, start, lt - 1);
        sort1(arr, gt + 1, end);
    }
}

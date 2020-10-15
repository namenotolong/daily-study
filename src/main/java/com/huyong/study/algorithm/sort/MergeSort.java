package com.huyong.study.algorithm.sort;

/**
 * 描述:归并排序
 *
 * @author huyong
 * @date 2020-10-14 2:12 下午
 */
public class MergeSort implements Sort {
    @Override
    public void sort(int[] arr) {
        System.arraycopy(sort(arr, 0, arr.length - 1), 0, arr, 0, arr.length);
    }

    public int[] sort(int[] arr, int start, int end) {
        if (start == end) {
            return new int[]{arr[start]};
        }
        int middle = (start + end) / 2;
        int[] left = sort(arr, start, middle);
        int[] right = sort(arr, middle + 1, end);
        int[] newArray = new int[left.length + right.length];
        int i = 0,j = 0,k = 0;
        for (; k < newArray.length && i < left.length && j < right.length;) {
            newArray[k++] = left[i] < right[j] ? left[i++] : right[j++];
        }
        for (;i < left.length;) {
            newArray[k++] = left[i++];
        }
        for (; j < right.length;) {
            newArray[k++] = right[j++];
        }
        return newArray;
    }

}

package com.huyong.study.algorithm.sort;

import com.huyong.study.algorithm.utils.CommonUtils;

/**
 * 描述: 冒泡排序，稳定
 *
 * @author huyong
 * @date 2020-10-14 4:03 下午
 */
public class BubblingSort implements Sort {
    @Override
    public void sort(final int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < arr.length - 1 - i; k++) {
                if (arr[k] > arr[k + 1]) {
                    CommonUtils.swap(arr, k, k + 1);
                }
            }
        }
    }
}

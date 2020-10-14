package com.huyong.study.algorithm.sort;

import com.huyong.study.algorithm.utils.CommonUtils;

/**
 * 描述: 插入
 *
 * @author huyong
 * @date 2020-10-14 3:59 下午
 */
public class InsertSort implements Sort {
    @Override
    public void sort(final int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int k = i; k > 0; k--) {
                if (arr[k] < arr[k - 1]) {
                    CommonUtils.swap(arr, k, k - 1);
                } else {
                    break;
                }
            }
        }
    }
}

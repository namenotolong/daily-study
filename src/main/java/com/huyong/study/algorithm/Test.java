package com.huyong.study.algorithm;

import com.huyong.study.algorithm.sort.*;

import java.util.Arrays;

/**
 * 描述: 算法相关
 *
 * @author huyong
 * @date 2020-10-14 2:12 下午
 */
public class Test {
    public static void main(String[] args) {
        Sort sort = new InsertSort();
        int[] arr = {1,2,3,1,2,3,12,13,65,233,98,10,234,1,2,7,8,8,1,2};
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

package com.huyong.study.algorithm.sort;

/**
 * 描述: 希尔排序（缩小增量排序），非稳定
 *
 * @author huyong
 * @date 2020-10-15 2:52 下午
 */
public class ShellSort implements Sort {
    @Override
    public void sort(int[] array){
        //gap可理解为一组中相邻元素的索引差值
        int gap = array.length;
        while (gap > 0) {
            gap /= 2;
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < array.length; j += gap) {
                    int temp = array[j];
                    int k = j - gap;
                    while (k >= 0 && array[k] > temp) {
                        array[k + gap] = array[k];
                        k -= gap;
                    }
                    array[k + gap] = temp;
                }
            }
        }
    }
}








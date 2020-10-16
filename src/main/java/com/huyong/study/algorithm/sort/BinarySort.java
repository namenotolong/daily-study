package com.huyong.study.algorithm.sort;

/**
 * 描述: 二分排序
 *
 * @author huyong
 * @date 2020-10-14 8:04 下午
 */
public class BinarySort implements Sort {
    @Override
    public void sort(final int[] a) {
        for(int i=1; i < a.length; i++){
            int temp=a[i];
            int leftIndex = 0;
            int rightIndex = i-1;
            while(leftIndex<=rightIndex){
                int midIndex=(leftIndex + rightIndex) / 2;
                if(temp > a[midIndex]){
                    leftIndex = midIndex + 1;
                } else{
                    rightIndex = midIndex - 1;
                }
            }
            if (i - leftIndex >= 0) {
                System.arraycopy(a, leftIndex, a, leftIndex + 1, i - leftIndex);
            }
            a[leftIndex] = temp;
        }
    }
}

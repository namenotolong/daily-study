package com.huyong.study.algorithm.sort;

/**
 * 描述: 基数排序，O (nlog(r)m),稳定（针对整型数）
 *
 * @author huyong
 * @date 2020-10-15 7:27 下午
 */
public class RadixSort implements Sort {
    @Override
    public void sort(int[] arr) {
        //大于0 小于0 等于0
        int negativeCount = 0;
        int positiveCount = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num > 0) {
                ++positiveCount;
            } else if (num < 0) {
                num *= -1;
                ++negativeCount;
            }
            int t = String.valueOf(num).length();
            if (t > max) {
                max = t;
            }
        }
        int[] negativeArray = new int[negativeCount];
        int[] positiveArray = new int[positiveCount];
        int m = 0;
        int k = 0;
        int size1 = 0;
        int size3 = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num > 0) {
                positiveArray[m++] = num;
                int t = String.valueOf(num).length();
                if (t > size1) {
                    size1 = t;
                }
            } else if (num < 0) {
                num *= -1;
                negativeArray[k++] = num;
                int t = String.valueOf(num).length();
                if (t > size3) {
                    size3 = t;
                }
            }
        }
        sort(negativeArray, size3);
        sort(positiveArray, size1);
        int[] newArray = new int[arr.length];
        int index = 0;
        int end = newArray.length - 1;
        for (int i = negativeArray.length - 1; i >= 0; i--) {
            newArray[index++] = negativeArray[i] * -1;
        }
        for (int i = positiveArray.length - 1; i >= 0; i--) {
            newArray[end--] = positiveArray[i];
        }
        System.arraycopy(newArray, 0, arr, 0 , arr.length);
    }

    private void sort(int[] arr, int num){
        int[][] bucket = new int[10][arr.length];
        int index = 0;
        int division = 1;
        int[] order = new int[10];
        while (index++ < num) {
            for (int i = 0; i < arr.length; i++) {
                int t = arr[i] / division % 10;
                bucket[t][order[t]] = arr[i];
                ++order[t];
            }
            int count = 0;
            for (int i = 0; i < bucket.length; i++) {
                for (int k = 0; k < order[i]; k++) {
                    arr[count++] = bucket[i][k];
                }
            }
            for (int i = 0; i < order.length; i++) {
                order[i] = 0;
            }
            division *= 10;
        }
    }
}

package com.huyong.study.algorithm;

import com.google.common.collect.Lists;
import com.huyong.study.algorithm.sort.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 描述: 算法相关
 *
 * @author huyong
 * @date 2020-10-14 2:12 下午
 */
public class Test {
    public static void main(String[] args) {


        Sort sort = new RadixSort();
        int[] arr = {1,2,3,1,2,3,12,13,65,0,0,233,98,10,0,234,1,2,7,8,8,1,2,-12,-56,-99,-1};
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
        new Test().testSpeed();;
    }
    public void testSpeed() {
        int[] data = new int[10000 * 1000];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt();
        }
        int[] data1 = new int[data.length];
        int[] data2 = new int[data.length];
        int[] data3 = new int[data.length];
        int[] data4 = new int[data.length];
        int[] data5 = new int[data.length];
        System.arraycopy(data, 0, data1, 0, data.length);
        System.arraycopy(data, 0, data2, 0, data.length);
        System.arraycopy(data, 0, data3, 0, data.length);
        System.arraycopy(data, 0, data4, 0, data.length);
        System.arraycopy(data, 0, data5, 0, data.length);
        System.out.println("随机数生成完成");
        List<Sort> list = Lists.newArrayList(new HeapSort(), new RadixSort(),
                new FastSort(), new MergeSort());
        List<int[]> dataList = Lists.newArrayList(data,data1,data2,data3,data4,data5);
        for (int i = 0; i < list.size(); i++) {
            Sort sort = list.get(i);
            int[] t = dataList.get(i);
            long start = System.currentTimeMillis();
            sort.sort(t);
            long end = System.currentTimeMillis();
            System.out.println(sort.getClass() + ":" + (end - start));
        }
        long start = System.currentTimeMillis();
        Arrays.sort(data4);
        long end = System.currentTimeMillis();
        System.out.println("jdk:" + (end - start));

    }
}

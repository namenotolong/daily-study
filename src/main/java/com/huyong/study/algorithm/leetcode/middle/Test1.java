package com.huyong.study.algorithm.leetcode.middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {

    public static void main(String[] args) {
        System.out.println('2' ^ '2');
    }

    //221. 最大正方形
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] arr = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == '0') {
                arr[i][0] = 0;
            } else {
                arr[i][0] = 1;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '0') {
                arr[0][i] = 0;
            } else {
                arr[0][i] = 1;
            }
        }
        int max = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = Math.min(Math.min(arr[i - 1][j], arr[i-1][j-1]), arr[i][j - 1]) + 1;
                    if (arr[i][j] > max) {
                        max = arr[i][j];
                    }
                }
            }
        }
        return max;
    }

    //152. 乘积最大子数组
    public int maxProduct(int[] nums) {
        int[] max = new int[2];
        int[] min = new int[2];
        max[0] = nums[0];
        min[0] = nums[0];
        int result = max[0];
        for (int i = 1; i < nums.length; i++) {
            max[i % 2] = Math.max(Math.max(max[(i + 1) % 2] * nums[i], nums[i]), min[(i + 1) % 2]*nums[i]);
            min[i % 2] = Math.min(Math.min(max[(i + 1) % 2] * nums[i], nums[i]), min[(i + 1) % 2]*nums[i]);
            if (max[i % 2] > result) {
                result = max[i % 2];
            }
        }
        return result;
    }

    //169. 多数元素
    public int majorityElement(int[] nums) {
        int count = 1;
        int curNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                curNum = nums[i];
            }
            if (nums[i] != curNum) {
                --count;
            } else {
                ++count;
            }
        }
        return curNum;
    }

    //148. 排序链表
    public ListNode sortList(ListNode head) {
        return null;
    }

    //面试题50. 第一个只出现一次的字符
    public char firstUniqChar(String s) {
        if (s.length() == 0) {
            return ' ';
        }
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            map.merge(c, 1, Integer::sum);

        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

}

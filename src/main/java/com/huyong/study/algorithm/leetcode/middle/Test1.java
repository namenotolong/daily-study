package com.huyong.study.algorithm.leetcode.middle;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println(new Test1().lengthOfLIS1(arr));
        System.out.println(1 ^ 1);
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

    //198. 打家劫舍
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int one = nums[0],two = 0,three = nums[0];
        if (nums.length > 1) {
            two = Math.max(one, nums[1]);
            three = Math.max(one, two);
        }
        for (int i = 2; i < nums.length; i++) {
            three = Math.max(one + nums[i], two);
            one = two;
            two = three;
        }
        return three;
    }

    //279. 完全平方数
    public int numSquares(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, result[i - j * j]);
            }
            result[i] = min + 1;
        }
        return result[n];
    }

    //560. 和为 K 的子数组
    public int subarraySum(int[] nums, int k) {
        int[] arr = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            arr[i] = sum;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        for (int j : arr) {
            int target = j - k;
            Integer value = map.get(target);
            if (value != null) {
                count += value;
            }
            map.merge(j, 1, Integer::sum);
        }
        return count;
    }

    //300. 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int max = 1;
        int[] arr = new int[nums.length];
        arr[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int cur = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    int temp = arr[j] + 1;
                    if (temp > cur) {
                        cur = temp;
                    }
                }
            }
            arr[i] = cur;
            if (cur > max) {
                max = cur;
            }
        }
        return max;
    }

    public int lengthOfLIS1(int[] nums) {
        int[] arr = new int[nums.length];
        int len = 0;
        arr[len++] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < arr[len - 1]) {
                int start = 0;
                int end = len - 1;
                while (start <= end) {
                    int mid = (start + end) >> 1;
                    if (arr[mid] == nums[i]) {
                        break;
                    } else if (arr[mid] < nums[i]){
                        start = mid + 1;
                    } else {
                        if (mid == 0) {
                            arr[0] = nums[i];
                            break;
                        }
                        if (arr[mid - 1] < nums[i]) {
                            arr[mid] = nums[i];
                        } else {
                            end = mid - 1;
                        }
                    }
                }
            } else if (nums[i] > arr[len - 1]){
                arr[len++] = nums[i];
            }
        }
        return len;
    }

    //461. 汉明距离
    public int hammingDistance(int x, int y) {
        int v = x ^ y;
        int count = 0;
        while (v > 0) {
            if ((v & 1) == 1) {
                ++count;
            }
            v >>= 1;
        }
        return count;
    }

}

package leetcode.easy;

import java.util.Arrays;

/**
 * @author huyong
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.reverse(120));
        System.out.println(test.reverse(-1231230));
    }
    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int index = findIndex(nums, target - nums[i], i + 1, nums.length);
            if (index != -1) {
                result[0] = i;
                result[1] = index;
                return result;
            }
        }
        return result;
    }

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int temp = result * 10;
            if (result != temp / 10) {
                return 0;
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result;
    }

    public int findIndex(int[] arr, int target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}

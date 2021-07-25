package leetcode.easy;

import leetcode.middle.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyong
 */
public class Test {

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

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        int size = list.size();
        int index = size - n;
        if (index > 0) {
            list.get(index - 1).next = list.get(index).next;
            return head;
        } else {
            if (list.size() == 1) {
                return null;
            }
            return list.get(1);
        }
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (n == 0) {
            return head;
        }
        ListNode k, l, pre;
        k = l = head;
        pre = null;
        int count = 0;
        while (l.next != null) {
            l = l.next;
            if (count >= n) {
                pre = k;
                k = k.next;
            }
            ++count;
        }
        if (pre == null) {
            return head.next;
        }
        pre.next = k.next;
        return head;
    }

    public static void main(String[] args) {
        Test test = new Test();

    }
}

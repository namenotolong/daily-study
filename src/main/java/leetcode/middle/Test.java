package leetcode.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huyong
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.longestPalindrome("babad"));
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode result,cur;
        result = cur = new ListNode();
        int pre = 0;
        while (true) {
            int val = t1.val + t2.val + pre;
            if (val >= 10) {
                pre = val / 10;
                val %= 10;
            } else {
                pre = 0;
            }
            cur.val = val;
            t1 = t1.next;
            t2 = t2.next;
            if (t1 != null && t2 != null) {
                ListNode temp = new ListNode();
                cur.next = temp;
                cur = temp;
            } else {
                break;
            }
        }
        ListNode t3 = t1 == null ? t2 : t1;
        cur.next = t3;
        while (t3 != null) {
            cur = t3;
            int val = t3.val + pre;
            if (val >= 10) {
                pre = val / 10;
                val = val % 10;
            } else {
                pre = 0;
            }
            t3.val = val;
            t3 = t3.next;
        }
        if (pre != 0) {
            cur.next = new ListNode(pre);
        }
        return result;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
     */
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int cur = 0;
        int checkStart = 0;
        for (int i = 0; i < chars.length; i++) {
            char item = chars[i];
            Integer index = map.get(item);
            if (index == null || index < checkStart) {
                ++cur;
                if (cur > max) {
                    max = cur;
                }
            } else {
                cur = i - index;
                checkStart = index + 1;
            }
            map.put(item, i);
        }
        return max;
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串
     */
    public String longestPalindrome(String s) {
        int leftIndex = 0;
        int begin = 0;
        int maxLen = 0;
        while (leftIndex < s.length()) {
            int rightIndex = leftIndex;
            while (rightIndex < s.length()) {
                if (((rightIndex - leftIndex) > maxLen && isPalindrome(s, leftIndex, rightIndex))) {
                    maxLen = rightIndex - leftIndex;
                    begin = leftIndex;
                }
                ++rightIndex;
            }
            ++leftIndex;
        }
        return s.substring(begin, begin + maxLen + 1);
    }

    public boolean isPalindrome(String s, int start, int end) {
        if (end < start) {
            return false;
        }
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}


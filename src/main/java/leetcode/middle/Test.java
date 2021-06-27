package leetcode.middle;

/**
 * @author huyong
 */
public class Test {
    public static void main(String[] args) {

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
        return -1;
    }
}


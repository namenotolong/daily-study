package leetcode.easy;

import leetcode.middle.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
     * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
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

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (isLeft(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!isEquals(stack.pop(), s.charAt(i))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isLeft(char c) {
        return c == '{' || c == '(' || c == '[';
    }

    private boolean isEquals(char c, char l) {
        return (c == '{' && l == '}')
                || (c == '[' && l == ']')
                || (c == '(' && l == ')');
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode item = null;
        ListNode head = null;
        while (l1 != null || l2 != null) {
            ListNode temp;
            if (l1 == null || l2 == null) {
                temp = l1 == null ? l2 : l1;
            } else {
                temp = l1.val > l2.val ? l2 : l1;
            }
            if (temp == l1) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
            if (head == null) {
                head = temp;
                item = head;
            } else {
                item = item.next = temp;
            }
        }
        return head;
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     */
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        list.add("(");
        return test(1, 0, n, list);
    }

    public List<String> test(int leftCount, int rightCount, int totalN, List<String> list) {
        if (leftCount == rightCount && leftCount == totalN) {
            return list;
        }
        List<String> list1 = null;
        List<String> list2 = null;
        boolean add = rightCount < leftCount;
        if (add) {
            list2 = new ArrayList<>();
        }
        boolean leftAdd = leftCount < totalN;
        if (leftAdd) {
            list1 = new ArrayList<>();
        }
        if (!leftAdd && !add) {
            return list;
        }
        for (String s : list) {
            if (leftAdd) {
                list1.add(s + "(");
            }
            if (add) {
                list2.add(s + ")");
            }
        }
        int newLeft = leftCount + 1;
        int newRight = rightCount + 1;
        if (list1 == null) {
            return test(leftCount, newRight, totalN, list2);
        }
        if (list2 == null) {
            return test(newLeft, rightCount, totalN, list1);
        }
        List<String> left = test(newLeft, rightCount, totalN, list1);
        left.addAll(test(leftCount, newRight, totalN, list2));
        return left;
    }


    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length < 2) {
            return lists[0];
        }
        return mergeKLists(lists, 0);
    }

    public ListNode mergeKLists(ListNode[] lists, int start) {
        if (lists.length > 2 + start) {
            return mergeTwo(mergeTwo(lists[start], lists[start + 1]), mergeKLists(lists, start + 2));
        }
        if (lists.length - start == 2) {
            return mergeTwo(lists[start], lists[start + 1]);
        }
        return lists[start];
    }
    public ListNode mergeTwo(ListNode left, ListNode right) {
        return mergeTwoLists(left, right);
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode result = head.next;
        int count = 0;
        while (cur.next != null) {
            if ((count++ & 1) == 0) {
                ListNode temp = cur.next;
                if (pre != null) {
                    pre.next = temp;
                }
                cur.next = cur.next.next;
                temp.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return result;
    }

    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail;
        int count = 0;
        ListNode temp = head;
        while (temp != null && ++count < k) {
            temp = temp.next;
        }
        if (count == k) {
            tail = temp;
        } else {
            return head;
        }
        reverse(head, reverseKGroup(tail.next, k), 1, k);
        return tail;
    }

    ListNode result;
    public ListNode reverse(ListNode head, ListNode pre) {
        if (head.next == null) {
            result = head;
        } else {
            reverse(head.next, head);
        }
        head.next = pre;
        return result;
    }

    public void reverse(ListNode head, ListNode pre, int count, int maxCount) {
        if (head.next == null || count == maxCount) {
        } else {
            reverse(head.next, head, ++count, maxCount);
        }
        head.next = pre;
    }

    public ListNode mirror(ListNode head) {
        return null;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地
     * 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     */
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                if (index + 1 != i) {
                    nums[index + 1] = nums[i];
                }
                ++index;
            }
        }
        return index + 1;
    }

    /**
     * 给你一个数组 nums 和一个值 val，
     * 你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     */
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (index != i) {
                    nums[index + 1] = nums[i];
                }
                ++index;
            }
        }
        return index + 1;
    }

    /**
     * 给你两个字符串haystack 和 needle ，请你在 haystack
     * 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.length() - i < needle.length()) {
                break;
            }
            int j = i;
            int count = 0;
            while (count < needle.length() && j < haystack.length() && haystack.charAt(j) == needle.charAt(count)) {
                ++count;
                ++j;
            }
            if (count == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Test test = new Test();
        ListNode listNode = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5,
                                                new ListNode(6))))));
        ListNode listNode1 = test.reverseKGroup(listNode, 3);
        System.out.println(listNode1);
    }
}

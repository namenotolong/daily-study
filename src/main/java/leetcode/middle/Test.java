package leetcode.middle;

import java.util.*;

/**
 * @author huyong
 */
public class Test {

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

    /**
     * 动态规划
     */
    public String longestPalindrome2(String s) {
        if (s.length() < 2) {
            return s;
        }
        boolean[][] arr = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i][i] = true;
        }
        int startIndex = 0;
        int maxLen = 0;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if ((i - j < 2  || arr[j + 1][i - 1]) && s.charAt(i) == s.charAt(j)) {
                    arr[j][i] = true;
                    if (maxLen < i - j) {
                        startIndex = j;
                        maxLen = i - j;
                    }
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLen + 1);
    }

    /**
     * 两边扩散
     */
    public String longestPalindrome3(String s) {
        if (s.length() < 2) {
            return s;
        }
        int startIndex = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            int size1 = getSize(s, i, i);
            int size2 = 0;
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                size2 = getSize(s, i, i + 1);
            }
            int more = size1 > size2 ? size1 : size2;
            if (more > maxLen) {
                maxLen = more;
                startIndex = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }

    private int getSize(String s, int left, int right) {
        int count = right - left + 1;
        --left;
        ++right;
        while (left >= 0 && right < s.length() && left < right) {
            if (s.charAt(left--) != s.charAt(right++)) {
                break;
            }
            count = count + 2;
        }
        return count;
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

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int cur = 0;
        while (cur <= x) {
            if (x == cur) {
                return true;
            }
            cur = cur * 10 + x % 10;
            if (cur == 0) {
                return false;
            }
            if (x == cur) {
                return true;
            }
            x = x / 10;
        }
        return x == cur / 10;
    }

    /**
     * 正则匹配 动态规划
     */
    public boolean isMatch(String s, String p) {
        return isMatch(s, p, s.length() - 1, p.length() - 1);
    }

    public boolean canEmpty(String p, int j) {
        if (j < 0) {
            return true;
        }
        char c = p.charAt(j);
        if (c != '*') {
            return false;
        }
        if (j == 0) {
            return true;
        }
        if (p.charAt(j - 1) == '*') {
            return canEmpty(p, j - 1);
        } else {
            return canEmpty(p, j - 2);
        }
    }


    public boolean isMatch(String s, String p, int i, int j) {
        if (i < 0) {
            if (j < 0) {
                return true;
            }
            return canEmpty(p, j);
        }
        if (j < 0) {
            return false;
        }
        char patten = p.charAt(j);
        char value = s.charAt(i);
        if (patten != '.' && patten != '*') {
            if (patten != value) {
                return false;
            }
            return isMatch(s, p, i - 1, j - 1);
        }
        if (patten == '.') {
            return isMatch(s, p, i - 1, j - 1);
        }
        if (j == 0) {
            return false;
        }
        char prePattern = p.charAt(j - 1);
        if (value != prePattern && prePattern != '.') {
            return isMatch(s, p, i, j - 2);
        }
        while (prePattern == '.' || s.charAt(i) == prePattern) {
            if (isMatch(s, p, i, j - 2)) {
                return true;
            }
            --i;
            if (i < 0) {
                break;
            }
        }
        return isMatch(s, p, i, j - 2);
    }
    public static void main(String[] args) {
        int[] arr = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        Test test = new Test();
        test.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(test.threeSum(arr));
    }

    /**
     * 三个元素 a，b，c ，使得 a + b + c = 0
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        sort(nums, 0, nums.length - 1);
        List<List<Integer>> result = new ArrayList<>();
        int preL = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && preL == nums[i]) {
                continue;
            }
            preL = nums[i];
            int target = -nums[i];
            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {
                if (start > i + 1 && nums[start - 1] == nums[start]) {
                    ++start;
                    continue;
                }
                int val = nums[start] + nums[end];
                if (val == target) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[start]);
                    item.add(nums[end]);
                    result.add(item);
                    start++;
                    end--;
                } else if (val > target) {
                    --end;
                } else {
                    ++start;
                }
            }
        }
        return result;
    }

    public void sort(int[] nums, int start, int end) {
        int i = start, j = end;
        int k = nums[i];
        while (i < j) {
            while (nums[j] > k && i < j) {
                --j;
            }
            while (nums[i] < k && i < j) {
                ++i;
            }
            if (nums[i] == nums[j] && i < j) {
                ++i;
            } else {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        if (i > start) {
            sort(nums, start, i - 1);
        }
        if (end > j) {
            sort(nums, j + 1, end);
        }
    }
}


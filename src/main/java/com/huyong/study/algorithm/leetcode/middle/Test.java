package com.huyong.study.algorithm.leetcode.middle;

import com.huyong.study.algorithm.leetcode.entity.TreeNode;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

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


    /**
     * 三个元素 a，b，c ，使得 a + b + c = 0
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        sort(nums, 0, nums.length - 1);
        return threeSortedSum(nums);
    }

    public List<List<Integer>> threeSortedSum(int[] nums) {
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

    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 4) {
            int result = 0;
            for (int num : nums) {
                result += num;
            }
            return result;
        }
        sort(nums, 0, nums.length - 1);
        int result = Integer.MAX_VALUE;
        int resultValue = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int k = i + 1;
            int j = nums.length - 1;
            while (k < j) {
                int temp = nums[i] + nums[k] + nums[j];
                int abs = Math.abs(temp - target);
                if (abs < result) {
                    result = abs;
                    resultValue = temp;
                }
                if (temp > target) {
                    --j;
                } else {
                    ++k;
                }
            }
        }
        return resultValue;
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

    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     */
    public int divide(int dividend, int divisor) {
        if (-2147483648 == dividend && divisor == -1) {
            return 2147483647;
        }
        if (-2147483648 == dividend) {
            return dividend/divisor;
        }
        int result = 0;
        int b = Math.abs(divisor);
        int a = Math.abs(dividend);
        if (a < b) {
            return 0;
        }
        int c2 = getLen(b);
        while (a >= b) {
            int c1 = getLen(a);
            int shift = c1 - c2;
            int value = a >> shift;
            if (value < b) {
                --shift;
            }
            value = a >> shift;
            result += 1 << shift;
            if (shift == 0) {
                break;
            }
            a = ((value << shift) ^ a) + ((value - b) << shift);
        }
        if ((dividend ^ divisor) < 0) {
            return -result;
        }
        return result;
    }

    public int getLen(int a) {
        int count = 1;
        while (a > 0) {
            a >>= 1;
            if (a > 0) {
                ++count;
            }
        }
        return count;
    }

    public int add(int a, int b) {
        int value1 = a ^ b;
        int value2 = (a & b) << 1;
        if (value1 == 0) {
            return value2;
        }
        if (value2 == 0) {
            return value1;
        }
        return add(value1, value2);
    }

    public String convert(String s, int numRows) {
        int gap = 2 * numRows - 2;
        if (gap < 1) {
            return s;
        }
        int init = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int count = 0;
            int j = i;
            while (j < s.length() && (gap != 0 || init != 0)) {
                sb.append(s.charAt(j));
                ++count;
                if ((count & 1) == 1 || init == 0) {
                    if (gap != 0) {
                        j += gap;
                    } else {
                        j += init;
                    }
                } else {
                    j += init;
                }
            }
            gap -= 2;
            init += 2;
        }
        return sb.toString();
    }

    public int myAtoi(String s) {
        boolean start = false;
        boolean off = false;
        int reduce = '0';
        boolean valid = true;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (start) {
                if (c >= '0' && c < 10 + reduce) {
                    if (result > (Integer.MAX_VALUE - c + reduce) / 10) {
                        if (off) {
                            return -Integer.MAX_VALUE - 1;
                        } else {
                            return Integer.MAX_VALUE;
                        }
                    }
                    result = result * 10 + c - reduce;
                    continue;
                }
                break;
            } else {
                if (c == ' ') {
                    if (!valid) {
                        return 0;
                    }
                    continue;
                }
                if (c == '-') {
                    if (!valid) {
                        return 0;
                    }
                    off = true;
                    start = true;
                    continue;
                }
                if (c == '+') {
                    if (!valid) {
                        return 0;
                    }
                    start = true;
                    continue;
                }
                if (c > '9' || c < '0') {
                    return 0;
                }
                if (c == '0') {
                    valid = false;
                    continue;
                }
                start = true;
                result = c - reduce;
            }
        }
        if (off) {
            return -result;
        }
        return result;
    }

    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            if (max < area) {
                max = area;
            }
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return max;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 2) {
            return strs[0];
        }
        String model = strs[0];
        if (model.length() == 0) {
            return "";
        }
        int max = 0;
        ok:while (max < model.length()) {
            String substring = model.substring(0, max + 1);
            for (int i = 1; i < strs.length; i++) {
                if (!strs[i].startsWith(substring)) {
                    break ok;
                }
            }
            ++max;
        }
        return model.substring(0, max);
    }



    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        List<List<String>> list = new ArrayList<>();

        for (int i = 0; i < digits.length(); i++) {
            List<String> item = new ArrayList<>();
            switch (digits.charAt(i)) {
                case '2' :
                {
                    item.add("a");
                    item.add("b");
                    item.add("c");
                };break;
                case '3' :
                {
                    item.add("d");
                    item.add("e");
                    item.add("f");
                };break;
                case '4' :
                {
                    item.add("g");
                    item.add("h");
                    item.add("i");
                };break;
                case '5' :
                {
                    item.add("j");
                    item.add("k");
                    item.add("l");
                };break;
                case '6' :
                {
                    item.add("m");
                    item.add("n");
                    item.add("o");
                };break;
                case '7' :
                {
                    item.add("p");
                    item.add("q");
                    item.add("r");
                    item.add("s");
                };break;
                case '8' :
                {
                    item.add("t");
                    item.add("u");
                    item.add("v");
                };break;
                case '9' :
                {
                    item.add("w");
                    item.add("x");
                    item.add("y");
                    item.add("z");
                };break;
                default:break;
            }
            list.add(item);
        }
        return letterCombinationsTest(list, digits.length() - 1);
    }

    public List<String> letterCombinationsTest(List<String> left, List<String> right) {
        List<String> result = new ArrayList<>();
        for (String s : left) {
            for (String s1 : right) {
                result.add(s + s1);
            }
        }
        return result;
    }

    public List<String> letterCombinationsTest(List<List<String>> list, int right) {
        if (right == 0) {
            return list.get(0);
        }
        if (right == 1) {
            return letterCombinationsTest(list.get(0), list.get(1));
        }
        return letterCombinationsTest(letterCombinationsTest(list, --right), list.get(right + 1));
    }

    /**
     * 给定一个包含n 个整数的数组nums和一个目标值target，
     * 判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d
     * 的值与target相等？找出所有满足条件且不重复的四元组。
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        sort(nums, 0, nums.length - 1);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = j + 1;
                int l = nums.length - 1;
                int v = target - nums[i] - nums[j];
                while (k < l) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        ++k;
                        continue;
                    }
                    int temp = nums[k] + nums[l];
                    if (temp < v) {
                        ++k;
                        continue;
                    }
                    if (temp > v) {
                        --l;
                        continue;
                    }
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[j]);
                    item.add(nums[k]);
                    item.add(nums[l]);
                    result.add(item);
                    ++k;
                }
            }
        }
        return result;
    }

    //36
    public boolean isValidSudoku(char[][] board) {
        boolean validSudokuMiddle = isValidSudokuMiddle(board, true);
        if (!validSudokuMiddle) {
            return false;
        }
        char[][] boardTemp = new char[9][9];
        for (int i = 0; i < 9; i++) {
            int startX = i % 3 * 3;
            int startY = i / 3 * 3;
            for (int j = 0; j < 9; j++) {
                int x = j % 3 + startX;
                int y = j / 3 + startY;
                boardTemp[i][j] = board[x][y];
            }
        }
        return isValidSudokuMiddle(boardTemp, false);
    }

    public boolean isValidSudokuMiddle(char[][] board, boolean checkY) {
        for (int i = 0; i < 9; i++) {
            int[] arr = new int[10];
            int[] buff = null;
            if (checkY) {
                buff = new int[10];
            }
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int temp = board[i][j]-'0';
                    if (arr[temp] != 0) {
                        return false;
                    }
                    arr[temp] = temp;
                }
                if (checkY) {
                    if (board[j][i] != '.') {
                        int temp = board[j][i]-'0';
                        if (buff[temp] != 0) {
                            return false;
                        }
                        buff[temp] = temp;
                    }
                }
            }
        }
        return true;
    }

    //37
    public void solveSudoku(char[][] board) {
        solveSudokuReactive(board, 0, 0);
    }
    public int[] solveSudokuMiddle(char[][] board, int x, int y) {
        Set<Integer> exits = new HashSet<>();
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = 0; i < 9; i++) {
            if (board[x][i] != '.') {
                exits.add(board[x][i] - '0');
            }
            if (board[i][y] != '.') {
                exits.add(board[i][y] - '0');
            }
            if (board[i % 3 + startX][i / 3 + startY] != '.') {
                exits.add(board[i % 3 + startX][i / 3 + startY] - '0');
            }
        }
        int size = 9 - exits.size();
        if (size == 0) {
            return null;
        }
        int[] result = new int[size];
        int index = 0;
        for (int i = 1; i < 10; i++) {
            if (!exits.contains(i)) {
                result[index++] = i;
            }
        }
        return result;
    }

    public int[] solveSudokuMiddleNextEmpty(char[][] board, int x, int y) {
        for (int i = x; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public boolean solveSudokuReactive(char[][] board, int startX, int startY) {
        int[] nextArr = solveSudokuMiddleNextEmpty(board, startX, startY);
        if (nextArr == null) {
            return true;
        }
        int[] arr = solveSudokuMiddle(board, nextArr[0], nextArr[1]);
        if (arr == null) {
            return false;
        }
        if (nextArr[1] == 8) {
            startY = 0;
            startX = nextArr[0] + 1;
        } else {
            startX = nextArr[0];
            startY = nextArr[1] + 1;
        }
        for (int j : arr) {
            board[nextArr[0]][nextArr[1]] = (char) (j + '0');
            boolean flag = solveSudokuReactive(board, startX, startY);
            if (flag) {
                return true;
            }
            board[nextArr[0]][nextArr[1]] = '.';
        }
        return false;
    }


    //38. 外观数列
    public String countAndSay(int n) {
        String start = "1";
        for (int i = 1; i < n; i++) {
            start = countAndSayDesc(start);
        }
        return start;
    }
    public String countAndSayDesc(String start) {
        int len = start.length();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < len) {
            char c = start.charAt(index);
            int count = 1;
            while (++index < len && start.charAt(index) == c) {
                ++count;
            }
            sb.append(count).append(c);
        }
        return sb.toString();
    }

    //39. 组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSumMiddle(result, list, 0, 0, target, candidates);
        return result;
    }

    public void combinationSumMiddle(List<List<Integer>> result,
                                        List<Integer> list, int index, int curSum,
                                        int target, int[] candidates) {
        int num = 0;
        while (true) {
            int i = num * candidates[index] + curSum;
            if (i <= target) {
                for (int j = 0; j < num; j++) {
                    list.add(candidates[index]);
                }
                if (i == target) {
                    result.add(new ArrayList<>(list));
                    for (int j = 0; j < num; j++) {
                        list.remove(list.size() - 1);
                    }
                    return;
                }
                if (index < candidates.length - 1) {
                    combinationSumMiddle(result, list, index + 1, i, target, candidates);
                }
                for (int j = 0; j < num; j++) {
                    list.remove(list.size() - 1);
                }
                ++num;
                continue;
            }
            return;
        }
    }

    //40. 组合总和 II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        combination2SumMiddle(result, list, 0, 0, target, candidates);
        return result;
    }

    public void combination2SumMiddle(List<List<Integer>> result,
                                     List<Integer> list, int index, int curSum,
                                     int target, int[] candidates) {
        int num = 0;
        while (true) {
            if (num > 1) {
                return;
            }
            int i = num * candidates[index] + curSum;
            if (i <= target) {
                for (int j = 0; j < num; j++) {
                    list.add(candidates[index]);
                }
                if (i == target) {
                    List<Integer> curList = list.stream().sorted().collect(Collectors.toList());
                    boolean contain = false;
                    for (List<Integer> items : result) {
                        if (items.equals(curList)) {
                            contain = true;
                            break;
                        }
                    }
                    if (!contain) {
                        result.add(curList);
                    }
                    for (int j = 0; j < num; j++) {
                        list.remove(list.size() - 1);
                    }
                    return;
                }
                if (index < candidates.length - 1) {
                    combination2SumMiddle(result, list, index + 1, i, target, candidates);
                }
                for (int j = 0; j < num; j++) {
                    list.remove(list.size() - 1);
                }
                ++num;
                continue;
            }
            return;
        }
    }

    //41. 缺失的第一个正数   利用了数据索引、缺失本身的性质1-n之间
    public int firstMissingPositive(int[] nums) {
        boolean hasOne = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                hasOne = true;
            }
            if (nums[i] < 1 || nums[i] > nums.length) {
                nums[i] = 1;
            }
        }
        if (!hasOne) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i] > 0 ? nums[i] : -nums[i];
            nums[temp - 1] = nums[temp - 1] > 0 ? -nums[temp - 1] : nums[temp - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    //42. 接雨水 DP // stack //双指针  ---- 每个挖槽单独积水 分析
    public int trap(int[] height) {
        //return trapMid(height, 0, height.length - 1);
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
            left[i] = max;
        }
        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > max) {
                max = height[i];
            }
            right[i] = max;
        }
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int min = left[i] > right[i] ? right[i] : left[i];
            sum += min - height[i];
        }
        return sum;
    }
    //每层每层接水 n + m
    public int trapMid(int[] height, int start, int end) {
        for (int i = start; i < height.length; i++) {
            if (height[i] == 0) {
                ++start;
            } else {
                break;
            }
        }
        for (int i = end; i > start; i--) {
            if (height[i] == 0) {
                --end;
            } else {
                break;
            }
        }
        if (start >= end) {
            return 0;
        }
        int min = height[start] > height[end] ? height[end] : height[start];
        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (i > start && i < end && height[i] < min) {
                sum += min - height[i];
            }
            height[i] -= min;
            if (height[i] < 0) {
                height[i] = 0;
            }
        }
        return sum + trapMid(height, start, end);
    }


    //1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] arr = new int[grid.length * grid.length][grid.length * grid.length];
        //生成邻接表
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int x = i * grid.length + j;
                for (int k = 0; k < grid.length * grid.length; k++) {
                    int kx = k / grid.length;
                    int ky = k % grid.length;
                    if (kx == i && j == ky) {
                        arr[x][k] = 0;
                        continue;
                    }
                    if (grid[kx][ky] == 1 || grid[i][j] == 1) {
                        arr[x][k] = -1;
                        continue;
                    }
                    if (Math.abs(kx - i) > 1 || Math.abs(j - ky) > 1) {
                        arr[x][k] = -1;
                    } else {
                        arr[x][k] = 1;
                    }
                }
            }
        }
        //dijkstra
        Map<Integer, Integer> dijkstra = dijkstra(arr, 0);
        if (dijkstra.containsKey(arr.length - 1)) {
            return dijkstra.get(arr.length - 1) + 1;
        }
        return -1;
    }

    public static Map<Integer, Integer> dijkstra(int[][] graph, int startVertex) {
        Map<Integer, Integer> result = new HashMap<>();
        Map<Integer, Integer> rest = new HashMap<>();
        for (int i = 0; i < graph[startVertex].length; i++) {
            rest.put(i, graph[startVertex][i]);
        }
        while (!rest.isEmpty()) {
            //找出当前最小值
            int min = Integer.MAX_VALUE;
            int minValue = Integer.MAX_VALUE;
            boolean find = false;
            for (Map.Entry<Integer, Integer> item : rest.entrySet()) {
                if (item.getValue() >= 0 && item.getValue() < minValue) {
                    minValue = item.getValue();
                    min = item.getKey();
                    find = true;
                }
            }
            if (!find) {
                return result;
            }
            Integer curValue = rest.get(min);
            result.put(min, curValue);
            for (int i = 0; i < graph[min].length; i++) {
                if (graph[min][i] >= 0 && i != min) {
                    Integer value = rest.get(i);
                    if (value != null) {
                        int updateValue = graph[min][i] + curValue;
                        if (value < 0 || updateValue < value) {
                            //更新最小值
                            rest.put(i, updateValue);
                        }
                    }
                }
            }
            rest.remove(min);
        }
        return result;
    }

    //43. 字符串相乘
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int j = num2.length() - 1;
        String result = "0";
        StringBuilder sb = new StringBuilder(num1);
        while (j >= 0) {
            String item = multiplyMidAloneMulti(sb.toString(), num2.charAt(j--));
            result = multiplyMidAdd(item, result);
            sb.append("0");
        }
        return result;
    }

    public String multiplyMidAdd(String num1, String num2) {
        if ("0".equals(num1)) {
            return num2;
        }
        if ("0".equals(num2)) {
            return num1;
        }
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int preRest = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 && j >= 0) {
            int temp = num1.charAt(i) + num2.charAt(j) - '0' - '0' + preRest;
            preRest = temp / 10;
            sb.append(temp % 10);
            --i;
            --j;
        }
        while (i >= 0) {
            int temp = num1.charAt(i) - '0' + preRest;
            preRest = temp / 10;
            sb.append(temp % 10);
            --i;
        }
        while (j >= 0) {
            int temp = num2.charAt(j) - '0' + preRest;
            preRest = temp / 10;
            sb.append(temp % 10);
            --j;
        }
        if (preRest != 0) {
            sb.append(preRest);
        }
        return sb.reverse().toString();
    }

    public String multiplyMidAloneMulti(String num1, char item) {
        if ('0' == item) {
            return "0";
        }
        int i = num1.length() - 1;
        int preRest = 0;
        StringBuilder sb = new StringBuilder();
        int he = item - '0';
        while (i >= 0) {
            int temp = (num1.charAt(i) - '0') * he + preRest;
            preRest = temp / 10;
            sb.append(temp % 10);
            --i;
        }
        if (preRest != 0) {
            sb.append(preRest);
        }
        return sb.reverse().toString();
    }

    //46. 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();;
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list);
        permuteMiddleUnique(result, 0, list, new boolean[list.size()], new ArrayList<>());
        return result;
    }

    public void permuteMiddle(List<List<Integer>> result, int start, List<Integer> nums) {
        if (nums.size() == start) {
            result.add(new ArrayList<>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            permuteMiddle(result, start + 1, nums);
            Collections.swap(nums, start, i);
        }
    }

    //47. 全排列 II
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();;
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list);
        permuteMiddleUnique(result, 0, list, new boolean[list.size()], new ArrayList<>());
        return result;
    }
    public void permuteMiddleUnique(List<List<Integer>> result,
                                    int start, List<Integer> nums,
                                    boolean[] flag, List<Integer> cache) {
        if (nums.size() == start) {
            result.add(new ArrayList<>(cache));
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            if (flag[i] || (i > 0 && nums.get(i).equals(nums.get(i - 1))) && !flag[i - 1]) {
                continue;
            }
            flag[i] = true;
            cache.add(nums.get(i));
            permuteMiddleUnique(result, start + 1, nums, flag, cache);
            flag[i] = false;
            cache.remove(cache.size() - 1);
        }
    }
    //50. Pow(x, n)
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        boolean flag = n > 0;
        boolean dou = (n & 1) == 0;
        double result;
        double value = myPow(x, n / 2);
        if (n > 0) {
            if (value < x && x > 1) {
                return 0;
            }
        } else {
            if (value > x && x > 1) {
                return 0;
            }
        }

        if (dou) {
            result = value * value;
        } else {
            if (flag) {
                result = value * value * x;
            } else {
                result = value * value * (1 / x);
            }
        }
        return result;
    }
//55
    public boolean canJump(int[] nums) {
        boolean[] buff = new boolean[nums.length];
        buff[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i && !buff[i]; j++) {
                buff[i] = buff[j] && nums[j] >= i - j;
            }
        }
        System.out.println(Arrays.toString(buff));
        return buff[nums.length - 1];
    }

    //61. 旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        int length = 1;
        ListNode temp = head;
        ListNode tail = null;
        while (temp != null) {
            if (temp.next == null) {
                tail = temp;
            }
            temp = temp.next;
            ++length;
        }
        if (length == 0) {
            return head;
        }
        k = k % length;
        if (k == 0) {
            return head;
        }
        int index = length - k;
        ListNode parent = head;
        while (index > 1) {
            parent = parent.next;
            --index;
        }
        ListNode result = parent.next;
        parent.next = null;
        tail.next = head;
        return result;
    }

    //62. 不同路径
    public int uniquePaths(int m, int n) {
        int[][] buff = new int[m][n];
        for (int i = 0; i < m; i++) {
            buff[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            buff[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                buff[i][j] = buff[i - 1][j] + buff[i][j - 1];
            }
        }
        return buff[m - 1][n - 1];
    }
    public void uniquePathsMiddle(int m, int n, int curM, int curN, int[] value) {
        if (curM < m) {
            uniquePathsMiddle(m ,n, curM + 1, curN, value);
        }
        if (curN < n) {
            uniquePathsMiddle(m ,n, curM, curN + 1, value);
        }
        if (curM == m && curN == n) {
            value[0] = ++value[0];
        }
    }
//66 +1
    public int[] plusOne(int[] digits) {
        int pre = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (pre == 0) {
                break;
            }
            int value = digits[i] + pre;
            pre = value / 10;
            digits[i] = value % 10;
        }
        if (pre > 0) {
            int[] digitsResult = new int[digits.length + 1];
            digitsResult[0] = pre;
            System.arraycopy(digits, 0, digitsResult, 1, digits.length);
            return digitsResult;
        }
        return digits;
    }
//67. 二进制求和
    public String addBinary(String a, String b) {
        int last = 0;
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < a.length() || i < b.length()) {
            int left,right;
            left = right = 0;
            if (i < a.length()) {
                left = a.charAt(a.length() - i - 1) - '0';
            }
            if (i < b.length()) {
                right = b.charAt(b.length() - i - 1) - '0';
            }
            int value = left ^ right ^ last;
            last = (left + right + last) > 1 ? 1 : 0;
            sb.append(value);
            ++i;
        }
        if (last != 0) {
            sb.append(last);
        }
        return sb.reverse().toString();
    }

    //75. 颜色分类
    public void sortColors(int[] nums) {
        int left,mid,right;
        left = mid = right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                left++;
            } else if (nums[i] == 1) {
                mid++;
            } else {
                right++;
            }
        }
        int index = 0;
        while (left > 0 || mid > 0 || right > 0) {
            if (left-- > 0) {
                nums[index++] = 0;
            } else if (mid-- > 0) {
                nums[index++] = 1;
            } else if (right-- > 0){
                nums[index++] = 2;
            }
        }
    }

    //70. 爬楼梯
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int left = 1;
        int right = 2;
        while (n-- > 2) {
            int temp = left + right;
            left = right;
            right = temp;
        }
        return right;
    }

    //69. x 的平方根
    public int mySqrt(int x) {
        long start = 0;
        long end = x;
        int resultV = 0;
        while (start <= end) {
            long value = (start + end) / 2;
            long result = value * value;
            if (result > x) {
                end = value - 1;
            } else {
                resultV = (int)value;
                start = value + 1;
            }
        }
        return resultV;
    }

    //77. 组合
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> buff = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        combineMiddle(n, k, 1, buff, result);
        return result;
    }
    public void combineMiddle(int n, int k, int start,
                                             List<Integer> buff, List<List<Integer>> result) {
        for (int i = start; i <= n; i++) {
            buff.add(i);
            if (buff.size() == k) {
                result.add(new ArrayList<>(buff));
            }
            combineMiddle(n, k, i + 1, buff, result);
            buff.remove(buff.size() - 1);
        }
    }

    //88
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m + n];
        int index = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (index < m + n) {
            int value;
            if ((leftIndex < m) && ( rightIndex >= n || nums1[leftIndex] < nums2[rightIndex])) {
                value = nums1[leftIndex];
                ++leftIndex;
            } else {
                value = nums2[rightIndex];
                ++rightIndex;
            }
            arr[index++] = value;
        }
        System.arraycopy(arr, 0, nums1, 0, m + n);
    }

    //92
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        int index = 1;

        ListNode startNode = head;
        ListNode leftEnd = null;
        ListNode endNode = null;
        ListNode endNext = null;

        ListNode lastNode = null;
        ListNode curNode = head;
        while (true) {
            if (index < left) {
                lastNode = curNode;
                curNode = curNode.next;
                ++index;
                continue;
            }
            if (index == left) {
                leftEnd = lastNode;
                startNode = curNode;
            } else if (index > right) {
                endNode = lastNode;
                endNext = curNode;
                break;
            }
            ListNode next = curNode.next;
            curNode.next = lastNode;
            lastNode = curNode;
            curNode = next;
            ++index;
        }
        startNode.next = endNext;
        if (leftEnd == null) {
            return endNode;
        }
        leftEnd.next = endNode;
        return head;
    }

    //86. 分隔链表
    public ListNode partition(ListNode head, int x) {
        ListNode itemParent = null;
        ListNode firstTarget = null;

        ListNode item = head;

        ListNode result = head;
        ListNode lastNode = null;
        while (item != null) {
            if (item.val >= x) {
                if (firstTarget == null) {
                    firstTarget = item;
                }
                lastNode = item;
                item = item.next;
            } else {
                if (itemParent == null) {
                    result = item;
                }
                ListNode next = item.next;
                if (lastNode != null) {
                    lastNode.next = next;
                }
                if (itemParent != null) {
                    itemParent.next = item;
                }
                itemParent = item;
                if (firstTarget != null) {
                    item.next = firstTarget;
                }
                item = next;
            }
        }
        return result;
    }

    //58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        int sum = 0;
        int index = s.length() - 1;
        boolean start = false;
        while (index >= 0) {
            char item = s.charAt(index--);
            if (item == ' ') {
                if (start) {
                    break;
                }
                continue;
            }
            start = true;
            ++sum;
        }
        return sum;
    }

    //100 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //97交错字符串
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[] f = new boolean[s2.length() + 1];
        f[0] = true;
        for (int i = 0; i < s1.length() + 1; i++) {
            for (int j = 0; j < s2.length() + 1; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return f[s2.length()];
    }

    //96
    public int numTrees(int n) {
        int[] num = new int[n + 1];
        num[0] = 1;
        num[1] = 1;
        for (int i = 2; i <= n; i++) {
            int result = 0;
            for (int j = 0; j < i; j++) {
                result += (num[j] * num[i - j - 1]);
            }
            num[i] = result;
        }
        return num[n];
    }

    //95. 不同的二叉搜索树 II
    public List<TreeNode> generateTrees(int n) {
        return null;
    }

    //56. 合并区间
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }
        mergeSort(intervals, 0, intervals.length);
        List<int[]> result = new ArrayList<>();
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= pre[1]) {
                int max = pre[1] > intervals[i][1] ? pre[1] : intervals[i][1];
                pre[1] = max;
            } else {
                result.add(pre);
                pre = intervals[i];
            }
        }
        result.add(pre);
        int[][] resultArr = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }
    public void mergeSort(int[][] intervals, int start, int end) {
        int i = start;
        int j = end;
        int temp = intervals[start][0];
        while (i < j) {
            while (i < j && intervals[j][0] > temp) {
                --j;
            }
            while (i < j && intervals[i][0] < temp) {
                ++i;
            }
            if (intervals[i][0] == intervals[j][0] && i < j) {
                ++i;
            } else {
                int[] arr = intervals[i];
                intervals[i] = intervals[j];
                intervals[j] = arr;
            }
        }
        if (start < i - 1) {
            mergeSort(intervals, start, i - 1);
        }
        if (end > j + 1) {
            mergeSort(intervals, j + 1, end);
        }
    }

    //76. 最小覆盖子串
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> exists = new HashMap<>();
        Map<Character, Integer> rest = new HashMap<>();
        Map<Character, Integer> copy = new HashMap<>();
        for (char c : t.toCharArray()) {
            rest.merge(c, 1, Integer::sum);
            copy.merge(c, 1, Integer::sum);
        }
        int i,j;
        i = j = 0;
        int start = 0;
        int end = Integer.MAX_VALUE;
        while (j < s.length()) {
            char c = s.charAt(j);
            Integer value = copy.get(c);
            if (value == null) {
                ++j;
                continue;
            }
            exists.merge(c, 1, Integer::sum);
            Integer restValue = rest.get(c);
            if (restValue != null) {
                if (restValue > 1) {
                    rest.put(c, restValue - 1);
                } else {
                    rest.remove(c);
                }
            }
            while (i < j) {
                char itemChar = s.charAt(i);
                Integer itemValue = copy.get(itemChar);
                if (itemValue == null) {
                    ++i;
                    continue;
                }
                Integer cur = exists.get(itemChar);
                if (cur > itemValue) {
                    ++i;
                    exists.put(itemChar, cur - 1);
                } else {
                    break;
                }
            }
            if (rest.isEmpty() && j - i < end - start) {
                start = i;
                end = j;
            }
            ++j;
        }
        if (!rest.isEmpty()) {
            return "";
        }
        return s.substring(start, end + 1);
    }

    //79. 单词搜索
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }
        boolean[][] cache = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean item = existMiddle(board, word, cache, 0, i, j);
                if (item) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existMiddle(char[][] board, String word, boolean[][] choose,
                               int curIndex, int startX, int startY) {
        if (board[startX][startY] != word.charAt(curIndex)) {
            return false;
        }
        if (curIndex == word.length() - 1) {
            return true;
        }
        choose[startX][startY] = true;
        if (startX < board.length - 1 && !choose[startX + 1][startY]) {
            boolean success = existMiddle(board, word, choose, curIndex + 1, startX + 1, startY);
            if (success) {
                return true;
            }
        }
        if (startX > 0 && !choose[startX - 1][startY]) {
            boolean success = existMiddle(board, word, choose, curIndex + 1, startX - 1, startY);
            if (success) {
                return true;
            }
        }
        if (startY < board[startX].length - 1 && !choose[startX][startY + 1]) {
            boolean success = existMiddle(board, word, choose, curIndex + 1, startX, startY + 1);
            if (success) {
                return true;
            }
        }
        if (startY > 0 && !choose[startX][startY - 1]) {
            boolean success = existMiddle(board, word, choose, curIndex + 1, startX, startY - 1);
            if (success) {
                return true;
            }
        }
        choose[startX][startY] = false;
        return false;
    }


    //78. 子集
    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, nums.length - 1);
    }

    public List<List<Integer>> subsets(int[] nums, int end) {
        if (end < 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> last = new ArrayList<>();
        last.add(nums[end]);
        result.add(last);
        List<List<Integer>> subsets = subsets(nums, end - 1);
        result.addAll(subsets);
        for (List<Integer> subset : subsets) {
            if (subset.size() > 0) {
                ArrayList<Integer> items = new ArrayList<>(subset);
                items.add(nums[end]);
                result.add(items);
            }
        }
        return result;
    }

    //124. 二叉树中的最大路径和
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSumMiddle(root, max);
        return max[0];
    }
    public int maxPathSumMiddle(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        } else {
            int leftMax = Math.max(maxPathSumMiddle(root.left, max), 0);
            int rightMax = Math.max(maxPathSumMiddle(root.right, max), 0);
            int cur = root.val + leftMax + rightMax;;
            if (cur > max[0]) {
                max[0] = cur;
            }
            return root.val + Math.max(leftMax, rightMax);
        }
    }

    //101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricMiddle(root, root);
    }
    public boolean isSymmetricMiddle(TreeNode left, TreeNode right) {
        if (left == null || null == right) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetricMiddle(left.left, right.right) && isSymmetricMiddle(left.right, right.left);
    }

    //104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalMiddle(root, result);
        return result;
    }
    public void inorderTraversalMiddle(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversalMiddle(root.left, result);
            result.add(root.val);
            inorderTraversalMiddle(root.right, result);
        }
    }

    //141. 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode one = head;
        ListNode two = head;
        while (one != null && two != null) {
            one = one.next;
            two = two.next;
            if (two == null) {
                return false;
            }
            two = two.next;
            if (one == two) {
                return true;
            }
        }
        return false;
    }

    //136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    //114. 二叉树展开为链表
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flattenMiddle(root);
    }

    public TreeNode flattenMiddle(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode leftTail = null;
        TreeNode rightTail = null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (root.left != null) {
            leftTail = flattenMiddle(left);
        }
        if (root.right != null) {
            rightTail = flattenMiddle(right);
        }
        root.left = null;
        if (left != null) {
            root.right = left;
        }
        if (leftTail != null) {
            leftTail.right = right;
        }
        return rightTail;
    }

    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        int curSize = 1;
        queue.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int nextSize = 0;
            for (int i = 0; i < curSize; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                    ++nextSize;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    ++nextSize;
                }
            }
            result.add(list);
            curSize = nextSize;
        }
        return result;
    }

    //128. 最长连续序列
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            //初始化
            map.put(num, num);
        }
        for (int num : nums) {
            //查
            Integer item = map.get(num - 1);
            if (item != null) {
                //并
                map.put(num, item);
            }
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            Integer key = item.getKey();
            Integer value = item.getValue();
            //递归并
            while (true) {
                Integer newValue = map.get(value);
                if (!value.equals(newValue)) {
                    value = newValue;
                } else {
                    break;
                }
            }
            map.put(key, value);
            if (key - value > max) {
                max = key - value;
            }
        }
        return max + 1;
    }

    //121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int minValue = prices[0];
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] - minValue > result) {
                result = prices[i] - minValue;
            }
            if (prices[i] < minValue) {
                minValue = prices[i];
            }
        }
        return result;
    }

    //105. 从前序与中序遍历序列构造二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeMiddle(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public TreeNode buildTreeMiddle(int[] preorder, int[] inorder, int leftStart, int rightStart, int rightEnd) {
        if (leftStart > preorder.length - 1 || rightStart > rightEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[leftStart]);;
        int rightCount = 0;
        for (int i = rightStart; i <= rightEnd; i++) {
            if (inorder[i] == preorder[leftStart]) {
                break;
            }
            ++rightCount;
        }
        if (rightCount > 0) {
            root.left = buildTreeMiddle(preorder, inorder,
                    leftStart + 1,
                    rightStart, rightStart + rightCount - 1);
        }
        root.right = buildTreeMiddle(preorder, inorder, leftStart + 1 + rightCount,
                rightStart + rightCount + 1, rightEnd);
        return root;
    }


    //617. 合并二叉树
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val = root2.val + root1.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public static void main(String[] args) {

        int[] pre ={3,9,20,15,7};
        int[] in = {9,3,15,20,7};

        TreeNode treeNode = new Test().buildTree(pre, in);
        System.out.println(treeNode);

    }


}


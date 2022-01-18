package com.huyong.study.algorithm.leetcode.middle;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
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

    public static void main(String[] args) {
        Test test = new Test();
        int[] arr = {7,8,9,11,12};
        System.out.println(test.firstMissingPositive(arr));;
    }
}


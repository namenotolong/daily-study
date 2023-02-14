package com.huyong.study.algorithm.leetcode.middle;

import com.huyong.study.algorithm.leetcode.entity.TreeNode;

import java.util.*;
import java.util.concurrent.*;

public class Test1 {


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

    public int ro2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
    //213 打家劫舍 II
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int one = nums[0],two = 0;
        if (nums.length == 1) {
            return one;
        }
        int a = 0;
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        a = second;

        first = nums[1];
        int b = first;
        if (nums.length > 2) {
            b = Math.max(b, nums[2]);
        }

        for (int i = 3; i < nums.length; i++) {
            int temp = b;
            b = Math.max(first + nums[i], b);
            first = temp;
        }

        return Math.max(a,b);
    }

    //337 打家劫舍 III
    Map<TreeNode, Integer> map = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Integer integer = map.get(root);
        if (integer != null) {
            return integer;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        int one = root.val;
        int two = 0;
        if (left != null) {
            one += rob(left.left) + rob((left.right));
            two += rob(left);
        }
        if (right != null) {
            one += rob(right.left) + rob((right.right));
            two += rob(right);
        }
        int result = Math.max(one, two);
        map.put(root, result);
        return result;
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

    //287 寻找重复数
    public int findDuplicate(int[] nums) {
        int slow,fast;
        slow = fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    //581. 最短无序连续子数组
    public int findUnsortedSubarray(int[] nums) {
        int pre = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (startIndex < 0) {
                if (nums[i] < pre) {
                    endIndex = i;
                    max = pre;

                    int cur = i - 1;
                    while (cur >= 0 && nums[cur] >= nums[i]) {
                        --cur;
                    }
                    startIndex = cur;
                }
            } else {
                if (nums[i] < max) {
                    endIndex = i;
                }
                if (startIndex > 0 && nums[i] < nums[startIndex - 1]) {
                    while (startIndex >= 0 && nums[i] < nums[startIndex - 1]) {
                        --startIndex;
                    }
                }
                max = Math.max(max, nums[i]);
            }
            pre = nums[i];
        }
        return endIndex - startIndex == 0 ? 0 : endIndex - startIndex + 1;
    }

    //200. 岛屿数量
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    ++count;
                    numIslandsMid(grid, i, j);
                }
            }
        }
        return count;
    }
    public void numIslandsMid(char[][] grid, int x, int y) {
        grid[x][y] = '0';
        if (x > 0 && grid[x - 1][y] == '1') {
            numIslandsMid(grid, x - 1, y);
        }
        if (x < grid.length - 1 && grid[x + 1][y] == '1') {
            numIslandsMid(grid, x + 1, y);
        }
        if (y > 0 && grid[x][y - 1] == '1') {
            numIslandsMid(grid, x, y - 1);
        }
        if (y < grid[x].length - 1 && grid[x][y + 1] == '1') {
            numIslandsMid(grid, x, y + 1);
        }
    }

    //251 第k大的数
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    Random random = new Random();

    private int quickSelect(int[] nums, int start, int end, int index) {
        int template = random.nextInt(end - start + 1) + start;
        int v = partition(nums, start, end, template);
        if (v + 1 == index) {
            return nums[v];
        }
        return v >= index ? quickSelect(nums, start, v - 1, index) : quickSelect(nums, v + 1, end, index);
    }

    private int partition(int[] nums, int start, int end, int temp) {
        int v = nums[temp];
        while (start < end) {
            while (start < end && nums[end] < v) {
                --end;
            }
            while (start < end && nums[start] > v) {
                ++start;
            }
            if (start < end && nums[start] == nums[end]) {
                ++start;
            } else {
                int a = nums[start];
                nums[start] = nums[end];
                nums[end] = a;
            }
        }
        return start;
    }

    private void buildHeap(int[] arr) {
        for (int i = arr.length - 1; i >= arr.length / 2; i--) {
            int temp = i;
            int parent = temp / 2;
            while (temp > 0) {
                if (arr[parent] > arr[temp]) {
                    int v = arr[parent];
                    arr[parent] = arr[temp];
                    arr[temp] = v;
                }
                temp = parent;
                parent = temp / 2;
            }
        }
    }

    private void quickSort(int[] arr, int start, int end) {
        int temp = arr[start], i = start, j = end;
        while (i < j) {
            while (arr[j] > temp && i < j) {
                --j;
            }
            while (arr[i] < temp && i < j) {
                ++i;
            }
            if (i < j && arr[i] == arr[j]) {
                ++i;
            } else {
                int v = arr[i];
                arr[i] = arr[j];
                arr[j] = v;
            }
        }
        if (i > start) {
            quickSort(arr, start, i - 1);
        }
        if (j < end) {
            quickSort(arr, j + 1, end);
        }
    }

    private void downHeap(int[] arr, int v) {
        if (v <= arr[0]) {
            return;
        }
        arr[0] = v;
        int i = 0;
        while (true) {
            int next = i * 2 + 1;
            if (next < arr.length - 1 && arr[next] > arr[next + 1]) {
                ++next;
            }
            if (next >= arr.length) {
                return;
            }
            if (arr[next] < arr[i]) {
                int temp = arr[i];
                arr[i] = arr[next];
                arr[next] = temp;
            }
            i = next;
        }
    }


    //538 二叉搜索树 转 累加树
    public TreeNode convertBST(TreeNode root) {
        convertBSTMiddle(root);
        return root;
    }

    int preV = 0;
    private void convertBSTMiddle(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.right != null) {
            convertBSTMiddle(root.right);
        }

        root.val += preV;
        preV = root.val;
        if (root.left != null) {
            convertBSTMiddle(root.left);
        }
    }

    private TreeNode mockTree() {
        Integer[] arr = {4,1,6,0,2,5,7,null,null,null,3,null,null,null,8};
        int size = 2;
        int depth = 1;
        int index = 1;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);
        while (index < arr.length) {
            size *= depth;
            ++depth;
            boolean left = true;
            TreeNode poll = null;
            for (; index < index + size && index < arr.length; index++) {
                if (left) {
                    poll = queue.poll();
                }
                if (poll != null) {
                    Integer item = arr[index];
                    if (item != null) {
                        TreeNode treeNode = new TreeNode(item);
                        if (left) {
                            poll.left = treeNode;
                        }  else {
                            poll.right = treeNode;
                        }
                        queue.offer(treeNode);
                    }
                }
                left = !left;
            }
        }
        return root;
    }

    public int coinChang2e(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    //322. 零钱兑换
    public int coinChange(int[] coins, int amount) {
        int[][] cache = new int[coins.length + 1][amount + 1];

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (i == 1) {
                    int rest = j % coins[i - 1];
                    if (rest > 0) {
                        cache[1][j] = -1;
                    } else {
                        cache[1][j] = j / coins[i - 1];
                    }
                } else {
                    int one = cache[i - 1][j];
                    int two = -1;
                    boolean more = j >= coins[i - 1];
                    if (more) {
                        int item = cache[i][j - coins[i - 1]];
                        if (item != -1) {
                            two = item + 1;
                        }
                    }
                    if (one == -1) {
                        cache[i][j] = two;
                        continue;
                    }
                    if (two == -1) {
                        cache[i][j] = one;
                        continue;
                    }
                    cache[i][j] = Math.min(one, two);
                }
            }
        }
        return cache[coins.length][amount];
    }

    //518. 零钱兑换 II
    public int change(int amount, int[] coins) {
        int[][] cache = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            cache[i][0] = 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (i == 1) {
                    int rest = j % coins[i - 1];
                    if (rest > 0) {
                        cache[1][j] = 0;
                    } else {
                        cache[1][j] = 1;
                    }
                } else {
                    int one = cache[i - 1][j];
                    int two = 0;
                    boolean more = j >= coins[i - 1];
                    if (more) {
                        two = cache[i][j - coins[i - 1]];
                    }
                    cache[i][j] = one + two;
                }
            }
        }
        return cache[coins.length][amount];
    }


    /**
     * 93 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        //先快速判断
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar < '0' || aChar > '9') {
                return result;
            }
        }
        test(chars, s, "", 4, 0, result);
        return result;
    }
    private void test(char[] chars, String s, String pre, int num, int start, List<String> list) {
        if (num == 1) {
            String substring = s.substring(start);
            if (substring.length() > 0
                    && substring.length() < 4) {
                if (substring.length() > 1 && substring.charAt(0) == '0') {
                    return;
                }
                int i = Integer.parseInt(substring);
                if (i <= 255) {
                    list.add(pre + substring);
                }
            }
        } else {
            String v = "";
            for (int i = start; i < chars.length; i++) {
                v += chars[i];
                if (Integer.parseInt(v) > 255) {
                    break;
                }
                test(chars, s, pre + v + ".", num - 1, i + 1, list);
                if (start == i && chars[i] == '0') {
                    break;
                }
            }
        }
    }


    /**
     * 91 解码方法
     */
    public int numDecodings(String s) {
        if (s.length() < 1) {
            return 0;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        int[] arr = new int[s.length()];
        arr[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            int temp = (s.charAt(i - 1) - 48) * 10 + s.charAt(i) - 48;
            int num = 0;
            if (temp < 27 && temp > 9) {
                num = i == 1 ? 1 : arr[i - 2];
            }
            if ((temp % 10) != 0) {
                num += arr[i - 1];
            }
            arr[i] = num;
        }
        return arr[s.length() - 1];
    }

    //"/a/./b/../../c/"
    //71
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] arr = path.split("/");
        for (String s : arr) {
            if ("".equals(s) || ".".equals(s)) {
                continue;
            }
            if ("..".equals(s)) {
                if (stack.size() > 0) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }
        return "/" + String.join("/", stack);
    }

    //394
    //"abc3[cd]xyz"
    // "3[a2[c]]"
    public String decodeString(String s) {
        StringBuilder numsSb = new StringBuilder();
        StringBuilder strsSb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                numsSb.append(c);
            } else if (c == '['){
                if (strsSb.length() > 0) {
                    stack.push(strsSb.toString());
                    strsSb.delete(0, strsSb.length());
                }
                stack.push(numsSb.toString());
                numsSb.delete(0, numsSb.length());
            } else if (c == ']') {
                //先入栈
                if (strsSb.length() > 0) {
                    stack.push(strsSb.toString());
                    strsSb.delete(0, strsSb.length());
                }
                //开始出栈
                int num = 0;
                while (stack.size() > 0) {
                    String pop = stack.pop();
                    if (pop.length() > 0 && pop.charAt(0) >= '0' && pop.charAt(0) <= '9') {
                        num = Integer.parseInt(pop);
                        break;
                    }
                    temp.insert(0, pop);
                }
                if (temp.length() > 0 && num > 0) {
                    String repeat = temp.toString().repeat(num);
                    stack.push(repeat);
                }
                temp.delete(0, temp.length());
            } else {
                strsSb.append(c);
            }
        }
        return String.join("", stack) + strsSb;
    }

    //416 [1,5,11,5]
    // [1,2,4,6] 7 7  8 9   9 10 14
    // f(x)(y) x:index y:容量 f(x,y) = f(x - 1)(y) || f(x - 1)(y - w[i])
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int t = sum / 2;
        boolean[][] arr = new boolean[nums.length + 1][t + 1];
        for (int i = 0; i < nums.length + 1; i++) {
            arr[i][0] = true;
        }
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 1; j < t + 1; j++) {
                if (nums[i - 1] > j) {
                    arr[i][j] = arr[i - 1][j];
                } else {
                    arr[i][j] = arr[i - 1][j] || arr[i - 1][j - nums[i - 1]];
                }
            }
        }
        return arr[nums.length][t];
    }

    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int t = sum / 2;
        boolean[] arr = new boolean[t + 1];
        arr[0] = true;
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = t; j > 0; j--) {
                if (!arr[j] && nums[i - 1] <= j) {
                    arr[j] = arr[j - nums[i - 1]];
                }
                if (arr[j] && j == t) {
                    return true;
                }
            }
        }
        return arr[t];
    }


    //437
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return pathSumMid(root, targetSum, 0, map);
    }

    public int pathSumMid(TreeNode root, long targetSum, long sum, Map<Long, Integer> map) {
        if (root == null) {
            return 0;
        }
        int num = 0;
        sum += root.val;
        num += map.getOrDefault(sum - targetSum, 0);
        map.merge(sum, 1, Integer::sum);
        num += pathSumMid(root.left, targetSum, sum, map);
        num += pathSumMid(root.right, targetSum, sum, map);
        map.put(sum, map.getOrDefault(sum, 1) - 1);
        return num;
    }

    public int testPre(int[] arr, int target) {
        int sum = 0;
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int j : arr) {
            sum += j;
            result += map.getOrDefault(sum - target, 0);
            map.merge(sum, 1, Integer::sum);
        }
        return result;
    }

    //494 nums = [1,1,1,1,1], target = 3
    // f(x, y) = f(x - 1, y + nums[i]) + f(x - 1, y - nums[i])  x:index y:target
    // f(0, nums[0]) = 1 f(0, -nums[0]) = 1  f(1)
    // f(x, y) = f(x - 1, y - num[i]) + f(x - 1, num[i]) || f(x - 1, num[i])
    public int findTargetSumWays(int[] nums, int target) {
        //return findTargetSumWaysDfs(nums, target, nums.length - 1);
        System.out.println(findTargetSumWaysDfs(nums, target, nums.length - 1));
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || ((sum - target) & 1) == 1) {
            return 0;
        }
        int seg = (sum - target) / 2;
        int[] arr = new int[seg + 1];
        arr[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = seg; j >= 0; j--) {
                if (j < nums[i - 1]) {
                    arr[j] = arr[j];
                } else {
                    arr[j] = arr[j] + arr[j - nums[i - 1]];
                }
            }
        }
        return arr[seg];
    }
    public int findTargetSumWaysDfs(int[] nums, int target, int start) {
        if (start == -1) {
            if (0 == target) {
                return 1;
            }
            return 0;
        }
        return findTargetSumWaysDfs(nums, target + nums[start], start - 1)
                + findTargetSumWaysDfs(nums, target - nums[start], start - 1);
    }


    //207 numCourses = 2, prerequisites = [[1,0],[0,1]]
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> listMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> list = listMap.get(prerequisite[1]);
            if (list == null) {
                list = new ArrayList<>();
                list.add(prerequisite[0]);
                listMap.put(prerequisite[1], list);
            } else {
                list.add(prerequisite[0]);
            }
        }
        int[] arr = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (arr[i] == 0) {
                //find
                boolean flag = canFinishDfs(listMap, arr, i);
                if (!flag) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean canFinishDfs(Map<Integer, List<Integer>> listMap, int[] arr, int key) {
        arr[key] = 1;
        List<Integer> list = listMap.get(key);
        if (list != null && list.size() > 0) {
            for (Integer item : list) {
                if (arr[item] == 1) {
                    return false;
                }
                if (arr[item] == 0) {
                    boolean flag = canFinishDfs(listMap, arr, item);
                    if (!flag) {
                        return false;
                    }
                }
            }
        }
        arr[key] = 2;
        return true;
    }


    // 438
    public List<Integer> findAnagrams(String s, String p) {
        return null;
    }

    //406
    public int[][] reconstructQueue(int[][] people) {
        //先按照身高降序排序
        Arrays.sort(people, (o1, o2) -> {
            if (o2[0] == o1[0]) {
                return o1[1] - o2[1];
            } else {
                return o2[0] - o1[0];
            }
        });
        for (int i = 1; i < people.length; i++) {
            if (i == people[i][1]) {
                continue;
            }
            if (i > people[i][1]) {
                //需要前移
                int diff = i - people[i][1];
                int temp = i;
                int[] pre = people[i];
                while (diff > 0 && temp > 0) {
                    people[temp] = people[temp - 1];
                    --temp;
                    --diff;
                }
                people[temp] = pre;
            }
        }
        return people;
    }

    //647
    // "aaa" 6 abc 3
    // f(x, y) = f(x + 1, y - 1) || (arr[x] == arr[y] && y - x < 2)
    public int countSubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        boolean[][] arr = new boolean[s.length()][s.length()];
        arr[0][0] = true;
        int sum = 1;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    continue;
                }
                if (i - j < 2) {
                    arr[j][i] = true;
                } else {
                    arr[j][i] = arr[j + 1][i - 1];
                }
                if (arr[j][i]) {
                    ++sum;
                }
            }
        }
        return sum;
    }

    //739
    //输入: temperatures = [73,74,75,71,69,72,76,73]
    //输出:[1,1,4,2,1,1,0,0]
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return result;
    }
    //239
    //输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
    //输出：[3,3,5,5,6,7]
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        Integer v = map.firstKey();
        res[0] = v;
        for (int i = 1; i < nums.length && i < res.length; i++) {
            map.merge(nums[i + k - 1], 1, Integer::sum);
            int size = map.get(nums[i - 1]);
            if (size > 1) {
                map.put(nums[i - 1], size - 1);
            } else {
                map.remove(nums[i - 1]);
            }
            res[i] = map.firstKey();
        }
        return res;
    }
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            } else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }
        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }

    //309
    public int maxProfit(int[] prices) {
        int[][] res = new int[prices.length][3];
        res[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            res[i][0] = Math.max(res[i - 1][0], res[i - 1][1] + prices[i]);
            res[i][1] = Math.max(res[i - 1][1], res[i - 1][2] - prices[i]);
            res[i][2] = res[i - 1][0];
        }
        return Math.max(res[prices.length - 1][0], res[prices.length - 1][2]);
    }
    public int maxProfit1(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int a,b,c,d,e,f;
        a = c = 0;
        b = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            d = Math.max(a, b + prices[i]);
            e = Math.max(b, c - prices[i]);
            f = a;
            a = d;
            b = e;
            c = f;
        }
        return Math.max(a, b);
    }
    public int maxProfitDfs(int[] prices, int index, boolean pre, boolean buy, int sum) {
        if (prices.length == index) {
            return sum;
        }
        if (pre) {
            return maxProfitDfs(prices, index + 1, false, buy, sum);
        } else {
            if (buy) {
                //买入状态
                return Math.max(maxProfitDfs(prices, index + 1, false, true, sum),
                        maxProfitDfs(prices, index + 1, true, false, sum + prices[index]));
            } else {
                return Math.max(maxProfitDfs(prices, index + 1, false, true, sum - prices[index]),
                        maxProfitDfs(prices, index + 1, false, false, sum));
            }
        }
    }

    // 234
    public boolean isPalindrome(ListNode head) {
        LinkedList<ListNode> queue = new LinkedList<>();
        ListNode temp = head;
        while (temp != null) {
            queue.add(temp);
            temp = temp.next;
        }
        while (queue.size() > 0) {
            if (queue.size() == 1) {
                return true;
            }
            if (queue.pollLast().val != queue.pollFirst().val) {
                return false;
            }
        }
        return true;
    }

    //347
    // nums = [1,1,1,2,2,3], k = 2   res 12
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        map.forEach((key, v) -> {
            priorityQueue.offer(new int[]{key, v});
        });
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll()[0];
        }
        return res;
    }

    //240
    //输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
    //输出：true
    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0;
        int end = matrix.length - 1;
        while (true) {
            while (end >= start) {
                int mid = (start + end) / 2;
                if (matrix[mid][0] == target) {
                    return true;
                }
                if (matrix[mid][0] > target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if (end == -1) {
                return false;
            }
            int k = 0;
            int j = matrix[end].length - 1;
            while (j >= k) {
                int mid = (k + j) / 2;
                if (matrix[end][mid] == target) {
                    return true;
                }
                if (matrix[end][mid] > target) {
                    j = mid - 1;
                } else {
                    k = mid + 1;
                }
            }
            if (j == start) {

            }
            start = k;

        }
    }
    private boolean testMiddleSearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (end >= start) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return true;
            }
            if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    //399
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        double[] res = new double[queries.size()];
        Map<String, Integer> ref = new HashMap<>();
        int[] parent = new int[equations.size() * 2];
        double[] weight = new double[equations.size() * 2];
        for (int i = 0; i < equations.size() * 2; i++) {
            parent[i] = i;
            weight[i] = 1.0;
        }
        int count = 0;
        int index = 0;
        for (List<String> equation : equations) {
            String left = equation.get(0);
            String right = equation.get(1);
            if (!ref.containsKey(left)) {
                ref.put(left, count++);
            }
            if (!ref.containsKey(right)) {
                ref.put(right, count++);
            }
            Integer x = ref.get(left);
            Integer y = ref.get(right);
            int leftRoot = find(weight, parent, x);
            int rightRoot = find(weight, parent, y);
            if (leftRoot != rightRoot) {
                parent[leftRoot] = rightRoot;
                weight[leftRoot] = weight[y] * values[index] / weight[x];
            }
            ++index;
        }
        for (int i = 0; i < res.length; i++) {
            List<String> item = queries.get(i);
            Integer left = ref.get(item.get(0));
            Integer right = ref.get(item.get(1));
            if (left == null || right == null) {
                res[i] = -1.0;
            } else {
                int leftParent = find(weight, parent, left);
                int rightParent = find(weight, parent, right);
                if (leftParent == rightParent) {
                    res[i] = weight[left] / weight[right];
                } else {
                    res[i] = -1.0;
                }
            }
        }
        return res;
    }
    private int find(double[] weight, int[] parent, int key) {
        if (parent[key] != key) {
            int parentKey = parent[key];
            parent[key] = find(weight, parent, parentKey);
            weight[key] *= weight[parentKey];
        }
        return parent[key];
    }

    //72
    // word1 = "horse", word2 = "ros" 3  hor ro  horse ros min(2 + arr[len1 -1][len2 - 1], 1 + arr[len1][len2-1])
    public int minDistance(String word1, String word2) {
        int[][] arr = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word2.length() + 1; i++) {
            arr[0][i] = i;
        }
        for (int i = 1; i < word1.length() + 1; i++) {
            arr[i][0] = i;
            char a = word1.charAt(i - 1);
            for (int j = 1; j < word2.length() + 1; j++) {
                char b = word2.charAt(j - 1);
                if (a == b) {
                    arr[i][j] = arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.min(Math.min(1 + arr[i - 1][j - 1], 1 + arr[i - 1][j]), 1 + arr[i][j - 1]);
                }
            }
        }
        return arr[word1.length()][word2.length()];
    }
    // 2 5 6
    // 3 5 6
    // 84  [2,4] 4  [2,1,5,6,5,3] 10 f(i,j) = f(i,j-1) || f(j)
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? heights.length : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }
        return max;
    }

    // 85
    //输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    //输出：6
    public int maximalRectangle(char[][] matrix) {
        int[][] left = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < matrix[0].length; i++) {
            stack.clear();
            int[] leftItem = new int[matrix.length];
            int[] rightItem = new int[matrix.length];
            Arrays.fill(rightItem, matrix.length);
            for (int j = 0; j < matrix.length; j++) {
                while (!stack.isEmpty() && left[stack.peek()][i] > left[j][i]) {
                    rightItem[stack.peek()] = j;
                    stack.pop();
                }
                leftItem[j] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(j);
            }
            for (int j = 0; j < matrix.length; j++) {
                res = Math.max(res, (rightItem[j] - leftItem[j] - 1) * left[j][i]);
            }
        }
        return res;
    }



    //118
    //输入: numRows = 5
    //输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
    // f(x, y) = f(x - 1, y - 1) + f(x - 1, y)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> pre = null;
        for (int i = 1; i <= numRows; i++) {
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    item.add(1);
                    continue;
                }
                item.add(pre.get(j - 1) + pre.get(j));
            }
            pre = item;
            result.add(item);
        }
        return result;
    }


    //301 ()))(()
    //：s = "()())()" ()(()  (((())((((()
    public List<String> removeInvalidParentheses(String s) {
        int start = 0;
        int end = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '(') {
                ++start;
            } else {
                break;
            }
        }
        for (int j = s.length() - 1; j >= 0; j--) {
            if (s.charAt(j) != ')') {
                --end;
            } else {
                break;
            }
        }
        if (start >= end) {
            return new ArrayList<>();
        }
        for (int i = start; i <= end; i++) {

        }
        return null;
    }

    //输入：nums = [3,1,5,8] 1 2 1 | 3 5   r(n) = max(r(n-1))
    // f(0) * f(1) + f(1)  || f(0) * f(1) + f(0)
    // f(0) * f(1) + f(12) || f(1) * f(2) * f(0) + f(02) || f(1) * f(2) + (01)
    // f(x,y) = [value, index] f(0,1) f(0,2) = max(f) x => n!
    //输出：167
    //312
    public int maxCoins(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        Test1 instance = new Test1();
        char[][] arr = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        String str = "[[\"1\",\"0\",\"1\",\"0\",\"0\"],[\"1\",\"0\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"0\",\"0\",\"1\",\"0\"]]";
        System.out.println(str.replaceAll("\\[", "{").replaceAll("]", "}").replaceAll("\"", "'"));
        System.out.println(instance.maximalRectangle(arr));
        System.out.println(instance.generate(6));
    }
}

package com.huyong.study.algorithm.leetcode.middle;

import com.huyong.study.algorithm.leetcode.entity.TreeNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

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

    //494. 目标和
    public int findTargetSumWays(int[] nums, int target) {
        return 0;
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();

        int[] arr = {2,7,9,3,1};

    }



}

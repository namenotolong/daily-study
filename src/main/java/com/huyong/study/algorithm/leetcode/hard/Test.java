package com.huyong.study.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyong
 */
public class Test {


    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        if (result.length == 0) {
            return 0;
        }
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (rightIndex == nums2.length) {
                result[i] = nums1[leftIndex];
                ++leftIndex;
                continue;
            }
            if (leftIndex == nums1.length) {
                result[i] = nums2[rightIndex];
                ++rightIndex;
                continue;
            }
            if (nums1[leftIndex] > nums2[rightIndex]) {
                result[i] = nums2[rightIndex];
                ++rightIndex;
            } else {
                result[i] = nums1[leftIndex];
                ++leftIndex;
            }
        }
        if (result.length == 1) {
            return result[0];
        }
        if ((result.length & 1) == 1) {
            return result[result.length / 2];
        } else {
            return (result[result.length / 2] + result[result.length / 2 - 1]) * 1.0 / 2;
        }
    }

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     * 请你找出并返回这两个正序数组的 中位数 。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] more = nums1.length > nums2.length ? nums1 : nums2;
        int[] less = nums1 == more ? nums2 : nums1;
        int start = 0;
        int end = more.length - 1;
        int[] arr = new int[2];
        while (start <= end) {
            int mid = (start + end) / 2;
            int value = more[mid];
            middleSearch(value, less, arr);
            int leftCount = mid + arr[0];
            int rightCount = more.length - mid - 1 + arr[1];
            //遇到相同直接返回
            if (leftCount == rightCount) {
                return value;
            }
            //偶数  需要找到两个
            if (leftCount - rightCount == 1 || leftCount - rightCount == -1) {
                List<Integer> list = new ArrayList<>(4);
                //定位第二值应该往右还是左查找
                if (leftCount > rightCount) {
                    if (arr[0] > 0) {
                        list.add(less[arr[0] - 1]);
                    }
                    if (mid > 0) {
                        list.add(more[mid - 1]);
                    }
                } else  {
                    if (arr[0] < less.length) {
                        list.add(less[arr[0]]);
                    }
                    if (mid < more.length - 1) {
                        list.add(more[mid + 1]);
                    }
                }

                if (list.size() == 0) {
                    return value;
                }
                int value2 = list.get(0);
                for (Integer integer : list) {
                    //有相同临界值  直接返回
                    if (integer == value) {
                        return value;
                    }
                    //返回和目标值最接近的值
                    value2 = Math.abs(value - integer) > Math.abs(value - value2) ? value2 : integer;
                }
                return (value + value2) * 1.0 / 2;
            }
            if (leftCount > rightCount) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        //在more没有找到  但是确定了more的位置
        int index = (more.length + less.length) / 2 - start;
        if (((more.length + less.length) & 1) == 1 || index == 0) {
            return less[index];
        }
        return (less[index] + less[index - 1]) * 1.0 / 2;
    }
    public void middleSearch(int value, int[] arr, int[] result) {
        if (arr.length == 0) {
            result[0] = 0;
            result[1] = 0;
            return;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int midValue = arr[mid];
            if (midValue == value) {
                result[0] = mid + 1;
                result[1] = arr.length - mid;
                return;
            }
            if (midValue > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (start > arr.length) {
            start = arr.length;
        }
        if (start < 0) {
            start = 0;
        }
        result[0] = start;
        result[1] = arr.length - start;
    }
    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     */
    public String longestPalindrome(String s) {

        return s;
    }

    /**
     * 给定一个字符串s和一些 长度相同 的单词words 。
     * 找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
     * 注意子串要与words 中的单词完全匹配，中间不能有其他字符 ，
     * 但不需要考虑words中单词串联的顺序。
     */
    public List<Integer> findSubstring(String s, String[] words) {
        return null;
    }


    /**
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新
     * 排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须 原地 修改，只允许使用额外常数空间。
     */
    public void nextPermutation(int[] nums) {
        int start = 0;
        if (nums.length > 2) {
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[i] > nums[i - 1]) {
                    int k;
                    for (k = i; k < nums.length && nums[k] > nums[i - 1]; k++) {
                    }
                    if (k >= nums.length || nums[k] <= nums[i - 1]) {
                        --k;
                    }
                    int temp = nums[i - 1];
                    nums[i - 1] = nums[k];
                    nums[k] = temp;

                    int mid = k;
                    while (k > i && nums[k] > nums[k - 1]) {
                        temp = nums[k];
                        nums[k] = nums[k - 1];
                        nums[k - 1] = temp;
                        --k;
                    }
                    k = mid;
                    while (k < nums.length - 1 && nums[k] < nums[k + 1]) {
                        temp = nums[k];
                        nums[k] = nums[k + 1];
                        nums[k + 1] = temp;
                        ++k;
                    }
                    start = i;
                    break;
                }
            }
        }
        int j = nums.length - 1;
        while (start < j) {
            int temp = nums[start];
            nums[start] = nums[j];
            nums[j] = temp;
            ++start;
            --j;
        }
    }

    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     */
    public int longestValidParentheses(String s) {
        int max = 0;
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                ++leftCount;
            } else {
                ++rightCount;
            }
            if (rightCount > leftCount) {
                leftCount = rightCount = 0;
            }
            if (leftCount == rightCount && leftCount > max) {
                max = leftCount;
            }
        }
        leftCount = rightCount = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                ++leftCount;
            } else {
                ++rightCount;
            }
            if (leftCount > rightCount) {
                leftCount = rightCount = 0;
            }
            if (leftCount == rightCount && leftCount > max) {
                max = leftCount;
            }
        }
        return 2 * max;
    }



    public int middleSearch2(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        if (nums[0] < nums[nums.length - 1]) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (right - left == 1) {
                return right;
            }
            int mid = (left + right) / 2;
            if (nums[mid] > nums[0]) {
                left = mid;
            } else if (nums[mid] < nums[0]) {
                if (mid == 0) {
                    return 0;
                }
                if (nums[mid] < nums[mid - 1]) {
                    return mid;
                }
                right = mid;
            }
        }
        return left;
    }


    public int search(int[] nums, int target) {
        int mid = middleSearch2(nums);
        int i = middleSearch(nums, target, 0, mid);
        if (i != -1) {
            return i;
        }
        return middleSearch(nums, target, mid, nums.length - 1);
    }

    public int middleSearch(int[] nums, int target, int start, int end) {
        if (nums.length < 1) {
            return -1;
        }
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (end == start) {
                return -1;
            }
            if (end - start == 1) {
                if (nums[start] == target) {
                    return start;
                }
                if (nums[end] == target) {
                    return end;
                }
                return -1;
            }
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Test test = new Test();
        int[] ints = {1};
        System.out.println(test.searchInsert(ints, 0));
    }
    public int searchInsert(int[] nums, int target) {
        if (nums.length < 1) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (end == start) {
                if (nums[mid] > target) {
                    return mid - 1 < 0 ? 0 : mid - 1;
                } else {
                    return mid + 1;
                }
            }
            if (end - start == 1) {
                if (nums[start] == target) {
                    return start;
                }
                if (nums[end] == target) {
                    return end;
                }
                return target > nums[end] ? end + 1 : target > nums[start] ? start + 1 : start - 1 < 0 ? 0 : start - 1 ;
            }
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return start;
    }
    public int[] searchRange(int[] nums, int target) {
        int mid = middleSearch(nums, target, 0, nums.length - 1);
        int[] arr = new int[2];
        if (mid == -1) {
            arr[0] = -1;
            arr[1] = -1;
            return arr;
        }
        int preLeft = mid;
        int left;
        while (true) {
            left = middleSearch(nums, target, 0, preLeft);
            if (left == preLeft) {
                break;
            }
            preLeft = left;
        }
        int preRight = mid;
        int right;
        while (true) {
            right = middleSearch(nums, target, preRight, nums.length);
            if (right == preRight) {
                break;
            }
            preRight = right;
        }
        arr[0] = left;
        arr[1] = right;
        return arr;
    }
}

package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyong
 */
public class Test {
    public static void main(String[] args) {
        int[] nums1 = {2};
        int[] nums2 = {1};
        System.out.println(new Test().findMedianSortedArrays2(nums1, nums2));
        System.out.println(new Test().findMedianSortedArrays(nums1, nums2));
    }

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
}

package com.wz.lists;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * <p>
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(nums, 0));
        System.out.println(search(nums, 3));

        nums = new int[]{3, 1};
        System.out.println(search(nums, 1));
    }

    /**
     * 找到发生旋转的下标，然后分别对两侧的数组进行二分查找
     */
    public static int search(int[] nums, int target) {
        int pivot = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                pivot = i;
                break;
            }
        }
        if (pivot == -1) {
            return search(nums, target, 0, nums.length - 1);
        }
        int result = search(nums, target, 0, pivot - 1);
        if (result != -1) {
            return result;
        }
        return search(nums, target, pivot, nums.length - 1);
    }

    private static int search(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

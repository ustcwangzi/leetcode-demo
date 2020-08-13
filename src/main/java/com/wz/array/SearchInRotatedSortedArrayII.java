package com.wz.array;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * You are given a target value to search. If found in the array return true, otherwise return false.
 * <p>
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * <p>
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 */
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        System.out.println(search(nums, 0));

        nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        System.out.println(search(nums, 3));

        nums = new int[]{3, 1};
        System.out.println(search(nums, 1));
    }

    /**
     * 思路与{@link SearchInRotatedSortedArray}类似，但这里可能存在重复元素
     * 例如[1,1,3]和[1,3,1,1,1]，对于这两种情况中间值等于最左值时，目标值3既可以在左边又可以在右边
     * 对于左侧有序和右侧有序的情况还是之前的处理方案，当nums[left] == nums[mid]时，直接left++
     */
    public static boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[left] < nums[mid]) {
                // 左侧有序
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] > nums[mid]) {
                // 右侧有序
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;
            }
        }
        return false;
    }
}

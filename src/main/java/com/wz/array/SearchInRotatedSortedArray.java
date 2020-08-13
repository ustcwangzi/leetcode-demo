package com.wz.array;

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
     * 因为只有一个旋转点，所以一分为二后，肯定有一半是有序的
     * 所以，还是可以用二分法，不过要先判断左半边有序还是右半边有序
     * 如果左半边有序，则直接将目标和左半边的边界比较，就知道目标在不在左半边了，如果不在左半边肯定在右半边
     * 如果右半边有序，则直接将目标和右半边的边界比较，就知道目标在不在右半边了，如果不在右半边肯定在左半边
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                // 左半边有序
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右半边有序
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

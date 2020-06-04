package com.wz.lists;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 *              or index number 5 where the peak element is 6.
 *
 * Follow up: Your solution should be in logarithmic complexity.
 */
public class FindPeakElement {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(findPeakElement(nums));

        nums = new int[]{1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums));
    }

    /**
     * 用二分查找的思路
     * nums[mid] < nums[mid + 1] 时，说明峰值在右侧，否则说明峰值在左侧
     */
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}

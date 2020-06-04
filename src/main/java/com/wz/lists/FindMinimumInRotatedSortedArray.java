package com.wz.lists;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 * Input: [3,4,5,1,2]
 * Output: 1
 *
 * Example 2:
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */
public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 5, 1, 2};
        System.out.println(findMin(nums));

        nums = new int[]{2, 3, 4, 5, 1};
        System.out.println(findMin(nums));
    }

    /**
     * 思路与{@link SearchInRotatedSortedArray}类似
     * 通过左边界和中间的大小关系来得到左边或者右边有序的信息
     * 如果左半边有序，那么左边第一个元素就是左半边最小，可以和当前最小相比取小的，然后走向右半边
     * 如果右半边有序，那么右边第一个元素就是右半边最小，可以和当前最小相比取小的，然后走向左半边
     */
    public static int findMin(int[] nums) {
        if (nums.length <= 1) {
            return nums.length == 0 ? 0 : nums[0];
        }

        int left = 0, right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        int result = nums[0], mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[left] <= nums[mid]) {
                // 左侧有序
                result = Math.min(result, nums[left]);
                left = mid + 1;
            } else {
                // 右侧有序
                result = Math.min(result, nums[mid]);
                right = mid - 1;
            }
        }
        return result;
    }
}

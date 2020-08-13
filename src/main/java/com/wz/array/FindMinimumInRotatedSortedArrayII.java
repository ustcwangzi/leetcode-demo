package com.wz.array;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * The array may contain duplicates.
 *
 * Example 1:
 * Input: [1,3,5]
 * Output: 1
 *
 * Example 2:
 * Input: [2,2,2,0,1]
 * Output: 0
 */
public class FindMinimumInRotatedSortedArrayII {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 2, 0, 1};
        System.out.println(findMin(nums));

        nums = new int[]{2, 0, 1, 2, 2};
        System.out.println(findMin(nums));
    }

    /**
     * 思路与{@link FindMinimumInRotatedSortedArray}类似
     * 只是当nums[mid] == nums[right]时，无法判断到底该去左边还是右边，直接right--，略过一个相同数字
     */
    public static int findMin(int[] nums) {
        if (nums.length <= 1) {
            return nums.length == 0 ? 0 : nums[0];
        }

        int left = 0, right = nums.length - 1, mid;
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                // 没有旋转或者旋转点在左边
                right = mid;
            } else if (nums[mid] > nums[right]) {
                // 旋转点在右边
                left = mid + 1;
            } else {
                right--;
            }
        }
        return nums[right];
    }

}

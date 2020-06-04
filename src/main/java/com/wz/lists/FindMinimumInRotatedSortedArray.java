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

        nums = new int[]{4, 1, 2, 3};
        System.out.println(findMin(nums));
    }

    /**
     * 思路与{@link SearchInRotatedSortedArray}类似
     * 通过右边界和中间的大小关系来得到左边或者右边有序的信息，这里使用右边界因为只有两个元素时，left == mid < right
     * 若数组没有旋转或者旋转点在左边的时候，中间值是一定小于右边界值的，所以要去左半边继续搜索，反之则去右半段查找
     */
    public static int findMin(int[] nums) {
        if (nums.length <= 1) {
            return nums.length == 0 ? 0 : nums[0];
        }

        int left = 0, right = nums.length - 1, mid;
        // 未发生旋转
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                // 没有旋转或者旋转点在左边
                right = mid;
            } else {
                // 旋转点在右边
                left = mid + 1;
            }
        }
        return nums[right];
    }
}

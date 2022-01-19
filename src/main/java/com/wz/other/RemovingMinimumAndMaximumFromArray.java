package com.wz.other;

/**
 * You are given a 0-indexed array of distinct integers nums.
 * There is an element in nums that has the lowest value and an element that has the highest value.
 * We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.
 * A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.
 * Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.
 *
 * Example 1:
 * Input: nums = [2,10,7,5,4,1,8,6]
 * Output: 5
 * Explanation:
 * The minimum element in the array is nums[5], which is 1.
 * The maximum element in the array is nums[1], which is 10.
 * We can remove both the minimum and maximum by removing 2 elements from the front and 3 elements from the back.
 * This results in 2 + 3 = 5 deletions, which is the minimum number possible.
 *
 * Example 2:
 * Input: nums = [0,-4,19,1,8,-2,-3,5]
 * Output: 3
 * Explanation:
 * The minimum element in the array is nums[1], which is -4.
 * The maximum element in the array is nums[2], which is 19.
 * We can remove both the minimum and maximum by removing 3 elements from the front.
 * This results in only 3 deletions, which is the minimum number possible.
 *
 * Example 3:
 * Input: nums = [101]
 * Output: 1
 * Explanation:
 * There is only one element in the array, which makes it both the minimum and maximum element.
 * We can remove it with 1 deletion.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 105
 * 2. -105 <= nums[i] <= 105
 * 3. The integers in nums are distinct.
 */
public class RemovingMinimumAndMaximumFromArray {
    public static void main(String[] args) {
        System.out.println(minimumDeletions(new int[]{2, 10, 7, 5, 4, 1, 8, 6}));
        System.out.println(minimumDeletions(new int[]{0, -4, 19, 1, 8, -2, -3, 5}));
    }

    /**
     * 只有三种情况：都从左边删除、都从右边删除、从左右两边删除
     * 先遍历数组求出最小值、最大值所在的位置 min、max，如果 min > max，则进行交换，方便后续处理，然后求出各种情况的最小值
     * - 全部从左边删除需要移除的元素个数： max+1
     * - 全部从右边删除需要移除的元素个数： n-min
     * - 从左右两边删除需要移除的元素个数： min+1+n-max
     */
    public static int minimumDeletions(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int min = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
            if (nums[i] < nums[min]) {
                min = i;
            }
        }
        if (min > max) {
            min = min ^ max;
            max = min ^ max;
            min = min ^ max;
        }
        return Math.min(max + 1, Math.min(nums.length - min, min + 1 + nums.length - max));
    }
}

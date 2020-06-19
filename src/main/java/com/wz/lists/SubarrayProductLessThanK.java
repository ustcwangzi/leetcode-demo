package com.wz.lists;

/**
 * Your are given an array of positive integers nums.
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 *
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class SubarrayProductLessThanK {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(numSubarrayProductLessThanK(nums, 3));

        nums = new int[]{10, 5, 2, 6};
        System.out.println(numSubarrayProductLessThanK(nums, 100));
    }

    /**
     * 维护一个数字乘积刚好小于k的滑动窗口，用变量 left 来记录其左边界的位置，右边界i就是当前遍历到的位置
     * 遍历原数组，用 product 乘上当前遍历到的数字，然后进行 while 循环，如果 prod 大于等于k，则滑动窗口的左边界left 需要向右移动一位，
     * 删除最左边的数字，那么少了一个数字，乘积就会改变，所以用 product 除以最左边的数字
     * 当确定了窗口的大小后，就可以统计子数组的个数了，就是窗口的大小。比如 [5 2 6] 这个窗口，k还是 100，右边界刚滑到6这个位置，
     * 这个窗口的大小就是包含6的子数组乘积小于k的个数，即 [6], [2 6], [5 2 6]，正好是3个。
     * 所以窗口每次向右增加一个数字，然后左边去掉需要去掉的数字后，窗口的大小就是新的子数组的个数，每次加到结果 result 中即可
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0, result = 0, product = 1;
        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
            // 乘积不满足，left右移
            while (product >= k && left <= i) {
                product /= nums[left++];
            }
            result += i - left + 1;
        }

        return result;
    }
}

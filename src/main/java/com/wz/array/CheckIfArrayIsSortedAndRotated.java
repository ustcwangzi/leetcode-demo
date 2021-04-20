package com.wz.array;

/**
 * Given an array nums, return true if the array was originally sorted in non-decreasing order,
 * then rotated some number of positions (including zero). Otherwise, return false.
 * There may be duplicates in the original array.
 * Note: An array A rotated by x positions results in an array B of the same length such that
 * A[i] == B[(i+x) % A.length], where % is the modulo operation.
 *
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: true
 * Explanation: [1,2,3,4,5] is the original sorted array.
 * You can rotate the array by x = 3 positions to begin on the the element of value 3: [3,4,5,1,2].
 *
 * Example 2:
 * Input: nums = [2,1,3,4]
 * Output: false
 * Explanation: There is no sorted array once rotated that can make nums.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. 1 <= nums[i] <= 100
 */
public class CheckIfArrayIsSortedAndRotated {
    public static void main(String[] args) {
        System.out.println(check(new int[]{3, 4, 5, 1, 2}));
        System.out.println(check(new int[]{2, 1, 3, 4}));
    }

    /**
     * 对于 i，nums[i] 大于 nums[i+1] 的次数只能小于等于一次
     * 遍历数组，计算下降次数，注意最后一个元素需要和开始元素进行比较
     */
    public static boolean check(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[(i + 1) % nums.length]) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}

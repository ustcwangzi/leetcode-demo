package com.wz.other;

/**
 * Given a 0-indexed integer array nums, return true if it can be made strictly increasing after removing exactly one element,
 * or false otherwise. If the array is already strictly increasing, return true.
 * The array nums is strictly increasing if nums[i - 1] < nums[i] for each index (1 <= i < nums.length).
 *
 * Example 1:
 * Input: nums = [1,2,10,5,7]
 * Output: true
 * Explanation: By removing 10 at index 2 from nums, it becomes [1,2,5,7].
 * [1,2,5,7] is strictly increasing, so return true.
 *
 * Example 2:
 * Input: nums = [2,3,1,2]
 * Output: false
 * Explanation:
 * [3,1,2] is the result of removing the element at index 0.
 * [2,1,2] is the result of removing the element at index 1.
 * [2,3,2] is the result of removing the element at index 2.
 * [2,3,1] is the result of removing the element at index 3.
 * No resulting array is strictly increasing, so return false.
 *
 * Constraints:
 * 1. 2 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 1000
 */
public class RemoveOneElementToMakeTheArrayStrictlyIncreasing {
    public static void main(String[] args) {
        System.out.println(canBeIncreasing(new int[]{1, 2, 10, 5, 7}));
        System.out.println(canBeIncreasing(new int[]{2, 3, 1, 2}));
    }

    /**
     * 遍历数组寻找驼峰或者低谷，找到出现降序的位置 descIndex，若出现了两次则一定无法通过移除一个元素实现升序
     * 若 descIndex 属于低谷，则直接移除该元素即可，此时需要满足 nums[descIndex-1] < nums[descIndex+1]，如 3,4,1,5,6 移除 1
     * 若 descIndex 属于驼峰的下一个位置，则直接移除 descIndex-1 即可，此时需要满足 nums[descIndex-2] < nums[descIndex-1]，如 3,4,10,5,6 移除 10
     * 同时需要考虑边界条件：无降序出现、降序出现在 index=1 的位置（如 100,2,100）、降序出现在最后一个位置
     */
    public static boolean canBeIncreasing(int[] nums) {
        int descIndex = -1, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) {
                if (descIndex == -1) {
                    descIndex = i;
                } else {
                    return false;
                }
            }
        }
        if (descIndex == -1 || descIndex == 1 || descIndex == n - 1) {
            return true;
        }
        return nums[descIndex - 1] < nums[descIndex + 1] || nums[descIndex - 2] < nums[descIndex];
    }
}

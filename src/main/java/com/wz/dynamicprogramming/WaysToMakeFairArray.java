package com.wz.dynamicprogramming;

/**
 * You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element.
 * Notice that the index of the elements may change after the removal.
 * For example, if nums = [6,1,7,4,1]:
 * Choosing to remove index 1 results in nums = [6,7,4,1].
 * Choosing to remove index 2 results in nums = [6,1,4,1].
 * Choosing to remove index 4 results in nums = [6,1,7,4].
 * An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
 * Return the number of indices that you could choose such that after the removal, nums is fair.
 *
 * Example 1:
 * Input: nums = [2,1,6,4]
 * Output: 1
 * Explanation:
 * Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
 * Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
 * Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
 * Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
 * There is 1 index that you can remove to make nums fair.
 *
 * Example 2:
 * Input: nums = [1,1,1]
 * Output: 3
 * Explanation: You can remove any index and the remaining array is fair.
 *
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: 0
 * Explanation: You cannot make a fair array after removing any index.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^4
 */
public class WaysToMakeFairArray {
    public static void main(String[] args) {
        System.out.println(waysToMakeFair(new int[]{2, 1, 6, 4}));
    }

    /**
     * even[i] 表示 nums[0...i-1] 的偶数位置元素之和，odd[i] 表示 nums[0...i-1] 的奇数位置元素之和
     * 移除 nums[i] 之后，i 之后的元素会向前移动，也就是 i 之后的元素奇偶位置发生变化，而 i 之前的元素位置保持不变
     * 还用之前的元素下标表示的话，移除 i 位置之后，奇偶位置元素之和分别是
     * 偶数位置之和为 (nums[0...i-1] 偶数位置之和) + (nums[i+1...n-1] 奇数位置之和)
     * 奇数位置之和为 (nums[0...i-1] 奇数位置之和) + (nums[i+1...n-1] 偶数位置之和)
     * 其中 nums[i+1...n-1] 奇数位置之和 就是 odd[n] - odd[i+1]
     *     nums[i+1...n-1] 偶数位置之和 就是 even[n] - even[i+1]
     */
    public static int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] even = new int[n + 1], odd = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                even[i + 1] = nums[i] + even[i];
                odd[i + 1] = odd[i];
            } else {
                even[i + 1] = even[i];
                odd[i + 1] = nums[i] + odd[i];
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (odd[i] + even[n] - even[i + 1] == even[i] + odd[n] - odd[i + 1]) {
                result++;
            }
        }
        return result;
    }
}

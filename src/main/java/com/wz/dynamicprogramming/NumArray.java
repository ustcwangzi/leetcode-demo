package com.wz.dynamicprogramming;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * Implement the NumArray class:
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int i, int j) Return the sum of the elements of the nums array in the range [i, j] inclusive (i.e., sum(nums[i], nums[i + 1], ... , nums[j]))
 *
 * Example 1:
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 *
 * Constraints:
 * 1. 0 <= nums.length <= 10^4
 * 2. -10^5 <= nums[i] <= 10^5
 * 3. 0 <= i <= j < nums.length
 * 4. At most 104 calls will be made to sumRange.
 */
public class NumArray {
    // 记录以 i 结尾的累加和
    int[] dp;

    public NumArray(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        this.dp = nums;
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return dp[j];
        }
        return dp[j] - dp[i - 1];
    }
}

package com.wz.greedy;

/**
 * You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:
 * 1. nums.length == n
 * 2. nums[i] is a positive integer where 0 <= i < n.
 * 3. abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * 4. The sum of all the elements of nums does not exceed maxSum.
 * 5. nums[index] is maximized.
 * Return nums[index] of the constructed array.
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 *
 * Example 1:
 * Input: n = 4, index = 2,  maxSum = 6
 * Output: 2
 * Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
 * There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
 *
 * Constraints:
 * 1. 1 <= n <= maxSum <= 10^9
 * 2. 0 <= index < n
 */
public class MaximumValueAtGivenIndexInBoundedArray {
    public static void main(String[] args) {
        System.out.println(maxValue(4, 2, 6));
    }

    /**
     * 二分查找
     */
    public static int maxValue(int n, int index, int maxSum) {
        long left = 1, right = maxSum;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = n - mid + 1;
            sum += (mid - 1 + Math.max(0, mid - index - 1)) * Math.min(mid, index + 1) / 2;
            sum += (mid - 1 + Math.max(0, mid - n + index)) * Math.min(mid, n - index) / 2;
            if (sum <= maxSum) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) right;
    }
}

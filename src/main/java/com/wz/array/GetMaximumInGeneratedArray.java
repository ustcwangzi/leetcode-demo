package com.wz.array;

/**
 * You are given an integer n. An array nums of length n + 1 is generated in the following way:
 * nums[0] = 0
 * nums[1] = 1
 * nums[2 * i] = nums[i] when 2 <= 2 * i <= n
 * nums[2 * i + 1] = nums[i] + nums[i + 1] when 2 <= 2 * i + 1 <= n
 * Return the maximum integer in the array nums.
 *
 * Example 1:
 * Input: n = 7
 * Output: 3
 * Explanation: According to the given rules:
 *   nums[0] = 0
 *   nums[1] = 1
 *   nums[(1 * 2) = 2] = nums[1] = 1
 *   nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 *   nums[(2 * 2) = 4] = nums[2] = 1
 *   nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 *   nums[(3 * 2) = 6] = nums[3] = 2
 *   nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * Hence, nums = [0,1,1,2,1,3,2,3], and the maximum is 3.
 *
 * Constraints:
 * 0 <= n <= 100
 */
public class GetMaximumInGeneratedArray {
    public static void main(String[] args) {
        System.out.println(getMaximumGenerated(7));
    }

    /**
     * 直接根据题意把 nums 数组模拟出来，在过程中记录最大值即可
     */
    public static int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }

        int[] nums = new int[n + 1];
        int max = 0;
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i < nums.length; i++) {
            nums[i] = nums[i / 2];
            if (i % 2 == 1) {
                nums[i] += nums[i / 2 + 1];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }
}

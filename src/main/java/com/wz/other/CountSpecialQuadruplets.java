package com.wz.other;

/**
 * Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:
 * 1. nums[a] + nums[b] + nums[c] == nums[d], and
 * 2. a < b < c < d
 *
 * Example 1:
 * Input: nums = [1,2,3,6]
 * Output: 1
 * Explanation: The only quadruplet that satisfies the requirement is (0, 1, 2, 3) because 1 + 2 + 3 == 6.
 *
 * Example 2:
 * Input: nums = [1,1,1,3,5]
 * Output: 4
 * Explanation: The 4 quadruplets that satisfy the requirement are:
 * - (0, 1, 2, 3): 1 + 1 + 1 == 3
 * - (0, 1, 3, 4): 1 + 1 + 3 == 5
 * - (0, 2, 3, 4): 1 + 1 + 3 == 5
 * - (1, 2, 3, 4): 1 + 1 + 3 == 5
 *
 * Constraints:
 * 1. 4 <= nums.length <= 50
 * 2. 1 <= nums[i] <= 100
 */
public class CountSpecialQuadruplets {
    public static void main(String[] args) {
        System.out.println(countQuadruplets(new int[]{1, 1, 1, 3, 5}));
    }

    /**
     * 直接四层遍历
     */
    public static int countQuadruplets(int[] nums) {
        int count = 0, n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}

package com.wz.heap;

/**
 * You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer maxOperations.
 *
 * You can perform the following operation at most maxOperations times:
 * Take any bag of balls and divide it into two new bags with a positive number of balls.
 * For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
 * Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
 * Return the minimum possible penalty after performing the operations.
 *
 * Example 1:
 * Input: nums = [9], maxOperations = 2
 * Output: 3
 * Explanation:
 * - Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
 * - Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
 * The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.
 *
 * Example 2:
 * Input: nums = [2,4,8,2], maxOperations = 4
 * Output: 2
 * Explanation:
 * - Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
 * The bag with the most number of balls has 2 balls, so your penalty is 2 an you should return 2.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= maxOperations, nums[i] <= 10^9
 */
public class MinimumLimitOfBallsInBag {
    public static void main(String[] args) {
        System.out.println(minimumSize(new int[]{2, 4, 8, 2}, 4));
    }

    /**
     * 二分查找
     * 对于开销为 mid 时，通过公式 (num−1)/mid 下取整来计算 num 个球的袋子需要分解的次数
     * 求每个袋子怎么操作，怎么分球，最后剩的最多的球数 -> 直接猜测一个球数，让所有袋子里的球都满足这个值，反向计算需要的操作次数
     */
    public static int minimumSize(int[] nums, int maxOperations) {
        int min = 0, max = 1000000007;
        // 确保 mid > min, 即 min 一定是非法的
        while (min + 1 < max) {
            int mid = min + (max - min) / 2;
            // 操作次数
            int count = 0;
            for (int num : nums) {
                count += (num - 1) / mid;
            }

            if (count <= maxOperations) {
                max = mid;
            } else {
                min = mid;
            }
        }
        return max;
    }
}

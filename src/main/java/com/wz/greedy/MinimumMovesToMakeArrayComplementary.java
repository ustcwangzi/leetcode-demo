package com.wz.greedy;

/**
 * You are given an integer array nums of even length n and an integer limit. In one move,
 * you can replace any integer from nums with another integer between 1 and limit, inclusive.
 * The array nums is complementary if for all indices i (0-indexed), nums[i] + nums[n - 1 - i] equals the same number.
 * For example, the array [1,2,3,4] is complementary because for all indices i, nums[i] + nums[n - 1 - i] = 5.
 * Return the minimum number of moves required to make nums complementary.
 *
 * Example 1:
 * Input: nums = [1,2,4,3], limit = 4
 * Output: 1
 * Explanation: In 1 move, you can change nums to [1,2,2,3] (underlined elements are changed).
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
 *
 * Example 2:
 * Input: nums = [1,2,2,1], limit = 2
 * Output: 2
 * Explanation: In 2 moves, you can change nums to [2,2,2,2]. You cannot change any number to 3 since 3 > limit.
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 2 <= n <= 10^5
 * 3. 1 <= nums[i] <= limit <= 10^5
 * 4. n is even.
 */
public class MinimumMovesToMakeArrayComplementary {
    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{1, 2, 4, 3}, 4));
    }

    /**
     * 差分数组 + 扫面线
     * 差分数组是原数组相邻两数的差值构成的数组，利用差分数组，可以快速地实现对区间内所有数同时加（减）去某个数。
     * 因为差分数组维护是原数组的变化量，在每次同时加（减）操作之后，除了差分数组两端的值会发生变化，差分数组内部的值保持不变。
     * index    0   1   2   3   4   5   6   7
     * origin   0   2   5   4   9   7   10  0
     * delta        2   3   -1  5   -2  3   -10
     * 对区间 [1,4] 中的元素同时加 3
     * index    0   1   2   3   4   5   6   7
     * origin   0   5   8   7   12  7   10  0
     * delta        5   3   -1  5   -5  3   -10
     * 可以发现，当对一个区间进行增减某个值的时候，差分数组对应的区间左端点的值会同步变化，右端点的后一个值则会相反地变化。
     *
     * 由已知条件可知，互补的两数之和 S = nums[i] + nums[n-1-i] 满足 2 ≤ S ≤ 2*limit，
     * 因此将 [2, 2*limit] 中的每一个数作为互补和求出对应的操作次数，取最小值即可。假设 a、b 为 nums[i] 和 nums[n-1-i]，分五种情况：
     * 1. 2 <= S < min(a,b) + 1，需要 a 和 b 都变小，两次变动
     * 2. min(a,b) + 1 <= S < a + b，需要 max(a,b) 变小，一次变动
     * 3. a + b = S，无需变动
     * 4. a + b < S <= max(a,b) + limit，需要 min(a,b) 变大，一次变动
     * 5. max(a,b) + limit < S <= 2 * limit，需要 a 和 b 都变大，两次变动
     */
    public static int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] delta = new int[2 + limit * 2];
        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - i - 1]), b = Math.max(nums[i], nums[n - i - 1]);
            delta[2] += 2;
            delta[a + 1]--;
            delta[a + b]--;
            delta[a + b + 1]++;
            delta[b + limit + 1]++;
        }

        int cur = 0, result = n;
        // 两数之和为 i 时所需的变动次数
        for (int i = 2; i <= limit * 2; i++) {
            cur += delta[i];
            result = Math.min(result, cur);
        }
        return result;
    }
}

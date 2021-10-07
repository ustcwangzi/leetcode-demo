package com.wz.dfs;

/**
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 * 1. Choose one integer x from either the start or the end of the array nums.
 * 2. Add multipliers[i] * x to your score.
 * 3. Remove x from the array nums.
 * Return the maximum score after performing m operations.
 *
 * Example 1:
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 *
 * Example 2:
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 * Constraints:
 * 1. n == nums.length
 * 2. m == multipliers.length
 * 3. 1 <= m <= 10^3
 * 4. m <= n <= 10^5
 * 5. -1000 <= nums[i], multipliers[i] <= 1000
 */
public class MaximumScoreFromPerformingMultiplicationOperations {
    public static void main(String[] args) {
        System.out.println(maximumScore(new int[]{-5, -3, -3, -2, 7, 1}, new int[]{-10, -5, 3, 4, 6}));
    }

    /**
     * multipliers 只能桉顺序依次选取，nums 每次可以选择最左边或最右边的元素
     * 使用 DFS 计算选择最左或最右能够得到的最大结果，并选择其中的较大值
     */
    public static int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length, m = multipliers.length;
        return dfs(nums, multipliers, 0, n - 1, 0, new int[m + 1][m + 1]);
    }

    private static int dfs(int[] nums, int[] multipliers, int i, int j, int index, int[][] cache) {
        if (index >= multipliers.length) {
            return 0;
        }
        if (cache[i][index] != 0) {
            return cache[i][index];
        }
        // 选择左侧元素，左指针 i 右移，右指针 j 不变
        int left = nums[i] * multipliers[index] + dfs(nums, multipliers, i + 1, j, index + 1, cache);
        // 选择右侧元素，左指针 i 不变，右指针 j 左移
        int right = nums[j] * multipliers[index] + dfs(nums, multipliers, i, j - 1, index + 1, cache);
        return cache[i][index] = Math.max(left, right);
    }
}

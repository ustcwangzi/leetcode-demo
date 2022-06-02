package com.wz.other;

/**
 * You are given an array nums. You can rotate it by a non-negative integer k so that the array becomes
 * [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]].
 * Afterward, any entries that are less than or equal to their index are worth one point.
 * For example, if we have nums = [2,4,1,3,0], and we rotate by k = 2, it becomes [1,3,0,2,4].
 * This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3 [one point], 4 <= 4 [one point].
 * Return the rotation index k that corresponds to the highest score we can achieve if we rotated nums by it.
 * If there are multiple answers, return the smallest such index k.
 *
 * Example 1:
 * Input: nums = [2,3,1,4,0]
 * Output: 3
 * Explanation: Scores for each k are listed below:
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * So we should choose k = 3, which has the highest score.
 *
 * Example 2:
 * Input: nums = [1,3,0,2,4]
 * Output: 0
 * Explanation: nums will always have 3 points no matter how it shifts.
 * So we will choose the smallest k, which is 0.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] < nums.length
 */
public class SmallestRotationWithHighestScore {
    public static void main(String[] args) {
        System.out.println(bestRotation(new int[]{2, 3, 1, 4, 0}));
        System.out.println(bestRotation(new int[]{1, 3, 0, 2, 4}));
    }

    /**
     * 差分数组
     * 以 [2, 3, 1, 4, 0] 为例
     * A[0] = 2, 移动到 A[A[0]] = A[2], k = 3
     * A[1] = 3, 移动到 A[A[1]] = A[3], k = 3
     * A[2] = 1, 移动到 A[A[2]] = A[1], k = 1
     * A[3] = 4, 移动到 A[A[3]] = A[4], k = 4
     * A[4] = 0, 移动到 A[A[4]] = A[0], k = 4
     * 这里的 k 表示移动之后 A[i] 位置就刚好可以得分，如果 k 增大，A[i] 将不得分，k 减小，A[i] 继续得分
     * 不得分的临界值 end = (i - A[i] + n + 1) % n，得分的临界值 start = i
     * 在开始的区间 -1，结束的区间 +1，然后累加求出最值即可
     */
    public static int bestRotation(int[] nums) {
        int n = nums.length, result = 0;
        int[] delta = new int[n];
        for (int i = 0; i < n; i++) {
            delta[(i - nums[i] + 1 + n) % n]--;
        }
        for (int i = 1; i < n; i++) {
            delta[i] += delta[i - 1] + 1;
            result = delta[i] > delta[result] ? i : result;
        }
        return result;
    }
}

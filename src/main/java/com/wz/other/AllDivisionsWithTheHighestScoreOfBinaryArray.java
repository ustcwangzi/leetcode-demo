package com.wz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 0-indexed binary array nums of length n. nums can be divided at index i (where 0 <= i <= n) into two arrays (possibly empty) numsleft and numsright:
 * 1. numsleft has all the elements of nums between index 0 and i - 1 (inclusive), while numsright has all the elements of nums between index i and n - 1 (inclusive).
 * 2. If i == 0, numsleft is empty, while numsright has all the elements of nums.
 * 3. If i == n, numsleft has all the elements of nums, while numsright is empty.
 * The division score of an index i is the sum of the number of 0's in numsleft and the number of 1's in numsright.
 * Return all distinct indices that have the highest possible division score. You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [0,0,1,0]
 * Output: [2,4]
 * Explanation: Division at index
 * - 0: numsleft is []. numsright is [0,0,1,0]. The score is 0 + 1 = 1.
 * - 1: numsleft is [0]. numsright is [0,1,0]. The score is 1 + 1 = 2.
 * - 2: numsleft is [0,0]. numsright is [1,0]. The score is 2 + 1 = 3.
 * - 3: numsleft is [0,0,1]. numsright is [0]. The score is 2 + 0 = 2.
 * - 4: numsleft is [0,0,1,0]. numsright is []. The score is 3 + 0 = 3.
 * Indices 2 and 4 both have the highest possible division score 3.
 * Note the answer [4,2] would also be accepted.
 *
 * Example 2:
 * Input: nums = [0,0,0]
 * Output: [3]
 * Explanation: Division at index
 * - 0: numsleft is []. numsright is [0,0,0]. The score is 0 + 0 = 0.
 * - 1: numsleft is [0]. numsright is [0,0]. The score is 1 + 0 = 1.
 * - 2: numsleft is [0,0]. numsright is [0]. The score is 2 + 0 = 2.
 * - 3: numsleft is [0,0,0]. numsright is []. The score is 3 + 0 = 3.
 * Only index 3 has the highest possible division score 3.
 *
 * Example 3:
 * Input: nums = [1,1]
 * Output: [0]
 * Explanation: Division at index
 * - 0: numsleft is []. numsright is [1,1]. The score is 0 + 2 = 2.
 * - 1: numsleft is [1]. numsright is [1]. The score is 0 + 1 = 1.
 * - 2: numsleft is [1,1]. numsright is []. The score is 0 + 0 = 0.
 * Only index 0 has the highest possible division score 2.
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 1 <= n <= 10^5
 * 3. nums[i] is either 0 or 1.
 */
public class AllDivisionsWithTheHighestScoreOfBinaryArray {
    public static void main(String[] args) {
        System.out.println(maxScoreIndices(new int[]{0, 0, 1, 0}));
    }

    /**
     * 前缀和，preSum[i+1] = preSum[i]+nums[i]，那么
     * nums[0...i-1] 中 0 的个数为 i - preSum[i]
     * nums[i...n-1] 中 1 的个数为 preSum[n] - preSum[i]
     * 计算对于每个 i，能够得到的最高分数，更新结果即可
     */
    public static List<Integer> maxScoreIndices(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        List<Integer> result = new ArrayList<>();
        int max = -1, zeroCount, oneCount;
        for (int i = 0; i < n + 1; i++) {
            zeroCount = i - preSum[i];
            oneCount = preSum[n] - preSum[i];
            int cur = oneCount + zeroCount;
            if (cur > max) {
                result.clear();
                max = cur;
            }
            if (cur == max) {
                result.add(i);
            }
        }
        return result;
    }
}

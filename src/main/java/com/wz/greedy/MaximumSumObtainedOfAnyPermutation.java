package com.wz.greedy;

import java.util.Arrays;

/**
 * We have an array of integers, nums, and an array of requests where requests[i] = [starti, endi].
 * The ith request asks for the sum of nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]. Both starti and endi are 0-indexed.
 * Return the maximum total sum of all requests among all permutations of nums.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
 * Output: 19
 * Explanation: One permutation of nums is [2,1,3,4,5] with the following result:
 * requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
 * requests[1] -> nums[0] + nums[1] = 2 + 1 = 3
 * Total sum: 8 + 3 = 11.
 * A permutation with a higher total sum is [3,5,4,2,1] with the following result:
 * requests[0] -> nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
 * requests[1] -> nums[0] + nums[1] = 3 + 5  = 8
 * Total sum: 11 + 8 = 19, which is the best that you can do.
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 1 <= n <= 10^5
 * 3. 0 <= nums[i] <= 10^5
 * 4. 1 <= requests.length <= 10^5
 * 5. requests[i].length == 2
 * 6. 0 <= starti <= endi < n
 */
public class MaximumSumObtainedOfAnyPermutation {
    public static void main(String[] args) {
        System.out.println(maxSumRangeQuery(new int[]{1, 2, 3, 4, 5}, new int[][]{{1, 3}, {0, 1}}));
    }

    /**
     * 把所有查询区间进行统计，就可以得到每个位置出现的次数，只要把出现次数最多的位置分配给最大的数，
     * 次多的位置分配给次大的数就可以得到所有查询结果之和的最大值。也就是依次将由大到小的数值分给由大到小的出现次数
     * 注意：查询数组长度可能有 10^5，每个查询区间的范围也可能很大，为此使用 差分+前缀和 进行统计
     */
    public static int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length, mod = 1000000007;
        int[] count = new int[n];
        // 差分
        for (int[] request : requests) {
            count[request[0]]++;
            if (request[1] < n - 1) {
                count[request[1] + 1]--;
            }
        }

        // 前缀和
        for (int i = 1; i < n; i++) {
            count[i] += count[i - 1];
        }

        Arrays.parallelSort(count);
        Arrays.parallelSort(nums);
        long result = 0;
        for (int i = n - 1; i >= 0; i--) {
            result = (result + ((long) nums[i] * count[i])) % mod;
        }
        return (int) result;
    }
}

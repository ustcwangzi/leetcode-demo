package com.wz.twopointer;

/**
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 * Return the number of nice sub-arrays.
 *
 * Example 1:
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 *
 * Example 2:
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 *
 * Example 3:
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 * Constraints:
 * 1. 1 <= nums.length <= 50000
 * 2. 1 <= nums[i] <= 10^5
 * 3. 1 <= k <= nums.length
 */
public class CountNumberOfNiceSubarrays {
    public static void main(String[] args) {
        System.out.println(numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        System.out.println(numberOfSubarrays(new int[]{2, 4, 6}, 1));
        System.out.println(numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }

    /**
     * 前缀和
     * 要求全部优美子数组个数，可以分解为求每个以 nums[i] 结尾的优美子数组个数，因此需要统计满足条件的 j 使得 nums[j...i] 中奇数个数为 k
     * 可使用前缀和快速求解，pre[i] 为 nums[0...i-1] 中奇数个数，则 num[j...i] 中奇数个数为 pre[i+1] - pre[j]
     * 目标是找到 pre[i+1] - pre[j] == k，即 pre[j] == pre[i+1] - k，找到每个 pre[j] 累加到结果中即可
     * 为减少重复计算，可使用 count 数组来记录每个前缀和 pre[i+1] 出现的次数，遍历数组并计算 pre，同时更新 count 数组
     * 此时 count[pre[i+1]-k] 就是以 i 结尾的优美子数组个数
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        int result = 0, n = nums.length;
        int[] pre = new int[n + 1], count = new int[n + 1];
        // 因为 pre[0] == 0
        count[0] = 1;

        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + (nums[i] & 1);
            count[pre[i + 1]]++;
            if (pre[i + 1] >= k) {
                result += count[pre[i + 1] - k];
            }
        }

        return result;
    }
}

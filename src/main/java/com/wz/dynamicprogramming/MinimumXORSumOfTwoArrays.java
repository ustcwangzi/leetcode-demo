package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * You are given two integer arrays nums1 and nums2 of length n.
 * The XOR sum of the two integer arrays is (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) (0-indexed).
 * For example, the XOR sum of [1,2,3] and [3,2,1] is equal to (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4.
 * Rearrange the elements of nums2 such that the resulting XOR sum is minimized.
 * Return the XOR sum after the rearrangement.
 *
 * Example 1:
 * Input: nums1 = [1,2], nums2 = [2,3]
 * Output: 2
 * Explanation: Rearrange nums2 so that it becomes [3,2].
 * The XOR sum is (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2.
 *
 * Example 2:
 * Input: nums1 = [1,0,3], nums2 = [5,3,4]
 * Output: 8
 * Explanation: Rearrange nums2 so that it becomes [5,4,3].
 * The XOR sum is (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8.
 *
 * Constraints:
 * 1. n == nums1.length
 * 2. n == nums2.length
 * 3. 1 <= n <= 14
 * 4. 0 <= nums1[i], nums2[i] <= 10^7
 */
public class MinimumXORSumOfTwoArrays {
    public static void main(String[] args) {
        System.out.println(minimumXORSum(new int[]{1, 2}, new int[]{2, 3}));
        System.out.println(minimumXORSum(new int[]{1, 0, 3}, new int[]{5, 3, 4}));
    }

    /**
     * 可以用一个长度为 n 的二进制数 mask 表示数组 nums2 中的数被选择的状态：如果 mask 从低到高的第 i 位为 1，说明 nums2[i] 已被选择，否则说明其未被选择。
     * dp[mask] 表示当选择了数组 nums2 中的元素的状态为 mask，并且选择了数组 nums1 的前 bitCount(mask) 个元素的情况下，可以组成的最小的异或值之和。
     * 这里的 bitCount(mask) 表示 mask 的二进制表示中 1 的个数，记为 bitCount
     * 在进行状态转移时，可以枚举 nums1[bitCount-1] 与 nums2 中的哪一个元素进行了异或运算，假设其为 nums2[j]，那么有状态转移方程：
     * dp[mask] = min{dp[mask\j] + (nums1[bitCount-1] ⊕ nums2[j])}
     * 其中 ⊕ 表示异或，mask\j 表示将 mask 的第 j 位从 1 变为 0，可以使用异或运算 mask ⊕ 2^j 实现
     * 最终的答案即为 dp[2^n - 1]
     */
    public static int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length, range = 1 << n;
        int[] dp = new int[range];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // 遍历二进制位
        for (int mask = 0; mask < range; mask++) {
            for (int j = 0; j < n; j++) {
                if (((mask >> j) & 1) == 1) {
                    dp[mask] = Math.min(dp[mask], dp[mask ^ (1 << j)] + (nums1[Integer.bitCount(mask) - 1] ^ nums2[j]));
                }
            }
        }
        return dp[range - 1];
    }
}

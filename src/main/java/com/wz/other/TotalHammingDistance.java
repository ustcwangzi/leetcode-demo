package com.wz.other;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given an integer array nums, return the sum of Hamming distances between all the pairs of the integers in nums.
 *
 * Example 1:
 * Input: nums = [4,14,2]
 * Output: 6
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
 * showing the four bits relevant in this case).
 * The answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^4
 * 2. 0 <= nums[i] <= 10^9
 * 3. The answer for the given input will fit in a 32-bit integer.
 */
public class TotalHammingDistance {
    public static void main(String[] args) {
        System.out.println(totalHammingDistance(new int[]{4, 14, 2}));
    }

    /**
     * 是对 {@link HammingDistance} 的扩展，需要用异或来求每个位上的情况，那么需要来找出某种规律来，比如 [4,14,2,1]
     * 4:   0 1 0 0
     * 14:  1 1 1 0
     * 2:   0 0 1 0
     * 1:   0 0 0 1
     * 先看最后一列，有三个 0 和一个 1，那么它们之间相互的汉明距离是 3，即 1 和其他三个 0 分别的距离累加，
     * 然后在看第三列，累加汉明距离为 4，因为每个 1 都会跟两个 0 产生两个汉明距离，同理第二列也是 4，第一列是 3。
     * 仔细观察累计汉明距离和 0 跟 1 的个数，可以发现其实就是 0 的个数乘以 1 的个数。因此只要统计出每一位的 1 的个数即可
     */
    public static int totalHammingDistance(int[] nums) {
        int result = 0;
        // 每一位的汉明距离就是该位 (0 的个数 * 1 的个数)
        for (int i = 0; i < 32; i++) {
            int count = 0;
            // 统计第 i 位上 1 的个数
            for (int num : nums) {
                count += (num >> i) & 1;
            }
            result += count * (nums.length - count);
        }
        return result;
    }
}

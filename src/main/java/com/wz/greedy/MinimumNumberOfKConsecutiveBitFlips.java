package com.wz.greedy;

/**
 * You are given a binary array nums and an integer k.
 * A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
 * Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [0,1,0], k = 1
 * Output: 2
 * Explanation: Flip nums[0], then flip nums[2].
 *
 * Example 2:
 * Input: nums = [1,1,0], k = 2
 * Output: -1
 * Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].
 *
 * Example 3:
 * Input: nums = [0,0,0,1,0,1,1,0], k = 3
 * Output: 3
 * Explanation:
 * Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
 * Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
 * Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= k <= nums.length
 */
public class MinimumNumberOfKConsecutiveBitFlips {
    public static void main(String[] args) {
        System.out.println(minKBitFlips1(new int[]{0, 1, 0}, 1));
        System.out.println(minKBitFlips2(new int[]{0, 1, 0}, 1));
        System.out.println(minKBitFlips1(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
        System.out.println(minKBitFlips2(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
    }

    /**
     * 贪心算法，每次遇到 0 就将从该元素开始的 k 个元素进行翻转，TLE
     */
    public static int minKBitFlips1(int[] nums, int k) {
        int result = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                continue;
            }
            if (i + k > n) {
                return -1;
            }
            for (int j = i; j < i + k; j++) {
                nums[j] = 1 - nums[j];
            }
            result++;
        }
        return result;
    }

    /**
     * 方案一之所以超时是当 k 非常大时，每次翻转的位置很多，效率将非常低
     * 使用 isFlipped[] 数组来代替真实的翻转，isFlipped[i] 表示在原数组位置 i 上是否进行了翻转
     * isFlipped[] 只能记录起始翻转位置，但由于是连续翻转 k 个元素，之后的位置也翻转了，为了记录非起始位置是否进行了翻转
     * 还需要 curFlipped 表示当前位置的翻转情况，如 [0,1,0,1], 3，先对位置 0 翻转，数组变为 [1,0,1,1]，
     * 位置为 1 时 curFlipped=ture 表示位置 1 上进行了翻转，然后再对位置 1 翻转，数组变为 [1,1,0,0]，
     * 位置为 2 时 curFlipped=false 表示位置 2 上没有进行翻转（实际翻转两次，等同于没有翻转）
     * 那么如何判断当前位置是否需要进行翻转呢，需要根据 curFlipped 与原数组 nums[i] 进行比较来判断，有四种情况：
     * 1. curFlipped = true，表示进行了翻转，若 nums[i] == 0，表示当前位置 i 会受翻转影响变为 1，因此不需要再翻转
     * 2. curFlipped = true，表示进行了翻转，若 nums[i] == 1，表示当前位置 i 会受翻转影响变为 0，因此需要翻转回来
     * 3. curFlipped = false，表示没有翻转，若 nums[i] == 0，需要对当前位置进行翻转
     * 4. curFlipped = false，表示没有翻转，若 nums[i] == 1，不会变，因此不需要进行翻转
     * 另外，需要注意，curFlipped 受影响的位置只是在大小为 k 的窗口内，若超出了窗口范围，并且 i-k 是进行了翻转的，需要将 curFlipped 还原
     */
    public static int minKBitFlips2(int[] nums, int k) {
        int result = 0, n = nums.length;
        boolean[] isFlipped = new boolean[n];
        boolean curFlipped = false;
        for (int i = 0; i < n; i++) {
            // 超过窗口范围，还原 curFlipped
            if (i >= k && isFlipped[i - k]) {
                curFlipped = !curFlipped;
            }
            // 当前位置无需翻转
            if ((curFlipped ? 0 : 1) == nums[i]) {
                continue;
            }
            // 超出数组范围，无法满足要求
            if (i + k > n) {
                return -1;
            }

            // 对当前位置进行翻转
            isFlipped[i] = true;
            curFlipped = !curFlipped;
            result++;
        }
        return result;
    }
}

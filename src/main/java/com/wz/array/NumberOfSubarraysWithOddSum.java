package com.wz.array;

/**
 * Given an array of integers arr. Return the number of sub-arrays with odd sum.
 *
 * As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 *
 * Example 1:
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All sub-arrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 *
 * Example 2:
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All sub-arrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 */
public class NumberOfSubarraysWithOddSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5};
        System.out.println(numOfSubarrays(arr));

        arr = new int[]{2, 4, 6};
        System.out.println(numOfSubarrays(arr));
    }

    /**
     * sum(i) 是以 i 结尾的子数组的前缀和，因为 奇数 + 偶数 == 奇数
     * 所以如果 sum(i) 为奇数，则只需要知道有多少个 j，满足 sum(j−1) 是偶数，就能组成结果是奇数
     * 反之如果 sum(i) 为偶数，则只需要知道有多少个 j，满足 sum(j−1) 是奇数，就能组成结果是奇数
     * 所以可以在过程中统计奇数前缀和与偶数前缀和的个数，另外，初始时，偶数前缀和的个数为 1
     */
    public static int numOfSubarrays(int[] arr) {
        final int mod = (int) (1e9 + 7);
        int result = 0, sum = 0, odd = 0, even = 1;
        for (int cur : arr) {
            sum += cur;
            if ((sum & 1) == 1) {
                // 当前sum是奇数，需要加上之前的偶数个数
                result = (result + even) % mod;
                odd++;
            } else {
                // 当前sum是偶数，需要加上之前的奇数个数
                result = (result + odd) % mod;
                even++;
            }
        }

        return result;
    }
}

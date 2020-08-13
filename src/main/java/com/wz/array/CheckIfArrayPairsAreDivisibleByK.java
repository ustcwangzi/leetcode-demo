package com.wz.array;

/**
 * Given an array of integers arr of even length n and an integer k.
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 * Return True If you can find a way to do that or False otherwise.
 *
 * Example 1:
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 *
 * Example 2:
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 *
 * Example 3:
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 */
public class CheckIfArrayPairsAreDivisibleByK {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 10, 6, 7, 8, 9};
        System.out.println(canArrange(arr, 5));

        arr = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(canArrange(arr, 7));

        System.out.println(canArrange(arr, 10));
    }

    /**
     * 将原数组中的元素按照模 k 的余数分类，统计余数为 0 到 k-1 的个数
     * 如果余数为 0 的个数不为偶数，则返回 false
     * 接下来判断余数为 i 和 k - i 的个数是否相等，如果 i == k - i，则需要判定余数为 i 的个数是否为偶数
     */
    public static boolean canArrange(int[] arr, int k) {
        int[] remainder = new int[k];
        for (int cur : arr) {
            // 兼容 cur 为负数的情况
            remainder[(cur % k + k) % k]++;
        }

        if (remainder[0] % 2 != 0) {
            return false;
        }

        for (int i = 1, j = k - 1; i <= j; i++, j--) {
            if (i == j) {
                if (remainder[i] % 2 != 0) {
                    return false;
                }
            } else if (remainder[i] != remainder[j]) {
                return false;
            }
        }

        return true;
    }
}

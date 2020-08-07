package com.wz.lists;

/**
 * Given an array of integers arr.
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 * Let's define a and b as follows:
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 * Return the number of triplets (i, j and k) Where a == b.
 *
 * Example 1:
 * Input: arr = [2,3,1,6,7]
 * Output: 4
 * Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
 *
 * Example 2:
 * Input: arr = [1,1,1,1,1]
 * Output: 10
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXOR {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 7};
        System.out.println(countTriplets(arr));

        arr = new int[]{1, 1, 1, 1, 1};
        System.out.println(countTriplets(arr));
    }

    /**
     * 既然 a 和 b 的结果都是通过位运算，而且 a == b，所以得出 a ^ b == 0，因为两数相同异或为 0
     * 因此问题转化为找到满足 a ^ b = 0 的三元组
     * 从 i 开始一路往后做异或操作，如果找到某一个坐标 k 使得数字 i 到 k 的异或为0，那么这个中间 j 的位置就有 k - i 种可能
     */
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        int result = 0;
        if (n < 2) {
            return result;
        }

        for (int i = 0; i < n - 1; i++) {
            int xor = arr[i];
            for (int j = i + 1; j < n; j++) {
                xor ^= arr[j];
                // arr[i...j] 异或为0，共有 j-i 种可能
                if (xor == 0) {
                    result += j - i;
                }
            }
        }
        return result;
    }
}

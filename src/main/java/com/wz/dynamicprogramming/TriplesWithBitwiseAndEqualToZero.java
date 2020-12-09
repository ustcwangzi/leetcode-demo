package com.wz.dynamicprogramming;

/**
 * Given an array of integers A, find the number of triples of indices (i, j, k) such that:
 * 0 <= i < A.length
 * 0 <= j < A.length
 * 0 <= k < A.length
 * A[i] & A[j] & A[k] == 0, where & represents the bitwise-AND operator.
 *
 * Example 1:
 * Input: [2,1,3]
 * Output: 12
 * Explanation: We could choose the following i, j, k triples:
 * (i=0, j=0, k=1) : 2 & 2 & 1
 * (i=0, j=1, k=0) : 2 & 1 & 2
 * (i=0, j=1, k=1) : 2 & 1 & 1
 * (i=0, j=1, k=2) : 2 & 1 & 3
 * (i=0, j=2, k=1) : 2 & 3 & 1
 * (i=1, j=0, k=0) : 1 & 2 & 2
 * (i=1, j=0, k=1) : 1 & 2 & 1
 * (i=1, j=0, k=2) : 1 & 2 & 3
 * (i=1, j=1, k=0) : 1 & 1 & 2
 * (i=1, j=2, k=0) : 1 & 3 & 2
 * (i=2, j=0, k=1) : 3 & 2 & 1
 * (i=2, j=1, k=0) : 3 & 1 & 2
 *
 * Note:
 * 1. 1 <= A.length <= 1000
 * 2. 0 <= A[i] < 2^16
 */
public class TriplesWithBitwiseAndEqualToZero {
    public static void main(String[] args) {
        System.out.println(countTriplets(new int[]{2, 1, 3}));
    }

    /**
     * 先计算任意两个数字之间的位与结果，存储到字典中，字典中保存的是位与出现的次数，然后再次对数组每个位置进行遍历，
     * 同时遍历字典中的每个元素，即可分析出任意三个数字位与的结果和次数
     */
    public static int countTriplets(int[] A) {
        int result = 0, max = (1 << 16);
        int[] dp = new int[max];
        for (int num : A) {
            for (int bit = 0; bit < max; bit++) {
                if ((num & bit) == 0) {
                    dp[bit]++;
                }
            }
        }

        for (int i : A) {
            for (int j : A) {
                result += dp[i & j];
            }
        }

        return result;
    }
}

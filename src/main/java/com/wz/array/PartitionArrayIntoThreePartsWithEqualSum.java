package com.wz.array;

import java.util.Arrays;

/**
 * Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.
 * Formally, we can partition the array if we can find indexes i+1 < j with
 * (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])
 *
 * Example 1:
 * Input: A = [0,2,1,-6,6,-7,9,1,2,0,1]
 * Output: true
 * Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 *
 * Example 2:
 * Input: A = [0,2,1,-6,6,7,9,-1,2,0,1]
 * Output: false
 *
 * Example 3:
 * Input: A = [3,3,6,5,-2,2,5,1,-9,4]
 * Output: true
 * Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 */
public class PartitionArrayIntoThreePartsWithEqualSum {
    public static void main(String[] args) {
        int[] A = new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        System.out.println(canThreePartsEqualSum(A));

        A = new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1};
        System.out.println(canThreePartsEqualSum(A));

        A = new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4};
        System.out.println(canThreePartsEqualSum(A));
    }

    /**
     * 分成三部分，则每部分之和一定能被三整除，且整个数组之和也能被三整除
     * 先求总和，再遍历求和，每当达到总和的1/3就重置，判断最后分出的部分是否大于等于3
     */
    public static boolean canThreePartsEqualSum(int[] A) {
        int sum = Arrays.stream(A).sum();
        if (sum % 3 != 0) {
            return false;
        }
        int average = sum / 3, partSum = 0, part = 0;
        for (int cur : A) {
            partSum += cur;
            if (partSum == average) {
                part++;
                partSum = 0;
            }
        }
        return part >= 3;
    }
}

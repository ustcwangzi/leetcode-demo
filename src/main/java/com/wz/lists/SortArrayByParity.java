package com.wz.lists;

import java.util.Arrays;

/**
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
 * You may return any answer array that satisfies this condition.
 *
 * Example 1:
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 */
public class SortArrayByParity {
    public static void main(String[] args) {
        int[] A = new int[]{3, 1, 2, 4};
        System.out.println(Arrays.toString(sortArrayByParity(A)));
    }

    /**
     * 偶数都排在奇数前面
     * 使用两个指针i和j，初始化均为0，然后j往后遍历，若遇到了偶数，则将 A[j] 和 A[i] 交换位置，同时i自增1
     */
    public static int[] sortArrayByParity(int[] A) {
        for (int i = 0, j = 0; j < A.length; j++) {
            if (A[j] % 2 == 0) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                i++;
            }
        }
        return A;
    }
}

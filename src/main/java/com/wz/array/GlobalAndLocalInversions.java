package com.wz.array;

/**
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 * Return true if and only if the number of global inversions is equal to the number of local inversions.
 *
 * Example 1:
 * Input: A = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion, and 1 local inversion.
 *
 * Example 2:
 * Input: A = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions, and 1 local inversion.
 */
public class GlobalAndLocalInversions {
    public static void main(String[] args) {
        int[] A = new int[]{1, 0, 2};
        System.out.println(isIdealPermutation(A));

        A = new int[]{1, 2, 0};
        System.out.println(isIdealPermutation(A));
    }

    /**
     * 全局倒置说的是坐标小的值大，局部倒置说的是相邻的两个数
     * 局部倒置是全局倒置的一种特殊情况，即局部倒置一定是全局倒置，而全局倒置不一定是局部倒置
     * 因此数组里只允许出现相邻的两个数字是大小反转的，其他间隔的两个数字，都只能是从小到大的顺序
     * 对于前i个数字的最大值都要小于i+2处的数字，用curMax记录前i个数字的最大值，逐步遍历进行比较
     */
    public static boolean isIdealPermutation(int[] A) {
        int curMax = -1;
        for (int i = 0; i < A.length - 2; i++) {
            curMax = Math.max(curMax, A[i]);
            if (curMax > A[i + 2]) {
                return false;
            }
        }

        return true;
    }
}

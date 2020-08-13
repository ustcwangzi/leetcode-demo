package com.wz.array;

import java.util.Arrays;

/**
 * Given an array of integers A sorted in non-decreasing order,
 * return an array of the squares of each number, also in sorted non-decreasing order.
 *
 * Example 1:
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 *
 * Example 2:
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */
public class SquaresOfASortedArray {
    public static void main(String[] args) {
        int[] A = new int[]{-4, -1, 0, 3, 10};
        System.out.println(Arrays.toString(sortedSquares(A)));

        A = new int[]{-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(sortedSquares(A)));
    }

    /**
     * 原数组已有序，但因此存在负数，平方后顺序会改变
     * 用两个指针 left 和 right，从两边遍历，比较取绝对值后的数字，把较大的进行平方，放入结果的末尾
     */
    public static int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        int left = 0, right = A.length - 1, index = A.length - 1;
        while (left <= right) {
            int leftValue = Math.abs(A[left]);
            int rightValue = Math.abs(A[right]);
            if (leftValue > rightValue) {
                result[index--] = (int) Math.pow(leftValue, 2);
                left++;
            } else {
                result[index--] = (int) Math.pow(rightValue, 2);
                right--;
            }
        }

        return result;
    }
}

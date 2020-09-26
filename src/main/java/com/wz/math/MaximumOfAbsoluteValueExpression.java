package com.wz.math;

/**
 * Given two arrays of integers with equal lengths, return the maximum value of:
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 *
 * Example 1:
 * Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * Output: 13
 *
 * Example 2:
 * Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * Output: 20
 */
public class MaximumOfAbsoluteValueExpression {
    public static void main(String[] args) {
        System.out.println(maxAbsValExpr(new int[]{1, 2, 3, 4}, new int[]{-1, 4, 5, 6}));
    }

    /**
     * 对于表达式 |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|，在i < j的情况下，这个表达式的值是下面其中四个之一：
     * 1. (arr1[i] + arr2[i] - i) - (arr1[j] + arr2[j] - j)
     * 2. (arr1[i] - arr2[i] - i) - (arr1[j] - arr2[j] - j)
     * 3. (arr2[i] - arr1[i] - i) - (arr2[j] - arr1[j] - j)
     * 4. (arr2[i] + arr1[i] + i) - (arr2[j] + arr1[j] + j)
     *  所以只要遍历一次数组，求出四个表达式中差值的最大值和最小值即可
     */
    public static int maxAbsValExpr(int[] arr1, int[] arr2) {
        int max1 = Integer.MIN_VALUE, max2 = max1, max3 = max1, max4 = max1;
        int min1 = Integer.MAX_VALUE, min2 = min1, min3 = min1, min4 = min1;
        int sum = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum = arr1[i] + arr2[i] + i;
            max1 = Math.max(max1, sum);
            min1 = Math.min(min1, sum);

            sum = arr1[i] + arr2[i] - i;
            max2 = Math.max(max2, sum);
            min2 = Math.min(min2, sum);

            sum = arr1[i] - arr2[i] + i;
            max3 = Math.max(max3, sum);
            min3 = Math.min(min3, sum);

            sum = arr1[i] - arr2[i] - i;
            max4 = Math.max(max4, sum);
            min4 = Math.min(min4, sum);
        }
        int diff1 = max1 - min1, diff2 = max2 - min2, diff3 = max3 - min3, diff4 = max4 - min4;
        return Math.max(Math.max(diff1, diff2), Math.max(diff3, diff4));
    }
}

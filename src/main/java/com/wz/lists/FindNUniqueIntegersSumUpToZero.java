package com.wz.lists;

import java.util.Arrays;

/**
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 *
 * Example 1:
 * Input: n = 5
 * Output: [-7,-1,1,3,4]
 * Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
 *
 * Example 2:
 * Input: n = 3
 * Output: [-1,0,1]
 *
 * Example 3:
 * Input: n = 1
 * Output: [0]
 */
public class FindNUniqueIntegersSumUpToZero {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sumZero(5)));
        System.out.println(Arrays.toString(sumZero(3)));
        System.out.println(Arrays.toString(sumZero(1)));
    }

    /**
     * 从 1 - n到 n - 1 按照步长为 2 加入到结果数组即可
     */
    public static int[] sumZero(int n) {
        int[] result = new int[n];
        for (int i = 0, num = 1 - n; i < n; i++, num += 2) {
            result[i] = num;
        }
        return result;
    }
}

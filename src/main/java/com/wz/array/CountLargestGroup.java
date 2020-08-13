package com.wz.array;

import java.util.Arrays;

/**
 * Given an integer n. Each number from 1 to n is grouped according to the sum of its digits.
 * Return how many groups have the largest size.
 *
 * Example 1:
 * Input: n = 13
 * Output: 4
 * Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
 * [1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9]. There are 4 groups with largest size.
 *
 * Example 2:
 * Input: n = 2
 * Output: 2
 * Explanation: There are 2 groups [1], [2] of size 1.
 *
 * Example 3:
 * Input: n = 15
 * Output: 6
 *
 * Example 4:
 * Input: n = 24
 * Output: 5
 *
 * Constraints:
 * 1 <= n <= 10^4
 */
public class CountLargestGroup {
    public static void main(String[] args) {
        System.out.println(countLargestGroup(13));
        System.out.println(countLargestGroup(2));
        System.out.println(countLargestGroup(15));
        System.out.println(countLargestGroup(24));
    }

    /**
     * n最多有四位，即9999，则每一位相加的最大和是36，因此可以使用长度为37的数组 sumArray 来存储每一位相加之和的次数
     * 然后对 sumArray 进行排序，最后一位就是最大出现次数，进行统计即可
     */
    public static int countLargestGroup(int n) {
        // 保存相加和的出现次数
        int[] sumArray = new int[37];
        for (int i = 1; i <= n; i++) {
            int sum = getSumOfDigits(i);
            sumArray[sum]++;
        }

        Arrays.parallelSort(sumArray);
        int result = 1, target = sumArray[sumArray.length - 1];
        // 统计最大出现次数
        for (int i = sumArray.length - 2; i >= 0; i--) {
            if (sumArray[i] == target) {
                result++;
            } else {
                break;
            }
        }

        return result;
    }

    /**
     * 按照每一位进行相加
     */
    private static int getSumOfDigits(int n) {
        int result = 0;
        while (n != 0) {
            result += n % 10;
            n /= 10;
        }
        return result;
    }
}

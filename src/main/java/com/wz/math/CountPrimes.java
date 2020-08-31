package com.wz.math;

import java.util.Arrays;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Example:
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }

    /**
     * 从 2 开始遍历到根号n，先找到第一个质数 2，然后将其所有的倍数全部标记出来，然后到下一个质数3，标记其所有倍数
     * 依次类推，直到根号n，此时数组中未被标记的数字就是质数。用一个 bool 型数组来记录每个数字是否被标记
     */
    public static int countPrimes(int n) {
        int result = 0;
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);
        for (int i = 2; i < n; i++) {
            if (!prime[i]) {
                continue;
            }
            result++;
            for (int j = 2; i * j < n; j++) {
                prime[i * j] = false;
            }
        }

        return result;
    }
}

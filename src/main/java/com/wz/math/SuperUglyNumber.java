package com.wz.math;

/**
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 *
 * Example:
 * Input: n = 12, primes = [2,7,13,19]
 * Output: 32
 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
 *              super ugly numbers given primes = [2,7,13,19] of size 4.
 *
 * Note:
 * 1. 1 is a super ugly number for any given primes.
 * 2. The given numbers in primes are in ascending order.
 * 3. 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * 4. The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */
public class SuperUglyNumber {
    public static void main(String[] args) {
        int[] primes = {2, 7, 13, 19};
        System.out.println(nthSuperUglyNumber(12, primes));
    }

    /**
     * 首先考虑一系列的数字是根据什么原则产生的，很显然，除了第一个数字，其余所有数字都是之前已有数字乘以任意一个在质数数组里的质数
     * 所以对于每一个已有的数字，都可以分别乘以所有在质数数组里的质数得到一系列的数字，这些数字肯定会存在在以后的序列中。
     * 由于是要得到从小到大的结果，所以可以维护 index 数组，来记录对于对应质数下一个需要被乘的已有数的下标，取最小的结果当做下个数，
     * 对于那个最小的结果，需要增加 index 数组中那个质数对应的下标，表明下一次用下个已有的数来乘对应的质数
     * 需要注意的地方是对于已有序列中的数，乘不同质数得到的结果会可能存在重复，比如题目中例子 2, 7 与 7, 2 就重复了，
     * 解决方法很简单，就是只要是等于最小的结果，就增加对应 index 数组中的元素
     */
    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] result = new int[n];
        int[] index = new int[primes.length];
        result[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            // 每次取最小的放入结果集
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * result[index[j]]);
            }
            result[i] = min;

            for (int j = 0; j < index.length; j++) {
                // 处理相乘结果相等的情况
                if (result[index[j]] * primes[j] == min) {
                    index[j]++;
                }
            }
        }

        return result[n - 1];
    }
}

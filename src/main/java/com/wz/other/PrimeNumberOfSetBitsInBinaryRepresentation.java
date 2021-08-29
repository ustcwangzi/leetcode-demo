package com.wz.other;

/**
 * Given two integers left and right, return the count of numbers in the inclusive range [left, right] having a prime number of set bits in their binary representation.
 * Recall that the number of set bits an integer has is the number of 1's present when written in binary.
 * For example, 21 written in binary is 10101 which has 3 set bits.
 *
 * Example 1:
 * Input: left = 6, right = 10
 * Output: 4
 * Explanation:
 * 6 -> 110 (2 set bits, 2 is prime)
 * 7 -> 111 (3 set bits, 3 is prime)
 * 9 -> 1001 (2 set bits , 2 is prime)
 * 10->1010 (2 set bits , 2 is prime)
 *
 * Example 2:
 * Input: left = 10, right = 15
 * Output: 5
 * Explanation:
 * 10 -> 1010 (2 set bits, 2 is prime)
 * 11 -> 1011 (3 set bits, 3 is prime)
 * 12 -> 1100 (2 set bits, 2 is prime)
 * 13 -> 1101 (3 set bits, 3 is prime)
 * 14 -> 1110 (3 set bits, 3 is prime)
 * 15 -> 1111 (4 set bits, 4 is not prime)
 *
 * Constraints:
 * 1. 1 <= left <= right <= 10^6
 * 2. 0 <= right - left <= 10^4
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {
    public static void main(String[] args) {
        System.out.println(countPrimeSetBits(6, 10));
        System.out.println(countPrimeSetBits(10, 15));
    }

    /**
     * 二进制中 1 的个数为质数，通过移位逐个统计 1 的个数，然后判断是否是质数即可
     */
    public static int countPrimeSetBits(int left, int right) {
        int result = 0;
        for (int i = left; i <= right; i++) {
            int count = 0, cur = i;
            while (cur > 0) {
                count += cur & 1;
                cur >>= 1;
            }
            result += isPrime(count) ? 1 : 0;
        }
        return result;
    }

    /**
     * 大于 1 的自然数中，只能被自身或 1 整除的数字，从 2 到 sqrt(num) 如果都没有整除说明是质数
     */
    private static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return num > 1;
    }
}

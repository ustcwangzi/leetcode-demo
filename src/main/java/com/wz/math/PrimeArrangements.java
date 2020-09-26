package com.wz.math;

/**
 * Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)
 * (Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 5
 * Output: 12
 * Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.
 *
 * Example 2:
 * Input: n = 100
 * Output: 682289015
 */
public class PrimeArrangements {
    public static void main(String[] args) {
        System.out.println(numPrimeArrangements(5));
        System.out.println(numPrimeArrangements(100));
    }

    /**
     * 计算一个由1到n组成的数列中，质数恰好位于质数索引上的排列组合个数，本质上是一个数学问题。
     * 结合n = 5的例子来看，1到5中，只有2,3,5是质数，1和4不是质数，因此排列质数就有3*2*1 = 6种可能，分别是:
     * [2,3,5],[2,5,3],[3,2,5],[3,5,2],[5,2,3],[5,3,2]
     * 不是质数的1和4，只有两种可能，分别是
     * [1,4],[4,1]
     * 因此，将质数和非质数组合起来，就是6*2 = 12种可能，分别是
     * [1,2,3,4,5],[1,2,5,4,3],[1,3,2,4,5],[1,3,5,4,2],[1,5,2,4,3],[1,5,3,4,2]
     * [4,2,3,1,5],[4,2,5,1,3],[4,3,2,1,5],[4,3,5,1,2],[4,5,2,1,3],[4,5,3,1,2]
     * 因此，只需要计算出n中有多少个质数和非质数，再计算两者的阶乘即可
     */
    public static int numPrimeArrangements(int n) {
        int mod = 1000000007;
        // 质数个数
        int primeNums = countPrime(n);
        // 非质数个数
        int nonPrimeNums = n - primeNums;

        long result = 1;
        for (int i = 2; i <= primeNums; i++) {
            result = (result * i) % mod;
        }
        for (int j = 2; j <= nonPrimeNums; j++) {
            result = (result * j) % mod;
        }

        return (int) result;
    }

    /**
     * 计算1到n中，质数（只能被1和自身整除）的个数
     */
    public static int countPrime(int n) {
        if (n <= 1) {
            return 0;
        }
        int count = 0;
        for (int i = 2; i <= n; i++) {
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }
}

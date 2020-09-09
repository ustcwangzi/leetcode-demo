package com.wz.math;

/**
 * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
 * Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
 *
 * Example:
 * Input: 28
 * Output: True
 * Explanation: 28 = 1 + 2 + 4 + 7 + 14
 * Note: The input number n will not exceed 100,000,000. (1e8)
 */
public class PerfectNumber {
    public static void main(String[] args) {
        System.out.println(checkPerfectNumber(28));
    }

    /**
     * 完美数字的定义，就是一个整数等于除其自身之外的所有的因子之和，那么由于不能包含自身，所以n必定大于1
     * 由于1肯定是因子，可以提前加上，那么找其他因子的范围是[2, sqrt(n)]。
     * 遍历这之间所有的数字，如果可以被n整除，那么我们把i和num/i都加上，对于n如果是平方数的话，那么此时相同的因子加来两次，
     * 所以要判断一下，如果相等，就不再加 num/i
     * 在循环结束后，首先判断num是否为1，因为题目中说了不能加包括本身的因子，然后再看sum是否和num相等
     */
    public static boolean checkPerfectNumber(int num) {
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i + (num / i == i ? 0 : num / i);
            }
        }
        return num != 1 && sum == num;
    }
}

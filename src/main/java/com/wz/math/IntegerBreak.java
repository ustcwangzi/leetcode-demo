package com.wz.math;

import java.util.Arrays;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and
 * maximize the product of those integers. Return the maximum product you can get.
 *
 * Example 1:
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 *
 * Example 2:
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class IntegerBreak {
    public static void main(String[] args) {
        System.out.println(integerBreak(2));
        System.out.println(integerBreak2(2));
        System.out.println(integerBreak(10));
        System.out.println(integerBreak2(10));
    }

    /**
     * 动态规则
     * dp[i] 表示数字 i 拆分为至少两个正整数之和的最大乘积，值初始化为1，因为正整数的乘积不会小于1
     * 从 3 开始遍历，因为 n 是从2开始的，而2只能拆分为两个1，乘积还是1
     * i从3遍历到n，对于每个i，需要遍历所有小于i的数字，对于任意小于i的数字j，首先计算拆分为两个数字的乘积，即 j*(i-j)，
     * 然后是拆分为多个数字的情况，这里就要用到 dp[i-j] 了，这个值表示数字 i-j 任意拆分可得到的最大乘积，
     * 再乘以 j 就是数字i可拆分得到的乘积，取二者的较大值来更新 dp[i]，最后返回 dp[n] 即可
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /**
     * 数字2拆成 1+1，所以乘积也为1
     * 数字3拆成 2+1，乘积最大，为2
     * 数字4拆成 2+2，乘积最大，为4
     * 数字5拆成 3+2，乘积最大，为6
     * 数字6拆成 3+3，乘积最大，为9
     * 数字7拆为 3+4，乘积最大，为 12
     * 数字8拆为 3+3+2，乘积最大，为 18
     * 数字9拆为 3+3+3，乘积最大，为 27
     * 数字10拆为 3+3+4，乘积最大，为 36
     * ....
     * 那么通过观察上面的规律，可以看出从5开始，数字都需要先拆出所有的3，一直拆到剩下一个数为2或者4，因为剩4就不用再拆了
     * 拆成两个2和不拆没有意义，而且4不能拆出一个3剩一个1，这样会比拆成 2+2 的乘积小
     * 先预处理n为2和3的情况，然后先将结果 result 初始化为1，然后当n大于4开始循环，结果 result 自乘3，n自减3，
     * 根据之前的分析，当跳出循环时，n只能是2或者4，再乘以 result 返回即可
     */
    public static int integerBreak2(int n) {
        if (n == 2 || n == 3) {
            return n - 1;
        }

        int result = 1;
        while (n > 4) {
            result *= 3;
            n -= 3;
        }
        return result * n;
    }
}

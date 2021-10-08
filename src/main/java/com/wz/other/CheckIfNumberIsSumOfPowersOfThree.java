package com.wz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
 * An integer y is a power of three if there exists an integer x such that y == 3^x.
 *
 * Example 1:
 * Input: n = 12
 * Output: true
 * Explanation: 12 = 3^1 + 3^2
 *
 * Example 2:
 * Input: n = 91
 * Output: true
 * Explanation: 91 = 3^0 + 3^2 + 3^4
 *
 * Example 3:
 * Input: n = 21
 * Output: false
 *
 *
 * Constraints:
 * 1 <= n <= 10^7
 */
public class CheckIfNumberIsSumOfPowersOfThree {
    public static void main(String[] args) {
        System.out.println(checkPowersOfThree(91));
        System.out.println(checkPowersOfThree(21));
        System.out.println(checkPowersOfThree2(91));
        System.out.println(checkPowersOfThree2(21));
    }

    /**
     * 每次将 n 减去当前范围内最大的 3 的幂值，再判断最后结果是否为 0
     */
    public static boolean checkPowersOfThree(int n) {
        List<Integer> nums = new ArrayList<>();
        int num = 1;
        // 计算可能用到的3的幂
        while (num <= n) {
            nums.add(num);
            num *= 3;
        }
        // 减去当前范围内最大的3的幂
        for (int i = nums.size() - 1; i >= 0; i--)
            if (n >= nums.get(i)) {
                n -= nums.get(i);
            }
        return n == 0;
    }

    /**
     * 将 n 分解为若干个 3 的幂次之和，并且每个幂次的数只出现一次，问题等价于 n 的三进制表示是不是每一位都不是 2
     */
    public static boolean checkPowersOfThree2(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}

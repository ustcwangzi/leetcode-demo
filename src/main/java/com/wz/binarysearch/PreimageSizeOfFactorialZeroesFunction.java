package com.wz.binarysearch;

import com.wz.math.FactorialTrailingZeroes;

/**
 * Let f(x) be the number of zeroes at the end of x!. Recall that x! = 1 * 2 * 3 * ... * x and by convention, 0! = 1.
 * For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has two zeroes at the end.
 * Given an integer k, return the number of non-negative integers x have the property that f(x) = k.
 *
 * Example 1:
 * Input: k = 0
 * Output: 5
 * Explanation: 0!, 1!, 2!, 3!, and 4! end with k = 0 zeroes.
 *
 * Example 2:
 * Input: k = 5
 * Output: 0
 * Explanation: There is no x such that x! ends in k = 5 zeroes.
 *
 * Example 3:
 * Input: k = 3
 * Output: 5
 *
 * Constraints:
 * 0 <= k <= 10^9
 */
public class PreimageSizeOfFactorialZeroesFunction {
    public static void main(String[] args) {
        System.out.println(preimageSizeFZF(0));
        System.out.println(preimageSizeFZF(5));
    }

    /**
     * f(x) 为 x! 的末尾 0 的个数，求出使得 f(x)=k 成立的非负整数的个数，是对 {@link FactorialTrailingZeroes} 的扩展
     * 末尾 0 其实是由 2 和 5 相乘得到的，而阶乘中 2 的数量远大于 5 的数量，所以 10 的个数取决于 5 的个数
     * 20，21，22，23，24，这五个数的阶乘数末尾零的个数其实是相同的，都是有 4 个，因为它们包含的5的个数相同
     * 而 19，18，17，16，15，这五个数末尾零个数相同，均为 3，需要注意的是，像 25，125 这样的不只含有一个 5 的数字需要考虑进去
     * 25 的阶乘末尾就有 6 个 0，可以发现，每五个数，必会至少多出 1 个 5，有可能更多
     * 所以阶乘末尾零个数均为 k 的元素 x 的个数，只有两种情况，要么是 5，要么是 0
     * 即需要找到一个数，其阶乘末尾零的个数等于 k，若能找到说明总共有 5 个这样的数，反之，就返回 0
     * 又末尾 0 的个数是增的，可以采用二分查找，左边界很好确定，是 0，至于右边界，一个数字的阶乘末尾零个数为 k，那么这个数字能有多大
     * 末尾有 4 个 0 的最大数字是 24，有 6 个 0 的最大数字是 29，可以发现它们都不会超过 5*(k+1) 这个范围
     * 即在 [0, 5*(k+1)] 范围内寻找是否存在阶乘末尾零的个数等于 k，存在则返回 5，不存在则返回 0
     */
    public static int preimageSizeFZF(int k) {
        long left = 0, right = 5L * (k + 1);
        while (left <= right) {
            long mid = left + (right - left) / 2;
            int count = trailingZeroes(mid);
            if (count == k) {
                return 5;
            }
            if (count > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }

    private static int trailingZeroes(long n) {
        int result = 0;
        while (n > 0) {
            n /= 5;
            result += n;
        }
        return result;
    }
}

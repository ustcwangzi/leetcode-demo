package com.wz.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k, whether a Fibonacci number could be used multiple times.
 * The Fibonacci numbers are defined as:
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 , for n > 2.
 * It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.
 *
 * Example 1:
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 *
 * Example 2:
 * Input: k = 10
 * Output: 2
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 *
 * Example 3:
 * Input: k = 19
 * Output: 3
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 */
public class FindMinimumNumberOfFibonacciNumbersWhoseSumIsK {
    public static void main(String[] args) {
        System.out.println(findMinFibonacciNumbers(7));
        System.out.println(findMinFibonacciNumbers(10));
        System.out.println(findMinFibonacciNumbers(19));
    }

    /**
     * 贪心算法
     * 只要 k 没减到 0，就从斐波那契数列中找出比 k 小的那个最大数字，让 k 减去它，计算共减了多少次
     */
    public static int findMinFibonacciNumbers(int k) {
        List<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(1);
        fibonacci.add(1);
        int a = 1, b = 1, cur = a + b;
        while (cur <= k) {
            fibonacci.add(cur);
            a = b;
            b = cur;
            cur = a + b;
        }

        int result = 0, right = -1;
        while (k > 0) {
            result++;
            right = search(fibonacci, k, right);
            k -= fibonacci.get(right);
        }
        return result;
    }

    /**
     * 从斐波那契数列中找出比 target 小的那个最大数字
     * 这里用 right 记录上一次的查找时的右边界，减少查询次数，因为本次结果肯定在上次更左的位置
     */
    private static int search(List<Integer> fibonacci, int target, int right) {
        int left = 0;
        right = right == -1 ? fibonacci.size() - 1 : right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (fibonacci.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}

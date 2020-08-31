package com.wz.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * Return True if n is a happy number, and False if not.
 *
 * Example:
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(11));
        System.out.println(isHappy(19));
    }

    /**
     * 对于某一个正整数，如果对其各个位上的数字分别平方，然后再加起来得到一个新的数字，再进行同样的操作，如果最终结果变成了1，则说明是快乐数，
     * 如果一直循环但不是1的话，就不是快乐数，来看一个不是快乐数的情况，比如数字 11 有如下的计算过程：
     * 1^2 + 1^2 = 2
     * 2^2 = 4
     * 4^2 = 16
     * 1^2 + 6^2 = 37
     * 3^2 + 7^2 = 58
     * 5^2 + 8^2 = 89
     * 8^2 + 9^2 = 145
     * 1^2 + 4^2 + 5^2 = 42
     * 4^2 + 2^2 = 20
     * 2^2 + 0^2 = 4
     * 发现在算到最后时数字4又出现了，那么之后的数字又都会重复之前的顺序，这个循环中不包含1，那么数字11不是一个快乐数
     * 用 HashSet 来记录所有出现过的数字，然后每出现一个新数字，在 HashSet 中查找看是否存在，若不存在则加入表中
     * 若存在则跳出循环，并且判断此数是否为 1，若为 1 返回 true，不为 1 返回 false
     */
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
            if (set.contains(n)) {
                break;
            }
            set.add(n);
        }

        return n == 1;
    }
}

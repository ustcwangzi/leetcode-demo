package com.wz.math;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 * Example:
 * Input: 38
 * Output: 2
 * Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
 *              Since 2 has only one digit, return it.
 */
public class AddDigits {
    public static void main(String[] args) {
        System.out.println(addDigits(38));
    }

    /**
     * 先来观察1到20的所有的结果：
     * 1    1
     * 2    2
     * 3    3
     * 4    4
     * 5    5
     * 6    6
     * 7    7
     * 8    8
     * 9    9
     *
     * 10   1
     * 11   2
     * 12   3
     * 13   4
     * 14   5
     * 15   6
     * 16   7
     * 17   8
     * 18   9
     *
     * 19   1
     * 20   2
     * 可以得出规律，每9个一循环，所有大于9的数的结果都是对9取余，那么等于9的数对9取余就是0了，
     * 为了得到其本身，而且同样也要对大于9的数适用，就用 (n-1)%9+1 这个表达式来包括所有的情况
     */
    public static int addDigits(int num) {
        return (num == 0) ? 0 : (num - 1) % 9 + 1;
    }
}

package com.wz.other;

/**
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 * Example 1:
 * Input: a = 1, b = 2
 * Output: 3
 *
 * Constraints:
 * -1000 <= a, b <= 1000
 */
public class SumOfTwoIntegers {
    public static void main(String[] args) {
        System.out.println(getSum(1, 2));
    }

    /**
     * 先分析如何做十进制的加法，比如是如何得出 5+17 = 22 这个结果的，可以分成三步：
     * 第一步：只做各位相加不进位，此时相加的结果是 12
     * 第二步：只做进位，进位的值是 10
     * 第三步：把前面两个结果加起来，12+10 的结果是 22
     * 而对于二进制，也可以使用同样的方法：
     * 首先，对 a 和 b 进行异或，得出没有考虑进位的和 sum
     * 然后，对 a 和 b 进行与运算，得出每一位上的进位 carry
     * 最后，因为是进位，所以需要将进位左移一位，然后再进行相加，令 b = carry<<1，a = sum，直到 b 为 0
     */
    public static int getSum(int a, int b) {
        int carry, sum = a;
        while (b != 0) {
            sum = a ^ b;
            carry = a & b;
            a = sum;
            b = carry << 1;
        }
        return sum;
    }
}

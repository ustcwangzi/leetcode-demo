package com.wz.math;

/**
 * On a broken calculator that has a number showing on its display, we can perform two operations:
 * Double: Multiply the number on the display by 2, or;
 * Decrement: Subtract 1 from the number on the display.
 * Initially, the calculator is displaying the number X.
 * Return the minimum number of operations needed to display the number Y.
 *
 * Example 1:
 * Input: X = 2, Y = 3
 * Output: 2
 * Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
 *
 * Example 2:
 * Input: X = 5, Y = 8
 * Output: 2
 * Explanation: Use decrement and then double {5 -> 4 -> 8}.
 *
 * Example 3:
 * Input: X = 1024, Y = 1
 * Output: 1023
 * Explanation: Use decrement operations 1023 times.
 */
public class BrokenCalculator {
    public static void main(String[] args) {
        System.out.println(brokenCalc(2, 3));
        System.out.println(brokenCalc(5, 8));
    }

    /**
     * 题目等价于Y只有除2和加1的操作，通过最少的操作使得Y变换到X
     * 如果X大于等于Y，那么必须通过X-Y次减1操作才能得到Y。
     * 如果X<Y，那么最少的操作应该是X通过若干次乘2的操作使得X>=Y，等同于Y经过若干次除2操作使得Y<=X，同时对不能整除时的状况进行判断。
     */
    public static int brokenCalc(int X, int Y) {
        int result = 0;
        while (Y > X) {
            if (Y % 2 == 0) {
                Y /= 2;
            } else {
                Y += 1;
            }
            ++result;
        }

        return result + X - Y;
    }
}

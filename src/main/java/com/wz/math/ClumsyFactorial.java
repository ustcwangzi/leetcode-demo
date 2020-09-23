package com.wz.math;

/**
 * Normally, the factorial of a positive integer n is the product of all positive integers less than or equal to n.
 * For example, factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1.
 * We instead make a clumsy factorial: using the integers in decreasing order, we swap out the multiply operations
 * for a fixed rotation of operations: multiply (*), divide (/), add (+) and subtract (-) in this order.
 * For example, clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1.  However, these operations are still applied using
 * the usual order of operations of arithmetic: we do all multiplication and division steps before any addition or subtraction steps,
 * and multiplication and division steps are processed left to right.
 * Additionally, the division that we use is floor division such that 10 * 9 / 8 equals 11.  This guarantees the result is an integer.
 * Implement the clumsy function as defined above: given an integer N, it returns the clumsy factorial of N.
 *
 * Example 1:
 * Input: 4
 * Output: 7
 * Explanation: 7 = 4 * 3 / 2 + 1
 *
 * Example 2:
 * Input: 10
 * Output: 12
 * Explanation: 12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 */
public class ClumsyFactorial {
    public static void main(String[] args) {
        System.out.println(clumsy(4));
        System.out.println(clumsy(10));
    }

    /**
     * 为题目规定了'*''/'具有最高的计算优先级，所以不能够按照正常计算阶乘的那种方式计算；
     * 通过观察可以发现，如果把所有AB/C用一个新的数D代替的话，那么这个式子就不需要再去考虑运算的优先级了
     * 这样的话每次用一个变量存储AB/C的值后，用前面计算所得的值减去当前计算的值即可
     */
    public static int clumsy(int N) {
        int result = 0, sum = N * (-1), index = 0;
        for (int i = N - 1; i > 0; i--) {
            if (index == 0) { // '*'
                sum *= i;
            } else if (index == 1) { // '/'
                sum /= i;
            } else if (index == 2) { // '+'
                result -= sum;
                result += i;
            } else { // '-'
                sum = i;
            }

            index = (index + 1) % 4;
        }
        if (index <= 2) {
            result -= sum;
        }

        return result;
    }
}

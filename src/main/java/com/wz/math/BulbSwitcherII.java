package com.wz.math;

/**
 * There is a room with n lights which are turned on initially and 4 buttons on the wall.
 * After performing exactly m unknown operations towards buttons,
 * you need to return how many different kinds of status of the n lights could be.
 * Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
 * 1. Flip all the lights.
 * 2. Flip lights with even numbers.
 * 3. Flip lights with odd numbers.
 * 4. Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
 *
 * Example 1:
 * Input: n = 1, m = 1.
 * Output: 2
 * Explanation: Status can be: [on], [off]
 *
 * Example 2:
 * Input: n = 2, m = 1.
 * Output: 3
 * Explanation: Status can be: [on, off], [off, on], [off, off]
 *
 * Example 3:
 * Input: n = 3, m = 1.
 * Output: 4
 * Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
 */
public class BulbSwitcherII {
    public static void main(String[] args) {
        System.out.println(flipLights(1, 1));
        System.out.println(flipLights(2, 1));
        System.out.println(flipLights(3, 1));
    }

    /**
     * 有四种关灯方法，全关，关偶数灯，关奇数灯，关 3k+1 的灯。现在给 n 盏灯，允许 m 步操作，问共能组成多少种不同的状态
     * 对四个状态进行分析会发现：
     * 全部反转+反转奇数=反转偶数、全部反转+反转偶数=反转奇数、反转奇数+反转偶数=全部反转
     * 因此最后会有这么几种状态：
     * 1)原始状态(即全亮)，2)操作一，3)操作二，4)操作三，５)操作四，6)操作一 + 操作四，7)操作二 + 操作四，8)操作三 + 操作四
     * 分情况讨论：
     * 1. 当 m 和 n 其中有任意一个数是0时，返回1
     * 2. 当 n = 1时，只有两种情况，0和1
     * 3. 当 n = 2时，要看 m 的次数，如果m = 1，那么有三种状态 00，01，10
     *                           当m > 1时，那么有四种状态，00，01，10，11
     * 4. 当 m = 1时，此时 n 至少为3，那么有四种状态，000，010，101，011
     * 5. 当 m = 2时，此时 n 至少为3，那么有七种状态：111，101，010，100，000，001，110
     * 6. 当 m > 2时，此时 n 至少为3，那么有八种状态：111，101，010，100，000，001，110，011
     */
    public static int flipLights(int n, int m) {
        if (n == 0 || m == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            return m == 1 ? 3 : 4;
        }
        if (m == 1) {
            return 4;
        }
        return m == 2 ? 7 : 8;
    }
}

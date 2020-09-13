package com.wz.math;

/**
 * You are standing at position 0 on an infinite number line. There is a goal at position target.
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 * Return the minimum number of steps required to reach the destination.
 *
 * Example 1:
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second step we step from 1 to 3.
 *
 * Example 2:
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second move we step  from 1 to -1.
 * On the third move we step from -1 to 2.
 *
 * Note:
 * target will be a non-zero integer in the range [-10^9, 10^9].
 */
public class ReachANumber {
    public static void main(String[] args) {
        System.out.println(reachNumber(3));
        System.out.println(reachNumber(2));
    }

    /**
     * 其实就是相当于
     * [ ]1[ ]2[ ]3[ ]...[ ]n = target
     * 在括号里填上 ± 符号，使得 n 最小
     *
     * 1. 如果按照某个方向一直走，那么走到第N步的时候，走过的距离为 1+2+3+...+N
     * 2. 如果其中第j步朝着反方向走，那么走过的距离就是在原来朝着某个方向走距离的基础上减去 2*j
     * 3. 所以可以一直朝着 target 方向走，如果超过了，继续向前走，直到走的距离-target 的值为偶数就行了，
     *    因为这个多出的偶数值肯定是可以被前面n步中的某一步以向反方向走的形式抵消
     */
    public static int reachNumber(int target) {
        target = Math.abs(target);
        int step = 0, sum = 0;
        // 走到刚好超过target
        while (sum < target) {
            step += 1;
            sum += step;
        }
        // 继续走，直到满足超过的距离是偶数，这样可以反方向抵消
        while ((sum - target) % 2 != 0) {
            step += 1;
            sum += step;
        }
        return step;
    }
}

package com.wz.math;

/**
 * You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north,
 * then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on.
 * In other words, after each move your direction changes counter-clockwise.
 * Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
 *
 * Example 1:
 * ┌───┐
 * │   │
 * └───┼──>
 *     │
 *
 * Input: [2,1,1,2]
 * Output: true
 *
 * Example 2:
 * ┌──────┐
 * │      │
 * │
 * │
 * └────────────>
 *
 * Input: [1,2,3,4]
 * Output: false
 *
 * Example 3:
 * ┌───┐
 * │   │
 * └───┼>
 *
 * Input: [1,1,1,1]
 * Output: true
 */
public class SelfCrossing {
    public static void main(String[] args) {
        System.out.println(isSelfCrossing(new int[]{2, 1, 1, 2}));
        System.out.println(isSelfCrossing(new int[]{1, 2, 3, 4}));
        System.out.println(isSelfCrossing(new int[]{1, 1, 1, 1}));
    }

    /**
     * 实际上相交的情况只有以下三种情况:
     *      x(1)
     *     ┌───┐
     * x(2)│   │x(0)
     *     └───┼──>
     *     x(3)│
     * 第 4+4n 条边和第 1+4n 条边相交（n>=0），此时条件是第 4 条边大等于第 2 条边，第 1 条边大等于第 3 条边
     *
     *       x(1)
     *     ┌──────┐
     *     │      │x(0)
     * x(2)│      ^
     *     │      │x(4)
     *     └──────│
     *       x(3)
     * 第 5+4n 条边和第 1+4n 条边相交（n>=0），此时条件是第 2 条边等于第 4 条边，第 5 条边大等于第 3、1 条边的差值
     *
     *        x(1)
     *     ┌──────┐
     *     │      │x(0)
     * x(2)│     <│────│
     *     │       x(5)│x(4)
     *     └───────────│
     *         x(3)
     * 第 6+4n 条边和第 1+4n 条边相交（n>=0），此时条件是第 4 条边大等于第 2 条边，第 3 条边大等于第 5 条边，
     * 第 5 条边大等于第 3、1 条边的差值，第 6 条边大等于第 4、2 条边的差值
     */
    public static boolean isSelfCrossing(int[] x) {
        for (int i = 3; i < x.length; i++) {
            if (x[i] >= x[i - 2] && x[i - 3] >= x[i - 1]) {
                return true;
            }
            if (i >= 4 && x[i - 1] == x[i - 3] && x[i] >= x[i - 2] - x[i - 4]) {
                return true;
            }
            if (i >= 5 && x[i - 2] >= x[i - 4] && x[i - 3] >= x[i - 1] && x[i - 1] >= x[i - 3] - x[i - 5] && x[i] >= x[i - 2] - x[i - 4]) {
                return true;
            }
        }
        return false;
    }
}

package com.wz.math;

/**
 * A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
 * Given a starting point (sx, sy) and a target point (tx, ty),
 * return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.
 *
 * Examples:
 * Input: sx = 1, sy = 1, tx = 3, ty = 5
 * Output: True
 * Explanation:
 * One series of moves that transforms the starting point to the target is:
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 *
 * Input: sx = 1, sy = 1, tx = 2, ty = 2
 * Output: False
 *
 * Input: sx = 1, sy = 1, tx = 1, ty = 1
 * Output: True
 *
 * Note:
 * sx, sy, tx, ty will all be integers in the range [1, 10^9].
 */
public class ReachingPoints {
    public static void main(String[] args) {
        System.out.println(reachingPoints(1, 1, 3, 5));
        System.out.println(reachingPoints(1, 1, 2, 2));
        System.out.println(reachingPoints(1, 1, 1, 1));
    }

    /**
     * 目标是将 tx 和 ty 分别缩小到 sx 和 sy，不可能一步就缩小到位，那么这肯定是一个循环，终止条件是 tx 和 ty 中任意一个小于 sx 和 sy
     * 想要缩小 tx 或 ty，先缩小两者中较大的那个，若 tx 大于 ty，可以尝试缩小 tx，但是如果此时 ty 等于 sy 了话，可以迅速判断出结果，
     * 通过计算此时 tx 和 sx 的差值是否是 ty 的倍数，因为此时 ty 不能改变了，只能缩小 tx，
     * 若能通过减去整数倍数个 ty 得到 sx 的，就表示可以到达。如果 ty 不等于 sy 的话，那么直接 tx 对 ty 取余即可。
     * 同理，若 ty 大于 tx，我们可以尝试缩小 ty，但是如果此时 tx 等于 sx 了话，可以迅速判断出结果，
     * 通过计算此时 ty 和 sy 的差值是否是 tx 的倍数，如果 tx 不等于 sx 的话，那么直接 ty 对 tx 取余即可
     */
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx > ty) {
                if (ty == sy) {
                    return (tx - sx) % ty == 0;
                }
                tx %= ty;
            } else {
                if (tx == sx) {
                    return (ty - sy) % tx == 0;
                } else ty %= tx;
            }
        }
        return false;
    }
}

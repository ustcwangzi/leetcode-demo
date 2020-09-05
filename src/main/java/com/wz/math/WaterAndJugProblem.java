package com.wz.math;

/**
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
 * You need to determine whether it is possible to measure exactly z litres using these two jugs.
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 * Operations allowed:
 * Fill any of the jugs completely with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 *
 * Example 1: (From the famous "Die Hard" example)
 * Input: x = 3, y = 5, z = 4
 * Output: True
 *
 * Example 2:
 * Input: x = 2, y = 6, z = 5
 * Output: False
 */
public class WaterAndJugProblem {
    public static void main(String[] args) {
        System.out.println(canMeasureWater(3, 5, 4));
        System.out.println(canMeasureWater(2, 6, 5));
    }

    /**
     * 有一个容量为3升和一个容量为5升的水罐，问我们如何准确的称出4升的水。
     * 先把5升水罐装满水，倒到3升水罐里，这时5升水罐里还有2升水，然后把3升水罐里的水都倒掉，把5升水罐中的2升水倒入3升水罐中，
     * 这时候把5升水罐解满，然后往此时有2升水的3升水罐里倒水，这样5升水罐倒出1升后还剩4升即为所求
     *
     * 有两个杯子，容量分别为x和y，问通过用两个杯子往里倒水，和往出舀水，问能不能使容器中的水刚好为z升。那么可以用一个公式来表达：
     * z = m * x + n * y
     * 其中m，n为舀水和倒水的次数，正数表示往里舀水，负数表示往外倒水，那么题目中的例子可以写成: 4 = (-2) * 3 + 2 * 5，
     * 即3升的水罐往外倒了两次水，5升水罐往里舀了两次水。
     * 那么问题就变成了对于任意给定的x,y,z，存不存在m和n使得上面的等式成立。
     * ax + by = d 的解为 d = gcd(x, y)，那么只要 z % d == 0，上面的等式就有解，
     * 所以只要看z是不是x和y的最大公约数的倍数就行了，同时还有个限制条件 x + y >= z，因为x和y不可能称出比它们之和还多的水
     */
    public static boolean canMeasureWater(int x, int y, int z) {
        return z == 0 || (x + y >= z && z % gcd(x, y) == 0);
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}

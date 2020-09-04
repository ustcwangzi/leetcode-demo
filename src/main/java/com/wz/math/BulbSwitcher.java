package com.wz.math;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 *
 * Example:
 * Input: 3
 * Output: 1
 * Explanation:
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 *
 * So you should return 1, because there is only one bulb is on.
 */
public class BulbSwitcher {
    public static void main(String[] args) {
        System.out.println(bulbSwitch(3));
        System.out.println(bulbSwitch2(3));
    }

    /**
     * n个灯泡，第一次打开所有的灯泡，第二次每两个更改灯泡的状态，第三次每三个更改灯泡的状态，以此类推，第n次每n个更改灯泡的状态
     * 比如只有5个灯泡的情况，'X'表示灭，‘√’表示亮，如下所示：
     * 初始状态：    X    X    X    X    X
     * 第一次：      √    √    √    √    √
     * 第二次：      √     X    √    X    √
     * 第三次：      √     X    X    X    √
     * 第四次：      √     X    X    √    √
     * 第五次：      √     X    X    √    X
     * 那么最后发现五次遍历后，只有1号和4号灯泡是亮的，而且很巧的是它们都是平方数
     * 仔细想想，对于第 n 个灯泡，只有当次数是 n 的因子的之后，才能改变灯泡的状态，即 n 能被当前次数整除
     * 比如当 n 为36时，它的因数有(1,36), (2,18), (3,12), (4,9), (6,6), 可以看到前四个括号里成对出现的因数各不相同，
     * 括号中前面的数改变了灯泡状态，后面的数又变回去了，等于灯泡的状态没有发生变化，只有最后那个(6,6)，在次数6的时候改变了一次状态，
     * 没有对应其它的状态能将其变回去了，所以灯泡就一直是点亮状态的。即所有平方数的灯泡都将会是点亮的状态。
     * 那么问题就简化为了求 1 到 n 之间完全平方数的个数
     */
    public static int bulbSwitch(int n) {
        int result = 1;
        while (result * result <= n) {
            result++;
        }
        return result - 1;
    }

    /**
     * 直接对 n 开方，返回的是一个整型数，这个整型数的平方最接近于 n，即为 n 包含的所有完全平方数的个数
     */
    public static int bulbSwitch2(int n) {
        return (int) Math.sqrt(n);
    }
}

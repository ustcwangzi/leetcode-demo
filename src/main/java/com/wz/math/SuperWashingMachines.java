package com.wz.math;

import java.util.Arrays;

/**
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 * For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine
 * to one of its adjacent washing machines at the same time .
 * Given an integer array representing the number of dresses in each washing machine from left to right on the line,
 * you should find the minimum number of moves to make all the washing machines have the same number of dresses.
 * If it is not possible to do it, return -1.
 *
 * Example1
 * Input: [1,0,5]
 * Output: 3
 * Explanation:
 * 1st move:    1     0 <-- 5    =>    1     1     4
 * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 * 3rd move:    2     1 <-- 3    =>    2     2     2
 *
 * Example2
 * Input: [0,3,0]
 * Output: 2
 * Explanation:
 * 1st move:    0 <-- 3     0    =>    1     2     0
 * 2nd move:    1     2 --> 0    =>    1     1     1
 *
 * Example3
 * Input: [0,2,0]
 * Output: -1
 * Explanation:
 * It's impossible to make all the three washing machines have the same number of dresses.
 *
 * Note:
 * The range of n is [1, 10000].
 * The range of dresses number in a super washing machine is [0, 1e5].
 */
public class SuperWashingMachines {
    public static void main(String[] args) {
        System.out.println(findMinMoves(new int[]{1, 0, 5}));
        System.out.println(findMinMoves(new int[]{0, 3, 0}));
        System.out.println(findMinMoves(new int[]{0, 2, 0}));
    }

    /**
     * 要求使得每台洗衣机具有一样的衣服数，即将数组中的数字平均分给数组中的每一个元素。
     * 则衣服总数是所有洗衣机个数的倍数，那么sum%size应该为0，而且达到平衡时的衣服数为 avg=sum/size。
     * 另外，每台洗衣机每次只能移动一件衣服到邻接的洗衣机处，所以需要找出达到平衡时需要移动最多步数的洗衣机，
     * 从左开始算，负数代表从左移动的步数，正数则是从右移动的步数，那么其绝对值则是最少移动步数。
     * 比如，已经有5件衣服需要经过第i台机器进行传递，同时这台机器本身又溢出了2件衣服，那么就需要传递7件衣服，共计7步
     * 然后用 result 实时记录最大值即可，遍历完数组中所有的元素再求最大值，就可得出真正的最少步数
     */
    public static int findMinMoves(int[] machines) {
        int sum = Arrays.stream(machines).sum();
        if (sum % machines.length != 0) {
            return -1;
        }
        int result = 0, count = 0, avg = sum / machines.length;
        for (int m : machines) {
            // 找出经过该元素的最大流量
            count += m - avg;
            result = Math.max(result, Math.max(Math.abs(count), m - avg));
        }
        return result;
    }
}

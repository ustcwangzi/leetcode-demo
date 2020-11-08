package com.wz.string;

/**
 * There is a room with n bulbs, numbered from 0 to n-1, arranged in a row from left to right. Initially all the bulbs are turned off.
 * Your task is to obtain the configuration represented by target where target[i] is '1' if the i-th bulb is turned on and is '0' if it is turned off.
 * You have a switch to flip the state of the bulb, a flip operation is defined as follows:
 * 1. Choose any bulb (index i) of your current configuration.
 * 2. Flip each bulb from index i to n-1.
 * When any bulb is flipped it means that if it is 0 it changes to 1 and if it is 1 it changes to 0.
 * Return the minimum number of flips required to form target.
 *
 * Example 1:
 * Input: target = "10111"
 * Output: 3
 * Explanation: Initial configuration "00000".
 * flip from the third bulb:  "00000" -> "00111"
 * flip from the first bulb:  "00111" -> "11000"
 * flip from the second bulb:  "11000" -> "10111"
 * We need at least 3 flip operations to form target.
 *
 * Example 2:
 * Input: target = "101"
 * Output: 3
 * Explanation: "000" -> "111" -> "100" -> "101".
 *
 * Constraints:
 * 1. 1 <= target.length <= 10^5
 * 2. target[i] == '0' or target[i] == '1'
 */
public class BulbSwitcherIV {
    public static void main(String[] args) {
        System.out.println(minFlips("10111"));
    }

    /**
     * 可以不按照题目给的例子进行翻转，将例1的翻转过程改为
     * "00000"--> "11111"-->"10000"--> "10111"
     * 再结合其他例子就可以总结出翻转的方法：
     * 遍历 target，遇到第一个 1 时，将 1 及以后所有数字都翻转一次；下一次就是遇到 0 时，0 及后边的数字都再翻转一次；
     * 也就是每次遇到和之前的数字不一样时，后面的数字都需要翻转，也就是增加了一次翻转次数
     */
    public static int minFlips(String target) {
        int result = 0, cur = 0;
        char[] array = target.toCharArray();
        for (char c : array) {
            if (c - '0' != cur) {
                cur ^= 1;
                ++result;
            }
        }
        return result;
    }
}

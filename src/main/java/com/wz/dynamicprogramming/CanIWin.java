package com.wz.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
 * The player who first causes the running total to reach or exceed 100 wins.
 * What if we change the game so that players cannot re-use integers?
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win,
 * otherwise return false. Assume both players play optimally.
 *
 * Example 1:
 * Input: maxChoosableInteger = 10, desiredTotal = 11
 * Output: false
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 *
 * Example 2:
 * Input: maxChoosableInteger = 10, desiredTotal = 0
 * Output: true
 *
 * Constraints:
 * 1. 1 <= maxChoosableInteger <= 20
 * 2. 0 <= desiredTotal <= 300
 */
public class CanIWin {
    public static void main(String[] args) {
        System.out.println(canIWin(10, 11));
        System.out.println(canIWin(10, 0));
    }

    /**
     * DFS
     * 使用 Map 来记录已经计算过的结果，首先判断如果给定数字范围大于等于目标值，返回 true，如果给定的数字总和小于目标值，返回 false
     * 然后进入递归，首先查找当前情况是否在 Map 中存在，存在则直接返回，使用 mask 按位来记录某个数字是否使用过，
     * 遍历 0～max 所有数字，如果 1 << i  和 mask 相与为 0，说明该数字没有使用过，若此时的目标值小于等于当前数字，说明已经赢了，
     * 或者进行递归，如果返回 false，说明也是第一个人赢了，返回 true。遍历完所有数字，说明自己输了，标记 false，并返回 false
     */
    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }
        return dfs(maxChoosableInteger, desiredTotal, 0, new HashMap<>());
    }

    private static boolean dfs(int max, int total, int mask, Map<Integer, Boolean> map) {
        if (map.containsKey(mask)) {
            return map.get(mask);
        }
        for (int i = 0; i < max; ++i) {
            int cur = (1 << i);
            if ((cur & mask) == 0) {
                if (total <= i + 1 || !dfs(max, total - (i + 1), cur | mask, map)) {
                    map.put(mask, true);
                    return true;
                }
            }
        }
        map.put(mask, false);
        return false;
    }
}

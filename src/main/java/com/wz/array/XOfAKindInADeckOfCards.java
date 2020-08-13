package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * In a deck of cards, each card has an integer written on it.
 * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 *
 * Example 1:
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 *
 * Example 2:
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false´
 * Explanation: No possible partition.
 *
 * Example 3:
 * Input: deck = [1,1]
 * Output: true
 * Explanation: Possible partition [1,1].
 *
 * Example 4:
 * Input: deck = [1,1,2,2,2,2]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[2,2].
 */
public class XOfAKindInADeckOfCards {
    public static void main(String[] args) {
        int[] deck = new int[]{1, 2, 3, 4, 4, 3, 2, 1};
        System.out.println(hasGroupsSizeX(deck));
        System.out.println(hasGroupsSizeX2(deck));

        deck = new int[]{1, 1, 1, 2, 2, 2, 3, 3};
        System.out.println(hasGroupsSizeX(deck));
        System.out.println(hasGroupsSizeX2(deck));

        deck = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
        System.out.println(hasGroupsSizeX(deck));
        System.out.println(hasGroupsSizeX2(deck));
    }

    /**
     * 将这副牌分成若干堆，每堆均有X个
     * 使用一个 HashMap 来建立牌跟其出现次数之间的映射，由于每堆X个，如果某张牌的个数小于X，则肯定无法分，所以X的范围是 [2, min]，
     * 其中 min 是数量最少的牌的个数，遍历 HashMap，找出最小的映射值 min，若 min 小于2，可以直接返回 false。
     * 否则就从2遍历到 min，依次来检验候选值X。检验的方法是看其个数是否能整除候选值X，不一定非要相等，
     * 比如 [1, 1, 2, 2, 2, 2], K=2 时就可以分为三堆 [1, 1], [2, 2], [2, 2]，即相同的牌也可以分到其他堆里，所以只要能整除X即可
     */
    public static boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : deck) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
        }

        int minCount = Integer.MAX_VALUE;
        for (int count : countMap.values()) {
            minCount = Math.min(minCount, count);
        }
        if (minCount < 2) {
            return false;
        }

        for (int i = 2; i <= minCount; ++i) {
            boolean success = true;
            for (int count : countMap.values()) {
                if (count % i != 0) {
                    success = false;
                    break;
                }
            }
            if (success) {
                return true;
            }
        }

        return false;
    }

    /**
     * 最大公约数解法
     * 公约数能被所有的数字个数整除，所以最大的那个公约数，就是能被所有数字个数整除的最大组大小
     */
    public static boolean hasGroupsSizeX2(int[] deck) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : deck) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
        }

        int result = 0;
        for (int count : countMap.values()) {
            result = gcd(result, count);
        }

        return result >= 2;
    }

    private static int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}

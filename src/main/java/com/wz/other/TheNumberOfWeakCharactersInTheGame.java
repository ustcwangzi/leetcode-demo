package com.wz.other;

import java.util.Arrays;

/**
 * You are playing a game that contains multiple characters, and each of the characters has two main properties:
 * attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
 * A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels.
 * More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
 * Return the number of weak characters.
 *
 * Example 1:
 * Input: properties = [[5,5],[6,3],[3,6]]
 * Output: 0
 * Explanation: No character has strictly greater attack and defense than the other.
 *
 * Example 2:
 * Input: properties = [[2,2],[3,3]]
 * Output: 1
 * Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 *
 * Example 3:
 * Input: properties = [[1,5],[10,4],[4,3]]
 * Output: 1
 * Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 *
 * Constraints:
 * 1. 2 <= properties.length <= 10^5
 * 2. properties[i].length == 2
 * 3. 1 <= attacki, defensei <= 10^5
 */
public class TheNumberOfWeakCharactersInTheGame {
    public static void main(String[] args) {
        System.out.println(numberOfWeakCharacters(new int[][]{{1, 5}, {10, 4}, {4, 3}}));
    }

    /**
     * 对数组按照 attack 降序排序，若 attack 相等则按照 defense 升序排序，然后遍历数组，同时记录已遍历到的最大 defense，记为 max
     * 若当前角色的 defense 小于 max，则满足条件，因为 attack 是按照降序排序的，当前角色的 attack 一定小于前面已出现的
     */
    public static int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o2[0], o1[0]);
        });

        int count = 0, max = 0;
        for (int[] property : properties) {
            if (property[1] < max) {
                count++;
            }
            max = Math.max(max, property[1]);
        }
        return count;
    }
}

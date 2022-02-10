package com.wz.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * There are n rings and each ring is either red, green, or blue. The rings are distributed across ten rods labeled from 0 to 9.
 * You are given a string rings of length 2n that describes the n rings that are placed onto the rods.
 * Every two characters in rings forms a color-position pair that is used to describe each ring where:
 * 1. The first character of the ith pair denotes the ith ring's color ('R', 'G', 'B').
 * 2. The second character of the ith pair denotes the rod that the ith ring is placed on ('0' to '9').
 * For example, "R3G2B1" describes n == 3 rings: a red ring placed onto the rod labeled 3,
 * a green ring placed onto the rod labeled 2, and a blue ring placed onto the rod labeled 1.
 * Return the number of rods that have all three colors of rings on them.
 *
 * Example 1:
 * Input: rings = "B0B6G0R6R0R6G9"
 * Output: 1
 * Explanation:
 * - The rod labeled 0 holds 3 rings with all colors: red, green, and blue.
 * - The rod labeled 6 holds 3 rings, but it only has red and blue.
 * - The rod labeled 9 holds only a green ring.
 * Thus, the number of rods with all three colors is 1.
 *
 * Example 2:
 * Input: rings = "B0R0G0R9R0B0G0"
 * Output: 1
 * Explanation:
 * - The rod labeled 0 holds 6 rings with all colors: red, green, and blue.
 * - The rod labeled 9 holds only a red ring.
 * Thus, the number of rods with all three colors is 1.
 *
 * Constraints:
 * 1. rings.length == 2 * n
 * 2. 1 <= n <= 100
 * 3. rings[i] where i is even is either 'R', 'G', or 'B' (0-indexed).
 * 4. rings[i] where i is odd is a digit from '0' to '9' (0-indexed).
 */
public class RingsAndRods {
    public static void main(String[] args) {
        System.out.println(countPoints("B0B6G0R6R0R6G9"));
        System.out.println(countPoints("B0R0G0R9R0B0G0"));
    }

    /**
     * 哪些 rod 上 RGB 三种 ring 都存在，直接使用 map 记录每个 rod 上 ring 的颜色
     */
    public static int countPoints(String rings) {
        Map<Integer, Set<Character>> map = new HashMap<>(10);
        for (int i = 0; i < rings.length(); i += 2) {
            int rod = rings.charAt(i + 1) - '0';
            map.putIfAbsent(rod, new HashSet<>(3));
            map.get(rod).add(rings.charAt(i));
        }

        int count = 0;
        for (int i = 0; i < 10; i++) {
            if (map.containsKey(i) && map.get(i).size() == 3) {
                count++;
            }
        }
        return count;
    }
}

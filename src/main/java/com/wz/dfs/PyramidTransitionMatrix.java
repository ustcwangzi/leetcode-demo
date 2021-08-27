package com.wz.dfs;

import java.util.*;

/**
 * We are stacking blocks to form a pyramid. Each block has a color which is a one-letter string.
 * We are allowed to place any color block C on top of two adjacent blocks of colors A and B, if and only if ABC is an allowed triple.
 * We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed.
 * Each allowed triple is represented as a string of length 3.
 * Return true if we can build the pyramid all the way to the top, otherwise false.
 *
 * Example 1:
 * Input: bottom = "BCD", allowed = ["BCG","CDE","GEA","FFF"]
 * Output: true
 * Explanation:
 * We can stack the pyramid like this:
 *     A
 *    / \
 *   G   E
 *  / \ / \
 * B   C   D
 * We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.
 *
 * Example 2:
 * Input: bottom = "AABA", allowed = ["AAA","AAB","ABA","ABB","BAC"]
 * Output: false
 * Explanation:
 * We cannot stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
 *
 * Constraints:
 * 1. 2 <= bottom.length <= 8
 * 2. 0 <= allowed.length <= 200
 * 3. allowed[i].length == 3
 * 4. The letters in all input strings are from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
 */
public class PyramidTransitionMatrix {
    public static void main(String[] args) {
        System.out.println(pyramidTransition("BCD", Arrays.asList("BCG", "CDE", "GEA", "FFF")));
    }

    /**
     * DFS
     * 每一层一个底，以这些底构成上一层的金字塔的底，然后再往上循环构建金字塔
     * 上层的底可以是多种，使用 map 保存，DFS 遍历每一种，无法构建就尝试下一种
     */
    public static boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<Character>> map = new HashMap<>();
        for (String str : allowed) {
            map.putIfAbsent(str.substring(0, 2), new ArrayList<>());
            map.get(str.substring(0, 2)).add(str.charAt(2));
        }
        List<Character> bottomList = new ArrayList<>();
        for (char c : bottom.toCharArray()) {
            bottomList.add(c);
        }
        return dfs(0, bottomList, new ArrayList<>(), map);
    }

    /**
     * @param index 当前层位置
     * @param cur 当前层
     * @param next 下一层
     * @param map 下一层能够使用的底
     */
    private static boolean dfs(int index, List<Character> cur, List<Character> next, Map<String, List<Character>> map) {
        // 当前层最后一个字符
        if (index == cur.size() - 1) {
            if (next.size() == 1) {
                return true;
            }
            index = 0;
            cur = next;
            next = new ArrayList<>();
        }

        String prefix = cur.get(index) + "" + cur.get(index + 1);
        if (!map.containsKey(prefix)) {
            return false;
        }

        // 尝试每一种可用字符
        for (char c : map.get(prefix)) {
            next.add(c);
            if (dfs(index + 1, cur, next, map)) {
                return true;
            }
            next.remove(next.size() - 1);
        }
        return false;
    }
}

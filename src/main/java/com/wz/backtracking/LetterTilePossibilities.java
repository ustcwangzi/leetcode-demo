package com.wz.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * You have n  tiles, where each tile has one letter tiles[i] printed on it.
 * Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.
 *
 * Example 1:
 * Input: tiles = "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 *
 * Constraints:
 * 1. 1 <= tiles.length <= 7
 * 2. tiles consists of uppercase English letters.
 */
public class LetterTilePossibilities {
    public static void main(String[] args) {
        System.out.println(numTilePossibilities("AAB"));
    }

    /**
     * 与 {@link PermutationsII} 类似
     * 区别在于 {@link PermutationsII} 需要判断长度，而本题不需要
     */
    public static int numTilePossibilities(String tiles) {
        List<List<Character>> result = new LinkedList<>();
        char[] array = tiles.toCharArray();
        Arrays.parallelSort(array);
        dfs(array, new boolean[array.length], result, new ArrayList<>());
        return result.size();
    }

    private static void dfs(char[] array, boolean[] visited, List<List<Character>> result, List<Character> group) {
        for (int i = 0; i < array.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && array[i] == array[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            group.add(array[i]);
            // 直接加入结果集
            result.add(new ArrayList<>(group));
            dfs(array, visited, result, group);
            visited[i] = false;
            group.remove(group.size() - 1);
        }
    }
}

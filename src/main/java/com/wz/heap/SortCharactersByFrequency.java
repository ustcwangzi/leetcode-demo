package com.wz.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 * Input:
 * "tree"
 * Output:
 * "eert"
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 * Input:
 * "cccaaa"
 * Output:
 * "cccaaa"
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }

    /**
     * 与 {@link TopKFrequentElements} 类似
     */
    public static String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] array = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char cur : array) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(map.size(), Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> cur = queue.poll();
            for (int i = 0; i < cur.getValue(); i++) {
                builder.insert(0, cur.getKey());
            }
        }
        return builder.toString();
    }
}

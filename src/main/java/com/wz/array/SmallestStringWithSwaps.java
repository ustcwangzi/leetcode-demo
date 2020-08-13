package com.wz.array;

import java.util.*;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 * Example 1:
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 *
 * Example 2:
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 *
 * Example 3:
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 */
public class SmallestStringWithSwaps {
    public static void main(String[] args) {
        List<List<Integer>> pairs = new LinkedList<>();
        List<Integer> pair = new ArrayList<>();
        pair.add(0);
        pair.add(3);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(1);
        pair.add(2);
        pairs.add(pair);
        System.out.println(smallestStringWithSwaps("dcab", pairs));

        pairs = new LinkedList<>();
        pair = new ArrayList<>();
        pair.add(0);
        pair.add(3);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(1);
        pair.add(2);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(0);
        pair.add(2);
        pairs.add(pair);
        System.out.println(smallestStringWithSwaps("dcab", pairs));

        pairs = new LinkedList<>();
        pair = new ArrayList<>();
        pair.add(0);
        pair.add(1);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(1);
        pair.add(2);
        pairs.add(pair);
        System.out.println(smallestStringWithSwaps("cba", pairs));
    }

    /**
     * 并查集
     * 如果将索引看做点，将每个索引对看做一条无向边，则这个图可以有若干个连通块，每个连通块内部可以任意排列组合对应的字符
     * 所以通过并查集的方式来求出每个连通块，然后在各个连通块内从小到大排序字符，然后再将排序好的字符放回，就是最终结果
     */
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFindSet set = new UnionFindSet(s.length());
        // 使用 pair 构建并查集
        for (List<Integer> list : pairs) {
            set.union(list.get(0), list.get(1));
        }

        char[] array = s.toCharArray();
        // 使用并查集构建连通块，这里使用 PriorityQueue 保证 get 时是有序的
        HashMap<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int key = set.findRoot(i);
            PriorityQueue<Character> q = map.getOrDefault(key, new PriorityQueue<>());
            q.add(array[i]);
            map.put(key, q);
        }

        // 依次取回排序好的字符
        for (int i = 0; i < array.length; i++) {
            int key = set.findRoot(i);
            if (key != -1) {
                array[i] = map.get(key).poll();
            }
        }

        return new String(array);
    }

    private static class UnionFindSet {
        private int[] elements;
        private int[] heights;

        public UnionFindSet(int n) {
            elements = new int[n];
            heights = new int[n];
            Arrays.fill(elements, -1);
            Arrays.fill(heights, 1);
        }

        /**
         * 寻找每个节点所在联通组的 root
         */
        public int findRoot(int i) {
            while (elements[i] != -1) {
                i = elements[i];
            }
            return i;
        }

        /**
         * 合并联通组 x 和 y，使用高度大的最为新的 root
         */
        public void union(int x, int y) {
            int xRoot = findRoot(x);
            int yRoot = findRoot(y);
            if (xRoot != yRoot) {
                if (heights[xRoot] > heights[yRoot]) {
                    elements[yRoot] = xRoot;
                } else if (heights[xRoot] < heights[yRoot]) {
                    elements[xRoot] = yRoot;
                } else {
                    elements[xRoot] = yRoot;
                    heights[yRoot]++;
                }
            }
        }

    }
}

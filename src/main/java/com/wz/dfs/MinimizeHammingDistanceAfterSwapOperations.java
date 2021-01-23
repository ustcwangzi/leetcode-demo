package com.wz.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps
 * where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index bi
 * (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple times and in any order.
 * The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements are different.
 * Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).
 * Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.
 *
 * Example 1:
 * Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
 * Output: 1
 * Explanation: source can be transformed the following way:
 * - Swap indices 0 and 1: source = [2,1,3,4]
 * - Swap indices 2 and 3: source = [2,1,4,3]
 * The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
 *
 * Example 2:
 * Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
 * Output: 2
 * Explanation: There are no allowed swaps.
 * The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
 */
public class MinimizeHammingDistanceAfterSwapOperations {
    public static void main(String[] args) {
        int[] source = new int[]{1, 2, 3, 4}, target = new int[]{2, 1, 4, 5};
        int[][] allowedSwaps = new int[][]{{0, 1}, {2, 3}};
        System.out.println(minimumHammingDistance(source, target, allowedSwaps));
    }

    /**
     * 并查集 Union-Find
     * 1. 对可以交换的下标位置，使用并查集进行合并
     * 2. 对 source 数组中每个位置的数，属于哪个集合，进行计数，存在 map 中
     * 3. 遍历 target 数组，对每个位置的数，查看对应集合，看是否存在，记录数量，并更新计数和结果
     */
    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (source[i] == target[i]) {
                continue;
            }
            int p = uf.find(i);
            map.putIfAbsent(p, new HashMap<>());
            map.get(p).put(source[i], map.get(p).getOrDefault(source[i], 0) + 1);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (source[i] == target[i]) {
                continue;
            }
            int p = uf.find(i);
            if (map.get(p).getOrDefault(target[i], 0) == 0) {
                result++;
            } else {
                map.get(p).put(target[i], map.get(p).get(target[i]) - 1);
            }
        }
        return result;
    }

    private static class UnionFind {
        int[] root;

        public UnionFind(int n) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
        }

        public int find(int i) {
            while (i != root[i]) {
                root[i] = root[root[i]];
                i = root[i];
            }
            return i;
        }

        public void union(int i, int j) {
            root[find(i)] = find(j);
        }
    }
}

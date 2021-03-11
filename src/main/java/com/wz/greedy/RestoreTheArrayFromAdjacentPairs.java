package com.wz.greedy;

import java.util.*;

/**
 * There is an integer array nums that consists of n unique elements, but you have forgotten it.
 * However, you do remember every pair of adjacent elements in nums.
 * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi]
 * indicates that the elements ui and vi are adjacent in nums.
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs,
 * either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
 * Return the original array nums. If there are multiple solutions, return any of them.
 *
 * Example 1:
 * Input: adjacentPairs = [[2,1],[3,4],[3,2]]
 * Output: [1,2,3,4]
 * Explanation: This array has all its adjacent pairs in adjacentPairs.
 * Notice that adjacentPairs[i] may not be in left-to-right order.
 *
 * Example 2:
 * Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * Output: [-2,4,1,-3]
 * Explanation: There can be negative numbers.
 * Another solution is [-3,1,4,-2], which would also be accepted.
 *
 * Constraints:
 * 1. nums.length == n
 * 2. adjacentPairs.length == n - 1
 * 3. adjacentPairs[i].length == 2
 * 4. 2 <= n <= 10^5
 * 5. -105 <= nums[i], ui, vi <= 10^5
 * 6. There exists some nums that has adjacentPairs as its pairs.
 */
public class RestoreTheArrayFromAdjacentPairs {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(restoreArray(new int[][]{{2, 1}, {3, 4}, {3, 2}})));
    }

    /**
     * 链表的头尾只有一个节点，找到头或尾节点，然后根据其相邻节点一路找下来就能恢复链表了
     * 1.建立无向图邻接表
     * 2.找到头节点或尾节点，即邻接链表长度为一的节点
     * 3.从该节点开始，逐个连接后续元素（连接后的元素放入set，防止造成死循环）
     */
    public static int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            map.putIfAbsent(pair[0], new LinkedList<>());
            map.putIfAbsent(pair[1], new LinkedList<>());
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }

        int cur = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                cur = entry.getKey();
                break;
            }
        }

        int[] result = new int[n];
        Set<Integer> visited = new HashSet<>();
        int index = -1;
        while (++index < n) {
            result[index] = cur;
            visited.add(cur);
            for (int adjacent : map.get(cur)) {
                if (!visited.contains(adjacent)) {
                    cur = adjacent;
                    break;
                }
            }
        }
        return result;
    }
}

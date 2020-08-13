package com.wz.array;

import java.util.*;

/**
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 *
 * Example 1:
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 *
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 5, 4};
        System.out.println(findLeastNumOfUniqueInts(arr, 1));

        arr = new int[]{4, 3, 1, 1, 3, 3, 2};
        System.out.println(findLeastNumOfUniqueInts(arr, 3));
    }

    /**
     * 先用 countMap 统计每个元素的出现次数，然后将 countMap 包装成 Node 存入 list 中
     * 对 list 中的 Node 根据次数 count 进行从小到大排序，然后从 list 中依次取出 Node 进行移除元素的操作
     * 最后，统计 list 中有多少 Node 的 count 大于0，就是最终的结果
     */
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Node> list = new ArrayList<>(countMap.size());
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        // 按照出现次数进行从小到大排序
        list.sort(Comparator.comparingInt(o -> o.count));

        // 从出现次数少的元素开始移除元素
        for (Node node : list) {
            while (node.count > 0 && k-- > 0) {
                node.count--;
            }
        }

        // 统计剩余次数大于0的元素个数
        int result = 0;
        for (Node node : list) {
            if (node.count > 0) {
                result++;
            }
        }
        return result;
    }

    private static class Node {
        private int value;
        private int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}

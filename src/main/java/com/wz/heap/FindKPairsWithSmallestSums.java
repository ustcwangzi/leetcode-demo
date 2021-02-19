package com.wz.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * Example 1:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 *              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 */
public class FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        System.out.println(kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
    }

    /**
     * 小根堆
     * 使用 PriorityQueue 保存 nums1 和 nums2 元素组合，当队列中组合数大于 k 时出队
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((num1, num2) -> Integer.compare(num2.get(0) + num2.get(1), num1.get(0) + num1.get(1)));
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            for (int j = 0; j < Math.min(k, nums2.length); j++) {
                queue.offer(Arrays.asList(nums1[i], nums2[j]));
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        return new ArrayList<>(queue);
    }
}

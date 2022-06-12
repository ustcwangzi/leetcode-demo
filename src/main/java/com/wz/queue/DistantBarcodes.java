package com.wz.queue;

import java.util.*;

/**
 * In a warehouse, there is a row of barcodes, where the ith barcode is barcodes[i].
 * Rearrange the barcodes so that no two adjacent barcodes are equal. You may return any answer, and it is guaranteed an answer exists.
 *
 * Example 1:
 * Input: barcodes = [1,1,1,2,2,2]
 * Output: [2,1,2,1,2,1]
 *
 * Example 2:
 * Input: barcodes = [1,1,1,1,2,2,3,3]
 * Output: [1,3,1,3,1,2,1,2]
 *
 * Constraints:
 * 1. 1 <= barcodes.length <= 10000
 * 2. 1 <= barcodes[i] <= 10000
 */
public class DistantBarcodes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(rearrangeBarcodes(new int[]{1, 1, 1, 1, 2, 2, 3, 3})));
    }

    /**
     * 大根堆
     * 每次选取两个出现次数最多的元素加入结果集，然后分别将次数减一，若剩下的次数依然大于 0，则再次加入堆中
     */
    public static int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int barcode : barcodes) {
            map.put(barcode, map.getOrDefault(barcode, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(map.size(), (o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }

        int index = 0;
        int[] result = new int[barcodes.length];
        while (queue.size() > 1) {
            Map.Entry<Integer, Integer> first = queue.poll(), second = queue.poll();
            result[index++] = first.getKey();
            result[index++] = second.getKey();
            if (first.getValue() > 1) {
                queue.offer(new AbstractMap.SimpleEntry<>(first.getKey(), first.getValue() - 1));
            }
            if (second.getValue() > 1) {
                queue.offer(new AbstractMap.SimpleEntry<>(second.getKey(), second.getValue() - 1));
            }
        }
        if (!queue.isEmpty()) {
            result[index] = queue.poll().getKey();
        }
        return result;
    }
}

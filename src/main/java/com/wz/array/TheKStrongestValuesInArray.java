package com.wz.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of integers arr and an integer k.
 * A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m| where m is the median of the array.
 * If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if arr[i] > arr[j].
 * Return a list of the strongest k values in the array. return the answer in any arbitrary order.
 * Median is the middle value in an ordered integer list. More formally, if the length of the list is n,
 * the median is the element in position ((n - 1) / 2) in the sorted list (0-indexed).
 * For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11] and
 * the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
 * For arr = [-7, 22, 17, 3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22] and
 * the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.
 *
 * Example 1:
 * Input: arr = [1,2,3,4,5], k = 2
 * Output: [5,1]
 * Explanation: Median is 3, the elements of the array sorted by the strongest are [5,1,4,2,3]. The strongest 2 elements are [5, 1]. [1, 5] is also accepted answer.
 * Please note that although |5 - 3| == |1 - 3| but 5 is stronger than 1 because 5 > 1.
 *
 * Example 2:
 * Input: arr = [1,1,3,5,5], k = 2
 * Output: [5,5]
 * Explanation: Median is 3, the elements of the array sorted by the strongest are [5,5,1,1,3]. The strongest 2 elements are [5, 5].
 */
public class TheKStrongestValuesInArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(getStrongest(arr, 2)));

        arr = new int[]{1, 1, 3, 5, 5};
        System.out.println(Arrays.toString(getStrongest(arr, 2)));
    }

    /**
     * 就是要求与 median 差值的绝对值的 TopK
     * 先对数组进行排序获取 median，然后用小根堆存储 TopK 个元素，当差值大于堆顶时，弹出堆顶，新值加入
     */
    public static int[] getStrongest(int[] arr, int k) {
        Arrays.parallelSort(arr);
        int n = arr.length;
        int median = arr[(n - 1) / 2];

        // 小根堆
        PriorityQueue<Node> queue = new PriorityQueue<>(k, (o1, o2) -> {
            if (o2.diff != o1.diff) {
                return o1.diff - o2.diff;
            }
            return o1.value - o2.value;
        });
        for (int num : arr) {
            Node node = new Node(num, Math.abs(num - median));
            if (queue.isEmpty() || queue.size() < k) {
                queue.offer(node);
            } else if (queue.peek().diff < node.diff || (queue.peek().diff == node.diff && queue.peek().value < node.value)) {
                queue.poll();
                queue.offer(node);
            }
        }

        int[] result = new int[k];
        int index = 0;
        while (!queue.isEmpty()) {
            result[index++] = queue.poll().value;
        }
        return result;
    }

    private static class Node {
        private int value;
        private int diff;

        public Node(int value, int diff) {
            this.value = value;
            this.diff = diff;
        }
    }
}

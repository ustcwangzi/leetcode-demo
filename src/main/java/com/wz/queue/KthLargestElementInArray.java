package com.wz.queue;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 *
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray {
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    /**
     * 小根堆
     * 以最大的 k 个元素建立小根堆，堆顶是这 k 个元素中最小的，即最终结果
     * 遍历数组，将当前元素加入堆中，继续遍历下一个元素
     * 若堆中元素个数大于 k，则弹出堆顶，此时弹出的是这些元素中的最小的，最后直接返回堆顶元素即可
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }
}

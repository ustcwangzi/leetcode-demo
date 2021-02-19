package com.wz.heap;

import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Implement KthLargest class:
 * 1. KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * 2. int add(int val) Returns the element representing the kth largest element in the stream.
 *
 * Example 1:
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 */
public class KthLargestElementInStream {
    public static void main(String[] args) {
        KthLargestElementInStream kthLargestElementInStream = new KthLargestElementInStream(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargestElementInStream.add(3));
        System.out.println(kthLargestElementInStream.add(5));
        System.out.println(kthLargestElementInStream.add(10));
        System.out.println(kthLargestElementInStream.add(9));
        System.out.println(kthLargestElementInStream.add(4));
    }

    PriorityQueue<Integer> queue;
    int size;

    /**
     * 与 {@link KthLargestElementInArray} 类似
     */
    public KthLargestElementInStream(int k, int[] nums) {
        queue = new PriorityQueue<>();
        size = k;
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
    }

    public int add(int val) {
        queue.offer(val);
        if (queue.size() > size) {
            queue.poll();
        }
        return queue.peek();
    }
}

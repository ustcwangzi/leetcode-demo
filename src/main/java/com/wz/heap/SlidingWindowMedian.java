package com.wz.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle values.
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the median array for each window in the original array. Answers within 10^-5 of the actual value will be accepted.
 *
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 *
 * Example 2:
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 *
 * Constraints:
 * 1. 1 <= k <= nums.length <= 10^5
 * 2. -2^31 <= nums[i] <= 2^31 - 1
 */
public class SlidingWindowMedian {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{10, 1, 2, 3, 4}, 2)));
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    /**
     * 是对 {@link com.wz.design.MedianFinder} 的扩展
     * 不同之处在于从 k 个元素开始，每次需要将第一个元素移除
     * 同时注意 {@link SlidingWindowMedian#rebalance(Queue, Queue)} 中 max 与 min 相差为一时，多的元素在 max 中
     * 这么设计还是因为本题中需要移除元素，以 [10, 1, 2, 3, 4], 2 为例说明多的元素不在 max 时会导致的问题
     * nums     10      1       2       3       4
     * max      10      1       1       3       3
     * min              10      2       2       4,2
     * median           5.5     1.5     2.5     2
     * delete           10      1       2       3
     * 在插入 3 之后，需要移除 2，此时由于 2 <= max栈顶元素3，因此会从 max 中去删除，删除失败，导致后面的结果不再符合预期
     * 原因是在插入 2 之后，需要移除 1，此时进行 rebalance，max 与 min 相差一，将 min 的元素移动到 max 即可，因为移除时先判断的 max
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        Queue<Integer> maxQueue = new PriorityQueue<>(k, Comparator.reverseOrder()), minQueue = new PriorityQueue<>(k);
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            add(maxQueue, minQueue, nums[i]);
            int index = i - k + 1;
            if (index >= 0) {
                result[index] = getMedian(maxQueue, minQueue);
                remove(maxQueue, minQueue, nums[index]);
            }
        }
        return result;
    }

    private static void add(Queue<Integer> maxQueue, Queue<Integer> minQueue, int num) {
        if (maxQueue.isEmpty() || maxQueue.peek() >= num) {
            maxQueue.offer(num);
        } else {
            minQueue.offer(num);
        }
        rebalance(maxQueue, minQueue);
    }

    private static void remove(Queue<Integer> maxQueue, Queue<Integer> minQueue, int num) {
        if (num <= maxQueue.peek()) {
            maxQueue.remove(num);
        } else {
            minQueue.remove(num);
        }
        rebalance(maxQueue, minQueue);
    }

    private static double getMedian(Queue<Integer> maxQueue, Queue<Integer> minQueue) {
        if (maxQueue.size() > minQueue.size()) {
            return maxQueue.peek();
        }
        if (minQueue.size() > maxQueue.size()) {
            return minQueue.peek();
        }
        return ((double) maxQueue.peek() + minQueue.peek()) / 2;
    }

    private static void rebalance(Queue<Integer> maxQueue, Queue<Integer> minQueue) {
        if (maxQueue.size() > minQueue.size() + 1) {
            minQueue.offer(maxQueue.poll());
        } else if (minQueue.size() > maxQueue.size()) {
            maxQueue.offer(minQueue.poll());
        }
    }
}

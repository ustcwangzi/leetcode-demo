package com.wz.design;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value and the median is the mean of the two middle values.
 * - For example, for arr = [2,3,4], the median is 3.
 * - For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data structure.
 * - double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * Constraints:
 * 1. -10^5 <= num <= 10^5
 * 2. There will be at least one element in the data structure before calling findMedian.
 * 3. At most 5 * 10^4 calls will be made to addNum and findMedian.
 */
public class MedianFinder {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    private final Queue<Integer> maxQueue;
    private final Queue<Integer> minQueue;

    public MedianFinder() {
        maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
        minQueue = new PriorityQueue<>();
    }

    /**
     * 使用两个堆分别存储一半元素，大根堆存储较小的一半、小根堆存储较大的一半，那么堆顶元素就是位于中间的
     * 加入元素时，若 num 不大于 maxQueue 的堆顶，说明属于较小的一半，需要加入 maxQueue，否则要加入 minQueue
     * 加入之后，需要判断两个堆的大小之差是否超过一，超过一时，需要将较多的移除元素加入到较少的那个
     */
    public void addNum(int num) {
        if (maxQueue.isEmpty() || maxQueue.peek() >= num) {
            maxQueue.offer(num);
        } else {
            minQueue.offer(num);
        }

        // 两个堆的大小之差不能超过一
        if (maxQueue.size() > minQueue.size() + 1) {
            minQueue.offer(maxQueue.poll());
        } else if (minQueue.size() > maxQueue.size() + 1) {
            maxQueue.offer(minQueue.poll());
        }
    }

    public double findMedian() {
        if (maxQueue.size() > minQueue.size()) {
            return maxQueue.peek();
        }
        if (minQueue.size() > maxQueue.size()) {
            return minQueue.peek();
        }
        return (double) (maxQueue.peek() + minQueue.peek()) / 2;
    }
}

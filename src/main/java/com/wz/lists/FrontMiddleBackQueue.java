package com.wz.lists;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Design a queue that supports push and pop operations in the front, middle, and back.
 * Implement the FrontMiddleBack class:
 * 1. FrontMiddleBack() Initializes the queue.
 * 2. void pushFront(int val) Adds val to the front of the queue.
 * 3. void pushMiddle(int val) Adds val to the middle of the queue.
 * 4. void pushBack(int val) Adds val to the back of the queue.
 * 5. int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
 * 6. int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
 * 7. int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
 * Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:
 * Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
 * Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].
 *
 * Example 1:
 * Input:
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * Output:
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 *
 * Explanation:
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // return 1 -> [4, 3, 2]
 * q.popMiddle();    // return 3 -> [4, 2]
 * q.popMiddle();    // return 4 -> [2]
 * q.popBack();      // return 2 -> []
 * q.popFront();     // return -1 -> [] (The queue is empty)
 *
 * Constraints:
 * 1. 1 <= val <= 10^9
 * 2. At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.
 */
public class FrontMiddleBackQueue {
    private Deque<Integer> firstQueue, secondQueue;
    private int size;

    /**
     * 用两个双端队列，一个存前半部分元素，一个存后半部分元素。同时，保持后半部分元素个数不少于前半部分，并且比前半部分最多多一个。
     * 这样在 pushMiddle 的时候永远都是向前队列队尾插入元素；popMiddle 的时候要判断下两个队列 size 是否一样，
     * 如果一样则 pop 前队列队尾，否则 pop 后队列队头；这里需要注意，popFront 的时候要看一下前队列是否为空
     */
    public FrontMiddleBackQueue() {
        firstQueue = new ArrayDeque<>();
        secondQueue = new ArrayDeque<>();
    }

    public void pushFront(int val) {
        firstQueue.offerFirst(val);
        adjust();
        size++;
    }

    public void pushMiddle(int val) {
        firstQueue.offerLast(val);
        adjust();
        size++;
    }

    public void pushBack(int val) {
        secondQueue.offerLast(val);
        adjust();
        size++;
    }

    public int popFront() {
        if (size == 0) {
            return -1;
        }

        int val = !firstQueue.isEmpty() ? firstQueue.pollFirst() : secondQueue.pollFirst();
        adjust();
        size--;
        return val;
    }

    public int popMiddle() {
        if (size == 0) {
            return -1;
        }

        // 如果两个队列size一样，则pop前一个队尾，否则pop后一个队头
        int val = firstQueue.size() == secondQueue.size() ? firstQueue.pollLast() : secondQueue.pollFirst();
        adjust();
        size--;
        return val;
    }

    public int popBack() {
        if (size == 0) {
            return -1;
        }

        int val = secondQueue.pollLast();
        adjust();
        size--;
        return val;
    }

    private void adjust() {
        if (secondQueue.size() - firstQueue.size() > 1) {
            firstQueue.offerLast(secondQueue.pollFirst());
        }
        if (secondQueue.size() < firstQueue.size()) {
            secondQueue.offerFirst(firstQueue.pollLast());
        }
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue queue = new FrontMiddleBackQueue();
        queue.pushFront(1);
        queue.pushBack(2);
        queue.pushMiddle(3);
        queue.pushMiddle(4);
        System.out.println(queue.popFront());
        System.out.println(queue.popMiddle());
        System.out.println(queue.popMiddle());
        System.out.println(queue.popBack());
        System.out.println(queue.popFront());
    }
}

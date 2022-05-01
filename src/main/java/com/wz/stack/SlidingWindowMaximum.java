package com.wz.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.
 *
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. -10^4 <= nums[i] <= 10^4
 * 3. 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1}, 1)));
    }

    /**
     * 单调队列，维护长度为 k 的队列，只允许更小的元素入队，那么队首永远是当前窗口内的最大元素
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // 长度不能超出 k
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            // 只允许更小的元素入队，遇到较大元素，则弹出队尾
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}

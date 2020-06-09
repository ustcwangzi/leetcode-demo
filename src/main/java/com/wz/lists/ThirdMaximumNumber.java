package com.wz.lists;

import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist,
 * return the maximum number. The time complexity must be in O(n).
 *
 * Example 1:
 * Input: [3, 2, 1]
 * Output: 1
 * Explanation: The third maximum is 1.
 *
 * Example 2:
 * Input: [1, 2]
 * Output: 2
 * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 *
 * Example 3:
 * Input: [2, 2, 3, 1]
 * Output: 1
 * Explanation: Note that the third maximum here means the third maximum distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(thirdMax(nums));

        nums = new int[]{2, 1};
        System.out.println(thirdMax(nums));

        nums = new int[]{2, 2, 3, 1};
        System.out.println(thirdMax(nums));
    }

    /**
     * 使用PriorityQueue实现小根堆，保存最大的三个数，堆顶元素就是最终结果
     */
    public static int thirdMax(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if (queue.contains(num)) {
                continue;
            }
            if (queue.size() < 3 || num > queue.peek()) {
                queue.add(num);
            }
            if (queue.size() > 3) {
                queue.poll();
            }
        }

        // 堆顶就是结果
        if (queue.size() == 3) {
            return queue.peek();
        }

        // 元素不满时，返回最大值
        while (queue.size() > 1) {
            queue.poll();
        }
        return queue.peek();
    }
}

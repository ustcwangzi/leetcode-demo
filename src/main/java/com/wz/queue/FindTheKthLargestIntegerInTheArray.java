package com.wz.queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.
 * Return the string that represents the kth largest integer in nums.
 * Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer,
 * "2" is the second-largest integer, and "1" is the third-largest integer.
 *
 * Example 1:
 * Input: nums = ["3","6","7","10"], k = 4
 * Output: "3"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
 * The 4th largest integer in nums is "3".
 *
 * Example 2:
 * Input: nums = ["2","21","12","1"], k = 3
 * Output: "2"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
 * The 3rd largest integer in nums is "2".
 *
 * Constraints:
 * 1. 1 <= k <= nums.length <= 10^4
 * 2. 1 <= nums[i].length <= 100
 * 3. nums[i] consists of only digits.
 * 4. nums[i] will not have any leading zeros.
 */
public class FindTheKthLargestIntegerInTheArray {
    public static void main(String[] args) {
        System.out.println(kthLargestNumber(new String[]{"2", "21", "12", "1"}, 3));
    }

    /**
     * 小根堆，与 {@link KthLargestElementInArray} 类似，只是重写了 compare 方法
     */
    public static String kthLargestNumber(String[] nums, int k) {
        Queue<String> queue = new PriorityQueue<>(Math.min(nums.length, k), (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return Integer.compare(o1.length(), o2.length());
        });
        for (String num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }
}

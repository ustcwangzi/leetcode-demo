package com.wz.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an integer array nums that is sorted in ascending order, return true if and only if you can split it into
 * one or more subsequences such that each subsequence consists of consecutive integers and has a length of at least 3.
 *
 * Example 1:
 * Input: nums = [1,2,3,3,4,5]
 * Output: true
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 *
 * Example 2:
 * Input: nums = [1,2,3,3,4,4,5,5]
 * Output: true
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 * Example 3:
 * Input: nums = [1,2,3,4,4,5]
 * Output: false
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^4
 * 2. -1000 <= nums[i] <= 1000
 * 3. nums is sorted in an ascending order.
 */
public class SplitArrayIntoConsecutiveSubsequences {
    public static void main(String[] args) {
        System.out.println(isPossible(new int[]{1, 2, 3, 3, 4, 5}));
    }

    /**
     * 优先将数字排在长度较小的队列后面
     * Map<Integer, PriorityQueue<Integer>> 的 key 为扑克牌队列的末尾元素，value 为优先队列，存储扑克牌队列的长度
     * 遍历数组，获取能和当前元素组合起来的最短队列进行出队，然后将队列长度加一后加入当前元素为 key 的 map 中
     * 遍历结束后，判断 map 中是否存在长度不足 3 的队列，否存在直接返回 false
     * 以 [1,2,3,3,4,5] 为例说明该过程
     * 1    {0,[]}, {1,[1]}
     * 2    {0,[]}, {1,[]}, {2,[2]}
     * 3    {0,[]}, {1,[]}, {2,[]}, {3,[3]}
     * 3    {0,[]}, {1,[]}, {2,[]}, {3,[1,3]]}
     * 4    {0,[]}, {1,[]}, {2,[]}, {3,[3]}, {4,[2]}
     * 5    {0,[]}, {1,[]}, {2,[]}, {3,[3]}, {4,[]}, {5,[3]}
     * 注意，遍历到第二个 3 时，因为前面的 1,2,3 已组合在一起，当前 3 只能自己一组，因为优先级队列中存储 [1,3]
     * 遍历到 4 时，取出 map 中 key 为 3 的最短长度 1，和 4 组合，3 的 value 变成 [3]，4 的 value 变成 [2]
     */
    public static boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            map.putIfAbsent(num - 1, new PriorityQueue<>());
            PriorityQueue<Integer> pre = map.get(num - 1);
            // 将长度最小的出队，与当前元素组合在一起
            int len = pre.isEmpty() ? 0 : pre.poll();

            map.putIfAbsent(num, new PriorityQueue<>());
            map.get(num).offer(len + 1);
        }

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            if (!entry.getValue().isEmpty() && entry.getValue().peek() < 3) {
                return false;
            }
        }
        return true;
    }
}

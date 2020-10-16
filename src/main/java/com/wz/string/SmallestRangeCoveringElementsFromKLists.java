package com.wz.string;

import java.util.*;

/**
 * You have k lists of sorted integers in non-decreasing order.
 * Find the smallest range that includes at least one number from each of the k lists.
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 *
 * Example 1:
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 * Example 2:
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 *
 * Constraints:
 * 1. nums.length == k
 * 2. 1 <= k <= 3500
 * 3. 1 <= nums[i].length <= 50
 * 3. -10^5 <= nums[i][j] <= 10^5
 * 5. nums[i] is sorted in non-decreasing order.
 */
public class SmallestRangeCoveringElementsFromKLists {
    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(4, 10, 15, 24, 26));
        nums.add(Arrays.asList(0, 9, 12, 20));
        nums.add(Arrays.asList(5, 18, 22, 30));
        System.out.println(Arrays.toString(smallestRange(nums)));
    }

    /**
     * 给定 k 个列表，需要找到最小区间，使得每个列表都至少有一个数在该区间中
     * 问题可以转化为，从 k 个列表中各取一个数，使得这 k 个数中的最大值与最小值的差最小
     * 那么需要维护 k 个指针，初始时将 k 个指针都指向第一个元素，为了让范围尽可能小，下一个候选应当是当前 k 中最小的元素右移
     * 为了每次快速定位最小元素，使用最小堆，堆中存放当前 k 个元素的所在行列，同时使用 max 保存当前 k 中的最大元素
     * 每次取出堆顶，即最小元素的所在行 row、所在列 col，用 max-nums[row][col] 和 end-start 比较来更新最小区间
     * 然后，将最小元素的下一个元素加入堆中，同时更新 max
     * 重复以上过程，直到遍历结束
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> nums.get(o[0]).get(o[1])));
        int max = Integer.MIN_VALUE, start = 0, end = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            queue.offer(new int[]{i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }
        while (queue.size() == nums.size()) {
            // 取出最小的元素获得到行列信息
            int[] top = queue.poll();
            int row = top[0], col = top[1];
            // 更新最小区间信息
            if (end - start > max - nums.get(row).get(col)) {
                start = nums.get(row).get(col);
                end = max;
            }
            // 将最小元素的下一个元素放入堆中
            if (col + 1 < nums.get(row).size()) {
                queue.offer(new int[]{row, col + 1});
                max = Math.max(max, nums.get(row).get(col + 1));
            }
        }

        return new int[]{start, end};
    }
}

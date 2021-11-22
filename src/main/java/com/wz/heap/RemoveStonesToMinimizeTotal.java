package com.wz.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile,
 * and an integer k. You should apply the following operation exactly k times:
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 * Return the minimum possible total number of stones remaining after applying the k operations.
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 *
 * Example 1:
 * Input: piles = [5,4,9], k = 2
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.
 *
 * Example 2:
 * Input: piles = [4,3,6,7], k = 3
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [4,3,3,7].
 * - Apply the operation on pile 3. The resulting piles are [4,3,3,4].
 * - Apply the operation on pile 0. The resulting piles are [2,3,3,4].
 * The total number of stones in [2,3,3,4] is 12.
 *
 * Constraints:
 * 1. 1 <= piles.length <= 10^5
 * 2. 1 <= piles[i] <= 10^4
 * 3. 1 <= k <= 10^5
 */
public class RemoveStonesToMinimizeTotal {
    public static void main(String[] args) {
        System.out.println(minStoneSum(new int[]{5, 4, 9}, 2));
    }

    /**
     * 大根堆，将 piles 全部放入堆中，然后依次取出堆顶元素(即当前最大值)，更新后重新放入堆中，同时从 sum 中减去减少的值，重复 k 次
     */
    public static int minStoneSum(int[] piles, int k) {
        int sum = Arrays.stream(piles).sum();
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        Arrays.stream(piles).forEach(queue::offer);
        while (k-- > 0 && !queue.isEmpty()) {
            int cur = queue.poll();
            int value = (cur + 1) / 2;
            queue.offer(value);
            sum -= cur - value;
        }
        return sum;
    }
}

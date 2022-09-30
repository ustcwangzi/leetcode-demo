package com.wz.other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive).
 * The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.
 * You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i
 * such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.
 * Return an array containing the answers to the queries.
 *
 * Example 1:
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
 *
 * Example 2:
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 *
 * Constraints:
 * 1. 1 <= intervals.length <= 10^5
 * 2. 1 <= queries.length <= 10^5
 * 3. intervals[i].length == 2
 * 4. 1 <= lefti <= righti <= 10^7
 * 5. 1 <= queries[j] <= 10^7
 */
public class MinimumIntervalToIncludeEachQuery {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(minInterval(new int[][]{{1, 4}, {2, 4}, {3, 6}, {4, 4}}, new int[]{2, 3, 4, 5})));
        System.out.println(Arrays.toString(minInterval(new int[][]{{2, 3}, {2, 5}, {1, 8}, {20, 25}}, new int[]{2, 19, 5, 22})));
    }

    /**
     * 对于 query1、query2，若 query1 < query2，则所有 right < query1 的 interval，right 必定小于 query2
     * 所以在处理 query2 之前，可以将所有 right < query1 的 interval 移除
     */
    public static int[] minInterval(int[][] intervals, int[] queries) {
        int[][] query = new int[queries.length][2];
        for (int i = 0; i < query.length; i++) {
            query[i] = new int[]{queries[i], i};
        }
        Arrays.parallelSort(query, Comparator.comparingInt(o -> o[0]));
        Arrays.parallelSort(intervals, Comparator.comparingInt(o -> o[0]));

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int[] result = new int[queries.length];
        int i = 0;
        for (int[] cur : query) {
            // left <= query 的 interval 入堆
            while (i < intervals.length && intervals[i][0] <= cur[0]) {
                pq.add(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][1]});
                i++;
            }
            // 移除超出边界的 interval
            while (pq.size() > 0 && pq.peek()[1] < cur[0]) {
                pq.poll();
            }
            result[cur[1]] = pq.size() == 0 ? -1 : pq.peek()[0];
        }
        return result;
    }
}

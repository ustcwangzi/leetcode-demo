package com.wz.other;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * There is a long table with a line of plates and candles arranged on top of it.
 * You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive).
 * For each query, you need to find the number of plates between candles that are in the substring.
 * A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|".
 * The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
 * Return an integer array answer where answer[i] is the answer to the ith query.
 *
 * Example 1:
 * @link ../../../../resource/PlatesBetweenCandles1.jpg
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 * Output: [2,3]
 * Explanation:
 * - queries[0] has two plates between candles.
 * - queries[1] has three plates between candles.
 *
 * Example 2:
 * @link ../../../../resource/PlatesBetweenCandles2.jpg
 * Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * Output: [9,0,0,0,0]
 * Explanation:
 * - queries[0] has nine plates between candles.
 * - The other queries have zero plates between candles.
 *
 * Constraints:
 * 1. 3 <= s.length <= 10^5
 * 2. s consists of '*' and '|' characters.
 * 3. 1 <= queries.length <= 10^5
 * 4. queries[i].length == 2
 * 5. 0 <= lefti <= righti < s.length
 */
public class PlatesBetweenCandles {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}})));
        System.out.println(Arrays.toString(platesBetweenCandles("***|**|*****|**||**|*", new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
    }

    /**
     * preSum + TreeSet
     * 使用 preSum[] 计算截止当前位置的 plate 个数，使用 TreeSet 记录每个 candle 的位置
     * 然后遍历 queries，对于每个 [left,right] 利用 ceiling() 和 floor() 快速得到左右边界 [leftMost,rightMost]
     * 使用 leftMost、rightMost 去 preSum[] 中得到 plate 个数，计算该范围内的 plate 个数
     */
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length(), leftPlateCount = 0;
        int[] preSum = new int[n];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') {
                set.add(i);
                preSum[i] = leftPlateCount;
            } else {
                leftPlateCount++;
            }
        }

        int index = 0;
        int[] result = new int[queries.length];
        for (int[] query : queries) {
            // ceiling: >= key 的最小元素，floor: <= key 的最大元素
            Integer leftMost = set.ceiling(query[0]), rightMost = set.floor(query[1]);
            if (leftMost != null && rightMost != null && leftMost < rightMost) {
                result[index] = preSum[rightMost] - preSum[leftMost];
            }
            index++;
        }
        return result;
    }
}

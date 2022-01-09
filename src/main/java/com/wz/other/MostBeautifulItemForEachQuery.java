package com.wz.other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of an item respectively.
 * You are also given a 0-indexed integer array queries. For each queries[j],
 * you want to determine the maximum beauty of an item whose price is less than or equal to queries[j].
 * If no such item exists, then the answer to this query is 0.
 * Return an array answer of the same length as queries where answer[j] is the answer to the jth query.
 *
 * Example 1:
 * Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
 * Output: [2,4,5,5,6,6]
 * Explanation:
 * - For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
 * - For queries[1]=2, the items which can be considered are [1,2] and [2,4].
 *   The maximum beauty among them is 4.
 * - For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
 *   The maximum beauty among them is 5.
 * - For queries[4]=5 and queries[5]=6, all items can be considered.
 *   Hence, the answer for them is the maximum beauty of all items, i.e., 6.
 *
 * Example 2:
 * Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
 * Output: [4]
 * Explanation:
 * The price of every item is equal to 1, so we choose the item with the maximum beauty 4.
 * Note that multiple items can have the same price and/or beauty.
 *
 * Example 3:
 * Input: items = [[10,1000]], queries = [5]
 * Output: [0]
 * Explanation:
 * No item has a price less than or equal to 5, so no item can be chosen.
 * Hence, the answer to the query is 0.
 *
 * Constraints:
 * 1. 1 <= items.length, queries.length <= 10^5
 * 2. items[i].length == 2
 * 3. 1 <= pricei, beautyi, queries[j] <= 10^9
 */
public class MostBeautifulItemForEachQuery {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maximumBeauty(new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}}, new int[]{1, 2, 3, 4, 5, 6})));
    }

    /**
     * 对 items 按照 price 进行排序，遍历 items，使用 map 保存 price 到 beauty 的映射
     * 因为 price 越往后越大，而结果需要的最大的 beauty，因此只允许 beauty 大的值加入 map 中
     * items 遍历结束后，接着遍历 queries，对于每个 query，在 map 中寻找 小于等于 query 的最大 price 对应的 beauty
     */
    public static int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.parallelSort(items, Comparator.comparingInt(o -> o[0]));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(items[0][0], items[0][1]);
        for (int[] item : items) {
            if (item[1] > map.lastEntry().getValue()) {
                map.put(item[0], item[1]);
            }
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            // 小于等于 queries[i] 的最大 key
            Integer floorKey = map.floorKey(queries[i]);
            result[i] = floorKey == null ? 0 : map.get(floorKey);
        }
        return result;
    }
}

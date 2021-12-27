package com.wz.other;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.
 * Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect.
 * Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
 * Design an algorithm that:
 * 1. Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
 * 2. Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 * 3. Finds the maximum price the stock has been based on the current records.
 * 4. Finds the minimum price the stock has been based on the current records.
 * Implement the StockPrice class:
 * 1. StockPrice() Initializes the object with no price records.
 * 2. void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
 * 3. int current() Returns the latest price of the stock.
 * 4. int maximum() Returns the maximum price of the stock.
 * 5. int minimum() Returns the minimum price of the stock.
 *
 * Example 1:
 * Input
 * ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
 * [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
 * Output
 * [null, null, null, 5, 10, null, 5, null, 2]
 * Explanation
 * StockPrice stockPrice = new StockPrice();
 * stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
 * stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
 * stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
 * stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
 * stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
 *                           // Timestamps are [1,2] with corresponding prices [3,5].
 * stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
 * stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
 * stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
 *
 * Constraints:
 * 1. 1 <= timestamp, price <= 10^9
 * 2. At most 10^5 calls will be made in total to update, current, maximum, and minimum.
 * 3. current, maximum, and minimum will be called only after update has been called at least once.
 */
public class StockPrice {
    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2, 5);
        System.out.println(stockPrice.current());
        System.out.println(stockPrice.maximum());
        stockPrice.update(1, 3);
        System.out.println(stockPrice.maximum());
        stockPrice.update(4, 2);
        System.out.println(stockPrice.minimum());
    }

    private final Map<Integer, Integer> map;
    private final TreeMap<Integer, Integer> treeMap;
    private int latestTime;

    public StockPrice() {
        map = new HashMap<>();
        treeMap = new TreeMap<>();
        latestTime = 0;
    }

    /**
     * 因为既需要根据时间查价格，又需要根据价格查个数，因此使用两个 map，第一个 map 存储 timestamp -> price
     * 第二个 map 存储 price -> count，另外有需要计算最小、最大价格，因此第二个 map 使用 TreeMap 存储
     * 更新价格时，若 timestamp 已存在，说明要修改之前的价格，因此需要从 map 中获取之前的价格、从 treeMap 中获取该价格出现次数
     * 并将其次数进行减一处理，然后，分别更新 map、treeMap，记录最新的价格，和其出现次数
     */
    public void update(int timestamp, int price) {
        latestTime = Math.max(latestTime, timestamp);
        if (map.containsKey(timestamp)) {
            int prePrice = map.get(timestamp), preCount = treeMap.get(prePrice);
            if (preCount == 1) {
                treeMap.remove(prePrice);
            } else {
                treeMap.put(prePrice, preCount - 1);
            }
        }
        map.put(timestamp, price);
        treeMap.put(price, treeMap.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return map.get(latestTime);
    }

    public int maximum() {
        return treeMap.lastKey();
    }

    public int minimum() {
        return treeMap.firstKey();
    }
}

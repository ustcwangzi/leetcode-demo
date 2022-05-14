package com.wz.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 * Implement the LFUCache class:
 * - LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * - int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * - void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[4,3], cnt(4)=2, cnt(3)=3
 *
 * Constraints:
 * 1. 0 <= capacity <= 10^4
 * 2. 0 <= key <= 10^5
 * 3. 0 <= value <= 10^9
 * 4. At most 2 * 10^5 calls will be made to get and put.
 */
public class LFUCache {
    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        System.out.println(lfu.get(1));
        lfu.put(3, 3);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(3));
        lfu.put(4, 4);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(4));
    }

    private int minFrequency;
    private final int capacity;
    private final Map<Integer, CacheNode> nodeMap;
    private final Map<Integer, Set<Integer>> countMap;

    private static class CacheNode {
        int value;
        int frequency;

        public CacheNode(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
    }

    /**
     * 使用 nodeMap 保存 key -> node，使用 countMap 保存 count -> keys
     * 注意 countMap 的 value 需要使用 LinkedHashSet，保证插入元素的有序
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>(capacity);
        this.countMap = new HashMap<>(capacity);
    }

    /**
     * 查询时，从 nodeMap 中获取需要的 node，然后更新 frequency
     */
    public int get(int key) {
        CacheNode cur = nodeMap.get(key);
        if (cur == null) {
            return -1;
        }

        update(key, cur);
        return cur.value;
    }

    /**
     * 插入时，若 key 已存在，则更新 value，同时更新 frequency
     * 若 key 不存在，则判断是不是已经到达 capacity，已到达则需要从 countMap 中删除 minFrequency 下的第一个 key，
     * 然后创建一个新 node，放入 nodeMap、countMap，同时更新 minFrequency
     */
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        CacheNode cur = nodeMap.get(key);
        if (cur != null) {
            cur.value = value;
            update(key, cur);
            return;
        }

        cur = new CacheNode(value, 1);
        if (nodeMap.size() == capacity) {
            Set<Integer> keys = countMap.get(minFrequency);
            int removeKey = keys.iterator().next();
            nodeMap.remove(removeKey);
            keys.remove(removeKey);
        }
        nodeMap.put(key, cur);
        countMap.putIfAbsent(cur.frequency, new LinkedHashSet<>());
        countMap.get(cur.frequency).add(key);
        minFrequency = 1;
    }

    private void update(int key, CacheNode node) {
        countMap.get(node.frequency).remove(key);
        if (countMap.get(node.frequency).size() == 0) {
            countMap.remove(node.frequency);
            if (node.frequency == minFrequency) {
                minFrequency++;
            }
        }
        node.frequency++;
        countMap.putIfAbsent(node.frequency, new LinkedHashSet<>());
        countMap.get(node.frequency).add(key);
    }
}

package com.wz.hash;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 * 1. LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * 2. int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * 3. void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 * Constraints:
 * 1. 1 <= capacity <= 3000
 * 2. 0 <= key <= 10^4
 * 3. 0 <= value <= 10^5
 * 4. At most 2 * 10^5 calls will be made to get and put.
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    private final int capacity;
    private final Map<Integer, Integer> map;

    /**
     * 使用 LinkedHashMap，该数据结构基于 HashMap 并通过双向链表来保证插入顺序性
     * 在每次访问缓存时，对该数据在 map 中进行更新，需要淘汰缓存时，map 中的第一个元素就是最少使用的
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>();
    }

    /**
     * 先移除再插入，保证顺序
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        int value = map.remove(key);
        map.put(key, value);
        return value;
    }

    /**
     * 不存在 key 时，直接插入
     * 容量满时，移除第一个位置的元素，因为它是最少使用的
     * 然后，将 k-v 加入 map 中
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
        } else if (map.size() == capacity) {
            // 移除第一个元素
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
        map.put(key, value);
    }

}

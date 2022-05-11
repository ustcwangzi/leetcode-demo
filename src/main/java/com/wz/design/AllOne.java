package com.wz.design;

import java.util.*;

/**
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 * Implement the AllOne class:
 * - AllOne() Initializes the object of the data structure.
 * - inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * - dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement,
 *   remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * - getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * - getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 *
 * Example 1:
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 *
 * Constraints:
 * 1. 1 <= key.length <= 10
 * 2. key consists of lowercase English letters.
 * 3. It is guaranteed that for each call to dec, key is existing in the data structure.
 * 4. At most 5 * 10^4 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */
public class AllOne {
    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

    private final Map<String, Integer> map;
    private final TreeMap<Integer, Set<String>> treeMap;

    /**
     * 使用 map 存储 key 的出现频次，使用 treeMap 存储每个出现次数下的 key 集合
     * inc 时，map 出现频次加一，treeMap[count+1] 中增加 key，同时 treeMap[count] 中移除 key
     * dec 时，map 出现频次减一，treeMap[count-1] 中增加 key，同时 treeMap[count] 中移除 key
     * getMaxKey、getMinKey 直接从 treeMap 获取 lastEntry、firstEntry 即可
     */
    public AllOne() {
        map = new HashMap<>();
        treeMap = new TreeMap<>();
    }

    public void inc(String key) {
        int count = map.getOrDefault(key, 0);
        map.put(key, count + 1);
        Set<String> set = treeMap.getOrDefault(count + 1, new HashSet<>());
        set.add(key);
        treeMap.put(count + 1, set);

        if (count > 0) {
            if (treeMap.get(count).size() == 1) {
                treeMap.remove(count);
            } else {
                treeMap.get(count).remove(key);
            }
        }
    }

    public void dec(String key) {
        int count = map.getOrDefault(key, 0);
        if (count == 0) {
            return;
        }

        if (count == 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
            Set<String> set = treeMap.getOrDefault(count - 1, new HashSet<>());
            set.add(key);
            treeMap.put(count - 1, set);
        }

        if (treeMap.get(count).size() == 1) {
            treeMap.remove(count);
        } else {
            treeMap.get(count).remove(key);
        }
    }

    public String getMaxKey() {
        Map.Entry<Integer, Set<String>> entry = treeMap.lastEntry();
        if (entry == null) {
            return "";
        }
        return entry.getValue().iterator().next();
    }

    public String getMinKey() {
        Map.Entry<Integer, Set<String>> entry = treeMap.firstEntry();
        if (entry == null) {
            return "";
        }
        return entry.getValue().iterator().next();
    }
}

package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a map that allows you to do the following:
 * 1. Maps a string key to a given value.
 * 2. Returns the sum of the values that have a key with a prefix equal to a given string.
 * Implement the MapSum class:
 * 1. MapSum() Initializes the MapSum object.
 * 2. void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * 3. int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 * Example 1:
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 *
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 * Constraints:
 * 1. 1 <= key.length, prefix.length <= 50
 * 2. key and prefix consist of only lowercase English letters.
 * 3. 1 <= val <= 1000
 * 4. At most 50 calls will be made to insert and sum.
 */
public class MapSum {
    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }

    private final Map<String, Integer> map;
    private final TrieNode root;

    public MapSum() {
        this.map = new HashMap<>();
        this.root = new TrieNode();
    }

    /**
     * 字典树 TrieNode，sum 保存经过当前节点的 val 之和，同时使用 map 保存原始的 key-val
     * 插入操作时，需要将所有经过的节点值全部进行更新操作
     * 获取 sum() 时，直接找到对应节点返回 sum 值即可
     */
    public void insert(String key, int val) {
        int diff = val - map.getOrDefault(key, 0);
        TrieNode cur = root;
        for (char c : key.toCharArray()) {
            int index = c - 'a';
            if (cur.child[index] == null) {
                cur.child[index] = new TrieNode();
            }
            cur = cur.child[index];
            cur.sum += diff;
        }
        map.put(key, val);
    }

    public int sum(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (cur.child[index] == null) {
                return 0;
            }
            cur = cur.child[index];
        }
        return cur.sum;
    }

    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int sum = 0;
    }

}

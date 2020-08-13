package com.wz.array;

import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements.
 *            Each element must have the same probability of being returned.
 */
public class RandomizedSet {
    /**
     * O(1)时间插入和删除元素，很容易想到hashMap，但hashMap不满足O(1)时间随机获取，因此增加一个list来满足O(1)时间随机获取
     * hashMap的key为真实元素值，value为list中对应的下标
     * 插入元素时，hashMap和list同时进行插入
     * 删除元素时，先通过hashMap获取下标，然后将list的最后一个元素换到该下标处，最后从hashMap移除元素
     */
    private Map<Integer, Integer> map;
    private List<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int removeIndex = map.get(val);
        map.remove(val);
        // 将要删除的元素和最后位置的元素进行交换，然后进行移除
        if (removeIndex != list.size() - 1) {
            int lastElement = list.get(list.size() - 1);
            map.put(lastElement, removeIndex);
            list.set(removeIndex, lastElement);
        }

        list.remove(list.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    public static void main(String[] args) {
        RandomizedSet randomSet = new RandomizedSet();
        System.out.println(randomSet.insert(0));
        System.out.println(randomSet.insert(1));

        System.out.println(randomSet.remove(0));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.remove(1));

        System.out.println(randomSet.getRandom());
    }
}

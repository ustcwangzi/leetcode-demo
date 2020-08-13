package com.wz.array;

import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each element being
 *            returned is linearly related to the number of same value the collection contains.
 */
public class RandomizedCollection {
    /**
     * 思路与{@link RandomizedSet}类似，只是将hashMap的value改为linkedHashSet
     */
    private Map<Integer, Set<Integer>> map;
    private List<Integer> list;

    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            map.get(val).add(list.size());
        } else {
            Set<Integer> set = new LinkedHashSet<>();
            set.add(list.size());
            map.put(val, set);
        }
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        Set<Integer> removeIndexSet = map.get(val);
        int removeIndex = removeIndexSet.iterator().next();
        removeIndexSet.remove(removeIndex);
        // 将要删除的元素和最后位置的元素进行交换，然后进行移除
        if (removeIndex != list.size() - 1) {
            int lastElement = list.get(list.size() - 1);
            map.get(lastElement).remove(list.size() - 1);
            map.get(lastElement).add(removeIndex);
            list.set(removeIndex, lastElement);
        }
        list.remove(list.size() - 1);
        if (removeIndexSet.isEmpty()) {
            map.remove(val);
        }
        return true;
    }

    public int getRandom() {
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        System.out.println(randomizedCollection.insert(0));
        System.out.println(randomizedCollection.insert(1));
        System.out.println(randomizedCollection.insert(1));

        System.out.println(randomizedCollection.remove(0));
        System.out.println(randomizedCollection.insert(2));
        System.out.println(randomizedCollection.remove(1));

        System.out.println(randomizedCollection.getRandom());
    }
}

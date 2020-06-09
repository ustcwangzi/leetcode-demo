package com.wz.lists;

import java.util.*;

public class RandomizedCollection {
    private Map<Integer, Set<Integer>> map;
    private List<Integer> list;
    private int size;

    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        size = 0;
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            map.get(val).add(size);
        } else {
            Set<Integer> set = new LinkedHashSet<>();
            set.add(size);
            map.put(val, set);
        }
        list.set(size, val);
        size++;
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        Set<Integer> removeIndexSet = map.get(val);
        int removeIndex = removeIndexSet.iterator().next();
        removeIndexSet.remove(removeIndex);
        if (removeIndex != size - 1) {
            int lastElement = list.get(size - 1);
            map.get(lastElement).remove(size - 1);
            map.get(lastElement).add(removeIndex);
            list.set(removeIndex, lastElement);
        }

        if (removeIndexSet.isEmpty()) {
            map.remove(val);
        }
        size--;
        return true;
    }

    public int getRandom() {
        int index = new Random().nextInt(size);
        return list.get(index);
    }
}

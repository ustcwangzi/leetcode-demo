package com.wz.hash;

/**
 * Design a HashSet without using any built-in hash table libraries.
 * Implement MyHashSet class:
 * 1. void add(key) Inserts the value key into the HashSet.
 * 2. bool contains(key) Returns whether the value key exists in the HashSet or not.
 * 3. void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 *
 * Example 1:
 * Input
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * Output
 * [null, null, null, true, false, null, true, null, false]
 *
 * Explanation
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // return True
 * myHashSet.contains(3); // return False, (not found)
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // return True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // return False, (already removed)
 *
 * Constraints:
 * 1. 0 <= key <= 10^6
 * 2. At most 104 calls will be made to add, remove, and contains.
 */
public class MyHashSet {
    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);
        myHashSet.add(2);
        System.out.println(myHashSet.contains(1));
        myHashSet.contains(3);
        myHashSet.add(2);
        System.out.println(myHashSet.contains(2));
        myHashSet.remove(2);
        System.out.println(myHashSet.contains(2));
    }

    boolean[] array;

    /**
     * 使用 boolean 类型的数组保存元素，插入时将对应位置的值设置为 true，删除时将对应位置的值设置为 false
     */
    public MyHashSet() {
        array = new boolean[1000001];
    }

    public void add(int key) {
        array[key] = true;
    }

    public void remove(int key) {
        array[key] = false;
    }

    public boolean contains(int key) {
        return array[key];
    }
}

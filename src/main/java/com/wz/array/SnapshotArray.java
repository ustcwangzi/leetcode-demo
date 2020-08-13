package com.wz.array;

import java.util.TreeMap;

/**
 * Implement a SnapshotArray that supports the following interface:
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 * Example 1:
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 *
 * Constraints:
 * 1 <= length <= 50000
 * At most 50000 calls will be made to set, snap, and get.
 * 0 <= index < length
 * 0 <= snap_id < (the total number of times we call snap())
 * 0 <= val <= 10^9
 */
public class SnapshotArray {
    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3);
        snapshotArr.set(0, 5);
        System.out.println(snapshotArr.snap());
        snapshotArr.set(0, 6);
        System.out.println(snapshotArr.get(0, 0));
    }

    private TreeMap<Integer, Integer>[] array;
    private int snap_id = 0;

    /**
     * 用 snap_id 记录当前 snap 的版本号，每次 set 操作的时候，把 (snap_id,index) 当做 key 存入字典中
     * get 操作的时候，把当前 (snap_id,index) 当做 key 在字典中查找，如果 key 存在则返回对应的值，
     * 如果不存在就把 snap_id 减去1继续查找，直到找到值或者 snap_id 为0为止
     * 使用 TreeMap 类型的数组实现，index 为数组下标， snap_id 为 TreeMap 的键
     */
    public SnapshotArray(int length) {
        array = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            array[i] = new TreeMap<>();
            array[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        array[index].put(snap_id, val);
    }

    public int snap() {
        return snap_id++;
    }

    public int get(int index, int snap_id) {
        // 获取小于等于 snap_id 的键所对应的值
        return array[index].floorEntry(snap_id).getValue();
    }
}

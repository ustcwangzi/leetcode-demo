package com.wz.array;

import java.util.*;

/**
 * Given an array of integers arr, replace each element with its rank.
 * The rank represents how large the element is. The rank has the following rules:
 * Rank is an integer starting from 1.
 * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * Rank should be as small as possible.
 *
 * Example 1:
 * Input: arr = [40,10,20,30]
 * Output: [4,1,2,3]
 * Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
 *
 * Example 2:
 * Input: arr = [100,100,100]
 * Output: [1,1,1]
 * Explanation: Same elements share the same rank.
 *
 * Example 3:
 * Input: arr = [37,12,28,9,100,56,80,5,12]
 * Output: [5,3,4,2,8,6,7,1,3]
 */
public class RankTransformOfAnArray {
    public static void main(String[] args) {
        int[] arr = new int[]{40, 10, 20, 30};
        System.out.println(Arrays.toString(arrayRankTransform(arr)));

        arr = new int[]{100, 100, 100};
        System.out.println(Arrays.toString(arrayRankTransform(arr)));
    }

    /**
     * 先用 treemap 把元素值存入 key 中，同时 value 用 list 记录元素的索引
     * 此时，map 中的元素是从小到大排好序的，然后遍历 list 中的索引，把对应的 rank 存入结果数组中，rank++
     */
    public static int[] arrayRankTransform(int[] arr) {
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            treeMap.putIfAbsent(arr[i], new ArrayList<>());
            treeMap.get(arr[i]).add(i);
        }

        int[] result = new int[arr.length];
        int rank = 1;
        for (Map.Entry<Integer, List<Integer>> entry : treeMap.entrySet()) {
            // 同值元素的序号相同
            for (int index : entry.getValue()) {
                result[index] = rank;
            }
            rank++;
        }
        return result;
    }
}

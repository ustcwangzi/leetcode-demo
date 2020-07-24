package com.wz.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
 * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
 * 1. a, b are from arr
 * 2. a < b
 * 2. b - a equals to the minimum absolute difference of any two elements in arr
 *
 * Example 1:
 * Input: arr = [4,2,1,3]
 * Output: [[1,2],[2,3],[3,4]]
 * Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
 *
 * Example 2:
 * Input: arr = [1,3,6,10,15]
 * Output: [[1,3]]
 *
 * Example 3:
 * Input: arr = [3,8,-10,23,19,-4,-14,27]
 * Output: [[-14,-10],[19,23],[23,27]]
 *
 * Constraints:
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 */
public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 1, 3};
        System.out.println(minimumAbsDifference(arr));

        arr = new int[]{1, 3, 6, 10, 15};
        System.out.println(minimumAbsDifference(arr));

        arr = new int[]{3, 8, -10, 23, 19, -4, -14, 27};
        System.out.println(minimumAbsDifference(arr));
    }

    /**
     * 先排序，第一次遍历获取相邻的两个数的最小差值, 即为最小绝对差, 第二次遍历找到等于最小绝对差的元素
     */
    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.parallelSort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }

        List<List<Integer>> result = new LinkedList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == min) {
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i - 1]);
                pair.add(arr[i]);
                result.add(pair);
            }
        }
        return result;
    }
}

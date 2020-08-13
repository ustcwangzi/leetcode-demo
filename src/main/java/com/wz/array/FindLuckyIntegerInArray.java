package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers arr, a lucky integer is an integer which has a frequency in the array equal to its value.
 * Return a lucky integer in the array.
 * If there are multiple lucky integers return the largest of them. If there is no lucky integer return -1.
 *
 *
 * Example 1:
 * Input: arr = [2,2,3,4]
 * Output: 2
 * Explanation: The only lucky number in the array is 2 because frequency[2] == 2.
 *
 * Example 2:
 * Input: arr = [1,2,2,3,3,3]
 * Output: 3
 * Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.
 *
 * Example 3:
 * Input: arr = [2,2,2,3,3]
 * Output: -1
 * Explanation: There are no lucky numbers in the array.
 */
public class FindLuckyIntegerInArray {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 3, 4};
        System.out.println(findLucky(arr));

        arr = new int[]{1, 2, 2, 3, 3, 3};
        System.out.println(findLucky(arr));

        arr = new int[]{2, 2, 2, 3, 3};
        System.out.println(findLucky(arr));
    }

    /**
     * 用 map 存储每个元素的出现次数，然后遍历 map，若元素值和出现次数相等，则更新 result
     */
    public static int findLucky(int[] arr) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        int result = -1;
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getKey().equals(entry.getValue())) {
                result = Math.max(result, entry.getKey());
            }
        }
        return result;
    }
}

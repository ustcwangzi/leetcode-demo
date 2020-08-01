package com.wz.lists;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array arr of integers, check if there exists two integers N and M such that N is the double of M ( i.e. N = 2 * M).
 * More formally check if there exists two indices i and j such that :
 * i != j
 * 0 <= i, j < arr.length
 * arr[i] == 2 * arr[j]
 *
 * Example 1:
 * Input: arr = [10,2,5,3]
 * Output: true
 * Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
 *
 * Example 2:
 * Input: arr = [7,1,14,11]
 * Output: true
 * Explanation: N = 14 is the double of M = 7,that is, 14 = 2 * 7.
 *
 * Example 3:
 * Input: arr = [3,1,7,11]
 * Output: false
 * Explanation: In this case does not exist N and M, such that N = 2 * M.
 */
public class CheckIfNAndItsDoubleExist {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 2, 5, 3};
        System.out.println(checkIfExist(arr));

        arr = new int[]{7, 1, 14, 11};
        System.out.println(checkIfExist(arr));

        arr = new int[]{-2, 0, 10, -19, 4, 6, -8};
        System.out.println(checkIfExist(arr));
    }

    /**
     * 把元素值和下标存在 map 中，然后检查 map 中是否存在值是当前元素的2倍并且下标不同的元素
     */
    public static boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i] * 2) && map.get(arr[i] * 2) != i) {
                return true;
            }
        }
        return false;
    }
}

package com.wz.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 * Example 1:
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 * Constraints:
 * 1. 1 <= arr1.length, arr2.length <= 1000
 * 2. 0 <= arr1[i], arr2[i] <= 1000
 * 3. All the elements of arr2 are distinct.
 * 4. Each arr2[i] is in arr1.
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(relativeSortArray(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6})));
    }

    /**
     * 使用 map 记录 arr2 中元素值和位置，将 arr1 转成 list，然后按照题目给定的规则进行排序
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        List<Integer> list = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        list.sort((o1, o2) -> {
            if (map.containsKey(o1) && map.containsKey(o2)) {
                return map.get(o1) - map.get(o2);
            }
            if (map.containsKey(o1)) {
                return -1;
            }
            if (map.containsKey(o2)) {
                return 1;
            }
            return o1 - o2;
        });

        return list.stream().mapToInt(i -> i).toArray();
    }
}

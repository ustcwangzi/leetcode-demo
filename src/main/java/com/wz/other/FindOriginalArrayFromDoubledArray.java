package com.wz.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original,
 * and then randomly shuffling the resulting array.
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array.
 * The elements in original may be returned in any order.
 *
 * Example 1:
 * Input: changed = [1,3,4,2,6,8]
 * Output: [1,3,4]
 * Explanation: One possible original array could be [1,3,4]:
 * - Twice the value of 1 is 1 * 2 = 2.
 * - Twice the value of 3 is 3 * 2 = 6.
 * - Twice the value of 4 is 4 * 2 = 8.
 * Other original arrays could be [4,3,1] or [3,1,4].
 *
 * Example 2:
 * Input: changed = [6,3,0,1]
 * Output: []
 * Explanation: changed is not a doubled array.
 *
 * Constraints:
 * 1. 1 <= changed.length <= 10^5
 * 2. 0 <= changed[i] <= 10^5
 */
public class FindOriginalArrayFromDoubledArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOriginalArray(new int[]{1, 3, 4, 2, 6, 8})));
        System.out.println(Arrays.toString(findOriginalArray(new int[]{6, 3, 0, 1})));
    }

    /**
     * 使用 map 保存每个数字出现次数，对数组进行排序后遍历数组，当前数字 num、num*2 都出现在 map 中时，满足条件加入结果集
     * 将 num、num*2 分别从 map 次数减一，若次数减到 0 则移除，注意处理 num==0 的情况
     * 最后判断 map 是否为空，为空说明全部元素处理完成，返回结果集，否则说明存在不满足条件的元素，返回空数组
     */
    public static int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if ((n & 1) == 1) {
            return new int[]{};
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : changed) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Arrays.parallelSort(changed);
        int index = 0;
        int[] result = new int[n / 2];
        for (int num : changed) {
            if (map.containsKey(num) && map.containsKey(num * 2)) {
                result[index++] = num;
                remove(map, num);
                // num == 0 的情况
                if (!map.containsKey(num * 2)) {
                    return new int[]{};
                }
                remove(map, num * 2);
            }
        }
        return map.size() == 0 ? result : new int[]{};
    }

    private static void remove(Map<Integer, Integer> map, int num) {
        if (map.get(num) == 1) {
            map.remove(num);
        } else {
            map.put(num, map.get(num) - 1);
        }
    }
}

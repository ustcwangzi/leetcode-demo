package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers A with even length, return true if and only if it is possible
 * to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.
 *
 * Example 1:
 * Input: [3,1,3,6]
 * Output: false
 *
 * Example 2:
 * Input: [2,1,2,6]
 * Output: false
 *
 * Example 3:
 * Input: [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 *
 * Example 4:
 * Input: [1,2,4,16,8,4]
 * Output: false
 */
public class ArrayOfDoubledPairs {
    public static void main(String[] args) {
        int[] A = new int[]{3, 1, 3, 6};
        System.out.println(canReorderDoubled(A));

        A = new int[]{4, -2, 2, -4};
        System.out.println(canReorderDoubled(A));

        A = new int[]{1, 2, 4, 16, 8, 4};
        System.out.println(canReorderDoubled(A));
    }

    /**
     * 判断数组里的数字是不是都能两两配对成 A[i] = 2 * A[j]，
     * 用map做辅助空间存储数字出现的次数，遍历map把map中可配对的数字的次数-1，如果都能两两配对，那最后map里的值一定都是0
     */
    public static boolean canReorderDoubled(int[] A) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : A) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int key = entry.getKey(), value = entry.getValue();
            // 对0特殊处理，因为0的两倍和一半都是本身
            if (value == 0 || (key == 0 && value % 2 == 0)) {
                countMap.put(key, 0);
                continue;
            }
            while (value-- > 0) {
                int target = key * 2;
                if (countMap.containsKey(target) && countMap.get(target) > 0) {
                    countMap.put(target, countMap.get(target) - 1);
                    continue;
                }
                target = key / 2;
                if (countMap.containsKey(target) && countMap.get(target) > 0) {
                    countMap.put(target, countMap.get(target) - 1);
                    continue;
                }
                return false;
            }
            countMap.put(key, 0);
        }
        for (int value : countMap.values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}

package com.wz.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:
 * 1. if x is even then x = x / 2
 * 2. if x is odd then x = 3 * x + 1
 * For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).
 * Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power value in ascending order,
 * if two or more integers have the same power value sort them by ascending order.
 * Return the k-th integer in the range [lo, hi] sorted by the power value.
 * Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps
 * and that the power of x is will fit in 32 bit signed integer.
 *
 * Example 1:
 * Input: lo = 12, hi = 15, k = 2
 * Output: 13
 * Explanation: The power of 12 is 9 (12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)
 * The power of 13 is 9
 * The power of 14 is 17
 * The power of 15 is 17
 * The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the second element which is 13.
 * Notice that 12 and 13 have the same power value and we sorted them in ascending order. Same for 14 and 15.
 *
 * Example 2:
 * Input: lo = 7, hi = 11, k = 4
 * Output: 7
 * Explanation: The power array corresponding to the interval [7, 8, 9, 10, 11] is [16, 3, 19, 6, 14].
 * The interval sorted by power is [8, 10, 11, 7, 9].
 * The fourth number in the sorted array is 7.
 *
 * Constraints:
 * 1. 1 <= lo <= hi <= 1000
 * 2. 1 <= k <= hi - lo + 1
 */
public class SortIntegersByThePowerValue {
    public static void main(String[] args) {
        System.out.println(getKth(12, 15, 2));
        System.out.println(getKth(7, 11, 4));
    }

    /**
     * 将元素存入 list，同时将每个元素对应的 power 存在 map 中，然后按照题目规则对 list 进行排序
     */
    public static int getKth(int lo, int hi, int k) {
        List<Integer> list = new ArrayList<>(hi - lo + 1);
        Map<Integer, Integer> map = new HashMap<>(hi - lo + 1);
        for (int i = lo; i <= hi; i++) {
            list.add(i);
            map.put(i, getPower(i));
        }

        list.sort((o1, o2) -> {
            int power1 = map.get(o1), power2 = map.get(o2);
            if (power1 == power2) {
                return Integer.compare(o1, o2);
            }
            return power1 - power2;
        });

        return list.get(k - 1);
    }

    private static int getPower(int value) {
        int count = 0;
        while (value != 1) {
            if (value % 2 == 0) {
                value /= 2;
                count++;
            } else {
                value = value * 3 + 1;
                count++;
            }
        }
        return count;
    }
}

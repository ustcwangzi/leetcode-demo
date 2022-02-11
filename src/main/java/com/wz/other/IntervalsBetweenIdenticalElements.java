package com.wz.other;

import java.util.*;

/**
 * You are given a 0-indexed array of n integers arr.
 * The interval between two elements in arr is defined as the absolute difference between their indices. More formally, the interval between arr[i] and arr[j] is |i - j|.
 * Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and each element in arr with the same value as arr[i].
 * Note: |x| is the absolute value of x.
 *
 * Example 1:
 * Input: arr = [2,1,3,1,2,3,3]
 * Output: [4,2,7,2,4,4,5]
 * Explanation:
 * - Index 0: Another 2 is found at index 4. |0 - 4| = 4
 * - Index 1: Another 1 is found at index 3. |1 - 3| = 2
 * - Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
 * - Index 3: Another 1 is found at index 1. |3 - 1| = 2
 * - Index 4: Another 2 is found at index 0. |4 - 0| = 4
 * - Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
 * - Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5
 *
 * Example 2:
 * Input: arr = [10,5,10,10]
 * Output: [5,0,3,4]
 * Explanation:
 * - Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
 * - Index 1: There is only one 5 in the array, so its sum of intervals to identical elements is 0.
 * - Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
 * - Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| = 4
 *
 * Constraints:
 * 1. n == arr.length
 * 2. 1 <= n <= 10^5
 * 3. 1 <= arr[i] <= 10^5
 */
public class IntervalsBetweenIdenticalElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getDistances1(new int[]{2, 1, 3, 1, 2, 3, 3})));
    }

    /**
     * 使用 map 保存每个元素出现的下标，然后依次计算差值之和，TLE
     */
    public static long[] getDistances1(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        long[] result = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = sum(map.get(arr[i]), i);
        }
        return result;
    }

    private static long sum(List<Integer> list, int index) {
        long sum = 0;
        for (int cur : list) {
            sum += Math.abs(cur - index);
        }
        return sum;
    }

    /**
     * 假设 'N' 的下标为 A, B, C, D, E, F，则 sum
     * = (D-A) + (D-B) + (D-C) + (E-D) + (F-D)
     * = (3*D - (SUM OF INDICES ON LEFT OF D AT WHICH 'N' OCCURS)) +
     * 	((SUM OF INDICES ON RIGHT OF D AT WHICH 'N' OCCURS) - 2*D)
     * = ((NO OF INDICES ON LEFT OF D AT WHICH 'N' OCCURS * D - PREFIX_SUM) +
     * 	((SUM OF ALL INDICES ON RIGHT OF D AT WHICH 'N' OCCURS) - (SIZE- i - 1) * D)
     */
    public static long[] getDistances2(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        long[] result = new long[arr.length];
        for (List<Integer> list : map.values()) {
            long sum = list.stream().mapToLong(Integer::longValue).sum();
            long preSum = 0L;
            for (long i = 0L; i < list.size(); i++) {
                int index = list.get((int) i);
                result[index] = index * i - preSum + (sum - preSum - index) - (list.size() - i - 1) * index;
                preSum += index;
            }
        }
        return result;
    }
}

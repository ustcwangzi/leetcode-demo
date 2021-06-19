package com.wz.twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
 * As the answer can be very large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (arr[i], arr[j], arr[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 *
 * Example 2:
 * Input: arr = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation:
 * arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 *
 * Constraints:
 * 1. 3 <= arr.length <= 3000
 * 2. 0 <= arr[i] <= 100
 * 3. 0 <= target <= 300
 */
public class ThreeSumWithMultiplicity {
    public static void main(String[] args) {
        System.out.println(threeSumMulti(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 8));
    }

    /**
     * 是对 {@link com.wz.array.ThreeSum} 的扩展，本题有大量的重复数字，每个组合可能会重复出现
     * 使用 Map 统计出每一个数字出现次数，然后遍历 Map 中任意两个数字组合 i 和 j，第三个数字 k 由 target-i-j 计算得到
     * 1. 若 k 不在 Map 中，直接跳过
     * 2. 若 i，j，k 三个数字相等，相当于在 map[i] 中任选三个数字进行排列组合
     * 3. 若 i 和 j 相等，但不等于k，则是在 map[i] 中任选两个数字进行排列组合，再乘以 map[k]
     * 4. 若三个数字都不相同，则直接用这三个数字 map[i]，map[j]，map[k] 相乘即可
     */
    public static int threeSumMulti(int[] arr, int target) {
        int mod = 1000000007;
        long result = 0;
        Map<Integer, Long> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0L) + 1);
        }

        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            for (Map.Entry<Integer, Long> entry2 : map.entrySet()) {
                int i = entry.getKey(), j = entry2.getKey(), k = target - i - j;
                if (!map.containsKey(k)) {
                    continue;
                }
                if (i == j && j == k) {
                    result += entry.getValue() * (entry.getValue() - 1) * (entry.getValue() - 2) / 6;
                } else if (i == j) {
                    result += entry.getValue() * (entry.getValue() - 1) / 2 * map.get(k);
                } else if (i < j && j < k) {
                    result += entry.getValue() * entry2.getValue() * map.get(k);
                }
            }
        }
        return (int) (result % mod);
    }
}

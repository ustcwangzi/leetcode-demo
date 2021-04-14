package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * A good meal is a meal that contains exactly two different food items with a sum of deliciousness equal to a power of two.
 * You can pick any two different foods to make a good meal.
 * Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the ith item of food,
 * return the number of different good meals you can make from this list modulo 10^9 + 7.
 * Note that items with different indices are considered different even if they have the same deliciousness value.
 *
 * Example 1:
 * Input: deliciousness = [1,3,5,7,9]
 * Output: 4
 * Explanation: The good meals are (1,3), (1,7), (3,5) and, (7,9).
 * Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.
 *
 * Example 2:
 * Input: deliciousness = [1,1,1,3,3,3,7]
 * Output: 15
 * Explanation: The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.
 *
 * Constraints:
 * 1. 1 <= deliciousness.length <= 10^5
 * 2. 0 <= deliciousness[i] <= 2^20
 */
public class CountGoodMeals {
    public static void main(String[] args) {
        System.out.println(countPairs(new int[]{1, 3, 5, 7, 9}));
        System.out.println(countPairs(new int[]{1, 1, 1, 3, 3, 3, 7}));
    }

    /**
     * 两数之和等于 2^i
     * 采用类似 {@link TwoSum} 的思路，因为 food 范围是 1～2^20 次方，因此两数之和最多是 2^21
     * 遍历 deliciousness，针对每个 food 寻找 map 中是否存在 2^i - food，若存在说明满足条件
     * map 中存的是每个 food 及其对应出现次数
     */
    public static int countPairs(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();
        int mod = 1000000007;
        long count = 0;
        // 尝试每种 2^i 组合
        for (int food : deliciousness) {
            int power = 1;
            for (int i = 0; i < 22; i++) {
                // 说明两数之和满足条件
                if (map.containsKey(power - food)) {
                    count += map.get(power - food);
                }
                power = power * 2;
            }
            map.put(food, map.getOrDefault(food, 0) + 1);
        }
        return (int) (count % mod);
    }
}

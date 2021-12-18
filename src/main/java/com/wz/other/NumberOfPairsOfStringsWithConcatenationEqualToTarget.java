package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of digit strings nums and a digit string target, return the number of pairs of indices (i, j) (where i != j)
 * such that the concatenation of nums[i] + nums[j] equals target.
 *
 * Example 1:
 * Input: nums = ["777","7","77","77"], target = "7777"
 * Output: 4
 * Explanation: Valid pairs are:
 * - (0, 1): "777" + "7"
 * - (1, 0): "7" + "777"
 * - (2, 3): "77" + "77"
 * - (3, 2): "77" + "77"
 *
 * Example 2:
 * Input: nums = ["123","4","12","34"], target = "1234"
 * Output: 2
 * Explanation: Valid pairs are:
 * - (0, 1): "123" + "4"
 * - (2, 3): "12" + "34"
 *
 * Example 3:
 * Input: nums = ["1","1","1"], target = "11"
 * Output: 6
 * Explanation: Valid pairs are:
 * - (0, 1): "1" + "1"
 * - (1, 0): "1" + "1"
 * - (0, 2): "1" + "1"
 * - (2, 0): "1" + "1"
 * - (1, 2): "1" + "1"
 * - (2, 1): "1" + "1"
 *
 * Constraints:
 * 1. 2 <= nums.length <= 100
 * 2. 1 <= nums[i].length <= 100
 * 3. 2 <= target.length <= 100
 * 4. nums[i] and target consist of digits.
 * 5. nums[i] and target do not have leading zeros.
 */
public class NumberOfPairsOfStringsWithConcatenationEqualToTarget {
    public static void main(String[] args) {
        System.out.println(numOfPairs(new String[]{"777", "7", "77", "77"}, "7777"));
        System.out.println(numOfPairs2(new String[]{"1", "1", "1"}, "11"));
    }

    /**
     * 直接双层遍历
     */
    public static int numOfPairs(String[] nums, String target) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && (nums[i] + nums[j]).equals(target)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 使用 map 记录每个字符串出现的次数，然后遍历数组，如果当前字符串不能作为 target 的前缀则直接跳过
     * 否则，判断 map 中是否存在 target 去除当前字符串后的后缀，存在则累加次数，注意前缀和后缀相同时，需要次数减一
     */
    public static int numOfPairs2(String[] nums, String target) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int result = 0;
        for (String num : nums) {
            if (!target.startsWith(num)) {
                continue;
            }

            String suffix = target.substring(num.length());
            if (countMap.containsKey(suffix)) {
                result += countMap.get(suffix);
                if (num.equals(suffix)) {
                    result--;
                }
            }
        }
        return result;
    }
}

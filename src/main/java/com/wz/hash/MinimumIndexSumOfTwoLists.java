package com.wz.hash;

import java.util.*;

/**
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers,
 * output all of them with no order requirement. You could assume there always exists an answer.
 *
 * Example 1:
 * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 *
 * Example 2:
 * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KFC","Shogun","Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 *
 * Example 3:
 * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KNN","KFC","Burger King","Tapioca Express","Shogun"]
 * Output: ["KFC","Burger King","Tapioca Express","Shogun"]
 *
 * Constraints:
 * 1. 1 <= list1.length, list2.length <= 1000
 * 2. 1 <= list1[i].length, list2[i].length <= 30
 * 3. list1[i] and list2[i] consist of spaces ' ' and English letters.
 * 4. All the stings of list1 are unique.
 * 5. All the stings of list2 are unique.
 */
public class MinimumIndexSumOfTwoLists {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"})));

        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"KFC", "Shogun", "Burger King"})));

        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"KNN", "KFC", "Burger King", "Tapioca Express", "Shogun"})));
    }

    /**
     * 使用 map1、map2 分别记录两个数组字符下标，然后遍历 map1，若当前 key 不存在与 map2 中，则直接跳过
     * 否则，计算 key 在两个 map 中的下标之和与 minSum 进行比较，若大于 minSum 直接跳过
     * 若小于 minSum，清空结果集并更新 minSum，然后将当前 key 放入结果集，若等于 minSum，直接将当前 key 放入结果集
     */
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map1 = new HashMap<>(list1.length), map2 = new HashMap<>(list2.length);
        for (int i = 0; i < list1.length; i++) {
            map1.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            map2.put(list2[i], i);
        }

        int minSum = list1.length + list2.length;
        List<String> result = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (!map2.containsKey(entry.getKey())) {
                continue;
            }

            int cur = entry.getValue() + map2.get(entry.getKey());
            if (cur > minSum) {
                continue;
            }

            if (cur < minSum) {
                minSum = cur;
                result = new LinkedList<>();
            }
            result.add(entry.getKey());
        }

        return result.toArray(new String[0]);
    }
}

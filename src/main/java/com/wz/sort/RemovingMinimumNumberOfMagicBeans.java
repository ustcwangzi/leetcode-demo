package com.wz.sort;

import java.util.Arrays;

/**
 * You are given an array of positive integers beans, where each integer represents the number of magic beans found in a particular magic bag.
 * Remove any number of beans (possibly none) from each bag such that the number of beans in each remaining non-empty bag
 * (still containing at least one bean) is equal. Once a bean has been removed from a bag, you are not allowed to return it to any of the bags.
 * Return the minimum number of magic beans that you have to remove.
 *
 * Example 1:
 * Input: beans = [4,1,6,5]
 * Output: 4
 * Explanation:
 * - We remove 1 bean from the bag with only 1 bean.
 *   This results in the remaining bags: [4,0,6,5]
 * - Then we remove 2 beans from the bag with 6 beans.
 *   This results in the remaining bags: [4,0,4,5]
 * - Then we remove 1 bean from the bag with 5 beans.
 *   This results in the remaining bags: [4,0,4,4]
 * We removed a total of 1 + 2 + 1 = 4 beans to make the remaining non-empty bags have an equal number of beans.
 * There are no other solutions that remove 4 beans or fewer.
 *
 * Example 2:
 * Input: beans = [2,10,3,2]
 * Output: 7
 * Explanation:
 * - We remove 2 beans from one of the bags with 2 beans.
 *   This results in the remaining bags: [0,10,3,2]
 * - Then we remove 2 beans from the other bag with 2 beans.
 *   This results in the remaining bags: [0,10,3,0]
 * - Then we remove 3 beans from the bag with 3 beans.
 *   This results in the remaining bags: [0,10,0,0]
 * We removed a total of 2 + 2 + 3 = 7 beans to make the remaining non-empty bags have an equal number of beans.
 * There are no other solutions that removes 7 beans or fewer.
 *
 * Constraints:
 * 1. 1 <= beans.length <= 10^5
 * 2. 1 <= beans[i] <= 10^5
 */
public class RemovingMinimumNumberOfMagicBeans {
    public static void main(String[] args) {
        System.out.println(minimumRemoval(new int[]{4, 1, 6, 5}));
        System.out.println(minimumRemoval(new int[]{2, 10, 3, 2}));
    }

    /**
     * 就是要移除较小的元素，然后将其他元素变为同一个值
     * 假设数组排序后为 [a, b, c, d] ( a < b < c < d )
     * - 如果将数组变为 [a, a, a, a]，需要移除的 beans 为 (b-a) + (c-a) + (d-a) == b+c+d - 3a == (a+b+c+d) - 4a
     * - 如果将数组变为 [0, a, a, a]，需要移除的 beans 为 a + (c-b) + (d-b) == a+c+d - 2b == (a+b+c+d) - 3b
     * - 如果将数组变为 [0, 0, a, a]，需要移除的 beans 为 a + b + (d-c) == a+b+d - c == (a+b+c+d) - 2c
     * - 如果将数组变为 [0, 0, 0, a]，需要移除的 beans 为 a + b + c == (a+b+c+d) - d
     * 因此题目转换为找到最大的 (n - i) * beans[i]，最终结果为 sum - max{(n - i) * beans[i]}
     */
    public static long minimumRemoval(int[] beans) {
        Arrays.parallelSort(beans);
        long sum = 0, max = 0, n = beans.length;
        for (int i = 0; i < n; i++) {
            sum += beans[i];
            max = Math.max(max, (n - i) * beans[i]);
        }
        return sum - max;
    }
}

package com.wz.twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 * You start at any tree of your choice, then repeatedly perform the following steps:
 * 1. Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * 2. Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2,
 * then back to step 1, then step 2, and so on until you stop.
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 * What is the total amount of fruit you can collect with this procedure?
 *
 * Example 1:
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 *
 * Example 2:
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 *
 * Example 3:
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 *
 * Example 4:
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 * Note:
 * 1. 1 <= tree.length <= 40000
 * 2. 0 <= tree[i] < tree.length
 */
public class FruitIntoBaskets {
    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{0, 1, 2, 2}));
        System.out.println(totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
    }

    /**
     * 滑动窗口
     * 本题的本质就是从任意位置开始，若最多只能收集两种水果，问最多能收集多少个水果，注意 tree[i] 代表水果种类，每个位置的水果个数都是1
     * 使用 Map 来记录每种水果出现次数，当 Mao 中当映射数量超过两个的时候，需要删掉一个映射，做法是滑动窗口的左边界 left 的水果减 1
     * 同时 left 右移，当映射数量回到两个的时候，用当前窗口的大小来更新结果，注意 Map 中水果减到 0 时，删除这种水果
     */
    public static int totalFruit(int[] tree) {
        int result = 0, left = 0, n = tree.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < n; right++) {
            map.put(tree[right], map.getOrDefault(tree[right], 0) + 1);
            // 保持最多有两种水果
            while (map.size() > 2) {
                map.put(tree[left], map.get(tree[left]) - 1);
                if (map.get(tree[left]) == 0) {
                    map.remove(tree[left]);
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}

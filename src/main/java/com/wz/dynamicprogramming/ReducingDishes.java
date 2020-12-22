package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
 * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied
 * by its satisfaction level  i.e.  time[i]*satisfaction[i]
 * Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.
 * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
 *
 * Example 1:
 * Input: satisfaction = [-1,-8,0,5,-9]
 * Output: 14
 * Explanation: After Removing the second and last dish, the maximum total Like-time coefficient will be equal to
 * (-1*1 + 0*2 + 5*3 = 14). Each dish is prepared in one unit of time.
 *
 * Example 2:
 * Input: satisfaction = [4,3,2]
 * Output: 20
 * Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
 *
 * Example 3:
 * Input: satisfaction = [-1,-4,-5]
 * Output: 0
 * Explanation: People don't like the dishes. No dish is prepared.
 *
 * Constraints:
 * 1. n == satisfaction.length
 * 2. 1 <= n <= 500
 * 3. -10^3 <= satisfaction[i] <= 10^3
 */
public class ReducingDishes {
    public static void main(String[] args) {
        System.out.println(maxSatisfaction(new int[]{-1, -8, 0, 5, -9}));
    }

    /**
     * 满意度越高的菜应该越在后面做，这样可以乘以等菜时间，实现喜爱时间最大化
     * 先把满意度进行排序
     * 从满意度最大的开始，向满意度小的遍历。每次添加的一个新的满意度对应的等菜时间是 1， 而已经添加过的菜的喜爱时间需要再增加一次
     * 使用 before 保存前面已经添加过的所有菜的喜爱时间，使用 sumTime 保存现在为止添加了所有的菜的总的喜爱时间
     */
    public static int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.parallelSort(satisfaction);
        int result = 0, sumTime = 0, before = 0;
        for (int i = n - 1; i >= 0; i--) {
            sumTime += before + satisfaction[i];
            before += satisfaction[i];
            result = Math.max(result, sumTime);
        }
        return result;
    }
}

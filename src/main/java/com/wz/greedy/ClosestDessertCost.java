package com.wz.greedy;

/**
 * You would like to make dessert and are preparing to buy the ingredients.
 * You have n ice cream base flavors and m types of toppings to choose from. You must follow these rules when making your dessert:
 * 1. There must be exactly one ice cream base.
 * 2. You can add one or more types of topping or have no toppings at all.
 * 3. There are at most two of each type of topping.
 * You are given three inputs:
 * 1. baseCosts, an integer array of length n, where each baseCosts[i] represents the price of the ith ice cream base flavor.
 * 2. toppingCosts, an integer array of length m, where each toppingCosts[i] is the price of one of the ith topping.
 * 3. target, an integer representing your target price for dessert.
 * You want to make a dessert with a total cost as close to target as possible.
 * Return the closest possible cost of the dessert to target. If there are multiple, return the lower one.
 *
 * Example 1:
 * Input: baseCosts = [1,7], toppingCosts = [3,4], target = 10
 * Output: 10
 * Explanation: Consider the following combination (all 0-indexed):
 * - Choose base 1: cost 7
 * - Take 1 of topping 0: cost 1 x 3 = 3
 * - Take 0 of topping 1: cost 0 x 4 = 0
 * Total: 7 + 3 + 0 = 10.
 *
 * Example 2:
 * Input: baseCosts = [2,3], toppingCosts = [4,5,100], target = 18
 * Output: 17
 * Explanation: Consider the following combination (all 0-indexed):
 * - Choose base 1: cost 3
 * - Take 1 of topping 0: cost 1 x 4 = 4
 * - Take 2 of topping 1: cost 2 x 5 = 10
 * - Take 0 of topping 2: cost 0 x 100 = 0
 * Total: 3 + 4 + 10 + 0 = 17. You cannot make a dessert with a total cost of 18.
 *
 * Example 3:
 * Input: baseCosts = [3,10], toppingCosts = [2,5], target = 9
 * Output: 8
 * Explanation: It is possible to make desserts with cost 8 and 10. Return 8 as it is the lower cost.
 *
 * Constraints:
 * 1. n == baseCosts.length
 * 2. m == toppingCosts.length
 * 3. 1 <= n, m <= 10
 * 4. 1 <= baseCosts[i], toppingCosts[i] <= 10^4
 * 5. 1 <= target <= 10^4
 */
public class ClosestDessertCost {
    public static void main(String[] args) {
        ClosestDessertCost dessertCost = new ClosestDessertCost();
        System.out.println(dessertCost.closestCost(new int[]{1, 7}, new int[]{3, 4}, 10));
        System.out.println(dessertCost.closestCost(new int[]{2, 3}, new int[]{4, 5, 100}, 18));
        System.out.println(dessertCost.closestCost(new int[]{3, 10}, new int[]{2, 5}, 9));
    }

    private int result = Integer.MAX_VALUE;

    /**
     * DFS
     * 求出所有搭配组合的价格
     */
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        for (int baseCost : baseCosts) {
            dfs(toppingCosts, target, baseCost, 0);
        }
        return result;
    }

    private void dfs(int[] toppingCosts, int target, int currentCost, int index) {
        if (Math.abs(currentCost - target) < Math.abs(result - target)) {
            result = currentCost;
        } else if (Math.abs(currentCost - target) == Math.abs(result - target)) {
            result = Math.min(result, currentCost);
        }

        if (index == toppingCosts.length) {
            return;
        }

        // 不选择 topping
        dfs(toppingCosts, target, currentCost, index + 1);
        // 选择一个 topping
        dfs(toppingCosts, target, currentCost + toppingCosts[index], index + 1);
        // 选择两个 topping
        dfs(toppingCosts, target, currentCost + 2 * toppingCosts[index], index + 1);
    }
}

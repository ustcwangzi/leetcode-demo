package com.wz.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti],
 * the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
 *
 * Example 1:
 * Input: costs = [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 * Constraints:
 * 1. 2 * n == costs.length
 * 2. 2 <= costs.length <= 100
 * 3. costs.length is even.
 * 4. 1 <= aCosti, bCosti <= 1000
 */
public class TwoCityScheduling {
    public static void main(String[] args) {
        int[][] costs = new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        System.out.println(twoCitySchedCost(costs));
    }

    /**
     * 通过计算去A市、B市之间花费的差值 cost[i][0]-cost[i][1]，来判断哪一部分人去A市，哪一部分人去B市，差值最小的人去A市，
     * 只要把去A市的人确定了，剩下的都去B市就行
     * 即：按照差值对数组进行排序，前一半去A市，后一半去B市
     */
    public static int twoCitySchedCost(int[][] costs) {
        Arrays.parallelSort(costs, Comparator.comparingInt(o -> (o[0] - o[1])));

        int result = 0;
        for (int i = 0; i < costs.length; i++) {
            if (i < costs.length / 2) {
                result += costs[i][0];
            } else {
                result += costs[i][1];
            }
        }
        return result;
    }
}

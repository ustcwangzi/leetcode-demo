package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * We have a collection of rocks, each rock has a positive integer weight.
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 *
 * Example 1:
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 *
 * Note:
 * 1. 1 <= stones.length <= 30
 * 2. 1 <= stones[i] <= 100
 */
public class LastStoneWeightII {
    public static void main(String[] args) {
        System.out.println(lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}));
    }

    /**
     * 分两步，第一步判断是否能得到重量 i，第二步根据第一步的结果获取最小重量
     * dp[i] 表示是否存在一个划分，使得重量总和为 i，对于每个 stone，有放和不放两种选择，因此 dp[i] = dp[i] || dp[i-stone]
     * 然后从 sum/2 到 0 开始枚举每个重量 i，如果 dp[i] == true，说明可以得到，直接返回 sum-i-i，因为两个石头抵消
     */
    public static int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;
        for (int stone : stones) {
            for (int j = sum / 2; j >= stone; j--) {
                dp[j] = dp[j] || dp[j - stone];
            }
        }
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[i]) {
                return sum - i - i;
            }
        }
        return sum;
    }
}

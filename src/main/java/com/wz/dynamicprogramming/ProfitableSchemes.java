package com.wz.dynamicprogramming;

/**
 * There is a group of G members, and a list of various crimes they could commit.
 * The ith crime generates a profit[i] and requires group[i] members to participate in it.
 * If a member participates in one crime, that member can't participate in another crime.
 * Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of members participating in that subset of crimes is at most G.
 * How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: G = 5, P = 3, group = [2,2], profit = [2,3]
 * Output: 2
 * Explanation:
 * To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
 * In total, there are 2 schemes.
 *
 * Example 2:
 * Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
 * Output: 7
 * Explanation:
 * To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
 * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 *
 * Note:
 * 1. 1 <= G <= 100
 * 2. 0 <= P <= 100
 * 3. 1 <= group[i] <= 100
 * 4. 0 <= profit[i] <= 100
 * 5. 1 <= group.length = profit.length <= 100
 */
public class ProfitableSchemes {
    public static void main(String[] args) {
        System.out.println(profitableSchemes(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8}));
    }

    /**
     * dp[k][i][j] 表示最多干 k 票买卖，总共用了 i 个人，获得利润为 j 的情况下分配方案的总数，初始化 dp[0][0][0] 为1
     * 整个规划的核心是买卖，总共买卖的个数是固定的，每多干一票买卖，可能的分配方法就可能增加，但不可能减少的，
     * 因为假如当前已经算出来做 k-1 次买卖的分配方法总数，再做一次买卖，之前的分配方法不会减少，顶多是人数不够，做不成当前这票买卖而已，
     * 所以 dp[k][i][j] 可以先更新为 dp[k-1][i][j]，然后再来看这第k个买卖还能不能做，假设这第 k 个买卖需要 g 个人，能获得利润 p，
     * 只有当现在的人数 i 大于等于 g 的时候，才有可能做这个任务，要用 g 个人来做任务 k 的话，那么其余的 k-1 个任务只能由 i-g 个人来做了，
     * 而且由于整个需要产生利润 j，第 k 个任务能产生利润 p，所以其余的 k-1 个任务需要产生利润 j-p，由于利润不能是负值，所以还需要跟0比较，
     * 取二者的最大值，综上所述，若选择做任务k，则能新产生的分配方案的个数为 dp[k-1][i-g][max(0,j-p)]，记得每次累加完要对超大数取余。
     * 最终需要将 dp[n][i][P] ( 0 <= i <= G ) 累加起来，因为不一定要全部使用G个人，只要能产生P的利润，用几个人都没关系，
     * 而k是表示最多干的买卖数，可能上并没有干到这么多，所以只需要累加人数这个维度即可
     */
    public static int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int n = group.length, result = 0, mod = 1000000007;
        int[][][] dp = new int[n + 1][G + 1][P + 1];
        dp[0][0][0] = 1;
        for (int k = 1; k <= n; ++k) {
            int g = group[k - 1], p = profit[k - 1];
            for (int i = 0; i <= G; ++i) {
                for (int j = 0; j <= P; ++j) {
                    dp[k][i][j] = dp[k - 1][i][j];
                    if (i >= g) {
                        dp[k][i][j] = (dp[k][i][j] + dp[k - 1][i - g][Math.max(0, j - p)]) % mod;
                    }
                }
            }
        }
        for (int i = 0; i <= G; ++i) {
            result = (result + dp[n][i][P]) % mod;
        }
        return result;
    }
}

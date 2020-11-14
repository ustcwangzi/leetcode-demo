package com.wz.dynamicprogramming;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * Constraints:
 * 1 <= n <= 19
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    /**
     * 在任一结点r的左（右）子树中，所有结点（若存在）均小于（大于）r
     * dp[i] 表示当有 i 个数字能组成的 BST 个数
     * dp[0] = 1，dp[1] = 1
     * dp[2] = dp[0] * dp[1]　(1为根的情况，则左子树一定不存在，右子树可以有一个数字)
     * 　　　 + dp[1] * dp[0]  (2为根的情况，则左子树可以有一个数字，右子树一定不存在)
     * dp[3] = dp[0] * dp[2]　(1为根的情况，则左子树一定不存在，右子树可以有两个数字)
     *　　  　+ dp[1] * dp[1]  (2为根的情况，则左右子树都可以各有一个数字)
     *　　　  + dp[2] * dp[0]  (3为根的情况，则左子树可以有两个数字，右子树一定不存在)
     * 由此可得，对于 j (j < i) 有：
     * dp[i] += dp[j] * dp[i - j - 1];
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}

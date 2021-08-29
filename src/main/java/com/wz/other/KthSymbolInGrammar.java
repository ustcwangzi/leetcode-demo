package com.wz.other;

/**
 * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row,
 * we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
 * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
 *
 * Example 1:
 * Input: n = 1, k = 1
 * Output: 0
 * Explanation: row 1: 0
 *
 * Example 2:
 * Input: n = 2, k = 1
 * Output: 0
 * Explanation:
 * row 1: 0
 * row 2: 01
 *
 * Example 3:
 * Input: n = 3, k = 1
 * Output: 0
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 *
 * Constraints:
 * 1. 1 <= n <= 30
 * 2. 1 <= k <= 2^(n - 1)
 */
public class KthSymbolInGrammar {
    public static void main(String[] args) {
        System.out.println(kthGrammar(2, 1));
        System.out.println(kthGrammar(3, 2));
    }

    /**
     * 第一行为 0，接下来的每一行，是将前一行中的 0 替换为 01，1 替换为 10，求第 n 行中第 k 个字符（k 从 1 开始）
     * 可以把 0 和 1 看作上一层 0 的左右子节点，把 1 和 0 看作上一层 1 的左右子节点，这样整个结构就可以转为二叉树，前四层结构如下：
     *               0
     *        /             \
     *       0               1
     *    /     \         /     \
     *   0       1       1       0
     *  / \     / \     / \     / \
     * 0   1   1   0   1   0   0   1
     * 可以发现，k 是奇数时是左节点，k 是偶数时是右节点，同时，左子节点和其父节点的值相同，右子节点和其父节点值相反
     * 因此，可以通过递归获取当前节点父节点的值来判断出当前节点的值
     */
    public static int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        if (k % 2 == 0) {
            // 右子节点和父节点值相反
            return (kthGrammar(n - 1, k / 2) == 0) ? 1 : 0;
        }
        // 左子节点和父节点值相同
        return kthGrammar(n - 1, (k + 1) / 2);
    }
}

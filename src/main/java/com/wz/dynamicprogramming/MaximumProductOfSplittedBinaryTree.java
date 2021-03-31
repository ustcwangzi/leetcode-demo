package com.wz.dynamicprogramming;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that
 * the product of the sums of the subtrees are maximized.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * @see ../../../../resource/MaximumProductOfSplittedBinaryTree1.jpg
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 *
 * Example 2:
 * @see ../../../../resource/MaximumProductOfSplittedBinaryTree2.jpg
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 *
 * Constraints:
 * 1. Each tree has at most 50000 nodes and at least 2 nodes.
 * 2. Each node's value is between [1, 10000].
 */
public class MaximumProductOfSplittedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2, new TreeNode(3), new TreeNode(4, new TreeNode(5), new TreeNode(6)));
        System.out.println(maxProduct(root));
    }

    private static final int MOD = 1000000007;
    private static final List<Integer> SUM_LIST = new LinkedList<>();

    /**
     * 先计算所有节点之和
     * 再将以每一个节点为切分点的子树的和放到 SUM_LIST 中，然后逐个计算乘积
     */
    public static int maxProduct(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int totalSum = calSum(root);
        long result = 0;
        for (int subSum : SUM_LIST) {
            result = Math.max(result, (long) subSum * (totalSum - subSum));
        }
        return (int) (result % MOD);
    }

    private static int calSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = (root.val + calSum(root.left) + calSum(root.right)) % MOD;
        SUM_LIST.add(sum);
        return sum;
    }
}

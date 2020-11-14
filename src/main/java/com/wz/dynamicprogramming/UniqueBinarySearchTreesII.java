package com.wz.dynamicprogramming;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * Example:
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * Constraints:
 * 0 <= n <= 8
 */
public class UniqueBinarySearchTreesII {
    public static void main(String[] args) {
        System.out.println(generateTrees(3));
    }

    /**
     * 以每个 i 为根节点，递归构建左右子树
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return buildTree(1, n);
    }

    private static List<TreeNode> buildTree(int start, int end) {
        List<TreeNode> result = new LinkedList<>();
        if (end < start) {
            result.add(null);
            return result;
        }

        // 以 i 为跟，构建 BST
        for (int i = start; i <= end; i++) {
            // 构建左子树
            List<TreeNode> leftTrees = buildTree(start, i - 1);
            // 构建右子树
            List<TreeNode> rightTrees = buildTree(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;

                    result.add(root);
                }
            }
        }

        return result;
    }
}
package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an integer n, return a list of all possible full binary trees with n nodes.
 * Each node of each tree in the answer must have Node.val == 0.
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Example 1:
 * @see ../../../../resource/AllPossibleFullBinaryTrees.jpg
 * Input: n = 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],
 *          [0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 */
public class AllPossibleFullBinaryTrees {
    public static void main(String[] args) {
        List<TreeNode> result = allPossibleFBT(7);
        result.forEach(System.out::println);
    }

    private static final Map<Integer, List<TreeNode>> map = new HashMap<>();

    /**
     * 对于完全二叉树的每一个节点，要么是叶节点，要么它的左右子树都是完全二叉树，而且完全二叉树的结点数目必然是奇数
     * 可以通过递归解决这个问题：对于要求的节点总数 n，枚举所有可能的左子树结点数（i，奇数）以及对应的右子树结点数（n - i - 1），
     * 然后把生成的子树拼在一起，得到所有可能的结果，同时将 n 对应的所有树存起来，减少迭代的次数
     */
    public static List<TreeNode> allPossibleFBT(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        List<TreeNode> result = new LinkedList<>();
        if (n % 2 == 0) {
            return result;
        }
        if (n == 1) {
            result.add(new TreeNode());
            map.put(1, result);
            return result;
        }

        // 枚举所有可能的左右子树情况
        for (int i = 1; i < n; i += 2) {
            int j = n - i - 1;
            List<TreeNode> leftTrees = allPossibleFBT(i);
            List<TreeNode> rightTrees = allPossibleFBT(j);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode();
                    result.add(root);
                    root.left = left;
                    root.right = right;
                }
            }
        }

        map.put(n, result);
        return result;
    }
}

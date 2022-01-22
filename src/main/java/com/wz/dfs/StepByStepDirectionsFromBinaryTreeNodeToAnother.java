package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n.
 * You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string
 * consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 * 1. 'L' means to go from a node to its left child node.
 * 2. 'R' means to go from a node to its right child node.
 * 3. 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 * Example 1:
 * @link ../../../../resource/StepByStepDirectionsFromBinaryTreeNodeToAnother1.jpg
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 *
 * Example 2:
 * @link ../../../../resource/StepByStepDirectionsFromBinaryTreeNodeToAnother2.jpg
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 *
 * Constraints:
 * 1. The number of nodes in the tree is n.
 * 2. 2 <= n <= 10^5
 * 3. 1 <= Node.val <= n
 * 4. All the values in the tree are unique.
 * 5. 1 <= startValue, destValue <= n
 * 6. startValue != destValue
 */
public class StepByStepDirectionsFromBinaryTreeNodeToAnother {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, new TreeNode(3), null);
        TreeNode right = new TreeNode(2, new TreeNode(6), new TreeNode(4));
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(getDirections(root, 3, 6));
    }

    /**
     * lowest common ancestor (LCA) + DFS
     * 先找到最近公共祖先 lca，那么问题就转换为从 start 到 lca，再从 lca 到 dest
     * start 到 lca 一定都是 'U'，lca 到 dest 可以是 'L' 或 'R'
     */
    public static String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = findLCA(root, startValue, destValue);
        StringBuilder result = new StringBuilder();
        // start -> lca
        findPath(lca, startValue, true, new StringBuilder(), result);
        // lca -> dest
        findPath(lca, destValue, false, new StringBuilder(), result);
        return result.toString();
    }

    /**
     * 最近公共祖先 {@link com.wz.tree.LowestCommonAncestorOfBinaryTree}
     */
    private static TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);
        // 两个节点位于不同的树，公共祖先是 root
        if (left != null && right != null) {
            return root;
        }
        // 两个节点位于同一树，其中一个树的公共祖先是 null
        return left != null ? left : right;
    }

    private static void findPath(TreeNode root, int value, boolean start, StringBuilder cur, StringBuilder result) {
        if (root == null) {
            return;
        }
        if (root.val == value) {
            result.append(cur);
            return;
        }
        findPath(root.left, value, start, start ? cur.append("U") : cur.append("L"), result);
        cur.deleteCharAt(cur.length() - 1);
        findPath(root.right, value, start, start ? cur.append("U") : cur.append("R"), result);
        cur.deleteCharAt(cur.length() - 1);
    }

}

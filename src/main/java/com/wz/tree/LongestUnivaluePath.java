package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value.
 * This path may or may not pass through the root.
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * @see ../../../../resource/LongestUnivaluePath1.jpg
 * Input: root = [5,4,5,1,1,5]
 * Output: 2
 *
 * Example 2:
 * @see ../../../../resource/LongestUnivaluePath2.jpg
 * Input: root = [1,4,5,4,4,5]
 * Output: 2
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 10^4].
 * 2. -1000 <= Node.val <= 1000
 * 3. The depth of the tree will not exceed 1000.
 */
public class LongestUnivaluePath {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(4, new TreeNode(1), new TreeNode(1));
        TreeNode right = new TreeNode(5, null, new TreeNode(5));
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(longestUnivaluePath(root));
        System.out.println(new LongestUnivaluePath().longestUnivaluePath2(root));

        left = new TreeNode(4, new TreeNode(4), new TreeNode(4));
        root = new TreeNode(1, left, right);
        System.out.println(longestUnivaluePath(root));
        System.out.println(new LongestUnivaluePath().longestUnivaluePath2(root));
    }

    /**
     * 与 {@link PathSumIII} 方案一类似，从任意节点出发进行DFS，最后结果为根节点、左子节点、右子节点出发的最长路径
     */
    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 根节点出发计算最长路径
        int cur = dfs1(root.left, root.val) + dfs1(root.right, root.val);
        // 左右子节点出发计算最长路径
        int left = longestUnivaluePath(root.left), right = longestUnivaluePath(root.right);
        return Math.max(cur, Math.max(left, right));
    }

    private static int dfs1(TreeNode root, int parent) {
        if (root == null || root.val != parent) {
            return 0;
        }

        int left = dfs1(root.left, parent), right = dfs1(root.right, parent);
        return Math.max(left, right) + 1;
    }

    private int result = 0;

    /**
     * DFS
     * 在找最长路径的时候，判断节点值，要是不相同，就重置为 0
     * 在处理完左子树的节点后，如果当前节点和其左子节点的节点值相等，那么 left 就在原基础上加 1，否则就重置为 0，右子树也是如此
     * 因为要想最长，从当前节点开始，就是其左右子树中的最长相同节点值路径加上当前节点本身
     */
    public int longestUnivaluePath2(TreeNode root) {
        dfs2(root);
        return result;
    }

    private int dfs2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs2(root.left), right = dfs2(root.right);
        // 与根节点不同，路径长度置为 0
        left = root.left != null && root.left.val == root.val ? left + 1 : 0;
        right = root.right != null && root.right.val == root.val ? right + 1 : 0;
        // 包含根节点时的最长路径
        result = Math.max(result, left + right);
        return Math.max(left, right);
    }
}

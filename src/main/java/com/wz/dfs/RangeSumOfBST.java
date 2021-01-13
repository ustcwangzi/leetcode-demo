package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the root node of a binary search tree, return the sum of values of all nodes with a value in the range [low, high].
 *
 * Example 1:
 * @see ../../../../resource/RangeSumOfBST.jpg
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 2 * 104].
 * 2. 1 <= Node.val <= 105
 * 3. 1 <= low <= high <= 105
 * 4. All Node.val are unique.
 */
public class RangeSumOfBST {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(5, new TreeNode(3), new TreeNode(7));
        TreeNode right = new TreeNode(15, null, new TreeNode(18));
        TreeNode root = new TreeNode(10, left, right);
        System.out.println(rangeSumBST(root, 7, 15));
    }

    /**
     * DFS
     * 可以剪枝来加快速度，因为左子树都小于根节点，右子树都大于根节点，当发现超出 [low,high] 时不要继续遍历
     */
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}

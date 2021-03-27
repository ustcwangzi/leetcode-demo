package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivelent or false otherwise.
 *
 * Example 1:
 * @see ../../../../resource/FlipEquivalentBinaryTrees.jpg
 * Flipped Trees Diagram
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 *
 * Constraints:
 * 1. The number of nodes in each tree is in the range [0, 100].
 * 2. Each tree will have unique node values in the range [0, 99].
 */
public class FlipEquivalentBinaryTrees {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(7), new TreeNode(8)));
        TreeNode right1 = new TreeNode(3, new TreeNode(6), null);
        TreeNode root1 = new TreeNode(1, left1, right1);

        TreeNode left2 = new TreeNode(3, null, new TreeNode(6));
        TreeNode right2 = new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(8), new TreeNode(7)));
        TreeNode root2 = new TreeNode(1, left2, right2);

        System.out.println(flipEquiv(root1, root2));
    }

    /**
     * 递归，判断左右子树是否能通过翻转相等
     */
    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}

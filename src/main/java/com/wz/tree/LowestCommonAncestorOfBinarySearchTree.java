package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 * @see ../../../../resource/LowestCommonAncestorOfBinarySearchTree.jpg
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 * Example 2:
 * @see ../../../../resource/LowestCommonAncestorOfBinarySearchTree.jpg
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [2, 10^5].
 * 2. -10^9 <= Node.val <= 10^9
 * 3. All Node.val are unique.
 * 4. p != q
 * 5. p and q will exist in the BST.
 */
public class LowestCommonAncestorOfBinarySearchTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(0), new TreeNode(4, new TreeNode(3), new TreeNode(5)));
        TreeNode right = new TreeNode(8, new TreeNode(7), new TreeNode(9));
        TreeNode root = new TreeNode(6, left, right);
        System.out.println(lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8)).val);
        System.out.println(lowestCommonAncestor(root, new TreeNode(2), new TreeNode(4)).val);
    }

    /**
     * 递归，因为给的是二叉搜索树，左 < 根 < 右，根节点的值一直都是中间值，大于左子树的所有节点值，小于右子树的所有节点值
     * 如果根节点的值大于 p 和 q 的较大值，说明 p 和 q 都在左子树中，进入根节点的左子节点继续递归
     * 如果根节点的值小于 p 和 q 的较小值，说明 p 和 q 都在右子树中，进入根节点的右子节点继续递归
     * 如果都不是，则说明当前根节点就是最小共同父节点，直接返回即可
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val > Math.max(p.val, q.val)) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < Math.min(p.val, q.val)) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}

package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 * @link ../../../../resource/LowestCommonAncestorOfBinaryTree.jpg
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * @link ../../../../resource/LowestCommonAncestorOfBinaryTree.jpg
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [2, 10^5].
 * 2. -10^9 <= Node.val <= 10^9
 * 3. All Node.val are unique.
 * 4. p != q
 * 5. p and q will exist in the tree.
 */
public class LowestCommonAncestorOfBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4)));
        TreeNode right = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(lowestCommonAncestor(root, left, right).val);
        System.out.println(lowestCommonAncestor(root, left, new TreeNode(4)).val);
    }

    /**
     * 是对 {@link LowestCommonAncestorOfBinarySearchTree} 的扩展
     * 本题是普通是二叉树，不是二叉搜索树，只能在二叉树中搜索 p 和 q，然后从路径中找到最后一个相同的节点即为父节点，可以用递归来实现
     * 首先判断当前节点是否为空，若为空则直接返回空，若为 p 或 q 中的任意一个，也直接返回当前节点
     * 本题限制了 p 和 q 一定都在二叉树中存在，当前节点不等于 p 或 q 时，分别在左右子树中寻找最小共同父节点
     * 若在左右子树中都找到了共同父节点，说明 p 和 q 位于不同树中，而当前节点就是其最小共同父节点，直接返回当前节点
     * 否则说明 p 和 q 位于不同树中，即全部位于左子树或全部位于右子树，此时不为空的父节点就是其最小共同父节点
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 两个节点位于不同的树，公共祖先是 root
        if (left != null && right != null) {
            return root;
        }
        // 两个节点位于同一树，其中一个树的公共祖先是 null
        return left != null ? left : right;
    }
}

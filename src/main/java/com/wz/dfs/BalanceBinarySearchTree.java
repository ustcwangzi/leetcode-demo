package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, return a balanced binary search tree with the same node values.
 * A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
 * If there is more than one answer, return any of them.
 *
 * Example 1:
 * @see ../../../../resource/BalanceBinarySearchTree.jpg
 * Input: root = [1,null,2,null,3,null,4,null,null]
 * Output: [2,1,3,null,null,null,4]
 * Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
 *
 * Constraints:
 * 1. The number of nodes in the tree is between 1 and 10^4.
 * 2. The tree nodes will have distinct values between 1 and 10^5.
 */
public class BalanceBinarySearchTree {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4)));
        TreeNode root = new TreeNode(1, null, right);
        TreeNode.traversalPreOrder(root);
        System.out.println();
        TreeNode.traversalPreOrder(balanceBST(root));
    }

    /**
     * DFS，与 {@link ConvertSortedArrayToBinarySearchTree} 类似
     * 二叉搜索树的中序遍历是有序的，因此先对其进行中序遍历，然后每次以中间节点为根，左右部分作为左右子树，递归处理
     */
    public static TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        return build(list, 0, list.size() - 1);
    }

    private static TreeNode build(List<TreeNode> list, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = list.get(mid);
        root.left = build(list, left, mid - 1);
        root.right = build(list, mid + 1, right);
        return root;
    }

    private static void inOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }
}

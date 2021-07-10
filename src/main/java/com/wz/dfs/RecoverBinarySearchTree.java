package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 *
 * Example 1:
 * @see ../../../../resource/RecoverBinarySearchTree1.jpg
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 *
 * Example 2:
 * @see ../../../../resource/RecoverBinarySearchTree2.jpg
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [2, 1000].
 * 2. -2^31 <= Node.val <= 2^31 - 1
 */
public class RecoverBinarySearchTree {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(4, new TreeNode(2), null);
        TreeNode root = new TreeNode(3, new TreeNode(1), right);
        TreeNode.traversalPreOrder(root);
        recoverTree(root);
        System.out.println();
        TreeNode.traversalPreOrder(root);
    }

    /**
     * 二叉搜索树中序遍历是有序的，可以利用这个性质进行恢复
     * 对二叉树进行中序遍历，遍历完成后对 value 进行排序，再遍历中序结果，根据排序后的 value 对每个 node 重新赋值
     */
    public static void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        inOrder(root, nodeList);
        List<Integer> valueList = nodeList.stream().map((node) -> node.val).sorted().collect(Collectors.toList());
        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).val = valueList.get(i);
        }
    }

    private static void inOrder(TreeNode root, List<TreeNode> nodeList) {
        if (root == null) {
            return;
        }
        inOrder(root.left, nodeList);
        nodeList.add(root);
        inOrder(root.right, nodeList);
    }
}

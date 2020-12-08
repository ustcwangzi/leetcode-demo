package com.wz.dynamicprogramming;

import com.wz.common.TreeNode;

/**
 * Given a binary tree, we install cameras on the nodes of the tree.
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 *
 * Example 1:
 * @see ../../../../resource/BinaryTreeCameras1.jpg
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 *
 * Example 2:
 * @see ../../../../resource/BinaryTreeCameras2.jpg
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree.
 * The above image shows one of the valid configurations of camera placement.
 *
 * Note:
 * 1. The number of nodes in the given tree will be in the range [1, 1000].
 * 2. Every node has value 0.
 */
public class BinaryTreeCameras {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        TreeNode left = new TreeNode(0);
        treeNode.left = left;
        left.left = new TreeNode(0);
        left.right = new TreeNode(0);
        System.out.println(minCameraCover(treeNode));
    }

    /**
     * 对于叶子节点，如果在叶子上放置照相机，显然是没有在其parent上放置相机来的合适的，因为叶子节点的覆盖范围没有其parent节点大。
     * 因此，就可以对所有的叶子的节点的parent进行放置相机，同时将所有已经覆盖掉的节点去除掉。对于剩下的节点重复上述的操作即可。
     * 这里在求解的时候并不需要真实的去删除节点，只要在每个节点上加上标注信息就可以了。
     * 叶子节点 ：1，放置照相机 ：2，已经被cover节点 ：3
     */
    public static int minCameraCover(TreeNode root) {
        int[] result = new int[1];
        int state = helper(root, result);
        if (state == 1) {
            result[0]++;
        }
        return result[0];
    }

    private static int helper(TreeNode root, int[] result) {
        if (root == null) {
            return 3;
        }
        int left = helper(root.left, result);
        int right = helper(root.right, result);
        if (left == 3 && right == 3) {
            return 1;
        }
        if (left == 1 || right == 1) {
            result[0]++;
            return 2;
        }
        return 3;
    }
}

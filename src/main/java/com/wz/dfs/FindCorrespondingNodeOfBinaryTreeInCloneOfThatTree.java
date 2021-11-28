package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 * The cloned tree is a copy of the original tree.
 * Return a reference to the same node in the cloned tree.
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
 * Follow up: Solve the problem if repeated values on the tree are allowed.
 *
 * Example 1:
 * @link ../../../../resource/FindCorrespondingNodeOfBinaryTreeInCloneOfThatTree.jpg
 * Input: tree = [7,4,3,null,null,6,19], target = 3
 * Output: 3
 * Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree.
 * The answer is the yellow node from the cloned tree.
 */
public class FindCorrespondingNodeOfBinaryTreeInCloneOfThatTree {
    public static void main(String[] args) {
        TreeNode target = new TreeNode(3, new TreeNode(6), new TreeNode(19));
        TreeNode original = new TreeNode(7, new TreeNode(4), target);
        TreeNode cloned = new TreeNode(7, new TreeNode(4), target);
        System.out.println(getTargetCopy(original, cloned, target));
    }

    /**
     * 直接进行BFS
     */
    public static TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> originalQueue = new LinkedList<>(), clonedQueue = new LinkedList<>();
        originalQueue.add(original);
        clonedQueue.add(cloned);
        while (!originalQueue.isEmpty()) {
            TreeNode originalCur = originalQueue.poll(), clonedCur = clonedQueue.poll();
            if (originalCur == target) {
                return clonedCur;
            }
            if (originalCur.left != null) {
                originalQueue.add(originalCur.left);
                clonedQueue.add(clonedCur.left);
            }
            if (originalCur.right != null) {
                originalQueue.add(originalCur.right);
                clonedQueue.add(clonedCur.right);
            }
        }
        return null;
    }
}

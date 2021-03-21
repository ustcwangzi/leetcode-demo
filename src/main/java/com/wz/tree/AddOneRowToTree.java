package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
 * Note that the root node is at depth 1.
 * The adding rule is:
 * 1. Given the integer depth, for each not null tree node cur at the depth depth - 1,
 *    create two tree nodes with value val as cur's left subtree root and right subtree root.
 * 2. cur's original left subtree should be the left subtree of the new left subtree root.
 * 3. cur's original right subtree should be the right subtree of the new right subtree root.
 * 4. If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val
 *    as the new root of the whole original tree, and the original tree is the new root's left subtree.
 *
 * Example 1:
 * @see ../../../../resource/AddOneRowToTree1.jpg
 * Input: root = [4,2,6,3,1,5], val = 1, depth = 2
 * Output: [4,1,1,2,null,null,6,3,1,5]
 *
 * Example 2:
 * @see ../../../../resource/AddOneRowToTree2.jpg
 * Input: root = [4,2,null,3,1], val = 1, depth = 3
 * Output: [4,2,null,1,1,3,null,null,1]
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 10^4].
 * 2. The depth of the tree is in the range [1, 10^4].
 * 3. -100 <= Node.val <= 100
 * 4. -10^5 <= val <= 10^5
 * 5. 1 <= depth <= the depth of tree + 1
 */
public class AddOneRowToTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(1));
        TreeNode right = new TreeNode(6, new TreeNode(5), null);
        TreeNode root = new TreeNode(4, left, right);
        TreeNode.traversalPreOrder(addOneRow(root, 1, 2));
    }

    /**
     * 在树中第 depth 层，插入 val，层序遍历
     * 将原有的左子结点连到新建的左子结点的左子结点上，将原有的右子结点连到新建的右子结点的右子结点
     */
    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 记录下一层的深度
        int curDepth = 1;
        while (!queue.isEmpty()) {
            curDepth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 当下一层是 depth 时，就将新节点接上去
                if (depth == curDepth) {
                    TreeNode left = cur.left, right = cur.right;
                    cur.left = new TreeNode(val);
                    cur.right = new TreeNode(val);
                    cur.left.left = left;
                    cur.right.right = right;
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 已添加完成，返回
            if (curDepth == depth) {
                return root;
            }
        }
        return root;
    }
}

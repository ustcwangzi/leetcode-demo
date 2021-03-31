package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary tree is named Even-Odd if it meets the following conditions:
 * 1. The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
 * 2. For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
 * 3. For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 *
 * Example 1:
 * @see ../../../../resource/EvenOddTree1.jpg
 * Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * Output: true
 * Explanation: The node values on each level are:
 * Level 0: [1]
 * Level 1: [10,4]
 * Level 2: [3,7,9]
 * Level 3: [12,8,6,2]
 * Since levels 0 and 2 are all odd and increasing, and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
 *
 * Example 2:
 * @see ../../../../resource/EvenOddTree2.jpg
 * Input: root = [5,4,2,3,3,7]
 * Output: false
 * Explanation: The node values on each level are:
 * Level 0: [5]
 * Level 1: [4,2]
 * Level 2: [3,3,7]
 * Node values in the level 2 must be in strictly increasing order, so the tree is not Even-Odd.
 *
 * Example 3:
 * @see ../../../../resource/EvenOddTree3.jpg
 * Input: root = [5,9,1,3,5,7]
 * Output: false
 * Explanation: Node values in the level 1 should be even integers.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 10^5].
 * 2. 1 <= Node.val <= 10^6
 */
public class EvenOddTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(10, new TreeNode(3, new TreeNode(12), new TreeNode(8)), null);
        TreeNode right = new TreeNode(4, new TreeNode(7, new TreeNode(6), null), new TreeNode(9, null, new TreeNode(2)));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(isEvenOddTree(root));
    }

    /**
     * BFS
     * 层次遍历，记录当前处于奇数层还是偶数层、以及上一个值，用来做节点值的奇偶判断和升降序判断
     */
    public static boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean evenLevel = true;
        while (!queue.isEmpty()) {
            int size = queue.size(), evenPre = Integer.MIN_VALUE, oddPre = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (evenLevel) {
                    // 偶数层，都是奇数节点且升序
                    if (cur.val % 2 == 0 || cur.val <= evenPre) {
                        return false;
                    }
                    evenPre = cur.val;
                } else {
                    // 奇数层，都是偶数节点且降序
                    if (cur.val % 2 == 1 || cur.val >= oddPre) {
                        return false;
                    }
                    oddPre = cur.val;
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            evenLevel = !evenLevel;
        }
        return true;
    }
}

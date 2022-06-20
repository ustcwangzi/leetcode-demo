package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.
 * If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
 * If a node has only one child, that child is guaranteed to be the left child.
 * Given the output traversal of this traversal, recover the tree and return its root.
 *
 * Example 1:
 * @link ../../../../resource/RecoverTreeFromPreorderTraversal1.jpg
 * Input: traversal = "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 *
 * Example 2:
 * @link ../../../../resource/RecoverTreeFromPreorderTraversal2.jpg
 * Input: traversal = "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 *
 * Example 3:
 * @link ../../../../resource/RecoverTreeFromPreorderTraversal3.jpg
 * Input: traversal = "1-401--349---90--88"
 * Output: [1,401,null,349,88,90]
 *
 * Constraints:
 * 1. The number of nodes in the original tree is in the range [1, 1000].
 * 2. 1 <= Node.val <= 10^9
 */
public class RecoverTreeFromPreorderTraversal {
    public static void main(String[] args) {
        RecoverTreeFromPreorderTraversal traversal = new RecoverTreeFromPreorderTraversal();
        TreeNode.traversalPreOrder(traversal.recoverFromPreorder("1-2--3--4-5--6--7"));
        System.out.println();

        traversal = new RecoverTreeFromPreorderTraversal();
        TreeNode.traversalPreOrder(traversal.recoverFromPreorder("1-2--3---4-5--6---7"));
        System.out.println();

        traversal = new RecoverTreeFromPreorderTraversal();
        TreeNode.traversalPreOrder(traversal.recoverFromPreorder("1-401--349---90--88"));
    }

    private int index;

    /**
     * '-'代表当前节点深度，数字代表节点值，采用DFS
     * 使用 index 记录当前遍历位置，depth 记录当前深度，递归过程中先统计'-'的个数 dashesCount
     * 若 dashesCount != depth，意味着接下来的数字并不属于这一层，直接返回 null 即可，注意 index 不要变
     * 如果相等，则继续遍历拿到数字 value，并使用 value 构建根节点，此时需要将 index 移动到当前位置，然后递归创建左右子树
     */
    public TreeNode recoverFromPreorder(String s) {
        return dfs(s, 0);
    }

    private TreeNode dfs(String s, int depth) {
        int dashesCount = 0;
        while (index + dashesCount < s.length() && s.charAt(index + dashesCount) == '-') {
            dashesCount++;
        }
        if (dashesCount != depth) {
            return null;
        }

        int numsCount = 0;
        while (index + dashesCount + numsCount < s.length() && s.charAt(index + dashesCount + numsCount) != '-') {
            numsCount++;
        }

        int value = Integer.parseInt(s.substring(index + dashesCount, index + dashesCount + numsCount));
        TreeNode root = new TreeNode(value);
        index += dashesCount + numsCount;
        root.left = dfs(s, depth + 1);
        root.right = dfs(s, depth + 1);
        return root;
    }
}

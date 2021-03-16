package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 * If the tree has more than one mode, return them in any order.
 * Assume a BST is defined as follows:
 * 1. The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * 3. Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *   1
 *    \
 *     2
 *    /
 *   2
 * Input: root = [1,null,2,2]
 * Output: [2]
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 10^4].
 * 2. -10^5 <= Node.val <= 10^5
 */
public class FindModeInBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(2), null));
        System.out.println(Arrays.toString(findMode(root)));
    }

    /**
     * 中序遍历的结果是有序的，只要比较前后两个元素是否相等，就可以统计出现某个元素出现的次数
     * 使用 pre 来记录上一个节点，max 记录最大次数，count 记录当前遍历元素出现次数，遍历过程中如果 pre 不为空，说明当前不是第一个节点，
     * 和之前一个节点值比较，如果相等，count + 1，如果不等，count 重置为 1。当 count 大于 max 时需要清空结果集，
     * 并把当前节点值加入结果中，每一步遍历需要把 pre 更新为当前节点
     */
    public static int[] findMode(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        Integer pre = null;
        int max = 0, count = 1;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
                continue;
            }

            cur = stack.pop();
            if (pre != null) {
                count = pre == cur.val ? count + 1 : 1;
            }
            if (count >= max) {
                if (count > max) {
                    result.clear();
                }
                result.add(cur.val);
                max = count;
            }
            pre = cur.val;
            cur = cur.right;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}

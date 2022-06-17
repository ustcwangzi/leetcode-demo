package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.*;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column
 * and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 * Return the vertical order traversal of the binary tree.
 *
 * Example 1:
 * @link ../../../../resource/VerticalOrderTraversalOfBinaryTree1.jpg
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 * Example 2:
 * @link ../../../../resource/VerticalOrderTraversalOfBinaryTree2.jpg
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 * Example 3:
 * @link ../../../../resource/VerticalOrderTraversalOfBinaryTree3.jpg
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 1000].
 * 2. 0 <= Node.val <= 1000
 */
public class VerticalOrderTraversalOfBinaryTree {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), right);
        System.out.println(verticalTraversal(root));

        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        right = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        root = new TreeNode(1, left, right);
        System.out.println(verticalTraversal(root));

        left = new TreeNode(1, new TreeNode(0), new TreeNode(2));
        right = new TreeNode(4, new TreeNode(2), null);
        root = new TreeNode(3, left, right);
        System.out.println(verticalTraversal(root));
    }

    /**
     * 按照列遍历二叉树，同一列中，先遍历行，同一行中先遍历 val 小的
     * 使用 TreeMap 记录 列 -> node 的映射，node 中存储 val 和 行
     * 先对二叉树做先序遍历，计算每个节点的坐标，并存储到 TreeMap 中（为了保持列的有序）
     * 遍历结束后，对同一列的 node 按照 行、val 进行排序，然后加入结果中
     */
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        // 同一列放在一起，列小的在前
        Map<Integer, List<Node>> map = new TreeMap<>();
        preOrder(root, map, 0, 0);
        List<List<Integer>> result = new LinkedList<>();
        for (List<Node> nodeList : map.values()) {
            // 先行、后 val
            nodeList.sort((o1, o2) -> {
                if (o1.level == o2.level) {
                    return Integer.compare(o1.val, o2.val);
                }
                return Integer.compare(o1.level, o2.level);
            });
            List<Integer> list = new LinkedList<>();
            nodeList.forEach(cur -> list.add(cur.val));
            result.add(list);
        }
        return result;
    }

    private static void preOrder(TreeNode root, Map<Integer, List<Node>> map, int row, int col) {
        if (root == null) {
            return;
        }

        Node node = new Node(root.val, row);
        map.putIfAbsent(col, new ArrayList<>());
        map.get(col).add(node);
        preOrder(root.left, map, row + 1, col - 1);
        preOrder(root.right, map, row + 1, col + 1);
    }

    private static class Node {
        private final int val;
        private final int level;

        public Node(int val, int level) {
            this.val = val;
            this.level = level;
        }
    }
}

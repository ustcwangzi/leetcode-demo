package com.wz.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 *
 * Follow up:
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 * Example 1:
 * @see ../../../../resource/PopulatingNextRightPointersInEachNodeII.jpg
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node,
 * just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 * Constraints:
 * 1. The number of nodes in the given tree is less than 6000.
 * 2. -100 <= node.val <= 100
 */
public class PopulatingNextRightPointersInEachNodeII {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2, new Node(4), new Node(5));
        root.right = new Node(3, null, new Node(7));
        connect(root);
    }

    /**
     * 与 {@link PopulatingNextRightPointersInEachNode} 区别在于本题不是完全二叉树
     * 层序遍历，每层的节点都按顺序加入 queue 中，而每当从 queue 中取出一个元素时，将其 next 指针指向 queue 中下一个节点即可，
     * 对于每层的开头元素开始遍历之前，先统计一下该层的节点个数，用 for 循环，这样当 for 循环结束的时候，该层就已经被遍历完了
     */
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每一层的节点个数
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                // 改层后面还存在节点时，才能设置 next
                if (i < size - 1) {
                    cur.next = queue.peek();
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}

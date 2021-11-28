package com.wz.dfs;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * The binary tree has the following definition:
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
 * @link ../../../../resource/PopulatingNextRightPointersInEachNode.jpg
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node,
 * just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 * Constraints:
 * 1. The number of nodes in the given tree is less than 4096.
 * 2. -1000 <= node.val <= 1000
 */
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2, new Node(4), new Node(5));
        root.right = new Node(3, new Node(6), new Node(7));
        connect(root);
    }

    /**
     * BFS
     * 层次遍历，由于是完全二叉树，所以若节点的左子结点存在的话，其右子节点必定存在，所以左子结点的 next 指针可以直接指向其右子节点，
     * 对于其右子节点的处理方法是，判断其父节点的 next 是否为空，若不为空，则指向其 next 指针指向的节点的左子结点，若为空则指向 NULL
     */
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            root.right.next = root.next != null ? root.next.left : null;
        }
        connect(root.left);
        connect(root.right);
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

package com.wz.lists;

import com.wz.common.ListNode;
import com.wz.common.TreeNode;

/**
 * Given a binary tree root and a linked list with head as the first node.
 * Return True if all the elements in the linked list starting from the head correspond to
 * some downward path connected in the binary tree otherwise return False.
 * In this context downward path means a path that starts at some node and goes downwards.
 *
 * Example:
 * @see ../../../../resource/LinkedListInBinaryTree.jpg
 * Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: true
 * Explanation: Nodes in blue form a subpath in the binary Tree.
 */
public class LinkedListInBinaryTree {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{4, 2, 8});
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(8);
        root.right.left.right.left = new TreeNode(1);
        root.right.left.right.right = new TreeNode(3);

        System.out.println(isSubPath(head, root));
    }

    /**
     * 递归处理，遍历所有节点，每次都将它当作链表的起点与链表进行比较
     */
    public static boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null || root == null) {
            return false;
        }

        // 递归处理三种情况：
        // 1. 当前节点作为链表起点；2. 左子树根节点作为链表起点；3. 右子树根节点作为链表起点
        return valid(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    /**
     * 判断能否以当前 root 作为 head 开始的链表
     */
    private static boolean valid(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null || head.val != root.val) {
            return false;
        }

        return valid(head.next, root.left) || valid(head.next, root.right);
    }
}

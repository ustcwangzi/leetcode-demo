package com.wz.lists;

import com.wz.common.ListNode;
import com.wz.common.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
 * subtrees of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted linked list: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedListToBinarySearchTree {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{-10, -3, 0, 5, 9});
        TreeNode.traversalPreOrder(sortedListToBST(head));
    }

    /**
     * 二叉搜索树按中序遍历的话，得到的是一个有序数组，那么反过来，根节点应该是有序数组的中间点，从中间点分开为左右两个有序数组，
     * 再分别找出子数组的中间点作为原中间点的左右两个子节点，因此核心就是找链表的中间节点，然后以中间节点划分，依次找下去
     * 链表查找中间点可以通过快慢指针来操作，快指针每次走两步，慢指针每次走一步，快指针停止时，慢指针就是指针中间节点
     */
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        ListNode slow = head, fast = head, pre = slow;
        // 停止时，slow 指向中间节点，pre 指向 slow 的前一个节点
        while (fast.next != null && fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 将 slow 从链表中移除，作为本次的 BST 节点
        fast = slow.next;
        pre.next = null;
        TreeNode cur = new TreeNode(slow.val);

        // 以 slow 作为分割，左右子链表作为当前节点的左右子树
        if (head != slow) {
            cur.left = sortedListToBST(head);
        }
        cur.right = sortedListToBST(fast);
        return cur;
    }
}

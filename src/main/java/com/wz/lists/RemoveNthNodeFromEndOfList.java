package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 * Given n will always be valid.
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        ListNode result = removeNthFromEnd(head, 2);
        ListNode.print(result);
    }

    /**
     * 第一次遍历求出链表长度 count，倒序第 n 个节点，就是正序第 count-n+1 个节点
     * 找到要删除的节点的前一个节点 pre，将 pre 指向要删除节点的后一个节点即完成删除操作
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        ListNode cur = head;
        // 计算链表长度
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        // 此时需要删除头节点
        if (n == count) {
            return head.next;
        }

        n = count - n;
        count = 0;
        cur = head;
        while (cur != null) {
            count++;
            // 找到要删除节点的前一个节点
            if (count == n) {
                cur.next = cur.next.next;
                break;
            }
            cur = cur.next;
        }

        return head;
    }
}

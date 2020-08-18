package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 *
 * Example 2:
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4});
        reorderList(head);
        ListNode.print(head);


        System.out.println();

        head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        reorderList(head);
        ListNode.print(head);
    }

    /**
     * 每次获取链表的最后一个节点 last， 然后将 last 插入到 cur 的后面，同时 cur 右移两位
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 获取最后一个节点
        ListNode last = removeLast(head);
        ListNode cur = head;
        while (last != null && cur != last) {
            // 将 last 插到 cur 的下一个位置
            last.next = cur.next;
            cur.next = last;
            // cur 右移，重新获取最后一个节点
            cur = cur.next.next;
            last = removeLast(cur);
        }
    }

    private static ListNode removeLast(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null && cur.next.next != null) {
            cur = cur.next;
        }
        ListNode result = cur.next;
        cur.next = null;
        return result;
    }
}

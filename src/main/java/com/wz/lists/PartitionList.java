package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class PartitionList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 4, 3, 2, 5, 2});
        ListNode.print(partition(head, 3));
    }

    /**
     * 先找到第一个大于等于给定值 x 的节点的前一个节点 pre，然后再找 pre 之后小于 x 的节点
     * 每找到一个就将其取出放在 pre 之后，同时 pre 右移
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(), pre = dummy;
        dummy.next = head;
        // 找到第一个大于等于 x 的节点的前一个节点 pre
        while (pre.next != null && pre.next.val < x) {
            pre = pre.next;
        }
        ListNode cur = pre;

        // 找小于 x 的节点的前一个节点 cur
        while (cur.next != null) {
            if (cur.next.val >= x) {
                cur = cur.next;
                continue;
            }

            // next 节点小于 x，将 next 节点取出放到 pre 后面
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            // pre 右移
            pre = pre.next;
        }

        return dummy.next;
    }
}

package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        ListNode.print(reverseKGroup(head, 2));
        System.out.println();
        head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        ListNode.print(reverseKGroup(head, 3));
    }

    /**
     * 用计数器 count 计算当前节点的个数，同时用 pre 和 next 保存本次计数中的前一个节点和后一个节点
     * 当 count == k 时，对 pre 和 next 之间的节点进行翻转，注意 pre 和 next 本身不参与翻转
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(), pre = dummy;
        dummy.next = head;
        int count = 0;
        while (head != null) {
            count++;
            // reverse 会导致 head 节点位置变化，因此这里需要将 next 保存下来
            ListNode next = head.next;
            if (count == k) {
                pre = reverse(pre, next);
                count = 0;
            }
            head = next;
        }
        return dummy.next;
    }

    /**
     * 翻转 (pre, end) 之间的节点，每次将节点 cur 头插到 pre 下一个位置
     * 最后返回翻转之后的 end 的前一个节点，作为下次翻转的 pre 节点
     * 0 -> 1 -> 2 -> 3 -> 4
     * |                   |
     * pre                end
     * 翻转之后
     * 0 -> 1 -> 2 -> 3 -> 4
     * |              |    |
     * pre           last end
     */
    private static ListNode reverse(ListNode pre, ListNode end) {
        ListNode last = pre.next;
        ListNode cur = last.next;
        while (cur != end) {
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            // 下一个需要进行头插的节点
            cur = last.next;
        }
        return last;
    }
}

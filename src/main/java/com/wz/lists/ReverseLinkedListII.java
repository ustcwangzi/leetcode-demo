package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        ListNode.print(reverseBetween(head, 2, 5));
    }

    /**
     * 先找到 m 的前一个节点 pre，然后从 pre 之后开始找需要翻转的节点 next，然后将 next 插到 pre 的下一个位置即可
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }

        ListNode dummy = new ListNode(), pre = dummy;
        dummy.next = head;
        int count = 1;
        // 找到 m 的前一个节点
        while (count < m) {
            pre = pre.next;
            count++;
        }

        ListNode cur = pre.next;
        while (count < n) {
            // 将 next 插到 pre 的下一个位置
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            count++;
        }

        return dummy.next;
    }
}

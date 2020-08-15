package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 *
 * Example 2:
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        ListNode.print(rotateRight(head, 2));

        System.out.println();

        head = ListNode.build(new int[]{0, 1, 2});
        ListNode.print(rotateRight(head, 4));
    }

    /**
     * 思路与 {@link ReverseNodesInKGroup} 类似，是其简化版
     * 每次翻转，就是要将最后一个节点插到 dummy 的下一个位置
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        int length = getLength(head);
        k = k % length;

        ListNode dummy = new ListNode();
        dummy.next = head;
        while (k > 0) {
            ListNode last = getLast(head);
            last.next = dummy.next;
            dummy.next = last;
            k--;
        }
        return dummy.next;
    }

    private static int getLength(ListNode head) {
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    private static ListNode getLast(ListNode head) {
        ListNode cur = head, next = cur.next;
        while (next.next != null) {
            cur = cur.next;
            next = next.next;
        }
        cur.next = null;
        return next;
    }
}

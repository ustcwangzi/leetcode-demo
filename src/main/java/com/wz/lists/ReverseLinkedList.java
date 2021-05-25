package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Reverse a singly linked list.
 *
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        ListNode.print(reverseList(head));
    }

    /**
     * 依次取出 head.next 插入到 dummy 的下一个位置即可，头插法
     * head.next 为空时，全部逆置，此时 dummy.next 指向新的头节点
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        while (head.next != null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = dummy.next;
            dummy.next = next;
        }

        return dummy.next;
    }
}

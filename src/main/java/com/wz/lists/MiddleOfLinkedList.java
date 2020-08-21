package com.wz.lists;

import com.wz.common.ListNode;

public class MiddleOfLinkedList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        ListNode.print(middleNode(head));

        System.out.println();

        head = ListNode.build(new int[]{1, 2, 3, 4, 5, 6});
        ListNode.print(middleNode(head));
    }

    /**
     * 快慢指针，题目要求当偶数个节点时返回中间的第二个节点，因此最终判断 fast 的下一个节点是否为空
     * 为空则说明有奇数个节点，直接返回 slow，不为空则说明有偶数个节点，返回 slow 的下一个节点
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return fast.next == null ? slow : slow.next;
    }
}

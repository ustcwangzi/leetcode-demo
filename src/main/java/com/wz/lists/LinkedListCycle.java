package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * To represent a cycle in the given linked list, we use an integer pos which represents the position
 * (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Example 1:
 * @see ../../../../resource/LinkedListCycle.jpg
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        head.next = node2;
        node2.next = new ListNode(0, new ListNode(-4, node2));
        System.out.println(hasCycle(head));
    }

    /**
     * 快慢指针，快指针每次走两步，慢指针每次走一步，若两指针相遇，则说明存在环，反之不存在
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}

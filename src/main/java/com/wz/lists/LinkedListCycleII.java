package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * To represent a cycle in the given linked list, we use an integer pos which represents the position
 * (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 * Note: Do not modify the linked list.
 *
 * Example:
 * @see ../../../../resource/LinkedListCycle.jpg
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 */
public class LinkedListCycleII {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        head.next = node2;
        node2.next = new ListNode(0, new ListNode(-4, node2));
        System.out.println(detectCycle(head).val);

        head = new ListNode(1);
        node2 = new ListNode(2, head);
        head.next = node2;
        System.out.println(detectCycle(head).val);
    }

    /**
     * 思路与{@link com.wz.array.FindDuplicateNumber} 类似
     * @see ../../../../resource/FindDuplicateNumber.jpg
     * slow 和 fast 都从起点开始出发，slow 的速度为 1，fast 的速度为 2
     * 在红点处相遇，相遇时 slow 走了 a+b，fast 走了 a+b+c+b，存在 a+b+c+b = 2*(a+b) => a == c
     * 此时，将 slow 重新放到起点，fast 速度设为 1，则相遇时刚好位于环的入口处
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 两者相遇，将 slow 放到起点处
            if (slow == fast) {
                slow = head;
                // slow 和 fast 速度都设为1
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }
}

package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 * Input: 1->2
 * Output: false
 *
 * Example 2:
 * Input: 1->2->2->1
 * Output: true
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 2, 1});
        System.out.println(isPalindrome(head));

        head = ListNode.build(new int[]{1, 2, 3, 2, 1});
        System.out.println(isPalindrome(head));
    }

    /**
     * 使用快慢指针找到中间节点，然后将中间节点之后的链表进行逆置
     * 从原链表头节点开始和逆置后的子链表进行比较，发现不相等直接返回 false，否则遍历完返回 true
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow 之后的子链表进行逆置
        ListNode dummy = new ListNode(0, slow.next);
        ListNode cur = dummy.next, next = cur.next;
        while (next != null) {
            cur.next = next.next;
            next.next = dummy.next;
            dummy.next = next;

            next = cur.next;
        }

        // 节点值进行比较
        slow = head;
        cur = dummy.next;
        while (cur != null && slow != null) {
            if (cur.val != slow.val) {
                return false;
            }
            cur = cur.next;
            slow = slow.next;
        }

        return true;
    }
}

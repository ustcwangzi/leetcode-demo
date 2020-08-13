package com.wz.lists;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in
 * reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    /**
     * 直接在遍历的过程中进行相加即可，统计记录进位 carry 和当前指向 cur
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode result = null, cur = null;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            if (result == null) {
                result = node;
            } else {
                // 加入新节点
                cur.next = node;
            }
            // 修改指针
            cur = node;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode l = l1 != null ? l1 : l2;
        while (l != null) {
            int sum = l.val + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = node;
            l = l.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }

        return result;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

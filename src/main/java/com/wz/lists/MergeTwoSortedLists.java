package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Merge two sorted linked lists and return it as a new sorted list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode l1 = ListNode.build(new int[]{1, 2, 4});
        ListNode l2 = ListNode.build(new int[]{1, 3, 4});
        ListNode result = mergeTwoLists(l1, l2);
        ListNode.print(result);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode result = null, cur = null;
        while (l1 != null && l2 != null) {
            ListNode node;
            if (l1.val <= l2.val) {
                node = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                node = new ListNode(l2.val);
                l2 = l2.next;
            }
            if (result == null) {
                result = node;
            } else {
                cur.next = node;
            }
            cur = node;
        }

        ListNode l = l1 != null ? l1 : l2;
        while (l != null) {
            ListNode node = new ListNode(l.val);
            cur.next = node;
            cur = node;
            l = l.next;
        }

        return result;
    }
}

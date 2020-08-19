package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class SortList {
    public static void main(String[] args) {

    }

    public static ListNode sortList(ListNode head) {
        return null;
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), cur =dummy;
        while (l1 != null && l2 != null) {
            if (l1.val<l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
        }

        return dummy.next;
    }
}

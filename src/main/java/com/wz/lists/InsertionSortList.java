package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Sort a linked list using insertion sort.
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 * Algorithm of Insertion Sort:
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class InsertionSortList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{4, 2, 1, 3});
        ListNode.print(insertionSortList(head));
    }

    /**
     * 每次从原链表中取出一个节点 head，在结果链表中找到插入位置 cur，然后将 head 插到 cur 之后
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(Integer.MIN_VALUE), cur;
        while (head != null) {
            ListNode next = head.next;
            // 从头开始寻找插入位置
            cur = dummy;
            while (cur.next != null && cur.next.val <= head.val) {
                cur = cur.next;
            }
            // 将 head 插到 cur 的下一个位置
            head.next = cur.next;
            cur.next = head;
            head = next;
        }

        return dummy.next;
    }
}

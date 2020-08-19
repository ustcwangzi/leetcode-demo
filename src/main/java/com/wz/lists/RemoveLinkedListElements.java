package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 6, 3, 4, 5, 6});
        ListNode.print(removeElements(head, 6));
    }

    /**
     * 直接在遍历中进行删除即可
     * 注意删除节点时，pre 指针不变，因为下一个节点可能还是满足删除条件
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(), pre = dummy;
        dummy.next = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }

        return dummy.next;
    }
}

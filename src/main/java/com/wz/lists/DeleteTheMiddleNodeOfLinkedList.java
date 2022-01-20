package com.wz.lists;

import com.wz.common.ListNode;

/**
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.
 * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 *
 * Example 1:
 * @link ../../../../resource/DeleteTheMiddleNodeOfLinkedList1.jpg
 * Input: head = [1,3,4,7,1,2,6]
 * Output: [1,3,4,1,2,6]
 * Explanation:
 * The above figure represents the given linked list. The indices of the nodes are written below.
 * Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
 * We return the new list after removing this node.
 *
 * Example 2:
 * @link ../../../../resource/DeleteTheMiddleNodeOfLinkedList2.jpg
 * Input: head = [1,2,3,4]
 * Output: [1,2,4]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 4, node 2 with value 3 is the middle node, which is marked in red.
 *
 * Constraints:
 * 1. The number of nodes in the list is in the range [1, 10^5].
 * 2. 1 <= Node.val <= 10^5
 */
public class DeleteTheMiddleNodeOfLinkedList {
    public static void main(String[] args) {
        ListNode result = deleteMiddle(ListNode.build(new int[]{1, 3, 4, 7, 1, 2, 6}));
        ListNode.print(result);

        System.out.println();

        result = deleteMiddle(ListNode.build(new int[]{1, 2, 3, 4}));
        ListNode.print(result);
    }

    /**
     * 快慢指针，fast 移动到尾部时，slow 位于中间位置，即需要移除的节点
     * 同时使用 pre 记录 slow 的上一个节点，就可以实现移除 slow 节点
     */
    public static ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }
        ListNode pre = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = slow.next;
        return head;
    }
}

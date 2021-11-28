package com.wz.lists;

import com.wz.common.ListNode;

/**
 * You are given the head of a linked list, and an integer k.
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 *
 * Example 1:
 * @link ../../../../resource/SwappingNodesInLinkedList.jpg
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 *
 * Constraints:
 * 1. The number of nodes in the list is n.
 * 2. 1 <= k <= n <= 10^5
 * 3. 0 <= Node.val <= 100
 */
public class SwappingNodesInLinkedList {
    public static void main(String[] args) {
        ListNode.print(swapNodes(ListNode.build(new int[]{1, 2, 3, 4, 5}), 2));
        System.out.println();
        ListNode.print(swapNodes2(ListNode.build(new int[]{1, 2, 3, 4, 5}), 2));
        System.out.println();

        ListNode.print(swapNodes(ListNode.build(new int[]{1, 2}), 1));
        System.out.println();
        ListNode.print(swapNodes2(ListNode.build(new int[]{1, 2}), 1));
        System.out.println();
    }

    /**
     * 直接交换两个节点的 val 即可
     * 方案一：先遍历链表获取总长度 size，再二次遍历链表获取第 k 个节点和第 size-k+1个节点
     */
    public static ListNode swapNodes(ListNode head, int k) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }

        ListNode node1 = head, node2 = head;
        int count = 1;
        cur = head;
        while (cur != null) {
            if (count == k) {
                node1 = cur;
            }
            if (count == size - k + 1) {
                node2 = cur;
            }
            count++;
            cur = cur.next;
        }

        swap(node1, node2);
        return head;
    }

    /**
     * 方案二：先使用 first 走 k 步，得到第一个节点，此时 node1 到 head 的距离是 k，然后用 second 从 head 开始遍历，
     * 保持 first 和 second 同步向前走，那么当 first 走到最后一个节点时，second 距离结尾正好是 k
     */
    public static ListNode swapNodes2(ListNode head, int k) {
        ListNode first = head, second = head;
        int count = 1;
        while (count < k) {
            count++;
            first = first.next;
        }
        ListNode node1 = first;
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        swap(node1, second);
        return head;
    }

    private static void swap(ListNode node1, ListNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
}

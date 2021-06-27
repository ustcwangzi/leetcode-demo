package com.wz.lists;

import com.wz.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of linked-lists lists, each linked list is sorted in ascending order.
 * Merge all the linked-lists into one sort linked-list and return it.
 *
 * Example :
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode l1 = ListNode.build(new int[]{1, 4, 5});
        ListNode l2 = ListNode.build(new int[]{1, 3, 4});
        ListNode l3 = ListNode.build(new int[]{2, 6});
        ListNode result = mergeKLists(new ListNode[]{l1, l2, l3});
        ListNode.print(result);
    }

    /**
     * 利用小根堆，首先把 k 个链表的首节点都加入小根堆中，然后每次取出最小的那个节点的元素值加入最终结果的链表中
     * 然后把取出节点的下一个节点再加入堆中，下次仍从堆中取出最小的节点做相同的操作，以此类推，直到堆中没有元素
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        // 将每个链表的首节点加入堆中
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        ListNode result = null, cur = null;
        while (!queue.isEmpty()) {
            ListNode top = queue.poll();
            // 依次从堆中取中头节点，即最小节点
            ListNode node = new ListNode(top.val);
            if (result == null) {
                result = node;
            } else {
                cur.next = node;
            }
            cur = node;

            // 将取出节点的下一个节点加入堆中
            if (top.next != null) {
                queue.offer(top.next);
            }
        }
        return result;
    }
}

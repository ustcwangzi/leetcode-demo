package com.wz.lists;

import com.wz.common.ListNode;

/**
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 * The blue edges and nodes in the following figure incidate the result:
 * @link ../../../../resource/MergeInBetweenLinkedLists.jpg
 * Build the result list and return its head.
 *
 * Example 1:
 * @link ../../../../resource/MergeInBetweenLinkedLists1.jpg
 * Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * Output: [0,1,2,1000000,1000001,1000002,5]
 * Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
 *
 * Example 2:
 * @link ../../../../resource/MergeInBetweenLinkedLists2.jpg
 * Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
 * Explanation: The blue edges and nodes in the above figure indicate the result.
 *
 * Constraints:
 * 1. 3 <= list1.length <= 10^4
 * 2. 1 <= a <= b < list1.length - 1
 * 3. 1 <= list2.length <= 10^4
 */
public class MergeInBetweenLinkedLists {
    public static void main(String[] args) {
        ListNode list1 = ListNode.build(new int[]{0, 1, 2, 3, 4, 5});
        ListNode list2 = ListNode.build(new int[]{1000000, 1000001, 1000002});
        ListNode.print(mergeInBetween(list1, 3, 4, list2));
    }

    /**
     * 遍历 list1，找到 a 的前一个节点 pre 和 b 的后一个节点 tail
     * 然后将 pre.next 指向 list2 的头节点，list2 的尾节点指向 tail
     */
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int count = 0;
        ListNode cur = list1;
        while (cur != null) {
            if (count == a - 1) {
                break;
            }
            count++;
            cur = cur.next;
        }
        // a 的前一个节点
        ListNode pre = cur;

        while (cur != null) {
            if (count == b + 1) {
                break;
            }
            count++;
            cur = cur.next;
        }

        pre.next = list2;
        // 找到 list2 的尾节点
        while (pre.next != null) {
            pre = pre.next;
        }
        // list2 的尾节点指向 b 的后一个节点
        pre.next = cur;
        return list1;
    }
}

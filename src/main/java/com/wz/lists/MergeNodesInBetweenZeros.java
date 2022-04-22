package com.wz.lists;

import com.wz.common.ListNode;

/**
 * You are given the head of a linked list, which contains a series of integers separated by 0's. The beginning and end of the linked list will have Node.val == 0.
 * For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes. The modified list should not contain any 0's.
 * Return the head of the modified linked list.
 *
 * Example 1:
 * @link ../../../../resource/MergeNodesInBetweenZeros1.jpg
 * Input: head = [0,3,1,0,4,5,2,0]
 * Output: [4,11]
 * Explanation:
 * The above figure represents the given linked list. The modified list contains
 * - The sum of the nodes marked in green: 3 + 1 = 4.
 * - The sum of the nodes marked in red: 4 + 5 + 2 = 11.
 *
 * Example 2:
 * @link ../../../../resource/MergeNodesInBetweenZeros2.jpg
 * Input: head = [0,1,0,3,0,2,2,0]
 * Output: [1,3,4]
 * Explanation:
 * The above figure represents the given linked list. The modified list contains
 * - The sum of the nodes marked in green: 1 = 1.
 * - The sum of the nodes marked in red: 3 = 3.
 * - The sum of the nodes marked in yellow: 2 + 2 = 4.
 *
 * Constraints:
 * 1. The number of nodes in the list is in the range [3, 2 * 10^5].
 * 2. 0 <= Node.val <= 1000
 * 3. There are no two consecutive nodes with Node.val == 0.
 * 4. The beginning and end of the linked list have Node.val == 0.
 */
public class MergeNodesInBetweenZeros {
    public static void main(String[] args) {
        ListNode.print(mergeNodes(ListNode.build(new int[]{0, 3, 1, 0, 4, 5, 2, 0})));
        ListNode.print(mergeNodes(ListNode.build(new int[]{0, 1, 0, 3, 0, 2, 2, 0})));
    }

    /**
     * 遍历链表，遍历过程中对 val 进行累加为 sum，遇到 0 时将之前的累加值进行合并
     * 合并后放到结果链表的结尾处，并重置 sum
     */
    public static ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode(), pre = dummy;
        head = head.next;
        int sum = 0;
        while (head != null) {
            if (head.val == 0) {
                pre.next = new ListNode(sum);
                pre = pre.next;
                sum = 0;
            } else {
                sum += head.val;
            }
            head = head.next;
        }
        return dummy.next;
    }
}

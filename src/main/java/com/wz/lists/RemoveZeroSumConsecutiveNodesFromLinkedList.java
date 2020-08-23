package com.wz.lists;

import com.wz.common.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 * After doing so, return the head of the final linked list.  You may return any such answer.
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * Example 1:
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 *
 * Example 2:
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 *
 * Example 3:
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 */
public class RemoveZeroSumConsecutiveNodesFromLinkedList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, -3, 3, 1});
        ListNode.print(removeZeroSumSublists(head));
    }

    /**
     * 遍历链表，记录每个前缀和对应的结点，存在 hashmap 中
     * 如果发现当前的前缀和已经在 hashmap 中出现过了，则说明找到了一段总和 0 的连续子区间
     * 取出 hashmap 中前缀和出现过的结点，然后再遍历到当前结点，遍历过程中，从 hashmap 中删除这些前缀和，更新链表
     * 然后继续从当前结点往后扫描
     * 以 1, 2, -3, 3, 1 为例说明以上过程(存在 dummy 节点)：
     * 遍历到 -3 节点时 map 中的 K-V 为 0:0, 1:1, 3:2，然后再次出现了 0
     * 从 map 中移除 1、3，从链表中删除 1、2、-3
     * 此时 map 中的 K-V 为 0:0，链表为 3, 1，然后从 3 开始继续遍历
     */
    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0, head), cur = dummy;
        Map<Integer, ListNode> map = new HashMap<>();
        int prefixSum = 0;
        while (cur != null) {
            prefixSum += cur.val;
            // prefixSum 出现过，删除 prefixSum 之后到 cur 的节点
            if (map.containsKey(prefixSum)) {
                ListNode node = map.get(prefixSum).next;
                int sum = prefixSum;
                // 从 hashmap 中逐个移除这个前缀和
                while (node != cur) {
                    sum += node.val;
                    map.remove(sum);
                    node = node.next;
                }
                // 删除 prefixSum 之后到 cur 的节点
                map.get(prefixSum).next = cur.next;
            } else {
                map.put(prefixSum, cur);
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}

package com.wz.lists;

import com.wz.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 *
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
 *
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 *
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
 *
 *
 *
 * Example 1:
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 *
 * Example 2:
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 */
public class NextGreaterNodeInLinkedList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{2, 7, 4, 3, 5});
        System.out.println(Arrays.toString(nextLargerNodes(head)));

        head = ListNode.build(new int[]{1, 7, 5, 1, 9, 2, 5, 1});
        System.out.println(Arrays.toString(nextLargerNodes(head)));
    }

    /**
     * 单调栈，只允许更小的元素入栈，如果当前元素大于栈顶元素则弹出栈顶
     * 对于弹出的元素而言，其右边第一个大于自己的元素就是当前元素
     */
    public static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return new int[]{};
        }

        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int[] result = new int[list.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            // 将小于当前元素的栈中元素弹出，同时记录结果
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i)) {
                result[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }
        return result;
    }
}

package com.wz.other;

import com.wz.common.ListNode;

import java.util.Arrays;

/**
 * A critical point in a linked list is defined as either a local maxima or a local minima.
 * A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.
 * A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.
 * Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.
 * Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is the minimum distance
 * between any two distinct critical points and maxDistance is the maximum distance between any two distinct critical points.
 * If there are fewer than two critical points, return [-1, -1].
 *
 * Example 1:
 * 3 -> 1
 * Input: head = [3,1]
 * Output: [-1,-1]
 * Explanation: There are no critical points in [3,1].
 *
 * Example 2:
 * 5 -> 3 -> 1 -> 2 -> 5 -> 1 -> 2
 * Input: head = [5,3,1,2,5,1,2]
 * Output: [1,3]
 * Explanation: There are three critical points:
 * - [5,3,1,2,5,1,2]: The third node is a local minima because 1 is less than 3 and 2.
 * - [5,3,1,2,5,1,2]: The fifth node is a local maxima because 5 is greater than 2 and 1.
 * - [5,3,1,2,5,1,2]: The sixth node is a local minima because 1 is less than 5 and 2.
 * The minimum distance is between the fifth and the sixth node. minDistance = 6 - 5 = 1.
 * The maximum distance is between the third and the sixth node. maxDistance = 6 - 3 = 3.
 *
 * Example 3:
 * 1 -> 3 -> 2 -> 2 -> 3 -> 2 -> 2 -> 2 -> 7
 * Input: head = [1,3,2,2,3,2,2,2,7]
 * Output: [3,3]
 * Explanation: There are two critical points:
 * - [1,3,2,2,3,2,2,2,7]: The second node is a local maxima because 3 is greater than 1 and 2.
 * - [1,3,2,2,3,2,2,2,7]: The fifth node is a local maxima because 3 is greater than 2 and 2.
 * Both the minimum and maximum distances are between the second and the fifth node.
 * Thus, minDistance and maxDistance is 5 - 2 = 3.
 * Note that the last node is not considered a local maxima because it does not have a next node.
 *
 * Constraints:
 * 1. The number of nodes in the list is in the range [2, 10^5].
 * 2. 1 <= Node.val <= 10^5
 */
public class FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nodesBetweenCriticalPoints(ListNode.build(new int[]{5, 3, 1, 2, 5, 1, 2}))));
        System.out.println(Arrays.toString(nodesBetweenCriticalPoints(ListNode.build(new int[]{1, 3, 2, 2, 3, 2, 2, 2, 7}))));
    }

    /**
     * 三指针
     * preNode、curNode、nextNode 分别指向相邻的三个节点，遍历链表
     * 若 curNode.val 大于左右两节点 或 小于左右两节点，则满足条件，更新最小差值 min、上一个满足条件的位置 preIndex 和 第一个满足条件的位置 firstIndex
     * 每遍历一个节点，都要更新 preNode、curNode、nextNode，同时 index++
     * 遍历结束后，若 firstIndex == -1，说明没有满足条件的位置，若 firstIndex == preIndex，说明满足条件的位置只有一个，两种情况都返回 {-1,-1}
     * 其他情况下，说明满足条件的位置至少有两个，最小值还是 min，最大值就是最后一个位置 减去 第一个位置，即 preIndex - firstIndex
     */
    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode preNode = head, curNode = head.next, nextNode = curNode.next;
        int preIndex = -1, firstIndex = -1, min = Integer.MAX_VALUE, index = 1;
        while (nextNode != null) {
            if ((preNode.val > curNode.val && curNode.val < nextNode.val) || (preNode.val < curNode.val && curNode.val > nextNode.val)) {
                if (preIndex != -1) {
                    min = Math.min(min, index - preIndex);
                }
                preIndex = index;
                if (firstIndex == -1) {
                    firstIndex = index;
                }
            }
            preNode = curNode;
            curNode = nextNode;
            nextNode = curNode.next;
            index++;
        }

        if (firstIndex == -1 || preIndex == firstIndex) {
            return new int[]{-1, -1};
        }
        return new int[]{min, preIndex - firstIndex};
    }
}

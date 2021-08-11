package com.wz.other;

import com.wz.common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 * Implement the Solution class:
 * 1. Solution(ListNode head) Initializes the object with the integer array nums.
 * 2. int getRandom() Chooses a node randomly from the list and returns its value. All the nodes of the list should be equally likely to be choosen.
 *
 * Example 1:
 * Input
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 3, 2, 2, 3]
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // return 1
 * solution.getRandom(); // return 3
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 3
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 *
 * Constraints:
 * 1. The number of nodes in the linked list will be in the range [1, 10^4].
 * 2. -10^4 <= Node.val <= 10^4
 * 3. At most 10^4 calls will be made to getRandom.
 */
public class LinkedListRandomNode {
    public static void main(String[] args) {
        LinkedListRandomNode solution = new LinkedListRandomNode(ListNode.build(new int[]{1, 2, 3}));
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
    }

    private final List<Integer> array;

    public LinkedListRandomNode(ListNode head) {
        array = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            array.add(cur.val);
            cur = cur.next;
        }
    }

    /** Returns a random node's value. */
    public int getRandom() {
        return array.get(new Random().nextInt(array.size()));
    }
}

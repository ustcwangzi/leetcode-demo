package com.wz.design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 * Implement the FreqStack class:
 * - FreqStack() constructs an empty frequency stack.
 * - void push(int val) pushes an integer val onto the top of the stack.
 * - int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 *
 * Example 1:
 * Input
 * ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
 * [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, null, 5, 7, 5, 4]
 * Explanation
 * FreqStack freqStack = new FreqStack();
 * freqStack.push(5); // The stack is [5]
 * freqStack.push(7); // The stack is [5,7]
 * freqStack.push(5); // The stack is [5,7,5]
 * freqStack.push(7); // The stack is [5,7,5,7]
 * freqStack.push(4); // The stack is [5,7,5,7,4]
 * freqStack.push(5); // The stack is [5,7,5,7,4,5]
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
 * freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
 * freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
 *
 * Constraints:
 * 1. 0 <= val <= 10^9
 * 2. At most 2 * 10^4 calls will be made to push and pop.
 * 3. It is guaranteed that there will be at least one element in the stack before calling pop.
 */
public class FreqStack {
    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }

    private int index;
    private final Map<Integer, Integer> map;
    private final Queue<Node> queue;

    /**
     * 大根堆 + map
     * 使用 map 记录每个元素出现次数，使用大根堆记录具体元素，注意需要保存 元素值、出现次数、位置，因为次数一样时，需要根据位置排序
     */
    public FreqStack() {
        map = new HashMap<>();
        queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.freq == o2.freq) {
                return Integer.compare(o2.position, o1.position);
            }
            return Integer.compare(o2.freq, o1.freq);
        });
    }

    /**
     * val 次数增加，同时在新的位置放置一个 val
     */
    public void push(int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
        queue.offer(new Node(val, map.get(val), index++));
    }

    public int pop() {
        Node node = queue.poll();
        if (node.freq == 1) {
            map.remove(node.val);
        } else {
            map.put(node.val, --node.freq);
        }
        return node.val;
    }

    private static class Node {
        private int val;
        private int freq;
        private int position;

        public Node(int val, int freq, int position) {
            this.val = val;
            this.freq = freq;
            this.position = position;
        }
    }
}

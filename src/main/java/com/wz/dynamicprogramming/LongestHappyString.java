package com.wz.dynamicprogramming;

import java.util.PriorityQueue;

/**
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.
 * Given three integers a, b and c, return any string s, which satisfies following conditions:
 * 1. s is happy and longest possible.
 * 2. s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
 * 3. s will only contain 'a', 'b' and 'c' letters.
 * If there is no such string s return the empty string "".
 *
 * Example 1:
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 *
 * Example 2:
 * Input: a = 2, b = 2, c = 1
 * Output: "aabbc"
 *
 * Example 3:
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It's the only correct answer in this case.
 *
 * Constraints:
 * 1. 0 <= a, b, c <= 100
 * 2. a + b + c > 0
 */
public class LongestHappyString {
    public static void main(String[] args) {
        System.out.println(longestDiverseString(1, 1, 7));
    }

    /**
     * 大根堆
     * 优先选择剩余次数最多的元素，因为不允许一个元素连续出现三次，因此需要在加入结果集之前判断前两个元素是否与当前元素相等
     * 若相等，则选择出现次数次多的元素加入结果集，然后再将出现次数最多的继续加入结果集
     * 注意，每次加入结果集之后，如果剩余的次数大于 0，则需要将元素重新放入优先级队列中
     */
    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[0], o1[0]));
        if (a > 0) {
            queue.offer(new int[]{a, 0});
        }
        if (b > 0) {
            queue.offer(new int[]{b, 1});
        }
        if (c > 0) {
            queue.offer(new int[]{c, 2});
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] first = queue.poll();
            int size = builder.length();
            // 已连续出现两个相同元素，不能选择当前元素
            if (size >= 2 && builder.charAt(size - 1) == first[1] + 'a' && builder.charAt(size - 2) == first[1] + 'a') {
                if (queue.isEmpty()) {
                    break;
                }

                int[] second = queue.poll();
                builder.append((char) ('a' + second[1]));
                // 重新加入堆中
                if (--second[0] > 0) {
                    queue.offer(second);
                }
            }

            builder.append((char) ('a' + first[1]));
            if (--first[0] > 0) {
                queue.offer(first);
            }
        }
        return builder.toString();
    }
}

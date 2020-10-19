package com.wz.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 * Input: S = "aab"
 * Output: "aba"
 *
 * Example 2:
 * Input: S = "aaab"
 * Output: ""
 */
public class ReorganizeString {
    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
    }

    /**
     * 使用 Map 来建立字母和其出现次数之间的映射，使用一个最大堆将出现次数多的字符排前面。
     * 如果某个字母出现的频率大于总长度的一半了，那么必然会有两个相邻的字母出现，直接返回空串即可
     * 每次从 queue 中取队首的两个字母，将其放在一起，然后将它们的出现次数自减1，如果减1后的次数仍大于0的话，将其再放回最大堆，
     * 由于是两个两个取的，所以循环退出后，有可能 queue 中还剩下元素，直接将其加入结果中即可，而且这个多余的元素一定只有一个字母了，
     * 因为提前判断过各个字母的出现次数是否小于等于总长度的一半，按这种机制来取字母，不可能会剩下多余一个的相同字母
     */
    public static String reorganizeString(String S) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        // 最大堆
        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> countMap.get(o2) - countMap.get(o1));
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > (S.length() + 1) / 2) {
                return "";
            }
            queue.add(entry.getKey());
        }

        StringBuilder builder = new StringBuilder();
        while (queue.size() >= 2) {
            Character c1 = queue.poll(), c2 = queue.poll();
            builder.append(c1).append(c2);
            int count1 = countMap.get(c1), count2 = countMap.get(c2);
            if (--count1 > 0) {
                countMap.put(c1, count1);
                queue.offer(c1);
            }
            if (--count2 > 0) {
                countMap.put(c2, count2);
                queue.offer(c2);
            }
        }
        if (queue.size() > 0) {
            builder.append(queue.peek());
        }
        return builder.toString();
    }
}

package com.wz.lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * In a string S of lowercase letters, these letters form consecutive groups of the same character.
 * For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
 * Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.
 * The final answer should be in lexicographic order.
 *
 * Example 1:
 * Input: "abbxxxxzzy"
 * Output: [[3,6]]
 * Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
 *
 * Example 2:
 * Input: "abc"
 * Output: []
 * Explanation: We have "a","b" and "c" but no large group.
 *
 * Example 3:
 * Input: "abcdddeeeeaabbbcd"
 * Output: [[3,5],[6,9],[12,14]]
 */
public class PositionsOfLargeGroups {
    public static void main(String[] args) {
        String S = "abbxxxxzzy";
        System.out.println(largeGroupPositions(S));

        S = "abc";
        System.out.println(largeGroupPositions(S));

        S = "abcdddeeeeaabbbcd";
        System.out.println(largeGroupPositions(S));

        S = "caaa";
        System.out.println(largeGroupPositions(S));
    }

    /**
     * 用start记录开始位置，然后遍历数组，遇到与start上元素不一致时，计算长度是否满足要求
     * 注意遍历完成后，需要对最后一个位置再次进行判断，否则会漏掉最后的连续元素
     */
    public static List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> result = new LinkedList<>();
        if (S.length() < 3) {
            return result;
        }

        int start = 0;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) != S.charAt(start)) {
                if (i - start >= 3) {
                    List<Integer> position = new ArrayList<>(2);
                    position.add(start);
                    position.add(i - 1);
                    result.add(position);
                }
                start = i;
            }
        }

        // 对最后的连续元素进行判断
        if (S.length() - start >= 3) {
            List<Integer> position = new ArrayList<>(2);
            position.add(start);
            position.add(S.length() - 1);
            result.add(position);
        }

        return result;
    }
}

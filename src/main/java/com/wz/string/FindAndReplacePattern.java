package com.wz.string;

import java.util.LinkedList;
import java.util.List;

/**
 * You have a list of words and a pattern, and you want to know which words in words matches the pattern.
 * A word matches the pattern if there exists a permutation of letters p
 * so that after replacing every letter x in the pattern with p(x), we get the desired word.
 * (Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter,
 * and no two letters map to the same letter.)
 * Return a list of the words in words that match the given pattern.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
 * since a and b map to the same letter.
 *
 * Note:
 * 1. 1 <= words.length <= 50
 * 2. 1 <= pattern.length = words[i].length <= 20
 */
public class FindAndReplacePattern {
    public static void main(String[] args) {
        System.out.println(findAndReplacePattern(new String[]{"abc", "deq", "mee", "aqq", "dkd", "ccc"}, "abb"));
    }

    /**
     * 遍历每个 word，将对应位置字符映射到同一个值上，使用 wMap 和 pMap 保存映射结果
     * 如果遍历过程中发现某个字符已经映射过，但值不同，说明不符合 pattern，跳过
     */
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new LinkedList<>();
        for (String word : words) {
            int[] wMap = new int[26], pMap = new int[26];
            boolean match = true;
            for (int i = 0; i < word.length(); i++) {
                int wIndex = word.charAt(i) - 'a', pIndex = pattern.charAt(i) - 'a';
                // 该字符之前遇到并且做过映射，此处值不同，说明不能匹配
                if (wMap[wIndex] != pMap[pIndex]) {
                    match = false;
                    break;
                }
                // int 数组每个值默认是0，因此这里使用 i+1 进行映射
                wMap[wIndex] = pMap[pIndex] = i + 1;
            }

            if (match) {
                result.add(word);
            }
        }

        return result;
    }
}

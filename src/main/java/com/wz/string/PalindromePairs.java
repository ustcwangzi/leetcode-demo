package com.wz.string;

import java.util.*;

/**
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list,
 * so that the concatenation of the two words words[i] + words[j] is a palindrome.
 *
 * Example 1:
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * Example 2:
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 *
 * Example 3:
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 *
 * Constraints:
 * 1. 1 <= words.length <= 5000
 * 2. 0 <= words[i] <= 300
 * 3. words[i] consists of lower-case English letters.
 */
public class PalindromePairs {
    public static void main(String[] args) {
        System.out.println(palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
        System.out.println(palindromePairs(new String[]{"bat", "tab", "cat"}));
        System.out.println(palindromePairs(new String[]{"a", ""}));
    }

    /**
     * 使用 indexMap 保存单词与下标的映射
     * 遍历 words，记当前单词为 word，下标为 i：
     * 1. 若 word 本身为回文，且 words 中存在空串，则将空串下标 index 与 i 加入结果集
     * 2. 若 word 的逆序串在 words 中，则将逆序串下标 index 与 i 加入结果集
     * 3. 将 word 拆分为左右两部分 left 和 right
     *    3.1 若 left 为回文，并且 right 的逆序串在 words 中，则将 i 与 right 的逆序串下标 index 加入结果集
     *    3.2 若 right 为回文，并且 left 的逆序串在 words 中，则将 left 的逆序串下标 index 与 i 加入结果集
     */
    public static List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> indexMap = new HashMap<>(words.length);
        for (int i = 0; i < words.length; i++) {
            indexMap.put(words[i], i);
        }

        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            // 自身回文并且包含空串
            if (isPalindrome(words[i]) && indexMap.containsKey("") && indexMap.get("") != i) {
                result.add(Arrays.asList(i, indexMap.get("")));
                result.add(Arrays.asList(indexMap.get(""), i));
            }

            // 包含自身的逆序
            String reversed = new StringBuffer(words[i]).reverse().toString();
            if (indexMap.containsKey(reversed) && indexMap.get(reversed) != i) {
                result.add(Arrays.asList(i, indexMap.get(reversed)));
            }

            // 拆分为 left、right
            for (int j = 1; j < words[i].length(); j++) {
                String left = words[i].substring(0, j), right = words[i].substring(j);

                // left回文、right逆序存在
                if (isPalindrome(left)) {
                    reversed = new StringBuffer(right).reverse().toString();
                    if (indexMap.containsKey(reversed)) {
                        result.add(Arrays.asList(indexMap.get(reversed), i));
                    }
                }

                // right回文、left逆序存在
                if (isPalindrome(right)) {
                    reversed = new StringBuffer(left).reverse().toString();
                    if (indexMap.containsKey(reversed)) {
                        result.add(Arrays.asList(i, indexMap.get(reversed)));
                    }
                }
            }
        }

        return result;
    }

    private static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}

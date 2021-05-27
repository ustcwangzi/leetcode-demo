package com.wz.hash;

import com.wz.dfs.WordDictionary;

/**
 * Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.
 *
 * Example 1:
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 *
 * Example 2:
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 *
 * Constraints:
 * 1. 1 <= words.length <= 1000
 * 2. 1 <= words[i].length <= 30
 * 3. words[i] consists of lowercase English letters.
 */
public class LongestWordInDictionary {
    public static void main(String[] args) {
        System.out.println(longestWord(new String[]{"ts", "e", "x"}));
        System.out.println(longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));
    }

    /**
     * 前缀树，与 {@link WordDictionary} 类似
     * 先将 word 全部加入到 Trie 中，然后遍历 word，依次判断每个字串是否包含在 Trie 中，包含则更新结果
     */
    public static String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        String result = "";
        for (String word : words) {
            if ("".equals(result) && word.length() == 1) {
                result = word;
            }
            insert(root, word);
        }
        for (String word : words) {
            if (word.length() < result.length()) {
                continue;
            }

            boolean valid = false;
            for (int i = 1; i < word.length(); i++) {
                valid = find(root, word.substring(0, i));
                if (!valid) {
                    break;
                }
            }
            if (!valid) {
                continue;
            }

            if (word.length() > result.length()) {
                result = word;
            } else if (word.length() == result.length()) {
                result = word.compareTo(result) > 0 ? result : word;
            }
        }
        return result;
    }

    private static void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.child[index] == null) {
                node.child[index] = new TrieNode();
            }
            if (i == word.length() - 1) {
                node.child[index].isWord = true;
            }
            node = node.child[index];
        }
    }

    private static boolean find(TrieNode root, String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.child[index] == null) {
                return false;
            }
            node = node.child[index];
        }
        return node.isWord;
    }

    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }
}

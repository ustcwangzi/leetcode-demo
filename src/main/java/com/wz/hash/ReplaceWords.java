package com.wz.hash;

import java.util.Arrays;
import java.util.List;

/**
 * In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor.
 * For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
 * replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root,
 * replace it with the root that has the shortest length.
 * Return the sentence after the replacement.
 *
 * Example 1:
 * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 * Example 2:
 * Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * Output: "a a b c"
 *
 * Example 3:
 * Input: dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
 * Output: "a a a a a a a a bbb baba a"
 *
 * Constraints:
 * 1. 1 <= dictionary.length <= 1000
 * 2. 1 <= dictionary[i].length <= 100
 * 3. dictionary[i] consists of only lower-case letters.
 * 4. 1 <= sentence.length <= 10^6
 * 5. sentence consists of only lower-case letters and spaces.
 * 6. The number of words in sentence is in the range [1, 1000]
 * 7. The length of each word in sentence is in the range [1, 1000]
 * 8. Each two consecutive words in sentence will be separated by exactly one space.
 * 9. sentence does not have leading or trailing spaces.
 */
public class ReplaceWords {
    public static void main(String[] args) {
        System.out.println(replaceWords(Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery"));
        System.out.println(replaceWords(Arrays.asList("a", "b", "c"), "aadsfasf absbs bbab cadsfafs"));
    }

    /**
     * 前缀树
     * 把所有的前缀都放到前缀树里面，而且在前缀的最后一个节点将字符串放到 str 中，表示从根节点到当前节点是一个前缀，
     * 然后遍历单词，在前缀树查找，如果当前单词对应的节点的 str 不为空，就返回这个前缀，否则返回原单词
     */
    public static String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();
        for (String str : dictionary) {
            insert(str, root);
        }
        String[] array = sentence.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String str : array) {
            builder.append(getRoot(str, root)).append(" ");
        }
        return builder.toString().trim();
    }

    private static void insert(String s, TrieNode node) {
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (node.child[index] == null) {
                node.child[index] = new TrieNode();
            }
            if (i == s.length() - 1) {
                node.child[index].str = s;
            }
            node = node.child[index];
        }
    }

    private static String getRoot(String s, TrieNode node) {
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (node.child[index] == null) {
                return s;
            }
            if (node.child[index].str != null) {
                return node.child[index].str;
            }
            node = node.child[index];
        }
        return s;
    }

    private static class TrieNode {
        String str = null;
        TrieNode[] child = new TrieNode[26];
    }
}

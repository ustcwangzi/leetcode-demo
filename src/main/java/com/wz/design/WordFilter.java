package com.wz.design;

import com.wz.dfs.WordDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.
 * Implement the WordFilter class:
 * - WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * - f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix.
 *   If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 *
 * Example 1:
 * Input
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * Output
 * [null, 0]
 * Explanation
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 *
 * Constraints:
 * 1. 1 <= words.length <= 15000
 * 2. 1 <= words[i].length <= 10
 * 3. 1 <= prefix.length, suffix.length <= 10
 * 4. words[i], prefix and suffix consist of lower-case English letters only.
 * 5. At most 15000 calls will be made to the function f.
 */
public class WordFilter {
    public static void main(String[] args) {
        WordFilter filter = new WordFilter(new String[]{"apple"});
        System.out.println(filter.f("a", "e"));
    }

    TrieNode root;
    String[] words;

    /**
     * 字典树 Trie，{@link WordDictionary}
     * 使用 Trie 来保存前缀，查找时，在 Trie 中找到符合 prefix 的 TrieNode，然后判断每个 word 是否满足 suffix
     */
    public WordFilter(String[] words) {
        root = new TrieNode();
        TrieNode node = root;
        for (int i = 0; i < words.length; i++) {
            for (char ch : words[i].toCharArray()) {
                if (root.child[ch - 'a'] == null) {
                    root.child[ch - 'a'] = new TrieNode();
                }

                root.child[ch - 'a'].indexList.add(i);
                root = root.child[ch - 'a'];
            }
            root = node;
        }

        this.words = words;
    }

    public int f(String prefix, String suffix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.child[ch - 'a'];
            if (node == null) {
                return -1;
            }
        }

        List<Integer> list = node.indexList;
        // 逆序遍历，找到的第一个就是最大的
        for (int i = list.size() - 1; i >= 0; i--) {
            if (words[list.get(i)].endsWith(suffix)) {
                return list.get(i);
            }
        }
        return -1;
    }

    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        List<Integer> indexList = new ArrayList<>();
    }
}

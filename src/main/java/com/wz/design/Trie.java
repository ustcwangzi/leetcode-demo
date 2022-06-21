package com.wz.design;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 * Implement the Trie class:
 * 1. Trie() Initializes the trie object.
 * 2. void insert(String word) Inserts the string word into the trie.
 * 3. boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * 4. boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 * Example 1:
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 * Constraints:
 * 1. 1 <= word.length, prefix.length <= 2000
 * 2. word and prefix consist only of lowercase English letters.
 * 3. At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 */
public class Trie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isEnd;
    }

    private final TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (char cur : word.toCharArray()) {
            if (node.child[cur - 'a'] == null) {
                node.child[cur - 'a'] = new TrieNode();
            }
            node = node.child[cur - 'a'];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char cur : word.toCharArray()) {
            if (node.child[cur - 'a'] == null) {
                return null;
            }
            node = node.child[cur - 'a'];
        }
        return node;
    }
}

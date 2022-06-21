package com.wz.design;

import java.util.Arrays;

/**
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.
 * For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z',
 * your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
 * Implement the StreamChecker class:
 * - StreamChecker(String[] words) Initializes the object with the strings array words.
 * - boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
 *
 *
 * Example 1:
 * Input
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * Output
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 * Explanation
 * StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 * streamChecker.query("a"); // return False
 * streamChecker.query("b"); // return False
 * streamChecker.query("c"); // return False
 * streamChecker.query("d"); // return True, because 'cd' is in the wordlist
 * streamChecker.query("e"); // return False
 * streamChecker.query("f"); // return True, because 'f' is in the wordlist
 * streamChecker.query("g"); // return False
 * streamChecker.query("h"); // return False
 * streamChecker.query("i"); // return False
 * streamChecker.query("j"); // return False
 * streamChecker.query("k"); // return False
 * streamChecker.query("l"); // return True, because 'kl' is in the wordlist
 *
 * Constraints:
 * 1. 1 <= words.length <= 2000
 * 2. 1 <= words[i].length <= 200
 * 3. words[i] consists of lowercase English letters.
 * 4. letter is a lowercase English letter.
 * 5. At most 4 * 10^4 calls will be made to query.
 */
public class StreamChecker {
    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('c'));
        System.out.println(streamChecker.query('d'));
        System.out.println(streamChecker.query('e'));
        System.out.println(streamChecker.query('f'));
        System.out.println(streamChecker.query('g'));
        System.out.println(streamChecker.query('h'));
        System.out.println(streamChecker.query('i'));
        System.out.println(streamChecker.query('j'));
        System.out.println(streamChecker.query('k'));
        System.out.println(streamChecker.query('l'));
    }

    private final TrieNode root;
    private final StringBuilder builder;

    /**
     * 字典树(Trie)，是对 {@link Trie} 的扩展，{@link Trie} 中是使用前缀构造的，本题是使用后缀构造的
     */
    public StreamChecker(String[] words) {
        root = new TrieNode();
        builder = new StringBuilder();
        Arrays.stream(words).forEach(this::buildTrie);
    }

    public boolean query(char letter) {
        TrieNode node = root;
        builder.append(letter);
        for (int i = builder.length() - 1; i >= 0; i--) {
            int index = builder.charAt(i) - 'a';
            TrieNode child = node.child[index];
            if (child == null) {
                return false;
            }
            if (child.isWord) {
                return true;
            }
            node = child;
        }
        return false;
    }

    private void buildTrie(String word) {
        TrieNode node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int index = word.charAt(i) - 'a';
            if (node.child[index] == null) {
                node.child[index] = new TrieNode();
            }
            node = node.child[index];
        }
        node.isWord = true;
    }

    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }
}

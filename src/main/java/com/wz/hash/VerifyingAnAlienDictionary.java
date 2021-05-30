package com.wz.hash;

/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographicaly in this alien language.
 *
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Constraints:
 * 1. 1 <= words.length <= 100
 * 2. 1 <= words[i].length <= 20
 * 3. order.length == 26
 * 4. All characters in words[i] and order are English lowercase letters.
 */
public class VerifyingAnAlienDictionary {
    public static void main(String[] args) {
        System.out.println(isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println(isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    }

    /**
     * 依照 order 来判断 words 是否有序
     * 将 order 中每个字符的顺序存入 map，然后遍历 words，判断临近的两个 word 是否有序
     */
    public static boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];
        for (int i = 0; i < order.length(); i++) {
            map[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (compare(words[i], words[i + 1], map)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compare(String word1, String word2, int[] map) {
        int m = word1.length(), n = word2.length(), k = Math.min(m, n);
        for (int i = 0; i < k; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                return map[word1.charAt(i) - 'a'] > map[word2.charAt(i) - 'a'];
            }
        }
        return m > n;
    }
}

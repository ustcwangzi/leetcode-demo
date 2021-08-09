package com.wz.other;

/**
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
 *
 * Example 1:
 * Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 *
 * Example 2:
 * Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 *
 * Example 3:
 * Input: words = ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 *
 * Constraints:
 * 1. 2 <= words.length <= 1000
 * 2. 1 <= words[i].length <= 1000
 * 3. words[i] consists only of lowercase English letters.
 */
public class MaximumProductOfWordLengths {
    public static void main(String[] args) {
        System.out.println(maxProduct1(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
        System.out.println(maxProduct2(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
    }

    /**
     * 求两个没有相同字母的单词长度之积的最大值
     * 方案一：因为都是小写字母，可以使用长度为 26 的数组保存每个字符出现次数，两个单词没有共同字母的条件是对应位置出现次数都是 0
     */
    public static int maxProduct1(String[] words) {
        int[][] freq = new int[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (char cur : words[i].toCharArray()) {
                freq[i][cur - 'a']++;
            }
        }

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (!isCommon(freq[i], freq[j])) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }

    private static boolean isCommon(int[] freq1, int[] freq2) {
        for (int i = 0; i < 26; i++) {
            if (freq1[i] != 0 && freq2[i] != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方案二：int 有 32 位，可以用后 26 位来对应 26 个字母，若为 1，说明该对应位置的字母出现过
     * 那么每个单词都可由一个 int 表示，两个单词没有共同字母的条件是这两个 int 与运算结果为 0
     */
    public static int maxProduct2(String[] words) {
        int[] freq = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char cur : words[i].toCharArray()) {
                freq[i] |= (1 << (cur - 'a'));
            }
        }

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((freq[i] & freq[j]) == 0) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }
}

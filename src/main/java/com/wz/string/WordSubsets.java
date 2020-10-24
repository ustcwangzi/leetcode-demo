package com.wz.string;

import java.util.LinkedList;
import java.util.List;

/**
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.
 * For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 * Return a list of all universal words in A.  You can return the words in any order.
 *
 * Example 1:
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 *
 * Example 2:
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * Output: ["google","leetcode"]
 *
 * Example 3:
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 *
 * Note:
 * 1. 1 <= A.length, B.length <= 10000
 * 2. 1 <= A[i].length, B[i].length <= 10
 * 3. A[i] and B[i] consist only of lowercase letters.
 * 4. All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 */
public class WordSubsets {
    public static void main(String[] args) {
        String[] A = new String[]{"amazon", "apple", "facebook", "google", "leetcode"};
        System.out.println(wordSubsets(A, new String[]{"e", "o"}));
        System.out.println(wordSubsets(A, new String[]{"lo", "eo"}));
        System.out.println(wordSubsets(A, new String[]{"ec", "oc", "ceo"}));
    }

    /**
     * 单词 b 中的每个字母都在单词 a 中出现了（包括重复字母），就说单词 b 是单词 a 的子集合
     * 即：若单词 a 中的每个字母的出现次数都大于等于单词 b 中每个字母的出现次数，单词 b 就一定是 a 的子集合
     * 只要对于每个字母，都统计出集合 B 中每个单词的每个字符出现的最大次数，如 ["eo","oo"]，其中 e 最多出现1次，而 o 最多出现2次，
     * 那么只要集合 A 中有单词的 e 出现不少1次，o 出现不少于2次，则集合 B 中的所有单词一定都是其子集合
     */
    public static List<String> wordSubsets(String[] A, String[] B) {
        // 记录 B 中每个单词的每个字符多次出现次数
        int[] count = new int[26];
        for (String cur : B) {
            int[] tmp = count(cur);
            for (int i = 0; i < 26; i++) {
                count[i] = Math.max(count[i], tmp[i]);
            }
        }

        List<String> result = new LinkedList<>();
        for (String cur : A) {
            int[] tmp = count(cur);
            int i = 0;
            for (; i < 26; i++) {
                if (tmp[i] < count[i]) {
                    break;
                }
            }
            // A 中单词出现次数大于等于 B 中对应单词出现次数，即满足要求
            if (i == 26) {
                result.add(cur);
            }
        }
        return result;
    }

    private static int[] count(String word) {
        int[] count = new int[26];
        for (int i = 0; i < word.length(); i++) {
            count[word.charAt(i) - 'a']++;
        }
        return count;
    }
}

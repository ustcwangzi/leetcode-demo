package com.wz.other;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given two 0-indexed arrays of strings startWords and targetWords. Each string consists of lowercase English letters only.
 * For each string in targetWords, check if it is possible to choose a string from startWords and perform a conversion operation on it to be equal to that from targetWords.
 * The conversion operation is described in the following two steps:
 * 1. Append any lowercase letter that is not present in the string to its end.
 *    For example, if the string is "abc", the letters 'd', 'e', or 'y' can be added to it, but not 'a'. If 'd' is added, the resulting string will be "abcd".
 * 2. Rearrange the letters of the new string in any arbitrary order.
 *    For example, "abcd" can be rearranged to "acbd", "bacd", "cbda", and so on. Note that it can also be rearranged to "abcd" itself.
 * Return the number of strings in targetWords that can be obtained by performing the operations on any string of startWords.
 * Note that you will only be verifying if the string in targetWords can be obtained from a string in startWords by performing the operations.
 * The strings in startWords do not actually change during this process.
 *
 * Example 1:
 * Input: startWords = ["ant","act","tack"], targetWords = ["tack","act","acti"]
 * Output: 2
 * Explanation:
 * - In order to form targetWords[0] = "tack", we use startWords[1] = "act", append 'k' to it, and rearrange "actk" to "tack".
 * - There is no string in startWords that can be used to obtain targetWords[1] = "act".
 *   Note that "act" does exist in startWords, but we must append one letter to the string before rearranging it.
 * - In order to form targetWords[2] = "acti", we use startWords[1] = "act", append 'i' to it, and rearrange "acti" to "acti" itself.
 *
 * Example 2:
 * Input: startWords = ["ab","a"], targetWords = ["abc","abcd"]
 * Output: 1
 * Explanation:
 * - In order to form targetWords[0] = "abc", we use startWords[0] = "ab", add 'c' to it, and rearrange it to "abc".
 * - There is no string in startWords that can be used to obtain targetWords[1] = "abcd".
 *
 * Constraints:
 * 1. 1 <= startWords.length, targetWords.length <= 5 * 10^4
 * 2. 1 <= startWords[i].length, targetWords[j].length <= 26
 * 3. Each string of startWords and targetWords consists of lowercase English letters only.
 * 4. No letter occurs more than once in any string of startWords or targetWords.
 */
public class CountWordsObtainedAfterAddingLetter {
    public static void main(String[] args) {
        System.out.println(wordCount(new String[]{"ant", "act", "tack"}, new String[]{"tack", "act", "acti"}));
    }

    /**
     * 因为可以对 word 进行重新排序，因此原始顺序不重要，可以使用二进制整数来表示 word
     * 遍历 startWords，将每个 word 转换为整数并存入 set，同时可以追加单词，将追加后能够得到的整数也加入 set
     * 最后遍历 targetWords，若当前 word 代表的整数在 set 中，说明可以通过转换得到
     */
    public static int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> set = new HashSet<>();
        for (String word : startWords) {
            int mask = bitmask(word);
            for (char ch = 'a'; ch <= 'z'; ch++) {
                // 通过转换能够得到的状态
                int cur = mask | 1 << ch - 'a';
                if (cur != mask) {
                    set.add(cur);
                }
            }
        }

        int result = 0;
        for (String word : targetWords) {
            if (set.contains(bitmask(word))) {
                result++;
            }
        }
        return result;
    }

    /**
     * 将 word 转换为标识所包含字母的二进制整数
     */
    private static int bitmask(String word) {
        int mask = 0;
        for (char ch : word.toCharArray()) {
            mask |= 1 << ch - 'a';
        }
        return mask;
    }
}

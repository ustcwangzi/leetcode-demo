package com.wz.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 * 1. words.length == indices.length
 * 2. The reference string s ends with the '#' character.
 * 3. For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 *
 * Example 1:
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 *
 * Example 2:
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 *
 * Constraints:
 * 1. 1 <= words.length <= 2000
 * 2. 1 <= words[i].length <= 7
 * 3. words[i] consists of only lowercase letters.
 */
public class ShortEncodingOfWords {
    public static void main(String[] args) {
        System.out.println(minimumLengthEncoding(new String[]{"time", "me", "bell"}));
        System.out.println(minimumLengthEncoding(new String[]{"t"}));
    }

    /**
     * 就是要检查一个单词是否是另一个单词的后缀，如果是则进行合并，不能合并的中间需要加入 #
     * 使用 set 对保存所有单词，然后遍历 words，看 set 中是否存在这些单词的后缀，存在则删掉，剩下的就是不可能再合并的
     * 最后将 set 中的元素累加长度，同时需要考虑 # 的长度
     */
    public static int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }
        int result = 0;
        for (String cur : set) {
            result += cur.length() + 1;
        }
        return result;
    }
}

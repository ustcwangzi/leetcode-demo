package com.wz.string;

/**
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted.
 * More specifically, if x occurs before y in S, then x should occur before y in the returned string.
 * Return any permutation of T (as a string) that satisfies this property.
 *
 * Example :
 * Input:
 * S = "cba"
 * T = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 *
 * Note:
 * 1. S has length at most 26, and no character is repeated in S.
 * 2. T has length at most 200.
 * 3. S and T consist of lowercase letters only.
 */
public class CustomSortString {
    public static void main(String[] args) {
        System.out.println(customSortString("cba", "abcd"));
    }

    /**
     * 按 S 中的字母顺序遍历，对于遍历到的每个字母，如果 T 存在，就先排出来，这样等 S 中的字母遍历完，再将 T 中剩下的字母加到后面即可
     * 使用 count 统计 T 中每个字母的出现次数，然后遍历 S，只要 T 中存在，就放在结果集中，同时次数减1，然后再遍历 T 中剩余字母加到结果集
     */
    public static String customSortString(String S, String T) {
        int[] count = new int[26];
        for (int i = 0; i < T.length(); i++) {
            count[T.charAt(i) - 'a']++;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            while (count[S.charAt(i) - 'a']-- > 0) {
                builder.append(S.charAt(i));
            }
        }
        for (int i = 0; i < T.length(); i++) {
            while (count[T.charAt(i) - 'a']-- > 0) {
                builder.append(T.charAt(i));
            }
        }
        return builder.toString();
    }
}

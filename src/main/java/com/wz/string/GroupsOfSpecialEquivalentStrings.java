package com.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an array A of strings.
 * A move onto S consists of swapping any two even indexed characters of S, or any two odd indexed characters of S.
 * Two strings S and T are special-equivalent if after any number of moves onto S, S == T.
 * For example, S = "zzxy" and T = "xyzz" are special-equivalent because we may make the moves
 * "zzxy" -> "xzzy" -> "xyzz" that swap S[0] and S[2], then S[1] and S[3].
 * Now, a group of special-equivalent strings from A is a non-empty subset of A such that:
 * 1. Every pair of strings in the group are special equivalent, and;
 * 2. The group is the largest size possible (ie., there isn't a string S not in the group such that S is special equivalent to every string in the group)
 * Return the number of groups of special-equivalent strings from A.
 *
 * Example 1:
 * Input: ["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
 * Output: 3
 * Explanation:
 * One group is ["abcd", "cdab", "cbad"], since they are all pairwise special equivalent,
 * and none of the other strings are all pairwise special equivalent to these.
 * The other two groups are ["xyzz", "zzxy"] and ["zzyx"].  Note that in particular, "zzxy" is not special equivalent to "zzyx".
 *
 * Example 2:
 * Input: ["abc","acb","bac","bca","cab","cba"]
 * Output: 3
 *
 * Note:
 * 1. 1 <= A.length <= 1000
 * 2. 1 <= A[i].length <= 20
 * 3. All A[i] have the same length.
 * 4. All A[i] consist of only lowercase letters.
 */
public class GroupsOfSpecialEquivalentStrings {
    public static void main(String[] args) {
        System.out.println(numSpecialEquivGroups(new String[]{"abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx"}));
    }

    /**
     * 偶数位置的字符可以互换，奇数位置字符也可以互换，为了获取正确的 group 数量，需要把那些重复的排除掉
     * 分别统计偶数位置和奇数位置的字符出现次数，然后组成一个新字符，存入 Set 中进行去重，最后只要返回 Set 的 size 即可
     */
    public static int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for (String str : A) {
            String key = evenOdd(str);
            set.add(key);
        }
        return set.size();
    }

    private static String evenOdd(String str) {
        int[] even = new int[26], odd = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i % 2 == 0) {
                even[c - 'a']++;
            } else {
                odd[c - 'a']++;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int x : even) {
            builder.append(x);
        }
        for (int x : odd) {
            builder.append(x);
        }
        return builder.toString();
    }
}

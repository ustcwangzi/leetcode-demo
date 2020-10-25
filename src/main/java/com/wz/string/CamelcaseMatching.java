package com.wz.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A query word matches a given pattern if we can insert lowercase letters to the pattern word so that it equals the query.
 * (We may insert each character at any position, and may insert 0 characters.)
 * Given a list of queries, and a pattern, return an answer list of booleans, where answer[i] is true if and only if queries[i] matches the pattern.
 *
 * Example 1:
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * Output: [true,false,true,true,false]
 * Explanation:
 * "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
 * "FootBall" can be generated like this "F" + "oot" + "B" + "all".
 * "FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".
 *
 * Example 2:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * Output: [true,false,true,false,false]
 * Explanation:
 * "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
 * "FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".
 *
 * Note:
 * 1. 1 <= queries.length <= 100
 * 2. 1 <= queries[i].length <= 100
 * 3. 1 <= pattern.length <= 100
 * 4. All strings consists only of lower and upper case English letters.
 */
public class CamelcaseMatching {
    public static void main(String[] args) {
        String[] queries = new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        System.out.println(camelMatch(queries, "FB"));
        System.out.println(camelMatch(queries, "FoBa"));
    }

    public static List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>(queries.length);
        Arrays.stream(queries).forEach(query ->
                result.add(isMatch(query, pattern))
        );
        return result;
    }

    /**
     * 双指针
     * 用 i 和 j 分别指向 query 和 pattern，遍历query
     * 1）若 query[i] == pattern[j]，j 右移
     * 2）如果不相等且 query[i] 不是小写字母，直接返回 false
     * 遍历结束后，判断 j 是否走到 pattern 末尾
     */
    private static boolean isMatch(String query, String pattern) {
        if (query.equals(pattern)) {
            return true;
        }
        int j = 0;
        for (int i = 0; i < query.length(); i++) {
            if (j < pattern.length() && query.charAt(i) == pattern.charAt(j)) {
                j++;
            } else if (!Character.isLowerCase(query.charAt(i))) {
                return false;
            }
        }
        return j == pattern.length();
    }
}

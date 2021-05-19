package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 *
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 *
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * Constraints:
 * 1. 1 <= s.length <= 5 * 10^4
 * 2. t.length == s.length
 * 3. s and t consist of any valid ascii character.
 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("badc", "baba"));
    }

    /**
     * 每个单词之间的映射是固定的，因此可以使用 map 来存储映射关系，发现映射关系不匹配时直接返回 false
     * 注意，需要使用两个 map 分别保存 s 到 t 和 t 到 s 的映射，不然可能会产生 s 中的多个单词映射到 t 中的一个
     */
    public static boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character, Character> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map1.containsKey(s.charAt(i))) {
                map1.put(s.charAt(i), t.charAt(i));
            } else if (map1.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            }
            if (!map2.containsKey(t.charAt(i))) {
                map2.put(t.charAt(i), s.charAt(i));
            } else if (map2.get(t.charAt(i)) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}

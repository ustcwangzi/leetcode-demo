package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 * Example 1:
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 *
 * Example 2:
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 *
 * Example 3:
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 *
 * Example 4:
 * Input: pattern = "abba", s = "dog dog dog dog"
 * Output: false
 *
 * Constraints:
 * 1. 1 <= pattern.length <= 300
 * 2. pattern contains only lower-case English letters.
 * 3. 1 <= s.length <= 3000
 * 4. s contains only lower-case English letters and spaces ' '.
 * 5. s does not contain any leading or trailing spaces.
 * 6. All the words in s are separated by a single space.
 */
public class WordPattern {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog cat cat fish"));
        System.out.println(wordPattern("abcd", "dog cat cat fish"));
    }

    /**
     * 与 {@link IsomorphicStrings} 类似
     */
    public static boolean wordPattern(String pattern, String s) {
        int n = pattern.length();
        String[] array = s.split(" ");
        if (n != array.length) {
            return false;
        }
        Map<String, String> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String str1 = String.valueOf(pattern.charAt(i)), str2 = array[i];
            if (!map1.containsKey(str1)) {
                map1.put(str1, str2);
            } else if (!map1.get(str1).equals(str2)) {
                return false;
            }
            if (!map2.containsKey(str2)) {
                map2.put(str2, str1);
            } else if (!map2.get(str2).equals(str1)) {
                return false;
            }
        }
        return true;
    }
}

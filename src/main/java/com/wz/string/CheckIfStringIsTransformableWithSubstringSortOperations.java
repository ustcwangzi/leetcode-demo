package com.wz.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and t, you want to transform string s into string t using the following operation any number of times:
 * Choose a non-empty substring in s and sort it in-place so the characters are in ascending order.
 * For example, applying the operation on the underlined substring in "14234" results in "12344".
 * Return true if it is possible to transform string s into string t. Otherwise, return false.
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: s = "84532", t = "34852"
 * Output: true
 * Explanation: You can transform s into t using the following sort operations:
 * "84532" (from index 2 to 3) -> "84352"
 * "84352" (from index 0 to 2) -> "34852"
 *
 * Example 2:
 * Input: s = "34521", t = "23415"
 * Output: true
 * Explanation: You can transform s into t using the following sort operations:
 * "34521" -> "23451"
 * "23451" -> "23415"
 *
 * Example 3:
 * Input: s = "12345", t = "12435"
 * Output: false
 *
 * Constraints:
 * 1. s.length == t.length
 * 2. 1 <= s.length <= 105
 * 3. s and t only contain digits from '0' to '9'.
 */
public class CheckIfStringIsTransformableWithSubstringSortOperations {
    public static void main(String[] args) {
        System.out.println(isTransformable("12345", "12435"));
    }

    /**
     * t 中每个较小的数字位置不能早于 s 中的位置
     */
    public static boolean isTransformable(String s, String t) {
        Map<Integer, List<Integer>> map = new HashMap<>(10);
        int[] count = new int[10];
        for (int i = 0; i < 10; i++) {
            map.put(i, new ArrayList<>());
        }
        // s 中每个数字出现的位置
        for (int i = 0; i < s.length(); i++) {
            map.get(s.charAt(i) - '0').add(i);
        }
        for (int i = 0; i < t.length(); i++) {
            int d = t.charAt(i) - '0';
            // 数字出现次数不相等
            if (count[d] >= map.get(d).size()) {
                return false;
            }
            // position of d to move in s;
            int dpos = map.get(d).get(count[d]);
            // looping all prev smaller digit, make sure not moving smaller digit after dpos;
            for (int j = 0; j < d; j++) {
                if (count[j] < map.get(j).size() && map.get(j).get(count[j]) < dpos) {
                    return false;
                }
            }
            count[d]++;
        }
        return true;
    }
}

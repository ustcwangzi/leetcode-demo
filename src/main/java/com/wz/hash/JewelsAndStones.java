package com.wz.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 * Example 1:
 * Input: jewels = "aA", stones = "aAAbbbb"
 * Output: 3
 *
 * Example 2:
 * Input: jewels = "z", stones = "ZZ"
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= jewels.length, stones.length <= 50
 * 2. jewels and stones consist of only English letters.
 * 3. All the characters of jewels are unique.
 */
public class JewelsAndStones {
    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
    }

    /**
     * 使用 set 保存 jewels，然后遍历 stones，若出现在 set 中则结果增加
     */
    public static int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = new HashSet<>(jewels.length());
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }
        int result = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (set.contains(stones.charAt(i))) {
                result++;
            }
        }
        return result;
    }
}

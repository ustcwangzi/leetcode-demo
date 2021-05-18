package com.wz.hash;

import java.util.*;

/**
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings)
 * that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 * Example 1:
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 *
 * Example 2:
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences1("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(findRepeatedDnaSequences1("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    /**
     * 方案一：暴力求解
     * 使用 set 保存长度为 10 的字符串，如果再次出现则将该字符串加入结果中
     */
    public static List<String> findRepeatedDnaSequences1(String s) {
        Set<String> set = new HashSet<>(), result = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String cur = s.substring(i, i + 10);
            if (set.contains(cur)) {
                result.add(cur);
            } else {
                set.add(cur);
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 方案二：位运算
     * 将 A、C、G、T 分别使用 0、1、2、3 表示，其二进制分别是 00、01、10、11，即两位，那么长度为 10 时需要 20 位， int 能存下
     * 遍历字符串，每次提取出后 20 位，需要用个 mask，取值为 0xFFFFF，用此 mask 可取出后 20 位
     * 其他和方案一类似，使用 set 保存长度为 10 的字符串所代表的 int 值，如果再次出现则将该字符串加入结果中
     */
    public static List<String> findRepeatedDnaSequences2(String s) {
        Map<Character, Integer> map = new HashMap<>(4);
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        Set<Integer> set = new HashSet<>();
        Set<String> result = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = (sum << 2) + map.get(s.charAt(i));
            sum = sum & 0xFFFFF;
            if (i < 9) {
                continue;
            }

            if (set.contains(sum)) {
                result.add(s.substring(i - 9, i + 1));
            } else {
                set.add(sum);
            }
        }
        return new ArrayList<>(result);
    }
}

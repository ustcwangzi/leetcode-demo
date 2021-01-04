package com.wz.dfs;

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there won't be input like 3a or 2[4].
 *
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 *
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 *
 * Constraints:
 * 1. 1 <= s.length <= 30
 * 2. s consists of lowercase English letters, digits, and square brackets '[]'.
 * 3. s is guaranteed to be a valid input.
 * 4. All the integers in s are in the range [1, 300].
 */
public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }

    /**
     * 与 {@link com.wz.math.BasicCalculator} 类似
     * 遇到 []，就找 [] 的起点和终点，然后调用 dfs，直到传入到 dfs 的 s 不含有 []
     */
    public static String decodeString(String s) {
        return dfs(s, 0, s.length() - 1);
    }

    private static String dfs(String s, int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                builder.append(s.charAt(i));
            } else {
                // 取出数字，即重复次数
                int num = 0;
                while (i <= end && s.charAt(i) != '[') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }

                // 取出 [] 的起始点，起点是 k，终点是 i-1
                int count = 0, k = i + 1;
                while (i < end) {
                    if (s.charAt(i) == '[') {
                        count++;
                    } else if (s.charAt(i) == ']') {
                        count--;
                    }
                    if (count == 0) {
                        break;
                    }
                    i++;
                }
                String str = dfs(s, k, i - 1);
                builder.append(buildString(str, num));
            }
        }
        return builder.toString();
    }

    private static String buildString(String s, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(s);
        }
        return builder.toString();
    }
}

package com.wz.string;

/**
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it.
 * If it is impossible for to be a substring of a after repeating it, return -1.
 * Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and repeated 2 times is "abcabc".
 *
 * Example 1:
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
 *
 * Example 2:
 * Input: a = "a", b = "aa"
 * Output: 2
 *
 * Constraints:
 * 1. 1 <= a.length <= 104
 * 2. 1 <= b.length <= 104
 * 3. a and b consist of lower-case English letters.
 */
public class RepeatedStringMatch {
    public static void main(String[] args) {
        System.out.println(repeatedStringMatch("abcd", "cdabcdab"));
    }

    /**
     * builder 每次增加一个字符串A，最多需要增加的个数为 B 的长度除以 A 的长度再加上2
     * 因为当 B 小于 A 的时候，那么可能需要两个A，所以i就是小于等于2，每次在 builder 中找 B，如果找到了，就返回i，没找到，就增加一个 A
     */
    public static int repeatedStringMatch(String a, String b) {
        int n = b.length() / a.length() + 2;
        StringBuilder builder = new StringBuilder(a);
        for (int i = 1; i <= n; i++) {
            String res = builder.toString();
            if (res.length() >= b.length() && builder.toString().contains(b)) {
                return i;
            }
            builder.append(a);
        }
        return -1;
    }
}

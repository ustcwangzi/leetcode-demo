package com.wz.string;

/**
 * You are given a string s, return the number of segments in the string.
 * A segment is defined to be a contiguous sequence of non-space characters.
 *
 * Example 1:
 * Input: s = "Hello, my name is John"
 * Output: 5
 * Explanation: The five segments are ["Hello,", "my", "name", "is", "John"]
 *
 * Constraints:
 * 1. 0 <= s.length <= 300
 * 2. s consists of lower-case and upper-case English letters, digits or one of the following characters "!@#$%^&*()_+-=',.:".
 * 3. The only space character in s is ' '.
 */
public class NumberOfSegmentsInString {
    public static void main(String[] args) {
        System.out.println(countSegments("Hello, my name is John"));
    }

    /**
     * 用""拆分成数组之后进行遍历，统计其中不为空的个数
     */
    public static int countSegments(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        int count = 0;
        String[] arr = s.split(" ");
        for (String value : arr) {
            if (!value.isEmpty()) {
                count++;
            }
        }
        return count;
    }
}

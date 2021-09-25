package com.wz.other;

/**
 * Given a string s and an integer array indices of the same length.
 * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 * Return the shuffled string.
 *
 * Example 1:
 * @see ../../../../resource/ShuffleString.jpg
 * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 * Output: "leetcode"
 * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 *
 * Example 2:
 * Input: s = "abc", indices = [0,1,2]
 * Output: "abc"
 * Explanation: After shuffling, each character remains in its position.
 *
 * Constraints:
 * 1. s.length == indices.length == n
 * 2. 1 <= n <= 100
 * 3. s contains only lower-case English letters.
 * 4. 0 <= indices[i] < n
 * 5. All values of indices are unique (i.e. indices is a permutation of the integers from 0 to n - 1).
 */
public class ShuffleString {
    public static void main(String[] args) {
        System.out.println(restoreString("codeleet", new int[]{4, 5, 6, 7, 0, 2, 1, 3}));
    }

    /**
     * 使用数组依次保存 indices 所指示的每个字符
     */
    public static String restoreString(String s, int[] indices) {
        char[] array = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            array[indices[i]] = s.charAt(i);
        }
        return new String(array);
    }
}

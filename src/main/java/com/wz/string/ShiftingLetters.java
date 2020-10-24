package com.wz.string;

/**
 * We have a string S of lowercase letters, and an integer array shifts.
 * Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 * Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
 * Return the final string after all such shifts to S are applied.
 *
 * Example 1:
 * Input: S = "abc", shifts = [3,5,9]
 * Output: "rpl"
 * Explanation:
 * We start with "abc".
 * After shifting the first 1 letters of S by 3, we have "dbc".
 * After shifting the first 2 letters of S by 5, we have "igc".
 * After shifting the first 3 letters of S by 9, we have "rpl", the answer.
 *
 * Note:
 * 1. 1 <= S.length = shifts.length <= 20000
 * 2. 0 <= shifts[i] <= 10 ^ 9
 */
public class ShiftingLetters {
    public static void main(String[] args) {
        System.out.println(shiftingLetters("abc", new int[]{3, 5, 9}));
    }

    /**
     * 每次对前 i 个元素加上 shifts[i]
     * 使用 sum 保存加上 shifts[i] 之后的结果，然后逆序遍历每个字符，这样就保证前 i 个元素的结果中都包含了之前的累加和
     */
    public static String shiftingLetters(String S, int[] shifts) {
        char[] array = S.toCharArray();
        int sum = 0;
        for (int i = shifts.length - 1; i >= 0; i--) {
            sum = (sum + shifts[i]) % 26;
            array[i] = (char) ((array[i] + sum - 'a') % 26 + 'a');
        }
        return new String(array);
    }
}

package com.wz.stack;

/**
 * An encoded string S is given.  To find and write the decoded string to a tape,
 * the encoded string is read one character at a time and the following steps are taken:
 * 1. If the character read is a letter, that letter is written onto the tape.
 * 2. If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
 * Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.
 *
 * Example 1:
 * Input: S = "leet2code3", K = 10
 * Output: "o"
 * Explanation:
 * The decoded string is "leetleetcodeleetleetcodeleetleetcode".
 * The 10th letter in the string is "o".
 *
 * Example 2:
 * Input: S = "ha22", K = 5
 * Output: "h"
 * Explanation:
 * The decoded string is "hahahaha".  The 5th letter is "h".
 *
 * Constraints:
 * 1. 2 <= S.length <= 100
 * 2. S will only contain lowercase letters and digits 2 through 9.
 * 3. S starts with a letter.
 * 4. 1 <= K <= 10^9
 * 5. It's guaranteed that K is less than or equal to the length of the decoded string.
 * 6. The decoded string is guaranteed to have less than 2^63 letters.
 */
public class DecodedStringAtIndex {
    public static void main(String[] args) {
        System.out.println(decodeAtIndex("ha22", 5));
    }

    /**
     * 首先想到的是取余操作，比如 hahahaha，K = 5，其实和 （K % 2 = 1）是一样的，因此本题的解法就很明确了，就是每次对 K 进行取余操作，
     * 如果碰到了  K == size 的情况，也就是当前的字符扩展后正好长度为 K，那么直接返回这个字符即可（当然首先需要判断是否为字母）
     */
    public static String decodeAtIndex(String S, int K) {
        long size = 0;
        int n = S.length();
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(S.charAt(i))) {
                size *= (S.charAt(i) - '0');
            } else {
                size++;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            char cur = S.charAt(i);
            K %= size;
            if (K == 0 && Character.isLetter(cur)) {
                return Character.toString(cur);
            }
            if (Character.isDigit(cur)) {
                size /= (cur - '0');
            } else {
                size--;
            }
        }
        return null;
    }
}

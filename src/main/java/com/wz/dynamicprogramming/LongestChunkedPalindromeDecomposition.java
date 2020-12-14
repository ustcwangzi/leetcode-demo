package com.wz.dynamicprogramming;

/**
 * Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:
 * Each a_i is a non-empty string;
 * Their concatenation a_1 + a_2 + ... + a_k is equal to text;
 * For all 1 <= i <= k,  a_i = a_{k+1 - i}.
 *
 * Example 1:
 * Input: text = "ghiabcdefhelloadamhelloabcdefghi"
 * Output: 7
 * Explanation: We can split the string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
 *
 * Example 2:
 * Input: text = "merchant"
 * Output: 1
 * Explanation: We can split the string on "(merchant)".
 *
 * Example 3:
 * Input: text = "antaprezatepzapreanta"
 * Output: 11
 * Explanation: We can split the string on "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".
 *
 * Example 4:
 * Input: text = "aaa"
 * Output: 3
 * Explanation: We can split the string on "(a)(a)(a)".
 *
 * Constraints:
 * 1. text consists only of lowercase English characters.
 * 2. 1 <= text.length <= 1000
 */
public class LongestChunkedPalindromeDecomposition {
    public static void main(String[] args) {
        System.out.println(longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
    }

    /**
     * 贪心
     * 如果能找到最短的字符串的前缀和后缀，就将其拆分出来，这样迭代下去，一定是答案最大的
     * 也就是说，不存在一个因为拆分出了长度更小的段，而导致整体答案变小的情况
     * 所以可以通过枚举首尾的情况，来拆分字符串
     * 对于当前字符串，从小到大枚举长度 len 表示期望的相同的段的长度，直到当前字符串长度的一半，然后枚举判断前缀和后缀子串是否相同
     * 如果找到了一个相同的前后缀，则答案加 2，更新字符串重新迭代；否则答案加 1，退出迭代
     */
    public static int longestDecomposition(String text) {
        int n = text.length(), l = 0, r = n - 1;
        int result = 0;
        while (l <= r) {
            int length = r - l + 1;
            boolean flag = false;
            // 枚举每个长度 len，判断前缀和后缀子串是否相同
            for (int len = 1; len <= length / 2; len++) {
                if (check(l, r, len, text)) {
                    l += len;
                    r -= len;
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                result++;
                break;
            } else {
                result += 2;
            }
        }
        return result;
    }

    private static boolean check(int l, int r, int len, String text) {
        for (int i = l, j = r - len + 1; j <= r; i++, j++) {
            if (text.charAt(i) != text.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}

package com.wz.other;

/**
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 *
 * Example 1:
 * @link ../../../../resource/MinimumFlipsToMakeAORBEqualToC.jpg
 * Input: a = 2, b = 6, c = 5
 * Output: 3
 * Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
 *
 * Example 2:
 * Input: a = 4, b = 2, c = 7
 * Output: 1
 *
 * Example 3:
 * Input: a = 1, b = 2, c = 3
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= a <= 10^9
 * 2. 1 <= b <= 10^9
 * 3. 1 <= c <= 10^9
 */
public class MinimumFlipsToMakeAORBEqualToC {
    public static void main(String[] args) {
        System.out.println(minFlips(2, 6, 5));
    }

    /**
     * 位移
     * 对于某一位 i，想得到 a[i] | b[i] == c[i]
     * 若 c[i] == 1，则需要 a[i] 或 b[i] 至少有一个为 1
     * 若 c[i] == 0，则需要 a[i]、b[i] 全为 0
     * 对 a、b、c 每次进行右移一位，并获取最后一位的值，然后进行判断即可
     */
    public static int minFlips(int a, int b, int c) {
        int count = 0;
        while (a > 0 || b > 0 || c > 0) {
            int bitA = a & 1, bitB = b & 1, bitC = c & 1;
            if (bitC == 1) {
                // bitA 和 bitB 至少有一个是 1øø
                if (bitA != 1 && bitB != 1) {
                    count++;
                }
            } else {
                // bitA 和 bitB 都需要是 0
                if (bitA != 0) {
                    count++;
                }
                if (bitB != 0) {
                    count++;
                }
            }
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return count;
    }
}

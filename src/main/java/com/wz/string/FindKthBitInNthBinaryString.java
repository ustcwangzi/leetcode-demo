package com.wz.string;

/**
 * Given two positive integers n and k, the binary string  Sn is formed as follows:
 * 1. S1 = "0"
 * 2. Si = Si-1 + "1" + reverse(invert(Si-1)) for i > 1
 * Where + denotes the concatenation operation, reverse(x) returns the reversed string x,
 * and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
 * For example, the first 4 strings in the above sequence are:
 * 1. S1 = "0"
 * 2. S2 = "011"
 * 3. S3 = "0111001"
 * 4. S4 = "011100110110001"
 * Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 *
 * Example 1:
 * Input: n = 3, k = 1
 * Output: "0"
 * Explanation: S3 is "0111001". The first bit is "0".
 *
 * Example 2:
 * Input: n = 4, k = 11
 * Output: "1"
 * Explanation: S4 is "011100110110001". The 11th bit is "1".
 *
 * Constraints:
 * 1. 1 <= n <= 20
 * 2. 1 <= k <= 2n - 1
 */
public class FindKthBitInNthBinaryString {
    public static void main(String[] args) {
        System.out.println(findKthBit(3, 1));
    }

    public static char findKthBit(int n, int k) {
        String[] array = new String[n + 1];
        array[1] = "0";
        return nthString(array, n).charAt(k - 1);
    }

    private static String nthString(String[] array, int n) {
        if (array[n] != null) {
            return array[n];
        }

        String result = nthString(array, n - 1) + "1" + invertAndReverse(nthString(array, n - 1));
        array[n] = result;
        return result;
    }

    private static String invertAndReverse(String s) {
        StringBuilder builder = new StringBuilder();
        for (char cur : s.toCharArray()) {
            builder.append(cur == '0' ? 1 : 0);
        }
        return builder.reverse().toString();
    }
}

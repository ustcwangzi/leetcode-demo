package com.wz.other;

import java.util.Arrays;

/**
 * There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.
 * It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1].
 * For example, if perm = [1,3,2], then encoded = [2,1].
 * Given the encoded array, return the original array perm. It is guaranteed that the answer exists and is unique.
 *
 * Example 1:
 * Input: encoded = [3,1]
 * Output: [1,2,3]
 * Explanation: If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]
 *
 * Example 2:
 * Input: encoded = [6,5,4,6]
 * Output: [2,4,1,5,3]
 *
 * Constraints:
 * 1. 3 <= n < 10^5
 * 2. n is odd.
 * 3. encoded.length == n - 1
 */
public class DecodeXORedPermutation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(decode(new int[]{3, 1})));
    }

    /**
     * 是对 {@link DecodeXORedArray} 是扩展，{@link DecodeXORedArray} 给出了第一个元素，而本题没给，因此需要先求出第一个元素
     * encoded[1] = perm[1] ^ perm[2]
     * encoded[3] = perm[3] ^ perm[4]
     * encoded[5] = perm[5] ^ perm[6]
     * ...
     * => encoded[1] ^ encoded[3] ... encoded[n-1] = perm[1] ^ perm[2] ^ ... ^ perm[n]
     * => encoded[1] ^ encoded[3] ... encoded[n-1] ^ perm[0] = perm[1] ^ perm[2] ^ ... ^ perm[n] ^ perm[0]
     * => encoded[1] ^ encoded[3] ... encoded[n-1] ^ perm[0] = 1 ^ 2 ^ ... ^ (n+1)
     * => perm[0] = encoded[1] ^ encoded[3] ... encoded[n-1] ^ 1 ^ 2 ^ ... ^ (n+1)
     * 即第一个元素 = encoded 中所有奇数位置元素异或值 ^ 1～n+1 所有元素异或值
     * 求出第一个元素之后，按照 {@link DecodeXORedArray} 的思路依次求出其他位置元素即可
     */
    public static int[] decode(int[] encoded) {
        int n = encoded.length;
        int allXOR = 0, oddXOR = 0;
        for (int i = 1; i <= n + 1; i++) {
            allXOR ^= i;
        }
        for (int i = 1; i < n; i += 2) {
            oddXOR ^= encoded[i];
        }

        int[] result = new int[n + 1];
        result[0] = allXOR ^ oddXOR;
        for (int i = 0; i < n; i++) {
            result[i + 1] = encoded[i] ^ result[i];
        }
        return result;
    }
}

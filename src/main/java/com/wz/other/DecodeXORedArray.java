package com.wz.other;

import java.util.Arrays;

/**
 * There is a hidden integer array arr that consists of n non-negative integers.
 * It was encoded into another integer array encoded of length n - 1, such that encoded[i] = arr[i] XOR arr[i + 1].
 * For example, if arr = [1,0,2,1], then encoded = [1,2,3].
 * You are given the encoded array. You are also given an integer first, that is the first element of arr, i.e. arr[0].
 * Return the original array arr. It can be proved that the answer exists and is unique.
 *
 * Example 1:
 * Input: encoded = [1,2,3], first = 1
 * Output: [1,0,2,1]
 * Explanation: If arr = [1,0,2,1], then first = 1 and encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 *
 * Example 2:
 * Input: encoded = [6,2,7,3], first = 4
 * Output: [4,2,0,7,4]
 *
 * Constraints:
 * 1. 2 <= n <= 10^4
 * 2. encoded.length == n - 1
 * 3. 0 <= encoded[i] <= 10^5
 * 4. 0 <= first <= 10^5
 */
public class DecodeXORedArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(decode(new int[]{1, 2, 3}, 1)));
    }

    /**
     * encoded[i] = arr[i] ^ arr[i+1]
     * => encoded[i] ^ arr[i] = arr[i] ^ arr[i+1] ^ arr[i]
     * => encoded[i] ^ arr[i] = arr[i+1] ^ 0
     * => encoded[i] ^ arr[i] = arr[i+1]
     */
    public static int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] result = new int[n + 1];
        result[0] = first;
        for (int i = 0; i < n; i++) {
            result[i + 1] = encoded[i] ^ result[i];
        }
        return result;
    }
}

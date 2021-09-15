package com.wz.other;

import java.util.Arrays;

/**
 * Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri],
 * for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ).
 * Return an array containing the result for the given queries.
 *
 * Example 1:
 * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * Output: [2,7,14,8]
 * Explanation:
 * The binary representation of the elements in the array are:
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * The XOR values for queries are:
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 *
 * Example 2:
 * Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * Output: [8,0,4,4]
 *
 * Constraints:
 * 1. 1 <= arr.length <= 3 * 10^4
 * 2. 1 <= arr[i] <= 10^9
 * 3. 1 <= queries.length <= 3 * 10^4
 * 4. queries[i].length == 2
 * 5. 0 <= queries[i][0] <= queries[i][1] < arr.length
 */
public class XORQueriesOfSubarray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(xorQueries(new int[]{1, 3, 4, 8}, new int[][]{{0, 1}, {1, 2}, {0, 3}, {3, 3}})));
    }

    /**
     * 可以使用前缀和 preSum 的思想 {@link com.wz.array.SumOfAllOddLengthSubarrays}
     * preXor[i+1] 表示 arr[0...i] 异或结果，由于一个数与自身异或值为 0，因此 preXor[i] ^ preXor[j+1] 结果就是 arr[i...j] 的异或
     * preXor[i] ^ preXor[j+1] = arr[0...i-1] ^ arr[0...j]，前面相同的数抵消为 0，只剩下 arr[i...j] 的异或
     */
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int[] preXor = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            preXor[i + 1] = arr[i] ^ preXor[i];
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = preXor[queries[i][0]] ^ preXor[queries[i][1] + 1];
        }
        return result;
    }
}

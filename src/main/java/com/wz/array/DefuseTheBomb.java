package com.wz.array;

import java.util.Arrays;

/**
 * You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.
 * To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.
 * 1. If k > 0, replace the ith number with the sum of the next k numbers.
 * 2. If k < 0, replace the ith number with the sum of the previous k numbers.
 * 3. If k == 0, replace the ith number with 0.
 * As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].
 * Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!
 *
 * Example 1:
 * Input: code = [5,7,1,4], k = 3
 * Output: [12,10,16,13]
 * Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.
 *
 * Example 2:
 * Input: code = [1,2,3,4], k = 0
 * Output: [0,0,0,0]
 * Explanation: When k is zero, the numbers are replaced by 0.
 *
 * Example 3:
 * Input: code = [2,4,9,3], k = -2
 * Output: [12,5,6,13]
 * Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the previous numbers.
 *
 * Constraints:
 * 1. n == code.length
 * 2. 1 <= n <= 100
 * 3. 1 <= code[i] <= 100
 * 4. -(n - 1) <= k <= n - 1
 */
public class DefuseTheBomb {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(decrypt(new int[]{5, 7, 1, 4}, 3)));
        System.out.println(Arrays.toString(decrypt(new int[]{2, 4, 9, 3}, -2)));
    }

    /**
     * k == 0，直接返回全 0 数组
     * k > 0，从左向右进行赋值，对 n 取余以免超过数组边界
     * k < 0，从右向左进行赋值，加 n 后对 n 取余用来取到后面的值
     */
    public static int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if (k == 0) {
            return result;
        }
        if (k > 0) {
            int sum = 0, j = 0;
            for (int i = 0; i < n; i++) {
                while (j <= i + k) {
                    sum += code[j % n];
                    j++;
                }
                // 不能包含当前值
                sum -= code[i];
                result[i] = sum;
            }
        } else {
            int sum = 0, j = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                while (j >= i + k) {
                    sum += code[(j + n) % n];
                    j--;
                }
                // 不能包含当前值
                sum -= code[i];
                result[i] = sum;
            }
        }
        return result;
    }
}

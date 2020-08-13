package com.wz.array;

import java.util.Arrays;

/**
 * We have an array A of integers, and an array queries of queries.
 * For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].
 * Then, the answer to the i-th query is the sum of the even values of A.
 * (Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)
 * Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.
 *
 * Example 1:
 * Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
 * Output: [8,6,2,4]
 * Explanation:
 * At the beginning, the array is [1,2,3,4].
 * After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
 * After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
 * After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
 * After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
 */
public class SumOfEvenNumbersAfterQueries {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 4};
        int[][] queries = new int[][]{{1, 0}, {-3, 1}, {-4, 0}, {2, 3}};
        System.out.println(Arrays.toString(sumEvenAfterQueries(A, queries)));
    }

    /**
     * 最终结果为每次的偶数之和，因为 偶数 + 偶数 == 偶数，偶数 + 奇数 == 奇数，奇数 + 奇数 == 偶数
     * 根据这一性质，可以先计算出原数组偶数之和 sum，然后假设本次向 x 增加 y，那么分为以下几种情况：
     * 1. x 为偶数， y 为偶数， 此时 x 已包含在 sum 中，直接将 y 加到 sum 即可， sum += y
     * 2. x 为偶数， y 为奇数， 此时需要将 x 从 sum 中移除，sum -= x
     * 3. x 为奇数， y 为奇数， 此时 x 不包含在 sum 中，将 x 和 y 都加到 sum， sum += x + y
     */
    public static int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] result = new int[A.length];
        int sum = 0;
        for (int cur : A) {
            sum += cur % 2 == 0 ? cur : 0;
        }

        int i = 0;
        for (int[] query : queries) {
            int index = query[1], value = query[0];
            boolean originEven = A[index] % 2 == 0, queryEven = value % 2 == 0;
            if (originEven && queryEven) {
                sum += value;
            } else if (originEven) {
                sum -= A[index];
            } else if (!queryEven) {
                sum += A[index] + value;
            }
            A[index] += value;
            result[i++] = sum;
        }
        return result;
    }
}

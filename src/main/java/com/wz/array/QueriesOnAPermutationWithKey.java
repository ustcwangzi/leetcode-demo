package com.wz.array;

import java.util.Arrays;

/**
 * Given the array queries of positive integers between 1 and m, you have to process all queries[i]
 * (from i=0 to i=queries.length-1) according to the following rules:
 * In the beginning, you have the permutation P=[1,2,3,...,m].
 * For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at
 * the beginning of the permutation P. Notice that the position of queries[i] in P is the result for queries[i].
 * Return an array containing the result for the given queries.
 *
 * Example 1:
 * Input: queries = [3,1,2,1], m = 5
 * Output: [2,1,2,1]
 * Explanation: The queries are processed as follow:
 * For i=0: queries[i]=3, P=[1,2,3,4,5], position of 3 in P is 2, then we move 3 to the beginning of P resulting in P=[3,1,2,4,5].
 * For i=1: queries[i]=1, P=[3,1,2,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,3,2,4,5].
 * For i=2: queries[i]=2, P=[1,3,2,4,5], position of 2 in P is 2, then we move 2 to the beginning of P resulting in P=[2,1,3,4,5].
 * For i=3: queries[i]=1, P=[2,1,3,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,2,3,4,5].
 * Therefore, the array containing the result is [2,1,2,1].
 *
 * Example 2:
 * Input: queries = [4,1,2,2], m = 4
 * Output: [3,1,2,0]
 *
 * Example 3:
 * Input: queries = [7,5,5,8,3], m = 8
 * Output: [6,5,0,7,5]
 */
public class QueriesOnAPermutationWithKey {
    public static void main(String[] args) {
        int[] queries = new int[]{3, 1, 2, 1};
        System.out.println(Arrays.toString(processQueries(queries, 5)));

        queries = new int[]{4, 1, 2, 2};
        System.out.println(Arrays.toString(processQueries(queries, 4)));

        queries = new int[]{7, 5, 5, 8, 3};
        System.out.println(Arrays.toString(processQueries(queries, 8)));
    }

    /**
     * 查找 queries[i] 在 permutation 中的位置 index，然后将 index 的值移到 permutation 的首位
     */
    public static int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        int[] result = new int[n];
        int[] permutation = new int[m];
        for (int i = 0; i < m; i++) {
            permutation[i] = i + 1;
        }

        for (int i = 0; i < queries.length; i++) {
            int index = findIndex(permutation, queries[i]);
            result[i] = index;
            swap(permutation, index);
        }

        return result;
    }

    /**
     * 查找 target 在 array 中的位置
     */
    private static int findIndex(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 将 index 的值移到 array 的首位
     */
    private static void swap(int[] array, int index) {
        int value = array[index];
        for (int i = index; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = value;
    }
}

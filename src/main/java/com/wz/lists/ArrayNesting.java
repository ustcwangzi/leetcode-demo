package com.wz.lists;

/**
 * A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S,
 * where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.
 * Suppose the first element in S starts with the selection of element A[i] of index = i,
 * the next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.
 *
 * Example 1:
 * Input: A = [5,4,0,3,1,6,2]
 * Output: 4
 * Explanation:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * One of the longest S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 */
public class ArrayNesting {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 0, 3, 1, 6, 2};
        System.out.println(arrayNesting(nums));
    }

    /**
     * 找嵌套数组的最大个数，给的数组总共有n个数字，范围均在[0, n-1]之间，只要找到和起始位置相同的数字位置，就可以结束本次查找
     * 遍历过程中，用visited数组记录已访问的位置，如果起始的元素已经被遍历过了，那么就不用再遍历
     */
    public static int arrayNesting(int[] nums) {
        int result = 0;
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (visited[nums[i]]) {
                continue;
            }

            // dfs，直到找到和本次遍历起始位置相同的元素
            int j = i, count = 0;
            while (count == 0 || j != i) {
                visited[i] = true;
                count++;
                j = nums[j];
            }

            result = Math.max(result, count);
        }

        return result;
    }
}

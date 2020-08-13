package com.wz.array;

/**
 * Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot,
 * and two sightseeing spots i and j have distance j - i between them.
 * The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) :
 * the sum of the values of the sightseeing spots, minus the distance between them.
 * Return the maximum score of a pair of sightseeing spots.
 *
 * Example 1:
 * Input: [8,1,5,2,6]
 * Output: 11
 * Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 */
public class BestSightseeingPair {
    public static void main(String[] args) {
        int[] A = new int[]{8, 1, 5, 2, 6};
        System.out.println(maxScoreSightseeingPair(A));
    }

    /**
     * 按照题意，需要找到尽可能高的两座山，且其距离越短越好
     * 假设已找到目前为止最高的山高度为 cur，再找另一座山的时候，随着距离增大，cur会不断减小，
     * 最终结果 result = cur 减小之后 + 新找到的山高
     */
    public static int maxScoreSightseeingPair(int[] A) {
        int result = 0, cur = 0;
        for (int num : A) {
            result = Math.max(result, cur + num);
            cur = Math.max(cur, num) - 1;
        }

        return result;
    }
}

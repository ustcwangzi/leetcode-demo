package com.wz.array;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * If it cannot be done, return -1.
 *
 * Example 1:
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 *
 * Example 2:
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 */
public class MinimumDominoRotationsForEqualRow {
    public static void main(String[] args) {
        int[] A = new int[]{2, 1, 2, 4, 2, 2}, B = new int[]{5, 2, 6, 2, 3, 2};
        System.out.println(minDominoRotations(A, B));

        A = new int[]{3, 5, 1, 2, 3};
        B = new int[]{3, 6, 3, 3, 4};
        System.out.println(minDominoRotations(A, B));
    }

    /**
     * A, B分别代表每个牌的正反面，翻转其中的一部分骨牌，使得正反面对调，能不能让A或者B出现完全相同的点数
     * 要实现某一面都是相同的点数，只需要将A[0]和B[0]作为基准即可。
     * 假设A[0]和B[0]都不能作为这个相同的点数，那么说明通过翻转不能实现某一面都是相同的点数。
     * 分别以A[0]和B[0]作为基准，统计与其不相等的元素个数，然后返回最小值即可
     */
    public static int minDominoRotations(int[] A, int[] B) {
        // 以A[0]作为基准
        for (int i = 0, ra = 0, rb = 0; i < A.length && (A[i] == A[0] || B[i] == A[0]); i++) {
            if (A[i] != A[0]) {
                ra++;
            }
            if (B[i] != A[0]) {
                rb++;
            }
            if (i == A.length - 1) {
                return Math.min(ra, rb);
            }
        }

        // 以B[0]作为基准
        for (int i = 0, ra = 0, rb = 0; i < A.length && (A[i] == B[0] || B[i] == B[0]); i++) {
            if (A[i] != B[0]) {
                ra++;
            }
            if (B[i] != B[0]) {
                rb++;
            }
            if (i == A.length - 1) {
                return Math.min(ra, rb);
            }
        }

        return -1;
    }
}

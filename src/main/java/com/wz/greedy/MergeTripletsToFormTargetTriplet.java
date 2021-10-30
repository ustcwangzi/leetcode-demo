package com.wz.greedy;

/**
 * A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the ith triplet.
 * You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.
 * To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
 * Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
 * For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.
 *
 * Example 1:
 * Input: triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
 * Output: true
 * Explanation: Perform the following operations:
 * - Choose the first and last triplets [[2,5,3],[1,8,4],[1,7,5]].
 *   Update the last triplet to be [max(2,1), max(5,7), max(3,5)] = [2,7,5]. triplets = [[2,5,3],[1,8,4],[2,7,5]]
 * The target triplet [2,7,5] is now an element of triplets.
 *
 * Example 3:
 * Input: triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
 * Output: true
 * Explanation: Perform the following operations:
 * - Choose the first and third triplets [[2,5,3],[2,3,4],[1,2,5],[5,2,3]].
 *   Update the third triplet to be [max(2,1), max(5,2), max(3,5)] = [2,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,2,3]].
 * - Choose the third and fourth triplets [[2,5,3],[2,3,4],[2,5,5],[5,2,3]].
 *   Update the fourth triplet to be [max(2,5), max(5,2), max(5,3)] = [5,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,5,5]].
 * The target triplet [5,5,5] is now an element of triplets.
 *
 * Constraints:
 * 1. 1 <= triplets.length <= 10^5
 * 2. triplets[i].length == target.length == 3
 * 3. 1 <= ai, bi, ci, x, y, z <= 1000
 */
public class MergeTripletsToFormTargetTriplet {
    public static void main(String[] args) {
        System.out.println(mergeTriplets(new int[][]{{2, 5, 3}, {2, 3, 4}, {1, 2, 5}, {5, 2, 3}}, new int[]{5, 5, 5}));
    }

    /**
     * 不能选取任意元素超过 target 对应数值的三元组参与更新，否则会导致所得到的组失效
     * 对所有值均在 target 范围内的三元组，即使对应值较小，参与更新后也不会对最终得到的组产生影响
     * 因此，可使用贪心方法，选取目标范围内的所有三元组参与更新，再判断最终结果是否得到了目标组
     */
    public static boolean mergeTriplets(int[][] triplets, int[] target) {
        int a = 0, b = 0, c = 0;
        for (int[] triplet : triplets) {
            if (triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]) {
                a = Math.max(a, triplet[0]);
                b = Math.max(b, triplet[1]);
                c = Math.max(c, triplet[2]);
            }
        }
        return a == target[0] && b == target[1] && c == target[2];
    }
}

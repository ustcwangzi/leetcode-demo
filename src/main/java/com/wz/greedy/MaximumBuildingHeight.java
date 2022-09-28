package com.wz.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.
 * However, there are city restrictions on the heights of the new buildings:
 * - The height of each building must be a non-negative integer.
 * - The height of the first building must be 0.
 * - The height difference between any two adjacent buildings cannot exceed 1.
 * Additionally, there are city restrictions on the maximum height of specific buildings.
 * These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti]
 * indicates that building idi must have a height less than or equal to maxHeighti.
 * It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.
 * Return the maximum possible height of the tallest building.
 *
 * Example 1:
 * @link ../../../../resource/MaximumBuildingHeight1.jpg
 * Input: n = 5, restrictions = [[2,1],[4,1]]
 * Output: 2
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.
 *
 * Example 2:
 * @link ../../../../resource/MaximumBuildingHeight2.jpg
 * Input: n = 6, restrictions = []
 * Output: 5
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.
 *
 * Example 3:
 * @link ../../../../resource/MaximumBuildingHeight3.jpg
 * Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
 * Output: 5
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.
 *
 * Constraints:
 * 1. 2 <= n <= 10^9
 * 2. 0 <= restrictions.length <= min(n - 1, 10^5)
 * 3. 2 <= idi <= n
 * 4. idi is unique.
 * 5. 0 <= maxHeighti <= 10^9
 */
public class MaximumBuildingHeight {
    public static void main(String[] args) {
        System.out.println(maxBuilding(5, new int[][]{{2, 1}, {4, 1}}));
        System.out.println(maxBuilding(6, new int[][]{}));
        System.out.println(maxBuilding(10, new int[][]{{5, 3}, {2, 5}, {7, 4}, {10, 3}}));
    }

    /**
     * 每一栋建筑在数组 restrictions 中最多只会出现一次，为了叙述方便，将限制表示为 (i, hi)，表示建筑 i 的高度不能超过 hi，
     * 虽然 (i, hi) 是限制在建筑 i 之上的，但实际上，该限制也会对其余的建筑产生影响，因为相邻建筑的高度差不能超过 1，
     * 因此：建筑 i - 1 的高度不能超过 hi + 1、建筑 i + 1 的高度不能超过 hi + 1，即建筑 j 的高度不能超过 hi + |i-j|。
     * 每一个限制 (i, hi) 实际上是对所有 n 栋建筑的限制，如果通过某种方法将每一个限制传递开来，得到对第 i 栋建筑的真正的最低限制，
     * 记为 limitI，那么第 i 栋建筑的高度不能超过 limitI。
     * 如果有两栋建筑 i 和 j，满足 i < j 并且它们之间没有其它出现在限制数组里面的建筑，那么根据限制的传递性，
     * i 到 j 之间建筑的高度应该形如一座山脉，即从建筑 i 开始，高度单调递增到达最大值，再单调递减到达建筑 j。
     * 假设这个最大值为 best(i,j)，那么需要满足：
     * (best(i,j) - limitI) + (best(i,j) - limitJ) ≤ j − i
     * 得到：best(i,j) = (j − i + limitI + limitJ) / 2
     * 思路与算法
     * 首先需要求出所有的 limitI，为了方便处理边界情况，在限制数组中增加 (1, 0) 和 (n, n-1) 这两个限制，并将限制数组根据建筑编号升序排序
     * 然后对限制数组进行两次遍历，第一次遍历将限制从左向右传递，第二次遍历将限制从右向左传递：
     * 在从左向右传递的过程中，对于在限制数组中相邻的两项 (i, hi)、(j, hj)，限制 (i, hi) 传递到第 j 栋建筑会变为 (j, hi + (j - i))，
     * 只需要将 hj 更新为其和 hi + (j - i) 中的较小值，就可以将第 j 栋建筑左侧的所有限制传递过来；
     * 在从右向左传递的过程中，对于在限制数组中相邻的两项 (i, hi)、(j, hj)，限制 (j, hj) 传递到第 i 栋建筑会变为 (i, hj + (j - i))，
     * 只需要将 hi 更新为其和 hj + (j - i) 中的较小值，就可以将第 i 栋建筑右侧的所有限制传递过来。
     * 在这之后，所有的 hi 即为 limitI，再根据 best(i,j) = (j − i + limitI + limitJ) / 2 求出最大值，即可得到最终的答案
     */
    public static int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, Comparator.comparingInt(a -> a[0]));
        int m = restrictions.length + 2;
        int[][] array = new int[m][2];
        array[0] = new int[]{1, 0};
        array[m - 1] = new int[]{n, n - 1};
        System.arraycopy(restrictions, 0, array, 1, m - 2);

        // 从左向右传递限制
        for (int i = 1; i < m; ++i) {
            array[i][1] = Math.min(array[i][1], array[i - 1][1] + (array[i][0] - array[i - 1][0]));
        }
        // 从右向左传递限制
        for (int i = m - 2; i >= 0; --i) {
            array[i][1] = Math.min(array[i][1], array[i + 1][1] + (array[i + 1][0] - array[i][0]));
        }

        int result = 0;
        for (int i = 0; i < m - 1; ++i) {
            // 计算 r[i][0] 和 r[i][1] 之间的建筑的最大高度
            int best = (array[i + 1][0] - array[i][0] + array[i][1] + array[i + 1][1]) / 2;
            result = Math.max(result, best);
        }

        return result;
    }
}

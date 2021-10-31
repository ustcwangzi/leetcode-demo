package com.wz.other;

import java.util.Arrays;

/**
 * The minimum absolute difference of an array a is defined as the minimum value of |a[i] - a[j]|, where 0 <= i < j < a.length and a[i] != a[j].
 * If all elements of a are the same, the minimum absolute difference is -1.
 * For example, the minimum absolute difference of the array [5,2,3,7,2] is |2 - 3| = 1. Note that it is not 0 because a[i] and a[j] must be different.
 * You are given an integer array nums and the array queries where queries[i] = [li, ri]. For each query i,
 * compute the minimum absolute difference of the subarray nums[li...ri] containing the elements of nums between the 0-based indices li and ri (inclusive).
 * Return an array ans where ans[i] is the answer to the ith query.
 * A subarray is a contiguous sequence of elements in an array.
 *
 * The value of |x| is defined as:
 * x if x >= 0.
 * -x if x < 0.
 *
 * Example 1:
 * Input: nums = [1,3,4,8], queries = [[0,1],[1,2],[2,3],[0,3]]
 * Output: [2,1,4,1]
 * Explanation: The queries are processed as follows:
 * - queries[0] = [0,1]: The subarray is [1,3] and the minimum absolute difference is |1-3| = 2.
 * - queries[1] = [1,2]: The subarray is [3,4] and the minimum absolute difference is |3-4| = 1.
 * - queries[2] = [2,3]: The subarray is [4,8] and the minimum absolute difference is |4-8| = 4.
 * - queries[3] = [0,3]: The subarray is [1,3,4,8] and the minimum absolute difference is |3-4| = 1.
 *
 * Example 2:
 * Input: nums = [4,5,2,2,7,10], queries = [[2,3],[0,2],[0,5],[3,5]]
 * Output: [-1,1,1,3]
 * Explanation: The queries are processed as follows:
 * - queries[0] = [2,3]: The subarray is [2,2] and the minimum absolute difference is -1 because all the
 *   elements are the same.
 * - queries[1] = [0,2]: The subarray is [4,5,2] and the minimum absolute difference is |4-5| = 1.
 * - queries[2] = [0,5]: The subarray is [4,5,2,2,7,10] and the minimum absolute difference is |4-5| = 1.
 * - queries[3] = [3,5]: The subarray is [2,7,10] and the minimum absolute difference is |7-10| = 3.
 *
 * Constraints:
 * 1. 2 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 100
 * 3. 1 <= queries.length <= 2 * 10^4
 * 4. 0 <= li < ri < nums.length
 */
public class MinimumAbsoluteDifferenceQueries {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(minDifference(new int[]{4, 5, 2, 2, 7, 10}, new int[][]{{2, 3}, {0, 2}, {0, 5}, {3, 5}})));
    }

    /**
     * 因为 num 的取值范围是 [1,100]，因此可使用前缀数组统计每个元素在区间内的次数
     * 然后遍历 queries，再从小到大遍历 [1,100]，若元素出现则计算最小的绝对值
     */
    public static int[] minDifference(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        // preCount[i][j] 表示 j 在 nums[0...i) 区间内出现的次数
        int[][] preCount = new int[n + 1][101];
        for (int i = 0; i < n; i++) {
            preCount[i + 1] = Arrays.copyOf(preCount[i], 101);
            preCount[i + 1][nums[i]]++;
        }

        int[] result = new int[m];
        Arrays.fill(result, 100);
        for (int i = 0; i < m; i++) {
            // 记录当前区间内出现的上一个的元素
            int lastNum = 0;
            for (int num = 1; num <= 100; num++) {
                // 说明 num 存在与该区间
                if (preCount[queries[i][1] + 1][num] - preCount[queries[i][0]][num] > 0) {
                    if (lastNum != 0) {
                        result[i] = Math.min(result[i], num - lastNum);
                    }
                    lastNum = num;
                }
            }
            result[i] = (result[i] == 100) ? -1 : result[i];
        }
        return result;
    }
}

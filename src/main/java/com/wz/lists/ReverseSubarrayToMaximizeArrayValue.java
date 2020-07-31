package com.wz.lists;

/**
 * You are given an integer array nums. The value of this array is defined as the sum of |nums[i]-nums[i+1]| for all 0 <= i < nums.length-1.
 * You are allowed to select any subarray of the given array and reverse it. You can perform this operation only once.
 * Find maximum possible value of the final array.
 *
 * Example 1:
 * Input: nums = [2,3,1,5,4]
 * Output: 10
 * Explanation: By reversing the subarray [3,1,5] the array becomes [2,5,1,3,4] whose value is 10.
 *
 * Example 2:
 * Input: nums = [2,4,9,24,2,1,10]
 * Output: 68
 */
public class ReverseSubarrayToMaximizeArrayValue {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 5, 4};
        System.out.println(maxValueAfterReverse(nums));

        nums = new int[]{2, 4, 9, 24, 2, 1, 10};
        System.out.println(maxValueAfterReverse(nums));
    }

    /**
     * 对于 ...a, [b, ... , c], d, ...，如果将其中的 [b, ... , c] 进行翻转
     * 如果区间 [min(a, b), max(a, b)] 与 [min(c, d), max(c, d)] 不相交，则交换 b 和 c 的值能带来增量
     * 用数学的公式表示就是 |a-b| + |c-d| < |a-c| + |b-d| 当且仅当这两个区间不相交
     * 假设 min(c, d) > max(a, b)，则交换所带来的增量为 diff = 2 * (min(c, d) - max(a, b))
     * 所以现在的目标就是让 diff 最大
     * 另外，还需要考虑翻转点在边界的情况
     */
    public static int maxValueAfterReverse(int[] nums) {
        int result = 0, n = nums.length, diff = 0;
        for (int i = 0; i < n - 1; i++) {
            result += Math.abs(nums[i + 1] - nums[i]);
        }

        // 端点不在两顶点
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            min = Math.min(min, Math.max(nums[i], nums[i + 1]));
            max = Math.max(max, Math.min(nums[i], nums[i + 1]));
        }
        diff = Math.max(diff, 2 * (max - min));

        // 一端点在左顶点
        for (int i = 1; i < n - 1; i++) {
            diff = Math.max(diff, Math.abs(nums[i + 1] - nums[0]) - Math.abs(nums[i + 1] - nums[i]));
        }

        // 一端点在右顶点
        for (int i = 1; i < n - 1; i++) {
            diff = Math.max(diff, Math.abs(nums[i - 1] - nums[n - 1]) - Math.abs(nums[i - 1] - nums[i]));
        }

        return result + diff;
    }
}

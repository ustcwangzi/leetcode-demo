package com.wz.other;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a 2D integer array nums where nums[i] is a non-empty array of distinct positive integers,
 * return the list of integers that are present in each array of nums sorted in ascending order.
 *
 * Example 1:
 * Input: nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
 * Output: [3,4]
 * Explanation:
 * The only integers present in each of nums[0] = [3,1,2,4,5], nums[1] = [1,2,3,4], and nums[2] = [3,4,5,6] are 3 and 4, so we return [3,4].
 *
 * Example 2:
 * Input: nums = [[1,2,3],[4,5,6]]
 * Output: []
 * Explanation:
 * There does not exist any integer present both in nums[0] and nums[1], so we return an empty list [].
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= sum(nums[i].length) <= 1000
 * 3. 1 <= nums[i][j] <= 1000
 * 4. All the values of nums[i] are unique.
 */
public class IntersectionOfMultipleArrays {
    public static void main(String[] args) {
        System.out.println(intersection(new int[][]{{3, 1, 2, 4, 5}, {1, 2, 3, 4}, {3, 4, 5, 6}}));
        System.out.println(intersection(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    /**
     * 多个数组相交的元素，遍历数组，使用 count[] 统计每个元素出现次数
     * 然后遍历 count[]，出现次数等于 nums.length 说明在每个子数组中都出现了，加入结果集
     */
    public static List<Integer> intersection(int[][] nums) {
        int[] count = new int[1001];
        for (int[] array : nums) {
            for (int cur : array) {
                count[cur]++;
            }
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] == nums.length) {
                result.add(i);
            }
        }
        return result;
    }
}

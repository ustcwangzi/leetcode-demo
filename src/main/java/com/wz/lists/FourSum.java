package com.wz.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c,
 * and d in nums such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class FourSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(nums, 0));

        nums = new int[]{-3, -2, -1, 0, 0, 1, 2, 3};
        System.out.println(fourSum(nums, 0));
    }

    /**
     * 思想类似{@link ThreeSum}
     * ThreeSum中固定i，将问题转换为2个数求和；本例中固定i、j，将问题转换为2个数求和
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int sum, left, right;
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                left = j + 1;
                right = nums.length - 1;
                while (left < right) {
                    List<Integer> group = new ArrayList<>();
                    sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        group.add(nums[i]);
                        group.add(nums[j]);
                        group.add(nums[left]);
                        group.add(nums[right]);
                        result.add(group);
                        left++;
                        right--;
                        // 超时优化
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result.parallelStream().distinct().collect(Collectors.toList());
    }
}

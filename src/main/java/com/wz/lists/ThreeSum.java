package com.wz.lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
        nums = new int[]{-2, 0, 0, 2, 2};
        System.out.println(threeSum(nums));
        nums = new int[]{-2, 0, 1, 1, 2};
        System.out.println(threeSum(nums));
    }

    /**
     * 数组排序后，依次固定i，将问题转换为2个数求和
     * 使用left和right两个指针，left指向当前元素的后一个位置，right指向最后一个位置
     * 三个数相加的和等于0时，加入结果；小于0时,把left往右移动；大于0时,把right往左移动
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.parallelSort(nums);
        int left, right;
        for (int i = 0; i < nums.length - 2; i++) {
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> group = new ArrayList<>();
                    group.add(nums[i]);
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
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result.parallelStream().distinct().collect(Collectors.toList());
    }
}

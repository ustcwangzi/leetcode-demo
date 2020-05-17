package com.wz.lists;

import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        System.out.println(threeSumClosest(nums, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.parallelSort(nums);
        int sum, left, right;
        int result = Integer.MAX_VALUE, diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                } else if (sum > target) {
                    if (sum - target < diff) {
                        diff = sum - target;
                        result = sum;
                    }
                    right--;
                } else {
                    if (target - sum < diff) {
                        diff = target - sum;
                        result = sum;
                    }
                    left++;
                }
            }
        }
        return result;
    }
}

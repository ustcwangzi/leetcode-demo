package com.wz.lists;

public class FindMinimumInRotatedSortedArrayII {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 5, 1, 2};
        System.out.println(findMin(nums));

        nums = new int[]{2, 3, 4, 5, 1};
        System.out.println(findMin(nums));
    }

    public static int findMin(int[] nums) {
        if (nums.length <= 1) {
            return nums.length == 0 ? 0 : nums[0];
        }

        int left = 0, right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        int result = nums[0], mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[left] < nums[mid]) {
                result = Math.min(result, nums[left]);
                left = mid + 1;
            } else if (nums[left] > nums[mid]) {
                result = Math.min(result, nums[mid]);
                right = mid - 1;
            } else {
                left++;
            }
        }
        return result;
    }

}

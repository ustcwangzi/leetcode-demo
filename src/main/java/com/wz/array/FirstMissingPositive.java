package com.wz.array;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * Note:
 * Your algorithm should run in O(n) time and uses constant extra space.
 * <p>
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 * <p>
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 * <p>
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0};
        System.out.println(firstMissingPositive(nums));

        nums = new int[]{3, 4, -1, 1};
        System.out.println(firstMissingPositive(nums));

        nums = new int[]{7, 8, 9, 11, 12};
        System.out.println(firstMissingPositive(nums));
    }

    /**
     * 最简单的做法是把所有数存入Set，然后循环从1开始找数字，哪个数字找不到就返回
     * 这种做法需要额外的空间，不满足要求，因此只能原地修改
     * 思路是把1放到位置0，把2放到位置1...即需要把nums[i]放到nums[nums[i]-1]
     * 遍历数组，如果nums[i]在1～N之间，并且nums[i] != nums[nums[i]-1]，则进行交换
     * 再遍历一次数组，nums[i] != i+1，直接返回i+1，否则说明所有数的位置都正确，返回N+1
     */
    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

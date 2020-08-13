package com.wz.array;

import java.util.Arrays;

/**
 * Given an integer array, return the k-th smallest distance among all the pairs.
 * The distance of a pair (A, B) is defined as the absolute difference between A and B.
 *
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 */
public class FindKthSmallestPairDistance {
    public static void main(String[] args) {
        int[] nums = new int[]{62, 100, 4};
        System.out.println(smallestDistancePair(nums, 2));
    }

    /**
     * 二分查找
     * 首先对nums进行排序，这样就可以得到distance的最小值left和最大值right了
     * 然后二分查找：对于一个介于left和right之间的数mid，统计差值小于mid的一共有count个
     * 如果小于k，那么说明mid的取值偏小，所以修改low的值；否则修改high的值
     * 这样不断迭代，最终当low > high的时候，low即为所求
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.parallelSort(nums);

        int left = 0, right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2, count = 0;
            for (int i = 0, j = 0; i < nums.length; i++) {
                while (j < nums.length && nums[j] - nums[i] <= mid) {
                    j++;
                }
                count += j - i - 1;
            }

            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}

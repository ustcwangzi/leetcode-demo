package com.wz.array;

/**
 * Given an array of integers nums.
 * A pair (i,j) is called good if nums[i] == nums[j] and i < j.
 * Return the number of good pairs.
 *
 * Example 1:
 * Input: nums = [1,2,3,1,1,3]
 * Output: 4
 * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
 *
 * Example 2:
 * Input: nums = [1,1,1,1]
 * Output: 6
 * Explanation: Each pair in the array are good.
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class NumberOfGoodPairs {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1, 1, 3};
        System.out.println(numIdenticalPairs(nums));

        nums = new int[]{1, 1, 1, 1};
        System.out.println(numIdenticalPairs(nums));
    }

    /**
     * 相同元素的排列组合个数，直接统计相同元素个数 n，然后 n*(n-1)/2 就是其排列组合个数
     */
    public static int numIdenticalPairs(int[] nums) {
        int[] count = new int[101];
        for (int num : nums) {
            count[num]++;
        }

        int result = 0;
        for (int n : count) {
            if (n > 1) {
                result += n * (n - 1) / 2;
            }
        }
        return result;
    }
}

package com.wz.lists;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 *
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * Output:
 * [2,3]
 */
public class FindAllDuplicatesInArray {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDuplicates(nums));
    }

    /**
     * 因为数组元素的取值在1～n之间，那么value-1不会越界，且数组中的元素只有出现一次和两次，那么可以把数组元素当成是数组的index
     * 第一次遇到index时，将nums[index-1]的值变为负数，若该index-1元素已经为负数，则说明该index之前已经出现过，必为重复元素
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new LinkedList<>();
        for (int num : nums) {
            int index = Math.abs(num);
            if (nums[index - 1] > 0) {
                nums[index - 1] = -nums[index - 1];
            } else {
                result.add(index);
            }
        }
        return result;
    }
}

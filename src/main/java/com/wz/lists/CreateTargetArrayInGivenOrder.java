package com.wz.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two arrays of integers nums and index. Your task is to create target array under the following rules:
 * Initially target array is empty.
 * From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
 * Repeat the previous step until there are no elements to read in nums and index.
 * Return the target array.
 * It is guaranteed that the insertion operations will be valid.
 *
 * Example 1:
 * Input: nums = [0,1,2,3,4], index = [0,1,2,2,1]
 * Output: [0,4,1,3,2]
 * Explanation:
 * nums       index     target
 * 0            0        [0]
 * 1            1        [0,1]
 * 2            2        [0,1,2]
 * 3            2        [0,1,3,2]
 * 4            1        [0,4,1,3,2]
 *
 * Example 2:
 * Input: nums = [1,2,3,4,0], index = [0,1,2,3,0]
 * Output: [0,1,2,3,4]
 * Explanation:
 * nums       index     target
 * 1            0        [1]
 * 2            1        [1,2]
 * 3            2        [1,2,3]
 * 4            3        [1,2,3,4]
 * 0            0        [0,1,2,3,4]
 */
public class CreateTargetArrayInGivenOrder {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 4}, index = new int[]{0, 1, 2, 2, 1};
        System.out.println(Arrays.toString(createTargetArray(nums, index)));

        nums = new int[]{1, 2, 3, 4, 0};
        index = new int[]{0, 1, 2, 3, 0};
        System.out.println(Arrays.toString(createTargetArray(nums, index)));
    }

    /**
     * 直接按照 index 存入 ArrayList 中，然后遍历 ArrayList 将元素依次放入结果中即可
     */
    public static int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0; i < index.length; i++) {
            result.add(index[i], nums[i]);
        }

        for (int i = 0; i < result.size(); i++) {
            nums[i] = result.get(i);
        }
        return nums;
    }
}

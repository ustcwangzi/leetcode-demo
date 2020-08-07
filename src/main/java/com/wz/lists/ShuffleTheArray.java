package com.wz.lists;

import java.util.Arrays;

/**
 * Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
 * Return the array in the form [x1,y1,x2,y2,...,xn,yn].
 *
 * Example 1:
 * Input: nums = [2,5,1,3,4,7], n = 3
 * Output: [2,3,5,4,1,7]
 * Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
 */
public class ShuffleTheArray {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 1, 3, 4, 7};
        System.out.println(Arrays.toString(shuffle(nums, 3)));
    }

    /**
     * 双指针，用指针 left 收集 x1,x2,...,xn，指针 right 收集 y1,y2,...,yn
     * 根据当前位置 i 是奇数还是偶数，选择移动 left 或者 right 即可
     */
    public static int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            result[i] = i % 2 == 0 ? nums[left++] : nums[n++];
        }
        return result;
    }
}

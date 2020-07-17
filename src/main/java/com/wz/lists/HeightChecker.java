package com.wz.lists;

import java.util.Arrays;

/**
 * Students are asked to stand in non-decreasing order of heights for an annual photo.
 * Return the minimum number of students that must move in order for all students to be standing in non-decreasing order of height.
 * Notice that when a group of students is selected they can reorder in any possible way between
 * themselves and the non selected students remain on their seats.
 *
 * Example 1:
 * Input: heights = [1,1,4,2,1,3]
 * Output: 3
 * Explanation:
 * Current array : [1,1,4,2,1,3]
 * Target array  : [1,1,1,2,3,4]
 * On index 2 (0-based) we have 4 vs 1 so we have to move this student.
 * On index 4 (0-based) we have 1 vs 3 so we have to move this student.
 * On index 5 (0-based) we have 3 vs 4 so we have to move this student.
 *
 * Example 2:
 * Input: heights = [5,1,2,3,4]
 * Output: 5
 *
 * Example 3:
 * Input: heights = [1,2,3,4,5]
 * Output: 0
 */
public class HeightChecker {
    public static void main(String[] args) {
        int[] heights = new int[]{1, 1, 4, 2, 1, 3};
        System.out.println(heightChecker(heights));

        heights = new int[]{5, 1, 2, 3, 4};
        System.out.println(heightChecker(heights));

        heights = new int[]{1, 2, 3, 4, 5};
        System.out.println(heightChecker(heights));
    }

    /**
     * 直接对数组进行排序，然后比较同一位置上有多少元素不一致即可
     */
    public static int heightChecker(int[] heights) {
        int[] array = Arrays.copyOf(heights, heights.length);
        Arrays.parallelSort(array);

        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            if (array[i] != heights[i]) {
                result++;
            }
        }

        return result;
    }
}

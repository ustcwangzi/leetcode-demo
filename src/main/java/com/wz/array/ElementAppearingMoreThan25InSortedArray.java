package com.wz.array;

/**
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.
 * Return that integer.
 *
 * Example 1:
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 */
public class ElementAppearingMoreThan25InSortedArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 6, 6, 6, 6, 7, 10};
        System.out.println(findSpecialInteger(arr));
    }

    /**
     * 直接遍历并统计相同元素的出现次数 count ，如果相同元素出现次数大于之前最大的次数 max，则更新 max 和 result
     */
    public static int findSpecialInteger(int[] arr) {
        int result = arr[0], count = 1, max = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count > max) {
                max = count;
                result = arr[i];
            }
        }
        return result;
    }
}

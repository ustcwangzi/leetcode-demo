package com.wz.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example 1:
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Example 2:
 * Input: nums = [-1]
 * Output: [0]
 *
 * Example 3:
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. -10^4 <= nums[i] <= 10^4
 */
public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        System.out.println(countSmaller(new int[]{5, 2, 5, 1}));
        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));
        System.out.println(countSmaller(new int[]{1, 1}));
    }

    /**
     * 将数组加入 list 中，然后对 list 进行排序，排序后遍历数组每个元素，找到其在 list 中第一次出现位置 index
     * 以 [5, 2, 5, 1] 为例，排序后为 [1, 2, 5, 5]，第一个 5 希望得到的 index 是 2，也就是第一次出现的位置
     * 由于 list 是排序后的，此时的 index 就是右侧小于它的元素个数，注意遍历一个元素，需要将其从 list 中移除，不然会计算错误
     * 以 [5, 6, 1] 为例，排序后为 [1, 5, 6]，5 在排序后的 index 为 1，右侧小于它的元素个数就是 1，
     * 再看 6，如果不将 5 移除，则得到的 index 为 2，而实际右侧小于它的元素个数是 1
     */
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> sortedList = new ArrayList<>(nums.length);
        Arrays.stream(nums).forEach(sortedList::add);
        sortedList.sort(Integer::compareTo);

        List<Integer> result = new LinkedList<>();
        for (int num : nums) {
            int index = binarySearch(sortedList, num);
            result.add(index);
            sortedList.remove(index);
        }
        return result;
    }

    /**
     * 找到第一个等于 target 的位置，value >= target 时，需要缩小 right 的范围
     * 并且在 value == target 时，记录 result，循环结束时返回 result 即可
     */
    private static int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1, result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = list.get(mid);
            if (value < target) {
                left = mid + 1;
            } else {
                if (value == target) {
                    result = mid;
                }
                right = mid - 1;
            }
        }
        return result;
    }
}

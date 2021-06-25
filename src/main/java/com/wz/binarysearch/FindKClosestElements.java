package com.wz.binarysearch;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 * An integer a is closer to x than an integer b if:
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 * Example 1:
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 * Constraints:
 * 1. 1 <= k <= arr.length
 * 2. 1 <= arr.length <= 10^4
 * 3. arr is sorted in ascending order.
 * 4. -10^4 <= arr[i], x <= 10^4
 */
public class FindKClosestElements {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 10, 10, 10};
        System.out.println(findClosestElements(arr, 3, 0));
        System.out.println(findClosestElements(arr, 3, 1));
        System.out.println(findClosestElements(arr, 3, 5));
        System.out.println(findClosestElements(arr, 3, 20));
        System.out.println(findClosestElements2(arr, 3, 20));
    }

    /**
     * 二分查找 + 双指针
     * 先找到 arr 中元素值 >= x 的位置 pos，则结果范围一定在 [pos-k, pos+k]，在这个范围里进行查找
     * 若左侧距离 x 更远，则 left 右移，否则 right 左移，直到窗口大小等于 k
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 找到第一个大于等于 x 的位置 left
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == x) {
                break;
            } else if (arr[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 结果一定在 [left-k, left+k] 范围内
        right = Math.min(arr.length - 1, left + k);
        left = Math.max(0, left - k);
        while (right - left + 1 != k) {
            // 逐步缩小范围
            if (Math.abs(arr[left] - x) > Math.abs(arr[right] - x)) {
                left++;
            } else {
                right--;
            }
        }

        List<Integer> result = new ArrayList<>(k);
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    /**
     * 直接将元素按照与 x 的距离进行排序，然后取前 k 个
     */
    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> result = Arrays.stream(arr).boxed().collect(Collectors.toList());
        result.sort(Comparator.comparingInt(num -> Math.abs(num - x)));
        result = result.subList(0, k);
        Collections.sort(result);
        return result;
    }
}

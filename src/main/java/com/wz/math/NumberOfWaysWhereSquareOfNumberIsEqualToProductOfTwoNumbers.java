package com.wz.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays of integers nums1 and nums2, return the number of triplets formed (type 1 and type 2) under the following rules:
 * Type 1: Triplet (i, j, k) if nums1[i]2 == nums2[j] * nums2[k] where 0 <= i < nums1.length and 0 <= j < k < nums2.length.
 * Type 2: Triplet (i, j, k) if nums2[i]2 == nums1[j] * nums1[k] where 0 <= i < nums2.length and 0 <= j < k < nums1.length.
 *
 * Example 1:
 * Input: nums1 = [7,4], nums2 = [5,2,8,9]
 * Output: 1
 * Explanation: Type 1: (1,1,2), nums1[1]^2 = nums2[1] * nums2[2]. (4^2 = 2 * 8).
 *
 * Example 2:
 * Input: nums1 = [1,1], nums2 = [1,1,1]
 * Output: 9
 * Explanation: All Triplets are valid, because 1^2 = 1 * 1.
 * Type 1: (0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2).  nums1[i]^2 = nums2[j] * nums2[k].
 * Type 2: (0,0,1), (1,0,1), (2,0,1). nums2[i]^2 = nums1[j] * nums1[k].
 */
public class NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers {
    public static void main(String[] args) {
        System.out.println(numTriplets(new int[]{7, 4}, new int[]{5, 2, 8, 9}));
        System.out.println(numTriplets(new int[]{1, 1}, new int[]{1, 1, 1}));
    }

    /**
     * 就是brute force，注意 int *int 用long来表示，然后把long转换成string来作为map的key
     */
    public static int numTriplets(int[] nums1, int[] nums2) {
        int count1 = getNum(nums1, nums2);
        int count2 = getNum(nums2, nums1);
        return count1 + count2;
    }

    private static int getNum(int[] A, int[] B) {
        Map<String, Integer> countMap = new HashMap<>();
        for (int k : A) {
            countMap.put(String.valueOf((long) k * k), countMap.getOrDefault(String.valueOf((long) k * k), 0) + 1);
        }

        int result = 0;
        for (int i = 0; i < B.length; i++) {
            for (int j = i + 1; j < B.length; j++) {
                result += countMap.getOrDefault(String.valueOf((long) B[i] * B[j]), 0);
            }
        }
        return result;
    }

}

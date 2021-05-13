package com.wz.greedy;

/**
 * You are given two non-increasing 0-indexed integer arrays nums1 and nums2.
 * A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid if both i <= j and nums1[i] <= nums2[j].
 * The distance of the pair is j - i.
 * Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
 * An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
 *
 * Example 1:
 * Input: nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
 * Output: 2
 * Explanation: The valid pairs are (0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4).
 * The maximum distance is 2 with pair (2,4).
 *
 * Example 2:
 * Input: nums1 = [2,2,2], nums2 = [10,10,1]
 * Output: 1
 * Explanation: The valid pairs are (0,0), (0,1), and (1,1).
 * The maximum distance is 1 with pair (0,1).
 *
 * Example 3:
 * Input: nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
 * Output: 2
 * Explanation: The valid pairs are (2,2), (2,3), (2,4), (3,3), and (3,4).
 * The maximum distance is 2 with pair (2,4).
 *
 * Constraints:
 * 1. 1 <= nums1.length <= 10^5
 * 2. 1 <= nums2.length <= 10^5
 * 3. 1 <= nums1[i], nums2[j] <= 10^5
 * 4. Both nums1 and nums2 are non-increasing.
 */
public class MaximumDistanceBetweenPairOfValues {
    public static void main(String[] args) {
        System.out.println(maxDistance(new int[]{55, 30, 5, 4, 2}, new int[]{100, 20, 10, 10, 5}));
    }

    /**
     * 双指针
     * 分别使用 i、j 指向两个数组的开始位置，然后开始遍历
     * 若 nums1[i] > nums2[j]，不满足条件，因为数组是非增的，后面的 nums[j] 更小，因此此时 j 不变，i 右移
     * 否则，说明满足条件，更新 result，因为后面的 nums[j] 更小，为找到更大的 distance，此时 i 不变，j 右移
     */
    public static int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0, result = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                i++;
                continue;
            }
            result = Math.max(result, j - i);
            j++;
        }
        return result;
    }
}

package com.wz.other;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * You are given a 0-indexed integer array nums and two integers key and k.
 * A k-distant index is an index i of nums for which there exists at least one index j such that |i - j| <= k and nums[j] == key.
 * Return a list of all k-distant indices sorted in increasing order.
 *
 * Example 1:
 * Input: nums = [3,4,9,1,3,9,5], key = 9, k = 1
 * Output: [1,2,3,4,5,6]
 * Explanation: Here, nums[2] == key and nums[5] == key.
 * - For index 0, |0 - 2| > k and |0 - 5| > k, so there is no j where |0 - j| <= k and nums[j] == key. Thus, 0 is not a k-distant index.
 * - For index 1, |1 - 2| <= k and nums[2] == key, so 1 is a k-distant index.
 * - For index 2, |2 - 2| <= k and nums[2] == key, so 2 is a k-distant index.
 * - For index 3, |3 - 2| <= k and nums[2] == key, so 3 is a k-distant index.
 * - For index 4, |4 - 5| <= k and nums[5] == key, so 4 is a k-distant index.
 * - For index 5, |5 - 5| <= k and nums[5] == key, so 5 is a k-distant index.
 * - For index 6, |6 - 5| <= k and nums[5] == key, so 6 is a k-distant index.
 * Thus, we return [1,2,3,4,5,6] which is sorted in increasing order.
 *
 * Example 2:
 * Input: nums = [2,2,2,2,2], key = 2, k = 2
 * Output: [0,1,2,3,4]
 * Explanation: For all indices i in nums, there exists some index j such that |i - j| <= k and nums[j] == key, so every index is a k-distant index.
 * Hence, we return [0,1,2,3,4].
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 1000
 * 3. key is an integer from the array nums.
 * 4. 1 <= k <= nums.length
 */
public class FindAllKDistantIndicesInArray {
    public static void main(String[] args) {
        System.out.println(findKDistantIndices(new int[]{3, 4, 9, 1, 3, 9, 5}, 9, 1));
        System.out.println(findKDistantIndices(new int[]{2, 2, 2, 2, 2}, 2, 2));
    }

    /**
     * 就是对于每个 nums[i] == key 将 [i-k, i+k] 加入结果
     * 因为可能产生重叠，因此使用 lastIndex 记录上一次已经用过的最大 index
     */
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> result = new LinkedList<>();
        int lastIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != key) {
                continue;
            }

            int left = Math.max(i - k, lastIndex + 1), right = Math.min(i + k, nums.length - 1);
            IntStream.rangeClosed(left, right).forEach(result::add);
            lastIndex = right;
        }
        return result;
    }
}

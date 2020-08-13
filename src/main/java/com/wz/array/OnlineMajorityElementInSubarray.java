package com.wz.array;

/**
 * Implementing the class MajorityChecker, which has the following API:
 * MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the given array arr;
 * int query(int left, int right, int threshold) has arguments such that:
 * 0 <= left <= right < arr.length representing a subarray of arr;
 * 2 * threshold > right - left + 1, ie. the threshold is always a strict majority of the length of the subarray
 * Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right]
 * that occurs at least threshold times, or -1 if no such element exists.
 *
 * Example:
 * MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
 * majorityChecker.query(0,5,4); // returns 1
 * majorityChecker.query(0,3,3); // returns -1
 * majorityChecker.query(2,3,2); // returns 2
 *
 * Constraints:
 * 1 <= arr.length <= 20000
 * 1 <= arr[i] <= 20000
 * For each query, 0 <= left <= right < len(arr)
 * For each query, 2 * threshold > right - left + 1
 * The number of queries is at most 10000
 */
public class OnlineMajorityElementInSubarray {
    public static void main(String[] args) {
        MajorityChecker majorityChecker = new MajorityChecker(new int[]{1, 1, 2, 2, 1, 1});
        System.out.println(majorityChecker.query(0, 5, 4));
        System.out.println(majorityChecker.query(0, 3, 3));
        System.out.println(majorityChecker.query(2, 3, 2));
    }

    /**
     * 与{@link MajorityElement} 思路类似
     * 先使用'投票算法'求出 left～right 之间的众数，然后计算众数出现的次数与 threshold 进行比较得出最后结果
     */
    static class MajorityChecker {
        int[] nums;

        public MajorityChecker(int[] arr) {
            nums = arr;
        }

        public int query(int left, int right, int threshold) {
            int count = 0, candidate = 0;
            // 求出众数
            for (int i = left; i <= right; i++) {
                if (count == 0) {
                    candidate = nums[i];
                    count++;
                } else if (candidate == nums[i]) {
                    count++;
                } else {
                    count--;
                }
            }

            count = 0;
            // 计算出现次数
            for (int i = left; i <= right; i++) {
                if (nums[i] == candidate) {
                    count++;
                }
            }
            return count >= threshold ? candidate : -1;
        }
    }
}

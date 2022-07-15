package com.wz.other;

/**
 * You are given an integer array target. You have an integer array initial of the same size as target with all elements initially zeros.
 * In one operation you can choose any subarray from initial and increment each value by one.
 * Return the minimum number of operations to form a target array from initial.
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * Example 1:
 * Input: target = [1,2,3,2,1]
 * Output: 3
 * Explanation: We need at least 3 operations to form the target array from the initial array.
 * [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
 * [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
 * [1,2,2,2,1] increment 1 at index 2.
 * [1,2,3,2,1] target array is formed.
 *
 * Example 2:
 * Input: target = [3,1,1,2]
 * Output: 4
 * Explanation: [0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2]
 *
 * Example 3:
 * Input: target = [3,1,5,4,2]
 * Output: 7
 * Explanation: [0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1] -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2].
 *
 * Constraints:
 * 1. 1 <= target.length <= 10^5
 * 2. 1 <= target[i] <= 10^5
 */
public class MinimumNumberOfIncrementsOnSubarraysToFormTargetArray {
    public static void main(String[] args) {
        System.out.println(minNumberOperations(new int[]{1, 2, 3, 2, 1}));
        System.out.println(minNumberOperations(new int[]{3, 1, 1, 2}));
        System.out.println(minNumberOperations(new int[]{3, 1, 5, 4, 2}));
    }

    /**
     * 线性扫描
     * 初始时，至少需要 target[0] 次累加使第一个位置符合要求，但累加的过程中也可以顺便累加后边的一些位置
     * 可以比较两个位置之间的差值，如果 target[i] > target[i-1]，则位置 i 至少要付出 target[i]−target[i-1] 次累加
     * 其余情况，i 位置都可以被 i-1 位置顺便累加
     */
    public static int minNumberOperations(int[] target) {
        int result = target[0];
        for (int i = 1; i < target.length; i++) {
            result += Math.max(0, target[i] - target[i - 1]);
        }
        return result;
    }
}

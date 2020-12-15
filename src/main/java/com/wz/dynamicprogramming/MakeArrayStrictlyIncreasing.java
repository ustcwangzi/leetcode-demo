package com.wz.dynamicprogramming;

import java.util.TreeSet;

/**
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 * If there is no way to make arr1 strictly increasing, return -1.
 *
 * Example 1:
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * Output: 1
 * Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
 *
 * Example 2:
 * Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * Output: 2
 * Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
 *
 * Example 3:
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * Output: -1
 * Explanation: You can't make arr1 strictly increasing.
 *
 * Constraints:
 * 1. 1 <= arr1.length, arr2.length <= 2000
 * 2. 0 <= arr1[i], arr2[i] <= 10^9
 */
public class MakeArrayStrictlyIncreasing {
    public static void main(String[] args) {
        System.out.println(makeArrayIncreasing(new int[]{1, 5, 3, 6, 7}, new int[]{4, 3, 1}));
    }

    public static int makeArrayIncreasing(int[] arr1, int[] arr2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int a : arr2) {
            set.add(a);
        }
        //dp[i][j]表示第i个数经过j次变换时，最后一个数的最小值
        //dp[i][j] represents from for arr1[0, i] the min number when performed j replaces
        int[][] dp = new int[arr1.length][arr1.length + 1];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j <= arr1.length; j++) {
                //set default to -1, cause it will never be -1
                dp[i][j] = -1;
            }
        }
        //if no replace happen, then set dp[0][0] to the first number
        dp[0][0] = arr1[0];
        //if smallest number in arr1 is not arr1[0], then replace it
        if (set.higher(-1) != null && set.higher(-1) < arr1[0]) {
            dp[0][1] = set.higher(-1);
        }
        //loop the number in arra
        for (int i = 1; i < arr1.length; i++) {
            //loop the possible replace count, it will be at most i
            for (int j = 0; j <= i; j++) {
                //当前数
                int current = arr1[i];
                //i-1位置时经过j次变换的最小值
                //the min result [0, i-1] and replace j number
                int last = dp[i - 1][j];
                //上一个位置为-1
                //-1 means this does't exist, break
                if (last == -1) {
                    continue;
                }
                //比前一数大的最小值
                //the min number higher than last
                Integer higher = set.higher(last);
                //当前数不大于前一位置的数，那么必须变换
                //if current is not bigger than last, then we must replace the current number
                if (current <= last) {
                    //if higher exists, then perform another replacement based on dp[i-1][j]
                    if (higher != null) {
                        dp[i][j + 1] = higher;
                    }
                } else {
                    //否则只在能取到比当前更小的情况下变化
                    //if current is bigger than last && higher is less than current, so we can make dp[i][] smaller than current
                    if (higher != null && higher < current) {
                        dp[i][j + 1] = higher;
                    }
                    //if dp[i][j] has been set, then we take the smaller one
                    if (dp[i][j] > -1) {
                        dp[i][j] = Math.min(dp[i][j], current);
                    } else {
                        //if we don't make any replacement, then dp[i][j] is current
                        dp[i][j] = current;
                    }
                }
            }
        }
        //dp[arr1.length -1][i] means we can perform i replacement to make arr ascending, the smallest i is result, otherwise is -1
        for (int i = 0; i <= arr1.length; i++) {
            if (dp[arr1.length - 1][i] > -1) {
                return i;
            }
        }
        return -1;
    }
}

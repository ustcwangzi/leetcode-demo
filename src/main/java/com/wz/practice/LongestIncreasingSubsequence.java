package com.wz.practice;

import java.util.Arrays;

/**
 * 给定数组arr，设长度为n，输出arr的最长递增子序列。（如果有多个答案，请输出其中 按数值(注：区别于按单个字符的ASCII码值)进行比较的 字典序最小的那个）
 * 示例1
 * 输入：
 * [2,1,5,3,6,4,8,9,7]
 * 返回值：
 * [1,3,4,8,9]
 *
 * 示例2
 * 输入：
 * [1,2,8,6,4]
 * 返回值：
 * [1,2,4]
 * 说明：
 * 其最长递增子序列有3个，（1，2，8）、（1，2，6）、（1，2，4）其中第三个 按数值进行比较的字典序 最小，故答案为（1，2，4）
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(LIS(new int[]{1, 3, 8, 6, 5, 2, 5})));
        System.out.println(Arrays.toString(LIS(new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7})));
    }

    public static int[] LIS(int[] arr) {
        int n = arr.length;
        // 当前递增子序列长度为 i+1 时，子序列结尾的最小值(因为有多个长度为 i+1 的递增子序列)
        int[] end = new int[n];
        // 以 arr[i] 结尾的递增子序列的最大长度
        int[] dp = new int[n];

        int len = 1;
        end[0] = arr[0];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            if (end[len - 1] < arr[i]) {
                // 当前元素较大，直接加入序列
                end[len++] = arr[i];
                dp[i] = len;
            } else {
                int position = search(end, len, arr[i]);

                // 将 arr[i] 放到 position
                end[position] = arr[i];
                // 递增子序列的总长度不变，但是为了复用原序列一些 < arr[i]的结果，选择二分把 arr[i] 替换到合适的位置
                // 所以 dp[i] 对应的序列其实是递增子序列的 [0,index] 部分，即长度为 index + 1
                dp[i] = position + 1;
            }
        }

        // 逆序遍历，找到满足长度的 dp 就更新（即同样值的dp，选尽量靠右边的）
        int[] result = new int[len];
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == len) {
                result[--len] = arr[i];
            }
        }

        return result;
    }

    /**
     * 查找第一个大于等于 num 的位置
     */
    private static int search(int[] end, int len, int num) {
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (end[mid] < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return end[left] >= num ? left : (left + 1);
    }
}

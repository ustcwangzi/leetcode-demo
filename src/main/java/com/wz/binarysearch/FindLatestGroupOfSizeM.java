package com.wz.binarysearch;

import java.util.TreeSet;

/**
 * Given an array arr that represents a permutation of numbers from 1 to n.
 * You have a binary string of size n that initially has all its bits set to zero.
 * At each step i (assuming both the binary string and arr are 1-indexed) from 1 to n, the bit at position arr[i] is set to 1.
 * You are given an integer m and you need to find the latest step at which there exists a group of ones of length m.
 * A group of ones is a contiguous substring of 1s such that it cannot be extended in either direction.
 * Return the latest step at which there exists a group of ones of length exactly m. If no such group exists, return -1.
 *
 * Example 1:
 * Input: arr = [3,5,1,2,4], m = 1
 * Output: 4
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "00101", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "11101", groups: ["111", "1"]
 * Step 5: "11111", groups: ["11111"]
 * The latest step at which there exists a group of size 1 is step 4.
 *
 * Example 2:
 * Input: arr = [3,1,5,4,2], m = 2
 * Output: -1
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "10100", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "10111", groups: ["1", "111"]
 * Step 5: "11111", groups: ["11111"]
 * No group of size 2 exists during any step.
 *
 * Constraints:
 * 1. n == arr.length
 * 2. 1 <= n <= 10^5
 * 3. 1 <= arr[i] <= n
 * 4. All integers in arr are distinct.
 * 5. 1 <= m <= arr.length
 */
public class FindLatestGroupOfSizeM {
    public static void main(String[] args) {
        System.out.println(findLatestStep(new int[]{3, 5, 1, 2, 4}, 1));
        System.out.println(findLatestStep(new int[]{1, 5, 2, 3, 4}, 2));
    }

    /**
     * TreeSet
     * 长度为 n 的全 0 数组，在第 arr[i] 的位置放置 1，求出最后一步得到长度为 m 的全 1 是在哪一步
     * 可以反过来看这道题，就是全 1 的数组放置 0，第一步得到 0 之间的距离为 1 在哪一步
     * 对于每个位置 arr[i]，只需要判断其左侧的 0 和右侧的 0，然后计算距离即可，因此可以使用 TreeSet
     * 以 arr = [1,5,2,3,4], m = 2 为例（其实 m 就是连续 1 的个数）：
     * step     set         m
     *  n    0,1,1,1,1,1,0  5
     *  n-1  0,1,1,1,0,1,0  1,3
     *  n-2  0,1,1,0,0,1,0  1,2
     */
    public static int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }

        // 保存 0 的位置
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(n + 1);
        for (int i = n - 1; i >= 0; i--) {
            // 求出 arr[i] 左侧和右侧最近 0 的位置
            int left = set.floor(arr[i]), right = set.ceiling(arr[i]);
            if (arr[i] - left - 1 == m || right - arr[i] - 1 == m) {
                return i;
            }
            set.add(arr[i]);
        }
        return -1;
    }
}

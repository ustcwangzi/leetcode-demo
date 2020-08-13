package com.wz.array;

/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * Find the kth positive integer that is missing from this array.
 *
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 *
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 */
public class KthMissingPositiveNumber {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 7, 11};
        System.out.println(findKthPositive(arr, 5));
        System.out.println(findKthPositive1(arr, 5));

        arr = new int[]{1, 2, 3, 4};
        System.out.println(findKthPositive(arr, 2));
        System.out.println(findKthPositive1(arr, 2));
    }

    /**
     * 用一个数组标记每个元素是否出现过，然后统计缺失元素的个数
     */
    public static int findKthPositive(int[] arr, int k) {
        int[] position = new int[1000];
        for (int num : arr) {
            position[num - 1] = 1;
        }
        int count = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] == 0) {
                count++;
            }
            if (count == k) {
                return i + 1;
            }
        }
        return 1000 + k - count;
    }

    /**
     * 若所有数字都连续存在，则数字与其所在的索引之差为 1；否则差大于 1，且前面若缺少 k 个数，则差为 k+1
     * 利用这个性质可使用二分查找找到缺失值大于等于 k 的位置
     */
    public static int findKthPositive1(int[] arr, int k) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] - mid - 1 >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // left 是第一个缺失值之差大于等于 k 的元素所在索引
        return left + k;
    }
}

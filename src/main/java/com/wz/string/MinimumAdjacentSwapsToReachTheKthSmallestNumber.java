package com.wz.string;

/**
 * You are given a string num, representing a large integer, and an integer k.
 * We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num.
 * There can be many wonderful integers. However, we only care about the smallest-valued ones.
 * For example, when num = "5489355142":
 * The 1st smallest wonderful integer is "5489355214".
 * The 2nd smallest wonderful integer is "5489355241".
 * The 3rd smallest wonderful integer is "5489355412".
 * The 4th smallest wonderful integer is "5489355421".
 *
 * Return the minimum number of adjacent digit swaps that needs to be applied to num to reach the kth smallest wonderful integer.
 * The tests are generated in such a way that kth smallest wonderful integer exists.
 *
 * Example 1:
 * Input: num = "5489355142", k = 4
 * Output: 2
 * Explanation: The 4th smallest wonderful number is "5489355421". To get this number:
 * - Swap index 7 with index 8: "5489355142" -> "5489355412"
 * - Swap index 8 with index 9: "5489355412" -> "5489355421"
 *
 * Example 2:
 * Input: num = "11112", k = 4
 * Output: 4
 * Explanation: The 4th smallest wonderful number is "21111". To get this number:
 * - Swap index 3 with index 4: "11112" -> "11121"
 * - Swap index 2 with index 3: "11121" -> "11211"
 * - Swap index 1 with index 2: "11211" -> "12111"
 * - Swap index 0 with index 1: "12111" -> "21111"
 *
 * Example 3:
 * Input: num = "00123", k = 1
 * Output: 1
 * Explanation: The 1st smallest wonderful number is "00132". To get this number:
 * - Swap index 3 with index 4: "00123" -> "00132"
 *
 * Constraints:
 * 1. 2 <= num.length <= 1000
 * 2. 1 <= k <= 1000
 * 3. num only consists of digits.
 */
public class MinimumAdjacentSwapsToReachTheKthSmallestNumber {
    public static void main(String[] args) {
        System.out.println(getMinSwaps("5489355142", 4));
        System.out.println(getMinSwaps("11112", 4));
    }

    /**
     * nextPermutation 与 {@link com.wz.array.NextPermutation} 一致
     * 计算最小的交换次数，直接暴力求解
     */
    public static int getMinSwaps(String num, int k) {
        char[] array = num.toCharArray();
        for (int i = 0; i < k; i++) {
            nextPermutation(array);
        }

        return countSwaps(num.toCharArray(), array);
    }

    private static void nextPermutation(char[] nums) {
        int firstDesc = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                firstDesc = i - 1;
                break;
            }
        }
        if (firstDesc == -1) {
            reverse(nums, 0, nums.length - 1);
        } else {
            for (int i = nums.length - 1; i > firstDesc; i--) {
                if (nums[i] > nums[firstDesc]) {
                    swap(nums, i, firstDesc);
                    reverse(nums, firstDesc + 1, nums.length - 1);
                    break;
                }
            }
        }
    }

    private static void swap(char[] nums, int i, int j) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void reverse(char[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /**
     * 每次将合适的 array2[j] 交换到 i 位置上
     * 以 [1,4,2] 与 [4,2,1] 为例，i = 0，j = 2
     * 第一次交换 array2[2] 与 array2[1]，变为 [4,1,2]
     * 第二次交换 array2[1] 与 array2[0]，变为 [1,4,2]
     */
    private static int countSwaps(char[] array1, char[] array2) {
        int result = 0;
        for (int i = 0; i < array1.length; i++) {
            int j = i;
            while (array1[i] != array2[j]) {
                j++;
            }

            // 交换相邻的元素，记录交换次数
            for (int k = j; k > i; k--) {
                swap(array2, k, k - 1);
                result++;
            }
        }
        return result;
    }
}

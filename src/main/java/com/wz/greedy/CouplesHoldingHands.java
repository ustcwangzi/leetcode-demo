package com.wz.greedy;

/**
 * There are n couples sitting in 2n seats arranged in a row and want to hold hands.
 *
 * The people and seats are represented by an integer array row where row[i] is the ID of the person sitting in the ith seat.
 * The couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2n - 2, 2n - 1).
 * Return the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.
 *
 * Example 1:
 * Input: row = [0,2,1,3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 *
 * Example 2:
 * Input: row = [3,2,0,1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 *
 * Constraints:
 * 1. 2n == row.length
 * 2. 2 <= n <= 30
 * 3. n is even.
 * 4. 0 <= row[i] < 2n
 * 5. All the elements of row are unique.
 */
public class CouplesHoldingHands {
    public static void main(String[] args) {
        System.out.println(minSwapsCouples(new int[]{0, 2, 1, 3}));
        System.out.println(minSwapsCouples(new int[]{3, 2, 0, 1}));
    }

    /**
     * 使用 index 保存每个元素的下标，然后遍历数组的每个奇数位置，对于当前位置 i，满足条件的元素 require 为
     * - row[i-1] 为偶数，当前位置元素为 row[i-1]+1，如 (0, 1)
     * - row[i-1] 为奇数，当前位置元素为 row[i-1]-1，如 (3, 2)
     * 若 row[i] == require，直接满足条件了，继续遍历
     * 否则，需要进行元素交换，即交换元素 require、row[i]，同时交换对应的位置 index[require]、i
     */
    public static int minSwapsCouples(int[] row) {
        int n = row.length, count = 0;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[row[i]] = i;
        }
        for (int i = 1; i < n; i += 2) {
            // 满足条件的元素值
            int require = row[i - 1] + ((row[i - 1] & 1) == 0 ? 1 : -1);
            if (row[i] == require) {
                continue;
            }

            row[index[require]] = row[i];
            index[row[i]] = index[require];
            index[require] = i;
            row[i] = require;
            count++;
        }
        return count;
    }
}

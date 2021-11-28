package com.wz.dynamicprogramming;

/**
 * Given an array of integers arr and an integer d. In one step you can jump from index i to index:
 * i + x where: i + x < arr.length and 0 < x <= d.
 * i - x where: i - x >= 0 and 0 < x <= d.
 * In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k]
 * for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
 * You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 * @link ../../../../resource/JumpGameV.jpg
 * Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
 * Output: 4
 * Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
 * Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9.
 * You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
 * Similarly You cannot jump from index 3 to index 2 or index 1.
 *
 * Example 2:
 * Input: arr = [3,3,3,3,3], d = 3
 * Output: 1
 * Explanation: You can start at any index. You always cannot jump to any index.
 *
 * Constraints:
 * 1. 1 <= arr.length <= 1000
 * 2. 1 <= arr[i] <= 10^5
 * 3. 1 <= d <= arr.length
 */
public class JumpGameV {
    public static void main(String[] args) {
        System.out.println(maxJumps(new int[]{6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12}, 2));
    }

    /**
     * 对可以访问的节点数从 1 开始进行遍历，dp[i][j] 表示第 i 个节点是否可以访问 j 个节点，直到左右 dp[i][j] 都为 false 时循环结束。
     * 对每一个 dp[i][j]，寻找其左边及右边满足条件的节点中可以使它为 true 的节点，一旦发现便退出循环
     */
    public static int maxJumps(int[] arr, int d) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][n + 1];
        for (int i = 0; i < n; ++i) {
            dp[i][0] = true;
        }

        for (int jp = 1; jp <= n; jp++) {
            boolean flag = false;
            for (int i = 0; i < n; ++i) {
                if (!dp[i][jp - 1]) {
                    continue;
                }

                for (int j = i - 1; j >= 0 && i - j <= d && arr[j] < arr[i]; j--) {
                    if (dp[j][jp - 1]) {
                        dp[i][jp] = true;
                        flag = true;
                        break;
                    }
                }
                if (dp[i][jp]) {
                    continue;
                }

                for (int j = i + 1; j < n && j - i <= d && arr[j] < arr[i]; j++) {
                    if (dp[j][jp - 1]) {
                        dp[i][jp] = true;
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                return jp;
            }
        }
        return n + 1;
    }
}

package com.wz.greedy;

import java.util.Arrays;

/**
 * You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
 * In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1.
 * Note that after doing so, there may be more than one ball in some boxes.
 * Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
 * Each answer[i] is calculated considering the initial state of the boxes.
 *
 * Example 1:
 * Input: boxes = "110"
 * Output: [1,1,3]
 * Explanation: The answer for each box is as follows:
 * 1) First box: you will have to move one ball from the second box to the first box in one operation.
 * 2) Second box: you will have to move one ball from the first box to the second box in one operation.
 * 3) Third box: you will have to move one ball from the first box to the third box in two operations,
 *               and move one ball from the second box to the third box in one operation.
 *
 * Example 2:
 * Input: boxes = "001011"
 * Output: [11,8,5,4,3,4]
 *
 * Constraints:
 * 1. n == boxes.length
 * 2. 1 <= n <= 2000
 * 3. boxes[i] is either '0' or '1'.
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(minOperations("110")));
        System.out.println(Arrays.toString(minOperations("001011")));
    }

    /**
     * 把其他球全部移动到位置 i 的操作次数 = 把左侧球移动到 i 的操作次数 + 把右侧球移动到 i 的操作次数
     * 从左到右遍历字符串，记录将左侧球全部移动到 i 的操作次数，从右向左遍历字符串，记录将右侧的球全部移动到 i 的操作次数
     * 最后，将两个操作次数相加即可
     */
    public static int[] minOperations(String boxes) {
        char[] array = boxes.toCharArray();
        int n = array.length, count = array[0] - '0';
        int[] left = new int[n], right = new int[n];

        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + count;
            count += array[i] - '0';
        }

        count = array[n - 1] - '0';
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + count;
            count += array[i] - '0';
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = left[i] + right[i];
        }
        return result;
    }
}

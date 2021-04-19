package com.wz.array;

/**
 * You are working in a ball factory where you have n balls numbered from lowLimit up to highLimit inclusive
 * (i.e., n == highLimit - lowLimit + 1), and an infinite number of boxes numbered from 1 to infinity.
 * Your job at this factory is to put each ball in the box with a number equal to the sum of digits of the ball's number.
 * For example, the ball number 321 will be put in the box number 3 + 2 + 1 = 6 and the ball number 10 will be put in the box number 1 + 0 = 1.
 * Given two integers lowLimit and highLimit, return the number of balls in the box with the most balls.
 *
 * Example 1:
 * Input: lowLimit = 1, highLimit = 10
 * Output: 2
 * Explanation:
 * Box Number:  1 2 3 4 5 6 7 8 9 10 11 ...
 * Ball Count:  2 1 1 1 1 1 1 1 1 0  0  ...
 * Box 1 has the most number of balls with 2 balls.
 *
 * Example 2:
 * Input: lowLimit = 5, highLimit = 15
 * Output: 2
 * Explanation:
 * Box Number:  1 2 3 4 5 6 7 8 9 10 11 ...
 * Ball Count:  1 1 1 1 2 2 1 1 1 0  0  ...
 * Boxes 5 and 6 have the most number of balls with 2 balls in each.
 *
 * Constraints:
 * 1 <= lowLimit <= highLimit <= 10^5
 */
public class MaximumNumberOfBallsInBox {
    public static void main(String[] args) {
        System.out.println(countBalls(1, 10));
        System.out.println(countBalls(5, 15));
    }

    /**
     * 将每个小球放入盒子中，盒子的编号等于小球编号上每位数字之和
     * 最大值为 10^5，因此最大的 sum 为 9999 的每位数字之和即 45，因此可以使用长度为 46 的数组来保存每个盒子放置的小球数量
     * 从 lowLimit 遍历到 highLimit，得到每个编号对应的数字之和，然后放到对应的盒子中
     */
    public static int countBalls(int lowLimit, int highLimit) {
        int[] array = new int[46];
        int result = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int sum = getSum(i);
            array[sum]++;
            result = Math.max(array[sum], result);
        }
        return result;
    }

    private static int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}

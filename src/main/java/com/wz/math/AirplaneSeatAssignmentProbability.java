package com.wz.math;

/**
 * n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly.
 * But after that, the rest of passengers will:
 * Take their own seat if it is still available,
 * Pick other seats randomly when they find their seat occupied
 * What is the probability that the n-th person can get his own seat?
 *
 * Example 1:
 * Input: n = 1
 * Output: 1.00000
 * Explanation: The first person can only get the first seat.
 *
 * Example 2:
 * Input: n = 2
 * Output: 0.50000
 * Explanation: The second person has a probability of 0.5 to get the second seat (when first person gets the first seat).
 */
public class AirplaneSeatAssignmentProbability {
    public static void main(String[] args) {
        System.out.println(nthPersonGetsNthSeat(1));
        System.out.println(nthPersonGetsNthSeat(2));
    }

    /**
     * 如果第一个人随机到了自己的位置，那么后面的人一定按自己机票座位号入座。
     * 如果第一个人随机到了第n个人的位置，那么第 n 个人得到自己座位的概率为0。
     * 如果第一个人随机到了第2个人的位置，那么第 n 个人得到自己座位的概率为f(n-1)。
     * 依次类推可得  f(n) = (1 + f(n-1) + f(n-2) + ... + f(2) + 0) / n ;
     * 假设当 1 < i <= k 时 f(i) = 1/2 , 容易证明 f(k+1) = 1/2; 所以f(n) 在 n > 1的时候恒等于 1/2 .
     */
    public static double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1.0;
        }

        double[] dp = new double[n];
        double sum = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = (1 + sum) / (i + 1);
            sum += dp[i];
        }
        return dp[n - 1];
    }

    public static double nthPersonGetsNthSeat2(int n) {
        if (n == 1) {
            return 1.0;
        } else {
            return 0.5;
        }
    }
}

package com.wz.array;

/**
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
 * Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 *
 * Example 1:
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 *
 * Example 2:
 * Input: [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    public static void main(String[] args) {
        int[] time = new int[]{30, 20, 150, 100, 40};
        System.out.println(numPairsDivisibleBy60(time));

        time = new int[]{60, 60, 60};
        System.out.println(numPairsDivisibleBy60(time));
    }

    /**
     * 对于元素 t, 希望找到 x 使得 (x + t) % 60 == 0. 然而这里存在一个问题, 由于 t % 60 结果是 0 ~ 59,
     * 但是 60 - (t % 60) 范围却是 1 ~ 60. 因此可以转换为: 判断 (60 - (t % 60)) % 60) 是否存在
     */
    public static int numPairsDivisibleBy60(int[] time) {
        int result = 0;
        // 记录余数出现的次数
        int[] table = new int[60];
        for (int t : time) {
            int mod = t % 60;
            result += table[(60 - mod) % 60];
            table[mod]++;
        }

        return result;
    }
}

package com.wz.math;

import java.util.Arrays;

/**
 * We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.
 * Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.
 * The mode is guaranteed to be unique.
 * (Recall that the median of a sample is:
 * The middle element, if the elements of the sample were sorted and the number of elements is odd;
 * The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)
 *
 * Example 1:
 * Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
 */
public class StatisticsFromLargeSample {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sampleStats(new int[]{0, 4, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})));
    }

    public static double[] sampleStats(int[] count) {
        int curMod = 0;
        double min = -1, max = -1, sum = 0, median = 0;
        int size = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                size += count[i];
                if (min == -1) {
                    min = i;
                }
                max = i;
                sum += count[i] * i;
                if (count[i] > count[curMod]) {
                    curMod = i;
                }
            }
        }

        // 当前访问到的数据个数
        int curNum = 0;
        // 当数据总数为奇数时为中位数；为偶数时为第二个中位数
        int k = size / 2 + 1;
        // 在遍历时记录当前访问数字的前一个数字（计数不为0）
        int lastNumber = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                curNum += count[i];
                if (curNum < k) {
                    lastNumber = i;
                    continue;
                }

                if (size % 2 != 1) {
                    if (curNum == k || curNum - count[i] == k - 1) {
                        median = (lastNumber + i) / 2.0;
                        break;
                    }
                }
                median = i;
                break;
            }
        }

        return new double[]{min, max, sum * 1.0 / size, median, curMod};
    }
}

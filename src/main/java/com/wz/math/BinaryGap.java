package com.wz.math;

/**
 * Given a positive integer N, find and return the longest distance between two consecutive 1's in the binary representation of N.
 * If there aren't two consecutive 1's, return 0.
 *
 * Example 1:
 * Input: 22
 * Output: 2
 * Explanation:
 * 22 in binary is 0b10110.
 * In the binary representation of 22, there are three ones, and two consecutive pairs of 1's.
 * The first consecutive pair of 1's have distance 2.
 * The second consecutive pair of 1's have distance 1.
 * The answer is the largest of these two distances, which is 2.
 *
 * Example 2:
 * Input: 5
 * Output: 2
 * Explanation:
 * 5 in binary is 0b101.
 */
public class BinaryGap {
    public static void main(String[] args) {
        System.out.println(binaryGap(22));
        System.out.println(binaryGap(6));
    }

    /**
     * 按位转换二进制时，依次对两个1之间的最大的值进行记录，因为N可能会很大，因此取counter为-32.
     */
    public static int binaryGap(int N) {
        int result = 0, counter = -32;
        while (N > 0) {
            if ((N & 1) > 0) {
                result = Math.max(result, counter);
                counter = 0;
            }
            N = N >> 1;
            counter++;
        }
        return result;
    }
}

package com.wz.math;

/**
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 *
 * Example 1:
 * Input: 1
 * Output: true
 *
 * Example 2:
 * Input: 10
 * Output: false
 *
 * Note:
 * 1 <= N <= 10^9
 */
public class ReorderedPowerOf2 {
    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(1));
        System.out.println(reorderedPowerOf2(10));
    }

    /**
     * 因为1 <= N <= 10^9，而2^32>10^9，因此，最多只有2^0，2^1，2^2.....2^31共32个数，每个数都是不相同的，
     * 因此，我们只要判断N中每个数字的个数是否和之前的32个数中某一个数中的每一个数字的个数是否相同，只要相同数字的个数相同，
     * 那么就可以重新组合成那个数。因此，可以把N中的每个数字分解出来，存在一个长度为10的数组里，然后将这个数组与前面32个数字分解的数组去对比，
     * 只要相等，就符合；但是，两个数组是否相等比较麻烦，这样，可以把每个数字设为10的多少次方，这样就没必要去比较整个数组是否相等，
     * 直接把这组数字用10的多少次方表示出来；
     * 比如N=4654，其中有2个4，1个5，1个6，因此可以表示为：10^4+10^4+10^5+10^6，这样出来的结果是唯一的，因此可以比较
     */
    public static boolean reorderedPowerOf2(int N) {
        long result = count(N);
        for (int i = 0; i < 32; i++) {
            if (count(1 << i) == result) {
                return true;
            }
        }
        return false;
    }

    private static long count(int N) {
        long result = 0;
        while (N > 0) {
            result += Math.pow(10, N % 10);
            N /= 10;
        }
        return result;
    }
}

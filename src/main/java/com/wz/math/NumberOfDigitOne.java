package com.wz.math;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * Example:
 * Input: 13
 * Output: 6
 * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberOfDigitOne {
    public static void main(String[] args) {
        System.out.println(countDigitOne(13));
    }

    /**
     * 先求所有数中个位是 1 的个数，再求十位是 1 的个数，再求百位是 1 的个数...
     * 假设 n = xyzdabc，此时我们求千位是 1 的个数，也就是 d 所在的位置
     * 那么此时有三种情况
     * 1. d == 0，那么千位上 1 的个数就是 xyz * 1000
     * 2. d == 1，那么千位上 1 的个数就是 xyz * 1000 + abc + 1
     * 3. d > 1，那么千位上 1 的个数就是 xyz * 1000 + 1000
     *
     * 当考虑千位是 1 的时候，也就是 xyz1abc
     * 对于 xyz 的话，可以取 0,1,2...(xyz-1)，也就是 xyz 种可能
     * 当 xyz 固定为上边其中的一个数的时候，abc 可以取 0,1,2...999，也就是 1000 种可能
     * 这样的话，总共就是 xyz*1000 种可能
     * 注意到，前三位只取到了 xyz-1，那么如果取 xyz 呢？
     * 此时就出现了上边的三种情况，取决于 d 的值
     * d == 1 的时候，千位刚好是 1，此时 abc 可以取的值就是 0 到 abc ，所以多加了 abc + 1
     * d > 1 的时候，d 如果取 1，那么 abc 就可以取 0 到 999，此时就多加了 1000
     *
     * 如果n = 4560234
     * 让我们统计一下千位有多少个 1
     * xyz 可以取 0 到 455, abc 可以取 0 到 999
     * 4551000 to 4551999 (1000)
     * 4541000 to 4541999 (1000)
     * 4531000 to 4531999 (1000)
     * ...
     *   21000 to   21999 (1000)
     *   11000 to   11999 (1000)
     *    1000 to    1999 (1000)
     * 总共就是 456 * 1000
     *
     * 如果 n = 4561234
     * xyz 可以取 0 到 455, abc 可以取 0 到 999
     * 4551000 to 4551999 (1000)
     * 4541000 to 4541999 (1000)
     * 4531000 to 4531999 (1000)
     * ...
     * 1000 to 1999 (1000)
     * xyz 还可以取 456, abc 可以取 0 到 234
     * 4561000 to 4561234 (234 + 1)
     * 总共就是 456 * 1000 + 234 + 1
     *
     * 如果 n = 4563234
     * xyz 可以取 0 到 455, abc 可以取 0 到 999
     * 4551000 to 4551999 (1000)
     * 4541000 to 4541999 (1000)
     * 4531000 to 4531999 (1000)
     * ...
     * 1000 to 1999 (1000)
     * xyz 还可以取 456, abc 可以取 0 到 999
     * 4561000 to 4561999 (1000)
     * 总共就是 456 * 1000 + 1000
     */
    public static int countDigitOne(int n) {
        int count = 0;
        for (int i = 1; i <= n; i *= 10) {
            // xyzdabc
            int abc = n % i;
            int xyzd = n / i;
            int d = xyzd % 10;
            int xyz = xyzd / 10;
            count += xyz * i;

            if (d > 1) {
                count += i;
            } else if (d == 1) {
                count += abc + 1;
            }

            // k 一直乘以 10, 但由于溢出的问题, k 本来要大于 n，却小于了 n 会再次进入循环
            // 因此加上该判断, 此时代表最高位是 1 的情况也考虑完成了
            if (xyz == 0) {
                break;
            }
        }

        return count;
    }
}

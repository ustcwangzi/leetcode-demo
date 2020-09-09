package com.wz.math;

/**
 * For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
 * Now given a string representing n, you should return the smallest good base of n in string format.
 *
 * Example 1:
 * Input: "13"
 * Output: "3"
 * Explanation: 13 base 3 is 111.
 *
 * Example 2:
 * Input: "1000000000000000000"
 * Output: "999999999999999999"
 * Explanation: 1000000000000000000 base 999999999999999999 is 11.
 *
 * Note:
 * The range of n is [3, 10^18].
 * The string representing n is always valid and will not have leading zeros.
 */
public class SmallestGoodBase {
    public static void main(String[] args) {
        System.out.println(smallestGoodBase("13"));
        System.out.println(smallestGoodBase("1000000000000000000"));
    }

    /**
     * 定义了一个大于等于2的基数k，如果可以把数字n转化为各位都是1的数，那么就称这个基数k是好基数。
     * 如果用k表示基数，m表示转为全1数字的位数，那么数字n就可以拆分为：
     * n = 1 + k + k^2 + k^3 + ... + k^(m-1)
     * 这是一个等比数列，利用求和公式可以表示为 n = (k^m - 1) / (k - 1)。
     * 目标是求最小的k，那么仔细观察这个式子，在n恒定的情况，k越小则m却大，就是说上面的等式越长越好。
     * 下面来分析m的取值范围，题目中给了n的范围，是 [3, 10^18]。由于k至少为2，n至少为3，那么肯定至少有两项，则 m>=2。
     * 再来看m的上限该如何求？想要m最大，k就要最小，k最小是2，那么m最大只能为 log2(n + 1)，数字n用二进制表示的时候可拆分出的项最多。
     * 但这道题要求变换后的数各位都是1，那么看题目中最后一个例子，可以发现，当 k=n-1 时，一定能变成 11，所以实在找不到更小的情况就返回 n-1
     * 下面来确定k的范围，由于k至少为2，那么就可以根据下面这个不等式来求k的上限：
     * n = 1 + k + k^2 + k^3 + ... + k^(m-1) > k^(m-1)
     * 解出 k < n^(1 / (m-1))，其实也可以可以通过 n < k^m - 1 来求出k的准确的下限，但由于是二分查找法，下限直接使用2也没问题。
     * 遍历所有可能的m值，然后利用二分查找法来确定k的值，对每一个k值，通过联合m值算出总和 sum，然后跟n来对比即可
     */
    public static String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        for (int i = (int) (Math.log(num + 1) / Math.log(2)); i >= 2; --i) {
            long left = 2, right = (long) (Math.pow(num, 1.0 / (i - 1)) + 1);
            while (left < right) {
                long mid = left + (right - left) / 2, sum = 0;
                for (int j = 0; j < i; ++j) {
                    sum = sum * mid + 1;
                }
                if (sum == num) {
                    return mid + "";
                }
                if (sum < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return "" + (num - 1);
    }
}

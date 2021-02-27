package com.wz.greedy;

/**
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
 *
 * Example 1:
 * Input: N = 10
 * Output: 9
 *
 * Example 2:
 * Input: N = 1234
 * Output: 1234
 *
 * Example 3:
 * Input: N = 332
 * Output: 299
 *
 * Note: N is an integer in the range [0, 10^9].
 */
public class MonotoneIncreasingDigits {
    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(332));
    }

    /**
     * 求小于等于给定数字，且该数字各位上的数字是递增的
     * 以 332 为例，最后一位 2 小于之前的 3，那么此时将前面位减 1，先变成 322，再往前看，还是小于前面的 3，
     * 那么再将前面位减 1，就变成了 222，此时 222 不是最大的单调递增数，可以将后面两位变成 9，于是乎就有了 299，小于给定的 332，符合题意。
     * 如果给定的数字是 232，那么就会得到 229，这样可以发现规律，要找到从后往前遍历的每一个值升高的位置，将值减一，
     * 并把后面的所有位都变成 9，就可以得到最大的递增数
     * 即：从右向左遍历，遇到值升高的位置，就更新最后位置 index，同时将该位置的值进行减一，遍历结束后，将 index 右边的所有位置值设置为 9
     */
    public static int monotoneIncreasingDigits(int N) {
        if (N < 10) {
            return N;
        }

        char[] array = String.valueOf(N).toCharArray();
        int index = array.length - 1;
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i - 1] > array[i]) {
                index = i - 1;
                array[i - 1]--;
            }
        }

        for (int i = index + 1; i < array.length; i++) {
            array[i] = '9';
        }

        return Integer.parseInt(new String(array));
    }
}

package com.wz.math;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 *
 * Example 1:
 * n = 5
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * Because the 3rd row is incomplete, we return 2.
 *
 * Example 2:
 * n = 8
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * Because the 4th row is incomplete, we return 3.
 */
public class ArrangingCoins {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(6));
        System.out.println(arrangeCoins(8));
    }

    /**
     * 从第一行开始，一行一行的从n中减去，如果此时剩余的硬币没法满足下一行需要的硬币数了，返回当前行数即可
     */
    public static int arrangeCoins(int n) {
        int result = 1, count = n - 1;
        // 检查剩余硬币数
        while (count >= result + 1) {
            result++;
            count -= result;
        }
        return n == 0 ? 0 : result;
    }
}

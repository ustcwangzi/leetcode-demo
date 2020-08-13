package com.wz.array;

/**
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and
 * only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
 *
 * Example 1:
 * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * Output: 1
 *
 * Constraints:
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 */
public class NumberOfEquivalentDominoPairs {
    public static void main(String[] args) {
        int[][] dominoes = new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}};
        System.out.println(numEquivDominoPairs(dominoes));
    }

    /**
     * 为了降低时间复杂度，就必须将二维数组降为一维数组，即把每两个元素变成一个两位数，较小的一个当做十位数，较大的当做个位数
     * 这样一转换后，二维数组就变成了一维数组，此时题目也就变成了计数的问题
     * 因为数组元素的取值范围是[1,9]，所以最大的两位数是99，最小的两位数是11，使用一个长度为100的整型数组即可
     * 最后遍历计数数组中的元素，计算对数，其实就是计算排列组合，有n个数，分两次取，共有 n*(n-1) 种可能，但是需要去重，
     * 因为i要小于j，所以最后就是 n*(n-1)/2 种可能，将每次的结果累加，最后返回即可
     */
    public static int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100];
        // 将两个元素转换为一个两位数
        for (int[] domino : dominoes) {
            int num = Math.min(domino[0], domino[1]) * 10 + Math.max(domino[0], domino[1]);
            count[num]++;
        }

        int result = 0;
        // 计算排列组合
        for (int num : count) {
            result += num * (num - 1) / 2;
        }

        return result;
    }
}

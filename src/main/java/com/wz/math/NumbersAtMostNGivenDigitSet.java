package com.wz.math;

/**
 * Given an array of digits, you can write numbers using each digits[i] as many times as we want.
 * For example, if digits = ['1','3','5'], we may write numbers such as '13', '551', and '1351315'.
 * Return the number of positive integers that can be generated that are less than or equal to a given integer n.
 *
 * Example 1:
 * Input: digits = ["1","3","5","7"], n = 100
 * Output: 20
 * Explanation:
 * The 20 numbers that can be written are:
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 */
public class NumbersAtMostNGivenDigitSet {
    public static void main(String[] args) {
        System.out.println(atMostNGivenDigitSet(new String[]{"1", "3", "5", "7"}, 100));
    }

    /**
     * 无限制次数使用 digits[] 中的任意个数字，能组成多个不同的小于等于 n 的数字。
     * 先来分析例子1，当n为 100 时，所有的一位数和两位数都是可以的，既然可以重复使用数字，假设总共有n个数字，那么对于两位数来说，
     * 十位上和个位上分别都有n种可能，总共就是 n^2 种可能，对于一位数来说，总共n种可能。
     * 那么看到这里就可以归纳出当n总共有 len 位的话，我们就可以快速的求出不超过 len-1 位的所有情况综合，用个 for 循环，累加n的指数即可。
     * 然后就要来分析和数字n位数相等的组合，题目中的两个的例子的n都是1开始的，实际上n可以是任何数字，举个例子来说吧，
     * 假如 digits={"1","3","5","7"}，n=365，那么根据前面的分析，可以很快的算出所有的两位数和一位数的组合情况总数 4 + 4^2 = 20 个。
     * 现在要来分析三位数都有哪些组合，由于digits数组是有序的，所以从开头遍历的话先取到的就是最小的，这时候有三种情况，小于，等于，和大于，
     * 每种的处理情况都有些许不同，这里就拿上面提到的例子进行一步一步的分析：
     * 对于n的百位数字3来说，digits中的1小于n中的百位上的3，那么此时百位上固定为1，十位和个位上就可以是任意值了，即 1xx，共有 4^2 = 16 个。
     * 对于n的百位数字3来说，digits中的3等于n中的百位上的3，那么此时百位上固定为3，十位和个位的值还是不确定，
     * 此时就不能再继续遍历digits中的数字了，因为之后的数字肯定大于3，但是可以继续尝试n的下一位。
     * 对于n的十位数字6来说，digits中的1小于n中的十位上的6，那么百位和十位分别固定为3和1，个位上就可以是任意值了，即 31x，共有 4 个。
     * 对于n的十位数字6来说，digits中的3小于n中的十位上的6，那么百位和十位分别固定为3和3，个位上就可以是任意值了，即 33x，共有 4 个。
     * 对于n的十位数字6来说，digits中的5小于n中的十位上的6，那么百位和十位分别固定为3和5，个位上就可以是任意值了，即 35x，共有 4 个。
     * 对于n的十位数字6来说，digits中的7大于n中的十位上的6，此时再也组不成小于n的数字了，直接返回最终的 20+16+4+4+4=48 个。
     */
    public static int atMostNGivenDigitSet(String[] digits, int n) {
        char[] array = String.valueOf(n).toCharArray();
        int result = 0, len = array.length;
        for (int i = 1; i < len; ++i) {
            result += Math.pow(digits.length, i);
        }

        for (int i = 0; i < len; ++i) {
            boolean hasSameNum = false;
            for (String cur : digits) {
                int compare = cur.compareTo(String.valueOf(array[i]));
                if (compare < 0) {
                    result += Math.pow(digits.length, len - 1 - i);
                } else if (compare == 0) {
                    hasSameNum = true;
                }
            }
            if (!hasSameNum) {
                return result;
            }
        }

        return result + 1;
    }
}

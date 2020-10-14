package com.wz.string;

import java.util.Arrays;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits
 * existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
 *
 * Example:
 * Input: 12
 * Output: 21
 */
public class NextGreaterElementIII {
    public static void main(String[] args) {
        System.out.println(nextGreaterElement(13452));
        System.out.println(nextGreaterElement(4321));
    }

    /**
     * 如果从第 k 位开始到尾部都是递减的并且第 k 位数字比第 k-1 位数字大，表明从第 k 位开始到尾部的这段数字是他们能组合出的最大数字，
     * 在下一个数字中他们要整体倒序变为能组合出的最小数字，倒序后从这段数字序列中找出第一个大于第k-1位数字的位置和第k-1位交换即可。
     * 举例 n=13452，其中52是递减的，而且5>4，先把52倒序，n变为13425，然后从25中找出第一个大于4的数字和4交换，就得到了最终结果13524
     *
     * 1. 从右到左找到第一个降序元素索引 i-1
     * 2. 如果 i == 0，表示从右到左都是升序的，直接返回-1
     * 3. 从右到左找到 i-1 右侧第一个大于 array[i-1] 的元素索引 j，然后交换 array[i-1] 与 array[j]
     * 4. 把 i-1 之后的数字按升序排列就是最终的结果
     * 以 13452 为例说明以上过程：
     * 1. 找到元素 4，索引为(2)
     * 2. 找到第一个大于 4 的元素 5，交换 4 和 5，结果为 13542
     * 3. 从索引(3)开始排序，结果为 13524
     */
    public static int nextGreaterElement(int n) {
        char[] array = String.valueOf(n).toCharArray();
        int i = array.length - 1;
        // 从右到左找到第一个降序元素索引
        for (; i > 0; i--) {
            if (array[i - 1] < array[i]) {
                break;
            }
        }
        // 从右到左都是升序的，直接返回-1
        if (i == 0) {
            return -1;
        }

        // 从右到左找到 i-1 右侧第一个大于 array[i-1] 的元素索引
        int j = array.length - 1;
        for (; j >= i; j--) {
            if (array[j] > array[i - 1]) {
                break;
            }
        }
        char tmp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = tmp;

        Arrays.parallelSort(array, i, array.length);
        long num = Long.parseLong(new String(array));
        return num <= Integer.MAX_VALUE ? (int) num : -1;
    }
}

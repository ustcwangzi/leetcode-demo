package com.wz.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Alice and Bob have candy bars of different sizes:
 * A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.
 * Since they are friends, they would like to exchange one candy bar each so that after the exchange,
 * they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)
 * Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange,
 * and ans[1] is the size of the candy bar that Bob must exchange.
 * If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.
 *
 * Example 1:
 * Input: A = [1,1], B = [2,2]
 * Output: [1,2]
 *
 * Example 2:
 * Input: A = [2], B = [1,3]
 * Output: [2,3]
 *
 * Example 3:
 * Input: A = [1,2,5], B = [2,4]
 * Output: [5,4]
 */
public class FairCandySwap {
    public static void main(String[] args) {
        int[] A = new int[]{1, 1};
        int[] B = new int[]{2, 2};
        System.out.println(Arrays.toString(fairCandySwap(A, B)));

        A = new int[]{2};
        B = new int[]{1, 3};
        System.out.println(Arrays.toString(fairCandySwap(A, B)));

        A = new int[]{1, 2, 5};
        B = new int[]{2, 4};
        System.out.println(Arrays.toString(fairCandySwap(A, B)));
    }

    /**
     * 让两人交换一个糖果，使得交换后两人的糖果总重量相同，而且限定了两人初始时的糖果总量不相同，且一定会有解。
     * 起始时 Alice 和 Bob 两人的糖果总重量的差值一定时偶数，因为最终两人的糖果总量时要相同的，那么起始时的重量差就应该能平均分为两部分，
     * 一部分来弥补轻的一方，一部分来抵消重的一方。那么有了这个 diff，只需要在两个数组中查找差值为 diff 的两个数字了，
     * 其实就是 {@link TwoSum} 的变种，使用一个 HashSet 先来保存数组 A 中所有的数字，
     * 然后遍历数组B中的每个数字 num，查找 HashSet 中否存在 num+diff 即可
     */
    public static int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        // 计算需要平分的重量
        int diff = (sumA - sumB) / 2;

        Set<Integer> set = new HashSet<>(A.length);
        for (int num : A) {
            set.add(num);
        }

        for (int num : B) {
            // 查找满足平分重量的元素
            if (set.contains(num + diff)) {
                return new int[]{num + diff, num};
            }
        }

        return null;
    }
}

package com.wz.array;

import java.util.LinkedList;
import java.util.List;

/**
 * For a non-negative integer X, the array-form of X is an array of its digits in left to right order.
 * For example, if X = 1231, then the array form is [1,2,3,1].
 * Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.
 *
 * Example 1:
 * Input: A = [2,7,4], K = 181
 * Output: [4,5,5]
 * Explanation: 274 + 181 = 455
 *
 * Example 2:
 * Input: A = [2,1,5], K = 806
 * Output: [1,0,2,1]
 * Explanation: 215 + 806 = 1021
 *
 * Example 3:
 * Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * Output: [1,0,0,0,0,0,0,0,0,0,0]
 * Explanation: 9999999999 + 1 = 10000000000
 */
public class AddToArrayFormOfInteger {
    public static void main(String[] args) {
        int[] A = new int[]{2, 7, 4};
        System.out.println(addToArrayForm(A, 181));

        A = new int[]{2, 1, 5};
        System.out.println(addToArrayForm(A, 806));
    }

    /**
     * 将 K 转换成链表，然后逆向遍历 kList 和 数组A，将相加的值对10取余后放入结果中，同时记录是否产生进位
     * 最后对剩余的 kList 和 数组A，再次进行遍历，直接将元素值放入结果中
     */
    public static List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> kList = new LinkedList<>();
        while (K / 10 > 0) {
            kList.addFirst(K % 10);
            K = K / 10;
        }
        kList.addFirst(K);

        LinkedList<Integer> result = new LinkedList<>();
        boolean bigThanNine = false;
        int i = A.length - 1;
        while (i >= 0 && kList.size() > 0) {
            int sum = A[i] + kList.pollLast() + (bigThanNine ? 1 : 0);
            result.addFirst(sum % 10);
            bigThanNine = sum > 9;
            i--;
        }
        while (i >= 0) {
            int sum = A[i] + (bigThanNine ? 1 : 0);
            result.addFirst(sum % 10);
            bigThanNine = sum > 9;
            i--;
        }
        while (kList.size() > 0) {
            int sum = kList.pollLast() + (bigThanNine ? 1 : 0);
            result.addFirst(sum % 10);
            bigThanNine = sum > 9;
        }
        if (bigThanNine) {
            result.addFirst(1);
        }
        return result;
    }
}

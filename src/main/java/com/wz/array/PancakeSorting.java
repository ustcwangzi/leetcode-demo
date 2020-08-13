package com.wz.array;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length,
 * then reverse the order of the first k elements of A.
 * We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.
 * Return the k-values corresponding to a sequence of pancake flips that sort A.
 * Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
 *
 * Example 1:
 * Input: [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation:
 * We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 * Starting state: A = [3, 2, 4, 1]
 * After 1st flip (k=4): A = [1, 4, 2, 3]
 * After 2nd flip (k=2): A = [4, 1, 2, 3]
 * After 3rd flip (k=4): A = [3, 2, 1, 4]
 * After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
 */
public class PancakeSorting {
    public static void main(String[] args) {
        int[] A = new int[]{3, 2, 4, 1};
        System.out.println(pancakeSort(A));
    }

    /**
     * 每次找出最大元素把它翻到最前，再把所有未排序的整个做翻转，最大元素就翻到当前范围最后去了
     */
    public static List<Integer> pancakeSort(int[] A) {
        List<Integer> result = new LinkedList<>();
        for (int max = A.length; max >= 1; max--) {
            for (int i = 0; i < max; i++) {
                if (A[i] == max) {
                    // 把最大数放在第一位
                    reverse(A, 0, i);
                    // 把最大数放在未排序的最后一位
                    reverse(A, 0, max - 1);
                    result.add(i + 1);
                    result.add(max);
                    break;
                }
            }
        }
        return result;
    }

    private static void reverse(int[] A, int left, int right) {
        for (int i = left, j = right; i < j; i++, j--) {
            int tmp = A[left];
            A[left] = A[right];
            A[right] = tmp;
        }
    }
}

package com.wz.lists;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
 * Return any permutation of A that maximizes its advantage with respect to B.
 *
 * Example 1:
 * Input: A = [2,7,11,15], B = [1,10,4,11]
 * Output: [2,11,7,15]
 *
 * Example 2:
 * Input: A = [12,24,8,32], B = [13,25,32,11]
 * Output: [24,32,8,12]
 *
 * Note:
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 */
public class AdvantageShuffle {
    public static void main(String[] args) {
        int[] A = new int[]{2, 7, 11, 15};
        int[] B = new int[]{1, 10, 4, 11};
        System.out.println(Arrays.toString(advantageCount(A, B)));

        A = new int[]{12, 24, 8, 32};
        B = new int[]{13, 25, 32, 11};
        System.out.println(Arrays.toString(advantageCount(A, B)));
    }

    /**
     * 田忌赛马
     * 既然要想办法大过B中的数，那么对于B中的每个数，先在A中找刚好大于该数的数字，用太大的数字就浪费了，如果A中没有比之大的数字，就用A中最小数字
     * 当两个数组都是有序的时候，就能快速找到各自的最大值与最小值，问题就变得容易很多了
     * 比如可以先从B的最大值开始，看A的最大值能否大过B，能的话，就移动到对应位置，不能的话就用最小值，然后再看B的次大值，这样双指针就可以解决问题
     * 所以可以先给A按从小到大的顺序，对于B的话，不能直接排序，以免原来的顺序丢失，将B中每个数字和其坐标组成一个 pair ，加入到一个最大堆中，
     * 这样B中的最大值就会最先被取出来，再进行上述的操作，根据 pair 中的坐标就可以直接更新结果 result 中对应的位置了
     */
    public static int[] advantageCount(int[] A, int[] B) {
        int n = A.length;
        int[] sortedA = A.clone(), result = new int[n];
        Arrays.sort(sortedA);

        // 大根堆
        PriorityQueue<Pair> queue = new PriorityQueue<>(n, (o1, o2) -> Integer.compare(o2.k, o1.k));
        for (int i = 0; i < n; i++) {
            queue.offer(new Pair(B[i], i));
        }

        int left = 0, right = n - 1;
        // 依次取出B的最大值与排序后的A进行比较
        while (!queue.isEmpty()) {
            Pair top = queue.poll();
            int value = top.k, index = top.v;
            if (sortedA[right] > value) {
                result[index] = sortedA[right--];
            } else {
                result[index] = sortedA[left++];
            }
        }

        return result;
    }

    static class Pair {
        int k;
        int v;

        public Pair(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }
}

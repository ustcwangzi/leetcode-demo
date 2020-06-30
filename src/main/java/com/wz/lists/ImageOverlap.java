package com.wz.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
 * We translate one image however we choose (sliding it left, right, up, or down any number of units),
 * and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
 * (Note also that a translation does not include any kind of rotation.)
 * What is the largest possible overlap?
 * Notes:
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 *
 * Example:
 * Input: A = [[1,1,0],
 *             [0,1,0],
 *             [0,1,0]]
 *        B = [[0,0,0],
 *             [0,1,1],
 *             [0,0,1]]
 * Output: 3
 * Explanation: We slide A to right by 1 unit and down by 1 unit.
 */
public class ImageOverlap {
    public static void main(String[] args) {
        int[][] A = new int[][]{
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        int[][] B = new int[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 1}
        };
        System.out.println(largestOverlap(A, B));
    }

    /**
     * 由于只有值为1的地方才有可能重叠，所以只需要关心A和B中值为1的地方，将其坐标位置分别存入两个数组 listA 和 listB 中。
     * 由于对于A和B中的任意两个1的位置，肯定有一种方法能将A平移到B，平移的方法就是横向平移其横坐标之差，竖向平移其纵坐标之差。
     * 由于其是一一对应关系，所以只要是横纵坐标差相同的两对儿位置，一定是在同一次平移上。
     * 那么就需要一个 HashMap 来建立坐标差值和其出现次数之间的映射，为了降维，将横纵坐标之差转为字符串，这样只要组成了相同的字符串，
     * 那么一定就是在同一个平移上，重叠部分加1。最后在 HashMap 中找到最大的值即可
     */
    public static int largestOverlap(int[][] A, int[][] B) {
        List<int[]> listA = new ArrayList<>();
        List<int[]> listB = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i][j] == 1) {
                    listA.add(new int[]{i, j});
                }
                if (B[i][j] == 1) {
                    listB.add(new int[]{i, j});
                }
            }
        }

        Map<String, Integer> diffMap = new HashMap<>();
        for (int[] pairA : listA) {
            for (int[] pairB : listB) {
                // 将横纵坐标之差转为字符串
                String key = (pairA[0] - pairB[0]) + " " + (pairA[1] - pairB[1]);
                diffMap.put(key, diffMap.getOrDefault(key, 0) + 1);
            }
        }

        int result = 0;
        for (int value : diffMap.values()) {
            result = Math.max(result, value);
        }

        return result;
    }
}

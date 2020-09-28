package com.wz.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the following details of a matrix with n columns and 2 rows :
 * 1. The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
 * 2. The sum of elements of the 0-th(upper) row is given as upper.
 * 3. The sum of elements of the 1-st(lower) row is given as lower.
 * 4. The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as an integer array with length n.
 * Your task is to reconstruct the matrix with upper, lower and colsum.
 * Return it as a 2-D integer array.
 * If there are more than one valid solution, any of them will be accepted.
 * If no valid solution exists, return an empty 2-D array.
 *
 * Example 1:
 * Input: upper = 2, lower = 1, colsum = [1,1,1]
 * Output: [[1,1,0],[0,0,1]]
 * Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.
 *
 * Example 2:
 * Input: upper = 2, lower = 3, colsum = [2,2,1,1]
 * Output: []
 */
public class Reconstruct2RowBinaryMatrix {
    public static void main(String[] args) {
        System.out.println(reconstructMatrix(2, 1, new int[]{1, 1, 1}));
        System.out.println(reconstructMatrix(2, 3, new int[]{2, 2, 1, 1}));
    }

    /**
     * 构建一个两行多列的数据，第一行的和为upper，第二行的和为lower，第i 列的和为 colsum[i], 如果不存在这样的数组返回空值
     * 1. 如果 upper + lower 不等于 colsum 的和，那么返回空。
     * 2. 如果 colsum 中2的个数大于 upper 或者 lower，返回空
     * 3. 从左到右，尽量保持上下均衡的前提下中插入1。（当colums[i]==1的时候，如果lower >= upper 插下，其余情况插上）
     */
    public static List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int sum = 0, numof2 = 0;
        for (int column : colsum) {
            sum += column;
            if (column == 2) {
                numof2++;
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        if (sum != upper + lower || numof2 > Math.min(upper, lower)) {
            return result;
        }

        List<Integer> upperList = new ArrayList<>(), lowerList = new ArrayList<>();
        result.add(upperList);
        result.add(lowerList);
        for (int column : colsum) {
            if (column == 0) {
                // colsum[i]=0，则 一定上下均为 0
                upperList.add(0);
                lowerList.add(0);
            } else if (column == 2) {
                // colsum[i]=2，则 一定上下均为 1
                upperList.add(1);
                upper--;
                lowerList.add(1);
                lower--;
            } else {
                // colsum[i]=1，则 上下一个 1 一个 0
                if (upper > lower) {
                    upperList.add(1);
                    lowerList.add(0);
                    upper--;
                } else {
                    upperList.add(0);
                    lowerList.add(1);
                    lower--;
                }
            }
        }
        return result;
    }
}

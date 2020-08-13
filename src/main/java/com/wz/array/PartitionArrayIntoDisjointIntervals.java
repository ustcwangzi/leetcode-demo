package com.wz.array;

/**
 * Given an array A, partition it into two (contiguous) subarrays left and right so that:
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 *
 * Example 1:
 * Input: [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 *
 * Example 2:
 * Input: [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 */
public class PartitionArrayIntoDisjointIntervals {
    public static void main(String[] args) {
        int[] A = new int[]{5, 0, 3, 8, 6};
        System.out.println(partitionDisjoint(A));

        A = new int[]{1, 1, 1, 0, 6, 12};
        System.out.println(partitionDisjoint(A));
    }

    /**
     * partition 表示分割点的位置，leftMax 表示 left 中的最大值，curMax 表示当前的最大值
     * 遍历每个数字，更新当前最大值 curMax，并且判断若当前数字 A[i] 小于 leftMax，说明此时整个遍历到的区域都是属于 left 的，
     * 所以 leftMax 要更新为 curMax，并且当前位置也就是潜在的分割点，所以 partitionIdx 更新为i
     */
    public static int partitionDisjoint(int[] A) {
        int leftMax = A[0], partition = 0, curMax = A[0];
        for (int i = 1; i < A.length; i++) {
            curMax = Math.max(curMax, A[i]);
            if (A[i] < leftMax) {
                leftMax = curMax;
                partition = i;
            }
        }

        return partition + 1;
    }
}

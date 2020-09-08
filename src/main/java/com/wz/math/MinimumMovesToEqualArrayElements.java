package com.wz.math;

/**
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
 *
 * Example:
 * Input:
 * [1,2,3]
 * Output:
 * 3
 * Explanation:
 * Only three moves are needed (remember each move increments two elements):
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
public class MinimumMovesToEqualArrayElements {
    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{1, 2, 3}));
    }

    /**
     * 每次可以对 n-1 个数字同时加1，问最少需要多少次这样的操作才能让数组中所有的数字相等。
     * 为了快速的缩小差距，每次需要给除了数组最大值的所有数字加1，这样能快速的到达平衡状态，但这样会超时。
     * 需要换一个角度来看问题，其实给 n-1 个数字加1，效果等同于给那个未被选中的数字减1，
     * 比如数组 [1，2，3]，给除去最大值的其他数字加1，变为 [2，3，3]，全体减1，并不影响数字间相对差异，变为 [1，2，2]，
     * 那么问题也可能转化为，将所有数字都减小到最小值
     * 求出数组的数字之和 sum，然后用 sum 减去最小值和数组长度的乘积，就是最终答案
     */
    public static int minMoves(int[] nums) {
        long min = Integer.MAX_VALUE, sum = 0;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        return (int) (sum - min * nums.length);
    }
}

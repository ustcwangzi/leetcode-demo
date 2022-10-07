package com.wz.binarysearch;

import java.util.Arrays;

/**
 * You have n packages that you are trying to place in boxes, one package in each box.
 * There are m suppliers that each produce boxes of different sizes (with infinite supply).
 * A package can be placed in a box if the size of the package is less than or equal to the size of the box.
 * The package sizes are given as an integer array packages, where packages[i] is the size of the ith package.
 * The suppliers are given as a 2D integer array boxes, where boxes[j] is an array of box sizes that the jth supplier produces.
 * You want to choose a single supplier and use boxes from them such that the total wasted space is minimized.
 * For each package in a box, we define the space wasted to be size of the box - size of the package.
 * The total wasted space is the sum of the space wasted in all the boxes.
 * For example, if you have to fit packages with sizes [2,3,5] and the supplier offers boxes of sizes [4,8],
 * you can fit the packages of size-2 and size-3 into two boxes of size-4 and the package with size-5 into a box of size-8.
 * This would result in a waste of (4-2) + (4-3) + (8-5) = 6.
 * Return the minimum total wasted space by choosing the box supplier optimally, or -1 if it is impossible to fit all the packages inside boxes.
 * Since the answer may be large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: packages = [2,3,5], boxes = [[4,8],[2,8]]
 * Output: 6
 * Explanation: It is optimal to choose the first supplier, using two size-4 boxes and one size-8 box.
 * The total waste is (4-2) + (4-3) + (8-5) = 6.
 *
 * Example 2:
 * Input: packages = [2,3,5], boxes = [[1,4],[2,3],[3,4]]
 * Output: -1
 * Explanation: There is no box that the package of size 5 can fit in.
 *
 * Example 3:
 * Input: packages = [3,5,8,10,11,12], boxes = [[12],[11,9],[10,5,14]]
 * Output: 9
 * Explanation: It is optimal to choose the third supplier, using two size-5 boxes, two size-10 boxes, and two size-14 boxes.
 * The total waste is (5-3) + (5-5) + (10-8) + (10-10) + (14-11) + (14-12) = 9.
 *
 * Constraints:
 * 1. n == packages.length
 * 2. m == boxes.length
 * 3. 1 <= n <= 105
 * 4. 1 <= m <= 105
 * 5. 1 <= packages[i] <= 105
 * 6. 1 <= boxes[j].length <= 105
 * 7. 1 <= boxes[j][k] <= 105
 * 8. sum(boxes[j].length) <= 105
 * 9. The elements in boxes[j] are distinct.
 */
public class MinimumSpaceWastedFromPackaging {
    public static void main(String[] args) {
        System.out.println(minWastedSpace(new int[]{2, 3, 5}, new int[][]{{2, 8}, {4, 8}}));
        System.out.println(minWastedSpace(new int[]{2, 3, 5}, new int[][]{{1, 4}, {2, 3}, {3, 4}}));
        System.out.println(minWastedSpace(new int[]{3, 5, 8, 10, 11, 12}, new int[][]{{12}, {11, 9}, {10, 5, 14}}));
        System.out.println(minWastedSpace(new int[]{7, 6, 5, 3, 4}, new int[][]{{2, 7}, {6}, {10, 5}}));
    }

    /**
     * 分别对 package、box 进行排序，然后遍历 box，若 最大的box 小于 最大的package，则本组 box 不满足条件，继续遍历下一组 box
     * 否则遍历本组 box，针对当前 boxSize，在 package 二分查找小于等于 boxSize 的最大索引 index
     * 若存在则说明 index 以及之前的 package 全部可以使用当前 box，将 boxSize 累加到结果中，继续遍历本组的下一个 boxSize
     * 注意，因为 package、box 都是已经排序的，下一次二分查找的开始位置可以从上一次查找结果的下一个位置开始，即 index+1
     * 每遍历完成一组，就更新 totalBoxSize，最后 totalBoxSize - totalPackageSize 就是需要的结果
     */
    public static int minWastedSpace(int[] packages, int[][] boxes) {
        long totalBoxSize = Long.MAX_VALUE, mod = 1000000007;
        Arrays.parallelSort(packages);
        for (int[] box : boxes) {
            Arrays.parallelSort(box);
            if (box[box.length - 1] < packages[packages.length - 1]) {
                continue;
            }

            int left = 0;
            long sum = 0;
            for (int boxSize : box) {
                int index = searchMaxIndexLessOrEqual(packages, boxSize, left);
                if (index != -1) {
                    sum += (long) boxSize * (index - left + 1);
                    left = index + 1;
                }
            }
            totalBoxSize = Math.min(sum, totalBoxSize);
        }

        if (totalBoxSize == Long.MAX_VALUE) {
            return -1;
        }
        return (int) ((totalBoxSize - Arrays.stream(packages).asLongStream().sum()) % mod);
    }

    /**
     * 在 nums[left...n-1] 中查找小于等于 target 的最大索引位置
     */
    private static int searchMaxIndexLessOrEqual(int[] nums, int target, int left) {
        int right = nums.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}

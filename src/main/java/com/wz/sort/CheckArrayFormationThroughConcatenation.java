package com.wz.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of distinct integers arr and an array of integer arrays pieces,
 * where the integers in pieces are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order.
 * However, you are not allowed to reorder the integers in each array pieces[i].
 * Return true if it is possible to form the array arr from pieces. Otherwise, return false.
 *
 * Example 1:
 * Input: arr = [15,88], pieces = [[88],[15]]
 * Output: true
 * Explanation: Concatenate [15] then [88]
 *
 * Example 2:
 * Input: arr = [49,18,16], pieces = [[16,18,49]]
 * Output: false
 * Explanation: Even though the numbers match, we cannot reorder pieces[0].
 *
 * Constraints:
 * 1. 1 <= pieces.length <= arr.length <= 100
 * 2. sum(pieces[i].length) == arr.length
 * 3. 1 <= pieces[i].length <= arr.length
 * 4. 1 <= arr[i], pieces[i][j] <= 100
 * 5. The integers in arr are distinct.
 * 6. The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
 */
public class CheckArrayFormationThroughConcatenation {
    public static void main(String[] args) {
        System.out.println(canFormArray(new int[]{15, 88}, new int[][]{{88}, {15}}));
        System.out.println(canFormArray(new int[]{49, 18, 16}, new int[][]{{16, 18, 49}}));
    }

    /**
     * 是否能够调整 pieces 块的顺序，让得到整数序列和 arr 是一样的，pieces 块之间可以交换顺序，但块内部不能交换
     * 因为所有数字都没有重复，因此可以使用 map 来存储块，key 是该块的第一个元素，value 是该块
     * 然后通过 arr 的元素去定位块，若不存在则直接返回 false，否则遍历块，判断顺序是否一致
     */
    public static boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>(pieces.length);
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }

        int i = 0;
        while (i < arr.length) {
            if (!map.containsKey(arr[i])) {
                return false;
            }

            int[] piece = map.get(arr[i]);
            for (int cur : piece) {
                if (cur != arr[i]) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }
}

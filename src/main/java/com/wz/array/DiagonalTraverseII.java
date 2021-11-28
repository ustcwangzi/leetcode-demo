package com.wz.array;

import java.util.*;

/**
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 *
 * Example 1:
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 *
 * Example 2:
 * @link ../../../../resource/DiagonalTraverseII.jpg
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 *
 * Example 3:
 * Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * Output: [1,4,2,5,3,8,6,9,7,10,11]
 *
 * Example 4:
 * Input: nums = [[1,2,3,4,5,6]]
 * Output: [1,2,3,4,5,6]
 */
public class DiagonalTraverseII {
    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        nums.add(list);

        list = new ArrayList<>();
        list.add(6);
        list.add(7);
        nums.add(list);

        list = new ArrayList<>();
        list.add(8);
        nums.add(list);

        list = new ArrayList<>();
        list.add(9);
        list.add(10);
        list.add(11);
        nums.add(list);

        list = new ArrayList<>();
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        nums.add(list);

        System.out.println(Arrays.toString(findDiagonalOrder(nums)));
    }

    /**
     * 同一个斜方向的 row+col 相等，因为可以使用 map 进行记录
     * 同时对 nums 从下往上遍历，出现的数字就是从前往后的顺序，按照 row+col 存入 map，最后按照 row+col 从小到大输出即可
     */
    public static int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int length = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            List<Integer> list = nums.get(i);
            for (int j = 0; j < list.size(); j++) {
                length++;
                map.putIfAbsent(i + j, new ArrayList<>());
                map.get(i + j).add(list.get(j));
            }
        }

        int[] result = new int[length];
        int index = 0;
        for (int key = 0; key < map.keySet().size(); key++) {
            List<Integer> list = map.get(key);
            for (Integer i : list) {
                result[index++] = i;
            }
        }

        return result;
    }

}

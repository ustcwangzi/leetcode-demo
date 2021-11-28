package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.
 * Given the array reservedSeats containing the numbers of seats already reserved, for example,
 * reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.
 * Return the maximum number of four-person groups you can assign on the cinema seats. A four-person group occupies four
 * adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent,
 * but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle split a
 * four-person group in the middle, which means to have two people on each side.
 *
 * Example 1:
 * @link ../../../../resource/CinemaSeatAllocation.jpg
 * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * Output: 4
 * Explanation: The figure above shows the optimal allocation for four groups,
 *              where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
 *
 * Example 2:
 * Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * Output: 2
 *
 * Example 3:
 * Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * Output: 4
 */
public class CinemaSeatAllocation {
    public static void main(String[] args) {
        int[][] reservedSeats = new int[][]{
                {1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}
        };
        System.out.println(maxNumberOfFamilies(3, reservedSeats));

        reservedSeats = new int[][]{
                {2, 1}, {1, 8}, {2, 6}
        };
        System.out.println(maxNumberOfFamilies(2, reservedSeats));
    }

    /**
     * 使用bit标记法，对每一排标记为 0b 000 0000 000 的一个二进制数
     * 使用 map 来保存被预订后每一行座位状况，如果某个位置被预定，则该位的二进制值为 1
     * 遍历 reservedSeats，更新所有被预定的位置
     * 然后再遍历 map 中每一行座位的预订情况，对于每行：
     * 1. 满足 011 1111 110 这些1的位置未被占用，有2种选择
     * 2. 满足 011 1100 000 这些1的位置未被占用，有1种选择
     * 3. 满足 000 0011 110 这些1的位置未被占用，有1种选择
     * 4. 满足 000 1111 000 这些1的位置未被占用，有1种选择
     * 把所有选择累加到 result 中
     * 再加上整行未被占用的情况，有 n-map.size() 行未被占用，每行有2种选择
     * 因此，最终结果为 result + 2*(n-map.size())
     */
    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reservedMap = new HashMap<>(reservedSeats.length);
        for (int[] seats : reservedSeats) {
            int row = seats[0];
            int col = seats[1] - 1;
            reservedMap.put(row, reservedMap.getOrDefault(row, 0) | (1 << col));
        }

        // 整行未被占用
        int result = (n - reservedMap.size()) * 2;
        int b1 = 0b011_1100_000, b2 = 0b000_0011_110, b3 = 0b000_1111_000;
        for (int row : reservedMap.values()) {
            int count = 0;
            if ((row & b1) == 0) {
                count++;
            }
            if ((row & b2) == 0) {
                count++;
            }
            if (count == 0 && (row & b3) == 0) {
                count = 1;
            }
            result += count;
        }

        return result;
    }
}

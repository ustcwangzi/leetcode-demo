package com.wz.other;

/**
 * You have a cubic storeroom where the width, length, and height of the room are all equal to n units.
 * You are asked to place n boxes in this room where each box is a cube of unit side length. There are however some rules to placing the boxes:
 * - You can place the boxes anywhere on the floor.
 * - If box x is placed on top of the box y, then each side of the four vertical sides of the box y must either be adjacent to another box or to a wall.
 * Given an integer n, return the minimum possible number of boxes touching the floor.
 *
 * Example 1:
 * @link ../../../../resource/BuildingBoxes1.jpg
 * Input: n = 3
 * Output: 3
 * Explanation: The figure above is for the placement of the three boxes.
 * These boxes are placed in the corner of the room, where the corner is on the left side.
 *
 * Example 2:
 * @link ../../../../resource/BuildingBoxes2.jpg
 * Input: n = 4
 * Output: 3
 * Explanation: The figure above is for the placement of the four boxes.
 * These boxes are placed in the corner of the room, where the corner is on the left side.
 *
 * Example 3:
 * @link ../../../../resource/BuildingBoxes3.jpg
 * Input: n = 10
 * Output: 6
 * Explanation: The figure above is for the placement of the ten boxes.
 * These boxes are placed in the corner of the room, where the corner is on the back side.
 *
 *
 * Constraints:
 * 1 <= n <= 10^9
 */
public class BuildingBoxes {
    public static void main(String[] args) {
        System.out.println(minimumBoxes(3));
        System.out.println(minimumBoxes(4));
        System.out.println(minimumBoxes(10));
    }

    /**
     * @link ../../../../resource/BuildingBoxes.jpg
     * 使用 rowCapacity、maxRowCapacity 代表已经占用的 box 个数和最大能占用的 box 个数
     * 然后逐层堆叠，当 rowCapacity == maxRowCapacity 时，需要将 maxRowCapacity 增大，同时 rowCapacity 重置
     */
    public static int minimumBoxes(int n) {
        int rowCapacity = 1, maxRowCapacity = 1;
        int result = 0;
        while (n > 0) {
            result++;
            n -= rowCapacity;
            if (rowCapacity == maxRowCapacity) {
                rowCapacity = 1;
                maxRowCapacity++;
            } else {
                rowCapacity++;
            }
        }
        return result;
    }
}

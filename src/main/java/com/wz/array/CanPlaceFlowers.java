package com.wz.array;

/**
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not.
 * However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
 * and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 *
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 *
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: False
 */
public class CanPlaceFlowers {
    public static void main(String[] args) {
        int[] flowerbed = new int[]{1, 0, 0, 0, 1};
        System.out.println(canPlaceFlowers(flowerbed, 1));
        System.out.println(canPlaceFlowers(flowerbed, 2));
    }

    /**
     * 不能有相邻的花，那么对每个位置要取决约其左右位置
     * 找到三个连续为0即可插入一个，即对于每个位置i，只需要判断左边i-1、右边i+1这三个位置数值是否都为0即可
     * 然后，使用一个count计算插入的个数，若大于等于n，则可以实现全部插入，否则不能
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            boolean left = i - 1 < 0 || flowerbed[i - 1] == 0;
            boolean right = i + 1 >= flowerbed.length || flowerbed[i + 1] == 0;

            if (flowerbed[i] == 0 && left && right) {
                flowerbed[i] = 1;
                count++;
                if (count >= n) {
                    return true;
                }
            }
        }

        return count >= n;
    }
}

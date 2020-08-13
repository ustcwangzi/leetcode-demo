package com.wz.array;

/**
 * There is a room with n bulbs, numbered from 1 to n, arranged in a row from left to right. Initially, all the bulbs are turned off.
 * At moment k (for k from 0 to n - 1), we turn on the light[k] bulb.
 * A bulb change color to blue only if it is on and all the previous bulbs (to the left) are turned on too.
 * Return the number of moments in which all turned on bulbs are blue.
 *
 * Example 1:
 * Input: light = [2,1,3,5,4]
 * Output: 3
 * Explanation: All bulbs turned on, are blue at the moment 1, 2 and 4.
 *
 * Example 2:
 * Input: light = [3,2,4,1,5]
 * Output: 2
 * Explanation: All bulbs turned on, are blue at the moment 3, and 4 (index-0).
 *
 * Example 3:
 * Input: light = [4,1,2,3]
 * Output: 1
 * Explanation: All bulbs turned on, are blue at the moment 3 (index-0).
 * Bulb 4th changes to blue at the moment 3.
 */
public class BulbSwitcherIII {
    public static void main(String[] args) {
        int[] lights = new int[]{2, 1, 3, 5, 4};
        System.out.println(numTimesAllBlue(lights));

        lights = new int[]{3, 2, 4, 1, 5};
        System.out.println(numTimesAllBlue(lights));
    }

    /**
     * 若某时刻 t 所有亮着等都是蓝色，那么该时刻亮着的灯一定是第1盏到第t盏，即 light[0] ... light[t-1] 的元素值都是 [1, t] 之间
     * 因此只要 light[0] ... light[t-1] 的最大值是 t 时，就说明第1盏到第t盏灯都被打开了
     */
    public static int numTimesAllBlue(int[] light) {
        int result = 0, max = 0;
        for (int i = 0; i < light.length; i++) {
            max = Math.max(max, light[i]);
            result += max == i + 1 ? 1 : 0;
        }
        return result;
    }
}

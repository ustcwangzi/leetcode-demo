package com.wz.array;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * @link ../../../../resource/TrappingRainWater.jpg
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trapOne(height));
        System.out.println(trapTwo(height));
    }

    /**
     * 对于每个元素都要考虑它能接多少雨水，每个元素能接的雨水量是：min(当前位置左边最大高度,当前位置右边最大高度)-当前位置高度
     * 从左到右遍历得到左边最大高度，然后从右到左遍历得到右边最大高度，最后计算每个位置的能接的雨水即可
     */
    public static int trapOne(int[] height) {
        if (height.length <= 2) {
            return 0;
        }

        int[] leftHeight = new int[height.length];
        int[] rightHeight = new int[height.length];
        int leftMax = 0, rightMax = 0, result = 0;

        // 从左到右遍历得到左边最大高度
        for (int i = 1; i < height.length; i++) {
            leftMax = Math.max(height[i - 1], leftMax);
            leftHeight[i] = leftMax;
        }

        // 从右到左遍历得到右边最大高度
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax = Math.max(height[i + 1], rightMax);
            rightHeight[i] = rightMax;
        }

        // 计算每个位置的能接的雨水
        for (int i = 0; i < height.length; i++) {
            int waterTrap = Math.min(leftHeight[i], rightHeight[i]) - height[i];
            result += Math.max(waterTrap, 0);
        }
        return result;
    }

    /**
     * 方案一需要O(n)的空间，因为存储了左右两边的最大数，其实可以不用存储
     * 因为最终计算每个位置的结果时用的是：min{当前位置左边最大高度,当前位置右边最大高度} - 当前位置高度
     * 因此只需要知道对于每个位置，其左右两边最大高度中较小的那个，然后减去自己的高度即可
     * 用左右指针 left 和 right 从两侧开始遍历，如果左边的数较小，说明对当前位置来说，其左右两边最大高度中较小的那个一定在左边
     * 因此更新左边的最大高度，然后计算面积即可；如果右边的数较小，则更新右边的最大高度，然后计算面积
     */
    public static int trapTwo(int[] height) {
        if (height.length <= 2) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, result = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                result += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }
}

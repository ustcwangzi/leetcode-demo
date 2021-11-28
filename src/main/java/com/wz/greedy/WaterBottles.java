package com.wz.greedy;

/**
 * Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.
 * The operation of drinking a full water bottle turns it into an empty bottle.
 * Return the maximum number of water bottles you can drink.
 *
 * Example 1:
 * @link ../../../../resource/WaterBottles1.jpg
 * Input: numBottles = 9, numExchange = 3
 * Output: 13
 * Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
 * Number of water bottles you can drink: 9 + 3 + 1 = 13.
 *
 * Example 2:
 * @link ../../../../resource/WaterBottles2.jpg
 * Input: numBottles = 15, numExchange = 4
 * Output: 19
 * Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
 * Number of water bottles you can drink: 15 + 3 + 1 = 19.
 *
 * Constraints:
 * 1. 1 <= numBottles <= 100
 * 2. 2 <= numExchange <= 100
 */
public class WaterBottles {
    public static void main(String[] args) {
        System.out.println(numWaterBottles(9, 3));
        System.out.println(numWaterBottles(15, 4));
    }

    /**
     * 初始结果就是瓶子个数，当瓶子数大于等于交换数时，每次可交换的数量为 瓶子数 除 交换数
     * 每次交换之后剩余的瓶子数为 (瓶子数 除 交换数)  + (瓶子数 对 交换数 取余)
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        int result = numBottles;
        while (numBottles >= numExchange) {
            result += numBottles / numExchange;
            numBottles = (numBottles / numExchange) + (numBottles % numExchange);
        }
        return result;
    }
}

package com.wz.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [positioni, speedi] represents:
 * - positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that positioni < positioni+1.
 * - speedi is the initial speed of the ith car in meters per second.
 * For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position.
 * Once a car collides with another car, they unite and form a single car fleet.
 * The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.
 * Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car,
 * or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.
 *
 * Example 1:
 * Input: cars = [[1,2],[2,1],[4,3],[7,2]]
 * Output: [1.00000,-1.00000,3.00000,-1.00000]
 * Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s.
 * After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.
 *
 * Example 2:
 * Input: cars = [[3,4],[5,4],[6,3],[9,1]]
 * Output: [2.00000,1.00000,1.50000,-1.00000]
 *
 * Constraints:
 * 1. 1 <= cars.length <= 10^5
 * 2. 1 <= positioni, speedi <= 10^6
 * 3. positioni < positioni+1
 */
public class CarFleetII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getCollisionTimes(new int[][]{{1, 2}, {2, 1}, {4, 3}, {7, 2}})));
        System.out.println(Arrays.toString(getCollisionTimes(new int[][]{{3, 4}, {5, 4}, {6, 3}, {9, 1}})));
    }

    /**
     * 单调栈，
     * 当后一辆车与前一辆车相遇后，后车速度变为与前车一致（前车速度一定慢于后车，否则不会相遇），位置与前车相同，等效于后车已消失，之后不用再考虑
     * 因此一辆车是否与前车相遇，只与它的前车有关，而与后车无关，在这一基础上，可从右向左考虑各车的相遇问题
     * 使用单调栈，存储当前车右侧且从快到慢的车，未入栈的车已与栈中的车相遇而被合并，当考虑新的一辆车时，只需依次考虑该车与栈顶各车是否能够相遇即可
     */
    public static double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] result = new double[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            int position = cars[i][0], speed = cars[i][1];
            while (!stack.isEmpty()) {
                int topPosition = cars[stack.peek()][0], topSpeed = cars[stack.peek()][1];
                // 当前车速较小，不会遇到后面的车
                if (speed <= topSpeed) {
                    stack.pop();
                    continue;
                }

                // 计算相遇时间
                double collisionTime = (double) (topPosition - position) / (speed - topSpeed);
                // 和栈顶车辆相遇，更新相遇时间
                if (collisionTime <= result[stack.peek()] || result[stack.peek()] == -1) {
                    result[i] = collisionTime;
                    break;
                }
                // 不是和栈顶相遇，而是和其他车辆相遇，需要将栈顶弹出
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
}

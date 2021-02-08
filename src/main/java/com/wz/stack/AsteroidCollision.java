package com.wz.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction
 * (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Example 2:
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3:
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * Example 4:
 * Input: asteroids = [-2,-1,1,2]
 * Output: [-2,-1,1,2]
 * Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 *
 * Constraints:
 * 1. 2 <= asteroids.length <= 104
 * 2. -1000 <= asteroids[i] <= 1000
 * 3. asteroids[i] != 0
 */
public class AsteroidCollision {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
        System.out.println(Arrays.toString(asteroidCollision(new int[]{10, 2, -5})));
    }

    /**
     * 使用栈收集结果，遍历数组，遇到正数入栈，遇到负数分为以下几种情况：
     * 1. 栈空或栈顶元素也是负数，当前元素入栈，然后遍历下一个
     * 2. 绝对值大于栈顶元素，弹出栈顶元素，继续遍历当前元素
     * 3. 绝对值等于栈顶元素，当前元素和栈顶元素同时消失，遍历下一个
     * 4. 绝对值小于栈顶元素，直接忽略当前元素，遍历下一个
     */
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        int index = -1, n = asteroids.length;
        while (++index < n) {
            int cur = asteroids[index];
            if (cur > 0) {
                stack.push(cur);
            } else if (stack.isEmpty() || stack.peek() < 0) {
                stack.push(cur);
            } else if (stack.peek() <= -cur) {
                // 需要继续遍历当前元素
                if (stack.peek() < -cur) {
                    index--;
                }
                stack.pop();
            }
        }

        int[] result = new int[stack.size()];
        index = stack.size();
        while (--index >= 0) {
            result[index] = stack.pop();
        }
        return result;
    }
}

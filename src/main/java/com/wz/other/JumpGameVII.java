package com.wz.other;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 * 1. i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * 2. s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 *
 * Example 1:
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation:
 * In the first step, move from index 0 to index 3.
 * In the second step, move from index 3 to index 5.
 *
 * Example 2:
 * Input: s = "01101110", minJump = 2, maxJump = 3
 * Output: false
 *
 * Constraints:
 * 1. 2 <= s.length <= 10^5
 * 2. s[i] is either '0' or '1'.
 * 3. s[0] == '0'
 * 4. 1 <= minJump <= maxJump < s.length
 */
public class JumpGameVII {
    public static void main(String[] args) {
        System.out.println(canReach("011010", 2, 3));
        System.out.println(canReach("01101110", 2, 3));
    }

    /**
     * 队列
     * 对每一个可到达的位置 i，都会新增可到达区间 [i + minJump, i + maxJump]，使用队列记录新增区间的边界并依次处理
     */
    public static boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') {
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        // 当前能够到达的最远位置
        int far = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll(), left = cur + minJump, right = cur + maxJump;
            // Math.max(left, far) 直接跳到已处理过的位置
            for (int i = Math.max(left, far); i <= right && i < n; i++) {
                // 只有 '0' 的地方是可达的
                if (s.charAt(i) == '0') {
                    queue.offer(i);
                    if (i == n - 1) {
                        return true;
                    }
                }
            }
            far = Math.max(far, right);
        }
        return false;
    }
}

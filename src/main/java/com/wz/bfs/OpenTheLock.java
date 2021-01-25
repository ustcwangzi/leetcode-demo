package com.wz.bfs;

import java.util.*;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 *
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 *
 * Constraints:
 * 1. 1 <= deadends.length <= 500
 * 2. deadends[i].length == 4
 * 3. target.length == 4
 * 4. target will not be in the list deadends.
 * 5. target and deadends[i] consist of digits only.
 */
public class OpenTheLock {
    public static void main(String[] args) {
        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        System.out.println(openLock(deadends, "0202"));
    }

    /**
     * BFS
     * 共四个 wheel， 每个 wheel 可以进行 +1、-1 操作，即八种操作，因此可以使用 BFS 进行层次遍历
     */
    public static int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        int step = -1;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (visited.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                visited.add(cur);
                // 对每个 wheel 进行 +1 或 -1 操作，并入队
                for (int j = 0; j < 4; j++) {
                    for (int k = -1; k < 2; k += 2) {
                        char[] tmp = cur.toCharArray();
                        tmp[j] = (char) ((tmp[j] - '0' + k + 10) % 10 + '0');
                        queue.add(new String(tmp));
                    }
                }
            }
        }
        return -1;
    }
}

package com.wz.dfs;

import java.util.*;

/**
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.
 * A key rooms[i][j] = v opens the room with number v.
 * Initially, all the rooms start locked (except for room 0).
 * You can walk back and forth between rooms freely.
 * Return true if and only if you can enter every room.
 *
 * Example 1:
 * Input: [[1],[2],[3],[]]
 * Output: true
 * Explanation:
 * We start in room 0, and pick up key 1.
 * We then go to room 1, and pick up key 2.
 * We then go to room 2, and pick up key 3.
 * We then go to room 3.  Since we were able to go to every room, we return true.
 *
 * Example 2:
 * Input: [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can't enter the room with number 2.
 *
 * Note:
 * 1. 1 <= rooms.length <= 1000
 * 2. 0 <= rooms[i].length <= 1000
 * 3. The number of keys in all rooms combined is at most 3000.
 */
public class KeysAndRooms {
    public static void main(String[] args) {
        List<List<Integer>> rooms = new LinkedList<>();
        rooms.add(Collections.singletonList(1));
        rooms.add(Collections.singletonList(2));
        rooms.add(Collections.singletonList(3));
        rooms.add(Collections.emptyList());
        System.out.println(canVisitAllRooms(rooms));
    }

    /**
     * BFS
     * 使用 visited 记录访问过的 key，然后从 0 房间开始遍历，没遍历一个房间就将该房间包含的 key 放入 visited 和 queue 中
     * 最后判断 visited 包含的 key 个数是否等于房间总数
     */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        Set<Integer> visited = new HashSet<>(n);
        visited.add(0);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int key : rooms.get(cur)) {
                if (visited.contains(key)) {
                    continue;
                }
                visited.add(key);
                if (visited.size() == n) {
                    return true;
                }
                queue.add(key);
            }
        }
        return visited.size() == n;
    }
}

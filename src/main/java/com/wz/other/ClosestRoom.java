package com.wz.other;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms where rooms[i] = [roomIdi, sizei]
 * denotes that there is a room with room number roomIdi and size equal to sizei. Each roomIdi is guaranteed to be unique.
 * You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej].
 * The answer to the jth query is the room number id of a room such that:
 * - The room has a size of at least minSizej, and
 * - abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
 * If there is a tie in the absolute difference, then use the room with the smallest such id. If there is no such room, the answer is -1.
 * Return an array answer of length k where answer[j] contains the answer to the jth query.
 *
 * Example 1:
 * Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
 * Output: [3,-1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
 * Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
 * Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.
 *
 * Example 2:
 * Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
 * Output: [2,1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
 * Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
 * Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.
 *
 * Constraints:
 * 1. n == rooms.length
 * 2. 1 <= n <= 10^5
 * 3. k == queries.length
 * 4. 1 <= k <= 10^4
 * 5. 1 <= roomIdi, preferredj <= 10^7
 * 6. 1 <= sizei, minSizej <= 10^7
 */
public class ClosestRoom {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestRoom(new int[][]{{2, 2}, {1, 2}, {3, 2}}, new int[][]{{3, 1}, {3, 3}, {5, 2}})));
        System.out.println(Arrays.toString(closestRoom(new int[][]{{1, 4}, {2, 3}, {3, 5}, {4, 1}, {5, 2}}, new int[][]{{2, 3}, {2, 4}, {2, 5}})));
    }

    /**
     * 将 rooms、queries 按 size 从大到小排序，尽量把 roomSize 大的房间分配给需求大的人
     * 结合有序集合 TreeSet，开始遍历 queries，对于每一个 query，当 roomSize 满足当前 minSize，把 roomId 加入 TreeSet
     * 当把满足当前 minSize 的所有 roomId 都记录好之后，再利用 TreeSet 去找到往上和往下最接近 preferredId 的房间号
     */
    public static int[] closestRoom(int[][] rooms, int[][] queries) {
        Arrays.sort(rooms, (o1, o2) -> Integer.compare(o2[1], o1[1]));

        int m = queries.length, n = rooms.length;
        int[][] query = new int[m][3];
        for (int i = 0; i < m; i++) {
            query[i][0] = queries[i][0];
            query[i][1] = queries[i][1];
            query[i][2] = i;
        }
        Arrays.sort(query, (o1, o2) -> Integer.compare(o2[1], o1[1]));

        int[] result = new int[m];
        TreeSet<Integer> treeSet = new TreeSet<>();
        int j = 0;
        for (int i = 0; i < m; i++) {
            // 满足 minSize
            while (j < n && rooms[j][1] >= query[i][1]) {
                treeSet.add(rooms[j++][0]);
            }
            Integer floor = treeSet.floor(query[i][0]), ceil = treeSet.ceiling(query[i][0]);
            if (floor == null && ceil == null) {
                result[query[i][2]] = -1;
            } else if (floor == null) {
                result[query[i][2]] = ceil;
            } else if (ceil == null) {
                result[query[i][2]] = floor;
            } else {
                result[query[i][2]] = ((ceil - query[i][0]) < (query[i][0] - floor) ? ceil : floor);
            }
        }
        return result;
    }
}

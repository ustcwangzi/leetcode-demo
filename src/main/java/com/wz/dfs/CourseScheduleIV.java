package com.wz.dfs;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.
 * For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
 * Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
 * You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
 * Return a boolean array answer, where answer[j] is the answer to the jth query.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * Output: [false,true]
 * Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
 * Course 0 is not a prerequisite of course 1, but the opposite is true.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * Output: [false,false]
 * Explanation: There are no prerequisites, and each course is independent.
 *
 * Example 3:
 * @link ../../../../resource/CourseScheduleIV.jpg
 * Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * Output: [true,true]
 *
 * Constraints:
 * 1. 2 <= numCourses <= 100
 * 2. 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * 3. prerequisites[i].length == 2
 * 4. 0 <= ai, bi <= n - 1
 * 5. ai != bi
 * 6. All the pairs [ai, bi] are unique.
 * 7. The prerequisites graph has no cycles.
 * 8. 1 <= queries.length <= 10^4
 * 9. 0 <= ui, vi <= n - 1
 * 10. ui != vi
 */
public class CourseScheduleIV {
    public static void main(String[] args) {
        System.out.println(checkIfPrerequisite(3, new int[][]{{1, 2}, {1, 0}, {2, 0}}, new int[][]{{1, 0}, {1, 2}}));
    }

    /**
     * DFS
     * 就是要检查才能从 query[0] 能否到达 query[1]，先对 prerequisites 建立有向图，然后对 queries 进行 DFS
     * 如果每次都对 query 进行全图 DFS，则会超时，因此使用 reachable 对已经走过的路进行记录
     */
    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            graph.putIfAbsent(prerequisite[0], new HashSet<>());
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }

        Boolean[][] reachable = new Boolean[numCourses][numCourses];
        List<Boolean> result = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            // 从 query[0] 出发进行 DFS，若能够访问到 query[1] 说明是可达的
            result.add(dfs(graph, reachable, query[0], query[1]));
        }
        return result;
    }

    private static boolean dfs(Map<Integer, Set<Integer>> graph, Boolean[][] reachable, int src, int dest) {
        if (src == dest) {
            return reachable[src][dest] = true;
        }
        if (reachable[src][dest] != null) {
            return reachable[src][dest];
        }

        for (int neighbor : graph.getOrDefault(src, Collections.emptySet())) {
            if (dfs(graph, reachable, neighbor, dest)) {
                return reachable[src][dest] = true;
            }
        }

        return reachable[src][dest] = false;
    }
}

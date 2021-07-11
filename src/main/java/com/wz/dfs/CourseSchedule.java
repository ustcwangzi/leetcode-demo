package com.wz.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 * Constraints:
 * 1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * 2. You may assume that there are no duplicate edges in the input prerequisites.
 * 3. 1 <= numCourses <= 10^5
 */
public class CourseSchedule {
    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1, 0}};
        System.out.println(canFinish(2, prerequisites));

        prerequisites = new int[][]{{1, 0}, {2, 0}};
        System.out.println(canFinish(3, prerequisites));
    }

    /**
     * DFS
     * 检查有向图是否存在环
     * 先将 prerequisites 转换为有向图，使用邻接表表示
     * 使用 visited 记录每个课程的遍历状态，0 代表未遍历、1 代表正在遍历，2 代表遍历结束
     * 如果不使用三个状态，针对 {{1, 0}, {2, 0}} 这种情况，第一次 DFS 时 visited[0] = 1，第二次又遍历到 0，直接返回 false
     * 遍历每个 course，将当前课程标记为 1，然后对新得到的课程调用 DFS，直到出现新的课程正在遍历，则返回 false，
     * 一次 DFS 结束没有返回 false 的话则返回 true，同时将该课程标记为遍历结束 2
     * 所有课程 DFS 结束则返回 true
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, visited, graph)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(int course, int[] visited, Map<Integer, List<Integer>> graph) {
        if (visited[course] == 1) {
            return false;
        }
        if (visited[course] == 2) {
            return true;
        }

        visited[course] = 1;
        for (int neighbor : graph.get(course)) {
            if (!dfs(neighbor, visited, graph)) {
                return false;
            }
        }
        visited[course] = 2;
        return true;
    }

    private static Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }
        return graph;
    }
}

package com.wz.dfs;

import java.util.*;

/**
 * There are a total of n courses you have to take labelled from 0 to n - 1.
 * Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
 * Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Constraints:
 * 1. 1 <= numCourses <= 2000
 * 2. 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * 3. prerequisites[i].length == 2
 * 4. 0 <= ai, bi < numCourses
 * 5. ai != bi
 * 6. All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {
    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(findOrder(4, prerequisites)));
    }

    /**
     * BFS
     * 先将 prerequisites 转换为有向图，使用邻接表表示，用 inDegree 记录每个节点的入度
     * 先将入度为 0 的节点加入到 queue 中，然后依次从 queue 中弹出当前课程进行遍历
     * 对于当前课程 cur，先加入到结果集中，然后遍历与之相连的其他课程，每遍历一个就将其入度减 1，减 1 若入度为 0，则加入 queue 中
     * 最后检查结果集的大小是否等于 numCourses，等于则直接返回，不等于就将结果集清空
     * {@link CourseSchedule} 也可以使用类似方法，只是不用记录结果集，只需检查是否存在入度不等于 0 的课程
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
            for (int neighbor : graph.get(cur)) {
                if (--inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        if (result.size() != numCourses) {
            return new int[0];
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

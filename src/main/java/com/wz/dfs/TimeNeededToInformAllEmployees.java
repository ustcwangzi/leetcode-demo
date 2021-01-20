package com.wz.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the one with headID.
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee,
 * manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.
 * The head of the company wants to inform all the employees of the company of an urgent piece of news.
 * He will inform his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates
 * (i.e After informTime[i] minutes, all his direct subordinates can start spreading the news).
 * Return the number of minutes needed to inform all the employees about the urgent news.
 *
 * Example 1:
 * @see ../../../../resource/TimeNeededToInformAllEmployees1.jpg
 * Input: n = 1, headID = 0, manager = [-1], informTime = [0]
 * Output: 0
 * Explanation: The head of the company is the only employee in the company.
 *
 * Example 2:
 * @see ../../../../resource/TimeNeededToInformAllEmployees2.jpg
 * Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
 * Output: 1
 * Explanation: The head of the company with id = 2 is the direct manager of all the employees in the company and needs 1 minute to inform them all.
 * The tree structure of the employees in the company is shown.
 *
 * Constraints:
 * 1. 1 <= n <= 10^5
 * 2. 0 <= headID < n
 * 3. manager.length == n
 * 4. 0 <= manager[i] < n
 * 5. manager[headID] == -1
 * 6. informTime.length == n
 * 7. 0 <= informTime[i] <= 1000
 * 8. informTime[i] == 0 if employee i has no subordinates.
 * 9. It is guaranteed that all the employees can be informed.
 */
public class TimeNeededToInformAllEmployees {
    public static void main(String[] args) {
        System.out.println(numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0}));
    }

    /**
     * DFS
     * 题目可以抽象成一棵多叉树，最顶端的根节点是 headID. 每一条边都有一个权重 informTime 代表的值，
     * 一个 manager 节点到其所有的子节点的 informTime 都是一样的。
     * 所以这道题就变成了，找到多叉树中，从根节点出发到叶子节点的路径，权重和的最大值是多少
     * 对节点进行DFS，返回当前节点到叶节点的时间和的最大值，即自己的 time 加上子节点中的最大值
     */
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] == -1) {
                continue;
            }
            graph.putIfAbsent(manager[i], new LinkedList<>());
            graph.get(manager[i]).add(i);
        }
        return dfs(headID, graph, informTime);
    }

    private static int dfs(int cur, Map<Integer, List<Integer>> graph, int[] informTime) {
        if (!graph.containsKey(cur)) {
            return 0;
        }

        int localMax = 0;
        for (int neighbor : graph.get(cur)) {
            localMax = Math.max(localMax, dfs(neighbor, graph, informTime));
        }
        return informTime[cur] + localMax;
    }
}

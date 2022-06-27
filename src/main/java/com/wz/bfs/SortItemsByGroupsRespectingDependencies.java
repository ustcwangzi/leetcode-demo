package com.wz.bfs;

import java.util.*;

/**
 * There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to
 * and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.
 * Return a sorted list of the items such that:
 * - The items that belong to the same group are next to each other in the sorted list.
 * - There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
 * Return any solution if there is more than one solution and return an empty list if there is no solution.
 *
 * Example 1:
 * @link ../../../../resource/SortItemsByGroupsRespectingDependencies.jpg
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
 * Output: [6,3,4,1,5,2,0,7]
 *
 * Example 2:
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
 * Output: []
 * Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
 *
 * Constraints:
 * 1. 1 <= m <= n <= 3 * 10^4
 * 2. group.length == beforeItems.length == n
 * 3. -1 <= group[i] <= m - 1
 * 4. 0 <= beforeItems[i].length <= n - 1
 * 5. 0 <= beforeItems[i][j] <= n - 1
 * 6. i != beforeItems[i][j]
 * 7. beforeItems[i] does not contain duplicates elements.
 */
public class SortItemsByGroupsRespectingDependencies {
    public static void main(String[] args) {
        List<List<Integer>> beforeItems = new ArrayList<>();
        beforeItems.add(Collections.emptyList());
        beforeItems.add(Collections.singletonList(6));
        beforeItems.add(Collections.singletonList(5));
        beforeItems.add(Collections.singletonList(6));
        beforeItems.add(Arrays.asList(3, 6));
        beforeItems.add(Collections.emptyList());
        beforeItems.add(Collections.emptyList());
        beforeItems.add(Collections.emptyList());
        System.out.println(Arrays.toString(sortItems(8, 2, new int[]{-1, -1, 1, 0, 0, 1, 0, -1}, beforeItems)));
    }

    /**
     * n 个项目和 m 个小组，group[i] 表示第 i 个项目所属的小组，如果这个项目目前无人接手，那么 group[i] 就等于 -1
     * 要求安排这些项目的进度，同一小组的项目，排序后在列表中彼此相邻，项目之间存在一定的依赖关系，用一个列表 beforeItems 来表示
     * 拓扑排序，将 group、item 转换为图，然后再对 group、item 进行拓扑排序，最后按照 group 进行聚合
     */
    public static int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, List<Integer>> groupGraph = new HashMap<>(), itemGraph = new HashMap<>();
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }
        for (int i = 0; i < m; i++) {
            groupGraph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            itemGraph.put(i, new ArrayList<>());
        }
        int[] groupsInDegree = new int[m], itemsInDegree = new int[n];
        buildGroupGraph(groupGraph, group, beforeItems, groupsInDegree);
        buildItemGraph(itemGraph, beforeItems, itemsInDegree, n);

        List<Integer> groupsList = topologicalSort(groupGraph, groupsInDegree, m),
                itemsList = topologicalSort(itemGraph, itemsInDegree, n);
        if (groupsList.size() == 0 || itemsList.size() == 0) {
            return new int[0];
        }

        Map<Integer, List<Integer>> groupsToItems = new HashMap<>();
        for (Integer item : itemsList) {
            groupsToItems.putIfAbsent(group[item], new ArrayList<>());
            groupsToItems.get(group[item]).add(item);
        }

        int[] result = new int[n];
        int index = 0;
        for (int cur : groupsList) {
            for (int item : groupsToItems.getOrDefault(cur, new ArrayList<>())) {
                result[index++] = item;
            }
        }
        return result;
    }

    private static void buildGroupGraph(Map<Integer, List<Integer>> graph, int[] group,
                                        List<List<Integer>> beforeItems, int[] groupsInDegree) {
        for (int i = 0; i < group.length; i++) {
            for (int beforeItem : beforeItems.get(i)) {
                if (group[i] != group[beforeItem]) {
                    graph.putIfAbsent(group[beforeItem], new ArrayList<>());
                    graph.get(group[beforeItem]).add(group[i]);
                    groupsInDegree[group[i]]++;
                }
            }
        }
    }

    private static void buildItemGraph(Map<Integer, List<Integer>> graph, List<List<Integer>> beforeItems,
                                       int[] itemsInDegree, int n) {
        for (int i = 0; i < n; i++) {
            for (int item : beforeItems.get(i)) {
                graph.putIfAbsent(item, new ArrayList<>());
                graph.get(item).add(i);
                itemsInDegree[i]++;
            }
        }
    }

    private static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph, int[] inDegree, int n) {
        Queue<Integer> queue = new LinkedList<>();
        for (int key : graph.keySet()) {
            if (inDegree[key] == 0) {
                queue.offer(key);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            n--;
            int cur = queue.poll();
            result.add(cur);
            for (int neighbor : graph.get(cur)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return n == 0 ? result : Collections.emptyList();
    }
}

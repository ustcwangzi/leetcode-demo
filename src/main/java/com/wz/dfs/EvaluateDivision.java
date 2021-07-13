package com.wz.dfs;

import java.util.*;

/**
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 * Constraints:
 * 1. 1 <= equations.length <= 20
 * 2. equations[i].length == 2
 * 3. 1 <= Ai.length, Bi.length <= 5
 * 4. values.length == equations.length
 * 5. 0.0 < values[i] <= 20.0
 * 6. 1 <= queries.length <= 20
 * 7. queries[i].length == 2
 * 8. 1 <= Cj.length, Dj.length <= 5
 * 9. Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        System.out.println(Arrays.toString(calcEquation(equations, new double[]{2.0, 3.0}, queries)));
    }

    /**
     * DFS
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Set<String> set = new HashSet<>();
        Map<String, Map<String, Double>> graph = buildGraph(equations, values, set);

        double[] result = new double[queries.size()];
        // 访问标记
        Map<String, Boolean> visited = new HashMap<>();
        for (int i = 0; i < queries.size(); i++) {
            for (String cur : set) {
                visited.put(cur, false);
            }

            List<String> query = queries.get(i);
            if (query.get(0).equals(query.get(1)) && set.contains(query.get(0))) {
                result[i] = 1;
            } else {
                result[i] = dfs(query.get(0), query.get(1), 1, graph, visited);
            }
        }
        return result;
    }

    private static double dfs(String s, String t, double base, Map<String, Map<String, Double>> graph, Map<String, Boolean> visited) {
        if (!graph.containsKey(s) || visited.get(s)) {
            return -1;
        }
        visited.put(s, true);
        Map<String, Double> map = graph.get(s);
        if (map.containsKey(t)) {
            return base * map.get(t);
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            double value = dfs(entry.getKey(), t, base * entry.getValue(), graph, visited);
            if (value != -1) {
                return value;
            }
        }
        return -1;
    }

    private static Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values, Set<String> set) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            // 记录出现的字符串
            set.add(equation.get(0));
            set.add(equation.get(1));
            // 构建邻接表
            graph.putIfAbsent(equation.get(0), new HashMap<>());
            graph.get(equation.get(0)).put(equation.get(1), values[i]);
            graph.putIfAbsent(equation.get(1), new HashMap<>());
            graph.get(equation.get(1)).put(equation.get(0), 1.0 / values[i]);
        }
        return graph;
    }
}

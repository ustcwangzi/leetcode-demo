package com.wz.dfs;

import java.util.*;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person
 * if there is some email that is common to both accounts. Note that even if two accounts have the same name,
 * they may belong to different people as people could have the same name. A person can have any number of accounts initially,
 * but all of their accounts definitely have the same name.
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name,
 * and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"],
 * ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Note:
 * 1. The length of accounts will be in the range [1, 1000].
 * 2. The length of accounts[i] will be in the range [1, 10].
 * 3. The length of accounts[i][j] will be in the range [1, 30].
 */
public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = new LinkedList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        System.out.println(accountsMerge(accounts));
    }

    /**
     * 使用 email 建立一个图，将有关联的 email 使用 graph 全部关联起来，使用 emailNameMap 保存 email 和 name 之间的映射
     * 然后对 graph 进行 DFS，收集一条 path 上所有的 email，然后将 name 放在首位，将 path 存在结果集中
     */
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailNameMap = new HashMap<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            // 邮箱从 1 开始
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailNameMap.put(email, name);
                graph.putIfAbsent(email, new HashSet<>());
                if (i > 1) {
                    graph.get(account.get(i - 1)).add(email);
                    graph.get(email).add(account.get(i - 1));
                }
            }
        }

        List<List<String>> result = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for (String email : graph.keySet()) {
            if (visited.contains(email)) {
                continue;
            }
            List<String> path = new LinkedList<>();
            dfs(path, email, graph, visited);
            // 将 name 放在第一个位置
            path.add(0, emailNameMap.get(email));
            Collections.sort(path);
            result.add(path);
        }
        return result;
    }

    private static void dfs(List<String> path, String email, Map<String, Set<String>> graph, Set<String> visited) {
        if (visited.contains(email)) {
            return;
        }
        visited.add(email);
        path.add(email);
        for (String str : graph.get(email)) {
            dfs(path, str, graph, visited);
        }
    }
}

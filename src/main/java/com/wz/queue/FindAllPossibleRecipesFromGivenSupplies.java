package com.wz.queue;

import java.util.*;

/**
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
 * The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
 * Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 * Note that two recipes may contain each other in their ingredients.
 *
 * Example 1:
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 *
 * Example 2:
 * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 *
 * Example 3:
 * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 * Constraints:
 * 1. n == recipes.length == ingredients.length
 * 2. 1 <= n <= 100
 * 3. 1 <= ingredients[i].length, supplies.length <= 100
 * 4. 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * 5. recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 * 6. All the values of recipes and supplies combined are unique.
 * 7. Each ingredients[i] does not contain any duplicate values.
 */
public class FindAllPossibleRecipesFromGivenSupplies {
    public static void main(String[] args) {
        List<List<String>> ingredients = new ArrayList<>();
        ingredients.add(Arrays.asList("yeast", "flour"));
        ingredients.add(Arrays.asList("bread", "meat"));
        System.out.println(findAllRecipes(new String[]{"bread", "sandwich"}, ingredients, new String[]{"yeast", "flour", "meat"}));
    }

    /**
     * 拓扑排序，使用 graph 记录 ingredient 到 recipe 的关系，使用 inDegree 记录 recipe 的入度，使用 queue 保存入度为 0 的元素
     * 将 supplies 全部加入 queue 中，因为 supplies 的入度全是 0
     * 依次从 queue 中弹出元素 cur，判断是否是需要的结果，若 cur 存在于 recipes，说明是需要的，加入结果集
     * 如果 cur 包含在 graph 中，则需要将对应 values 的入度全部减一，同时若减一之后入度为 0，则将其加入 queue
     */
    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            for (String ingredient : ingredients.get(i)) {
                graph.computeIfAbsent(ingredient, x -> new ArrayList<>()).add(recipes[i]);
                inDegree.put(recipes[i], inDegree.getOrDefault(recipes[i], 0) + 1);
            }
        }

        Set<String> recipeSet = new HashSet<>(Arrays.asList(recipes));
        Deque<String> queue = new ArrayDeque<>(Arrays.asList(supplies));
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (recipeSet.contains(cur)) {
                result.add(cur);
            }

            if (graph.containsKey(cur)) {
                // 所有 neighbor 的入度减一
                for (String neighbor : graph.get(cur)) {
                    int newOccurrence = inDegree.get(neighbor) - 1;
                    inDegree.put(neighbor, newOccurrence);
                    // 入度为 0，加入 queue 中
                    if (newOccurrence == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return result;
    }
}

package com.wz.dfs;

import java.util.*;

/**
 * A kingdom consists of a king, his children, his grandchildren, and so on. Every once in a while, someone in the family dies or a child is born.
 * The kingdom has a well-defined order of inheritance that consists of the king as the first member.
 * Let's define the recursive function Successor(x, curOrder), which given a person x and the inheritance order so far,
 * returns who should be the next person after x in the order of inheritance.
 * Successor(x, curOrder):
 *     if x has no children or all of x's children are in curOrder:
 *         if x is the king return null
 *         else return Successor(x's parent, curOrder)
 *     else return x's oldest child who's not in curOrder
 * For example, assume we have a kingdom that consists of the king, his children Alice and Bob (Alice is older than Bob), and finally Alice's son Jack.
 * In the beginning, curOrder will be ["king"].
 * Calling Successor(king, curOrder) will return Alice, so we append to curOrder to get ["king", "Alice"].
 * Calling Successor(Alice, curOrder) will return Jack, so we append to curOrder to get ["king", "Alice", "Jack"].
 * Calling Successor(Jack, curOrder) will return Bob, so we append to curOrder to get ["king", "Alice", "Jack", "Bob"].
 * Calling Successor(Bob, curOrder) will return null. Thus the order of inheritance will be ["king", "Alice", "Jack", "Bob"].
 * Using the above function, we can always obtain a unique order of inheritance.
 *
 * Implement the ThroneInheritance class:
 * 1. ThroneInheritance(string kingName) Initializes an object of the ThroneInheritance class. The name of the king is given as part of the constructor.
 * 2. void birth(string parentName, string childName) Indicates that parentName gave birth to childName.
 * 3. void death(string name) Indicates the death of name. The death of the person doesn't affect the Successor
 *    function nor the current inheritance order. You can treat it as just marking the person as dead.
 * 4. string[] getInheritanceOrder() Returns a list representing the current order of inheritance excluding dead people.
 *
 * Example 1:
 * Input
 * ["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
 * [["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
 * Output
 * [null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]
 *
 * Explanation
 * ThroneInheritance t= new ThroneInheritance("king"); // order: king
 * t.birth("king", "andy"); // order: king > andy
 * t.birth("king", "bob"); // order: king > andy > bob
 * t.birth("king", "catherine"); // order: king > andy > bob > catherine
 * t.birth("andy", "matthew"); // order: king > andy > matthew > bob > catherine
 * t.birth("bob", "alex"); // order: king > andy > matthew > bob > alex > catherine
 * t.birth("bob", "asha"); // order: king > andy > matthew > bob > alex > asha > catherine
 * t.getInheritanceOrder(); // return ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
 * t.death("bob"); // order: king > andy > matthew > bob > alex > asha > catherine
 * t.getInheritanceOrder(); // return ["king", "andy", "matthew", "alex", "asha", "catherine"]
 *
 * Constraints:
 * 1. 1 <= kingName.length, parentName.length, childName.length, name.length <= 15
 * 2. kingName, parentName, childName, and name consist of lowercase English letters only.
 * 3. All arguments childName and kingName are distinct.
 * 4. All name arguments of death will be passed to either the constructor or as childName to birth first.
 * 5. For each call to birth(parentName, childName), it is guaranteed that parentName is alive.
 * 6. At most 105 calls will be made to birth and death.
 * 7. At most 10 calls will be made to getInheritanceOrder.
 */
public class ThroneInheritance {
    public static void main(String[] args) {
        ThroneInheritance t = new ThroneInheritance("king"); // order: king
        t.birth("king", "andy");
        t.birth("king", "bob");
        t.birth("king", "catherine");
        t.birth("andy", "matthew");
        t.birth("bob", "alex");
        t.birth("bob", "asha");
        t.getInheritanceOrder();
        t.death("bob");
        System.out.println(t.getInheritanceOrder());
    }

    private final String king;
    private final Set<String> deaths;
    private final Map<String, List<String>> graph;

    /**
     * 使用 king 记录国王，使用 map 保存每个 parent 下的 children，使用 set 记录去世的人
     * 获取 inheritance 时，从 king 开始进行 dfs，过滤掉去世者，将其他人按照 dfs 的顺序加入结果中
     */
    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.deaths = new HashSet<>();
        this.graph = new HashMap<>();
    }

    public void birth(String parentName, String childName) {
        graph.putIfAbsent(parentName, new LinkedList<>());
        graph.get(parentName).add(childName);
    }

    public void death(String name) {
        deaths.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> inheritance = new LinkedList<>();
        dfs(king, inheritance);
        return inheritance;
    }

    private void dfs(String cur, List<String> inheritance) {
        if (!deaths.contains(cur)) {
            inheritance.add(cur);
        }
        if (!graph.containsKey(cur)) {
            return;
        }
        for (String next : graph.get(cur)) {
            dfs(next, inheritance);
        }
    }
}

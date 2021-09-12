package com.wz.other;

/**
 * You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4
 * and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
 * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.
 *
 * Example 1:
 * Input: equations = ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
 * There is no way to assign the variables to satisfy both equations.
 *
 * Example 2:
 * Input: equations = ["a==b","b==c","a==c"]
 * Output: true
 *
 * Constraints:
 * 1. 1 <= equations.length <= 500
 * 2. equations[i].length == 4
 * 3. equations[i][0] is a lowercase letter.
 * 4. equations[i][1] is either '=' or '!'.
 * 5. equations[i][2] is '='.
 * 6. equations[i][3] is a lowercase letter.
 */
public class SatisfiabilityOfEqualityEquations {
    public static void main(String[] args) {
        System.out.println(equationsPossible(new String[]{"a==b", "b!=a"}));
        System.out.println(equationsPossible(new String[]{"a==b", "b==c", "a==c"}));
    }

    /**
     * 并查集 Union-Find
     * 遍历 equations 中的等式，在 root 数组中将两者连接起来
     * 再遍历 equations 中的不等式，然后判断两者 root 节点是否相等，若相等则产生了矛盾
     */
    public static boolean equationsPossible(String[] equations) {
        UnionFind unionFind = new UnionFind(26);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                unionFind.union(equation.charAt(0) - 'a', equation.charAt(3) - 'a');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!' && unionFind.find(equation.charAt(0) - 'a') == unionFind.find(equation.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
    }

    private static class UnionFind {
        private final int[] root;

        public UnionFind(int n) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
        }

        public int find(int i) {
            if (i == root[i]) {
                return i;
            }
            return root[i] = find(root[i]);
        }

        public void union(int i, int j) {
            root[find(i)] = find(j);
        }
    }
}

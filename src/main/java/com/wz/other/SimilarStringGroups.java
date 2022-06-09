package com.wz.other;

/**
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
 *
 * Example 1:
 * Input: strs = ["tars","rats","arts","star"]
 * Output: 2
 *
 * Example 2:
 * Input: strs = ["omv","ovm"]
 * Output: 1
 *
 * Constraints:
 * 1. 1 <= strs.length <= 300
 * 2. 1 <= strs[i].length <= 300
 * 3. strs[i] consists of lowercase letters only.
 * 5. All words in strs have the same length and are anagrams of each other.
 */
public class SimilarStringGroups {
    public static void main(String[] args) {
        System.out.println(numSimilarGroups(new String[]{"tars", "rats", "arts", "star"}));
        System.out.println(numSimilarGroups(new String[]{"omv", "ovm"}));
    }

    /**
     * 并查集 Union-Find
     * 双层遍历数组，若两个字符串满足 similar，则将这两个字符串进行 union 操作，同时 size 减一
     */
    public static int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (similar(strs[i], strs[j])) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.size;
    }

    private static boolean similar(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return true;
    }

    private static class UnionFind {
        private final int[] root;
        private int size;

        public UnionFind(int n) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
            size = n;
        }

        public int find(int i) {
            if (i == root[i]) {
                return i;
            }
            return root[i] = find(root[i]);
        }

        public void union(int i, int j) {
            int rootA = find(i), rootB = find(j);
            if (rootA == rootB) {
                return;
            }
            root[rootA] = rootB;
            size--;
        }
    }
}

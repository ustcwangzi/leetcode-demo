package com.wz.backtracking;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Design the CombinationIterator class:
 * 1. CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters
 *    of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * 2. next() Returns the next combination of length combinationLength in lexicographical order.
 * 3. hasNext() Returns true if and only if there exists a next combination.
 *
 * Example 1:
 * Input
 * ["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [["abc", 2], [], [], [], [], [], []]
 * Output
 * [null, "ab", true, "ac", true, "bc", false]
 * Explanation
 * CombinationIterator itr = new CombinationIterator("abc", 2);
 * itr.next();    // return "ab"
 * itr.hasNext(); // return True
 * itr.next();    // return "ac"
 * itr.hasNext(); // return True
 * itr.next();    // return "bc"
 * itr.hasNext(); // return False
 *
 * Constraints:
 * 1. 1 <= combinationLength <= characters.length <= 15
 * 2. All the characters of characters are unique.
 * 3. At most 104 calls will be made to next and hasNext.
 * 4. It's guaranteed that all calls of the function next are valid.
 */
public class CombinationIterator {
    public static void main(String[] args) {
        CombinationIterator iterator = new CombinationIterator("abc", 2);
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }

    Queue<String> queue = new LinkedList<>();

    /**
     * 与 {@link Combinations} 类似
     * 由于字符串已有序，因此直接进行DFS即可，同时为了方便调用 next、hasNext，使用 queue 来收集结果
     */
    public CombinationIterator(String characters, int combinationLength) {
        dfs(characters, combinationLength, 0, new StringBuilder());
    }

    public String next() {
        return queue.poll();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void dfs(String str, int n, int start, StringBuilder path) {
        if (path.length() == n) {
            queue.add(path.toString());
            return;
        }
        for (int i = start; i < str.length(); i++) {
            path.append(str.charAt(i));
            dfs(str, n, i + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

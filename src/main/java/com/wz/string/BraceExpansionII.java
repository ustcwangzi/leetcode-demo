package com.wz.string;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Under a grammar given below, strings can represent a set of lowercase words.
 * Let's use R(expr) to denote the set of words the expression represents.
 * Grammar can best be understood through simple examples:
 * 1. Single letters represent a singleton set containing that word.
 *  R("a") = {"a"}
 *  R("w") = {"w"}
 * 2. When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
 *  R("{a,b,c}") = {"a","b","c"}
 *  R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
 * 3. When we concatenate two expressions, we take the set of possible concatenations between two words where
 *    the first word comes from the first expression and the second word comes from the second expression.
 *  R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
 *  R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
 * Formally, the 3 rules for our grammar:
 * 1. For every lowercase letter x, we have R(x) = {x}
 * 2. For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * 3. For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)},
 *    where + denotes concatenation, and × denotes the cartesian product.
 * Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.
 *
 * Example 1:
 * Input: "{a,b}{c,{d,e}}"
 * Output: ["ac","ad","ae","bc","bd","be"]
 *
 * Example 2:
 * Input: "{{a,z},a{b,c},{ab,z}}"
 * Output: ["a","ab","ac","z"]
 * Explanation: Each distinct word is written only once in the final answer.
 *
 * Constraints:
 * 1. 1 <= expression.length <= 60
 * 2. expression[i] consists of '{', '}', ','or lowercase English letters.
 * 3. The given expression represents a set of words based on the grammar given in the description.
 */
public class BraceExpansionII {
    public static void main(String[] args) {
        System.out.println(braceExpansionII("{a,b}{c,{d,e}}"));
        System.out.println(braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
    }

    public static List<String> braceExpansionII(String expression) {
        List<String> result = new LinkedList<>();
        if (expression.length() <= 1) {
            result.add(expression);
            return result;
        }

        if (expression.charAt(0) == '{') {
            int count = 0;
            int idx = 0;
            while (idx < expression.length()) {
                if (expression.charAt(idx) == '{') {
                    count += 1;
                }
                if (expression.charAt(idx) == '}') {
                    count -= 1;
                }
                if (count == 0) {
                    break;
                }
                idx++;
            }

            List<String> strs = helper(expression.substring(1, idx));
            HashSet<String> set = new HashSet<>();
            for (String str : strs) {
                set.addAll(braceExpansionII(str));
            }

            List<String> rest = braceExpansionII(expression.substring(idx + 1));
            for (String str1 : set) {
                for (String str2 : rest) {
                    result.add(str1 + str2);
                }
            }
        } else {
            String prev = expression.charAt(0) + "";
            List<String> rest = braceExpansionII(expression.substring(1));
            for (String s : rest) {
                result.add(prev + s);
            }
        }

        Collections.sort(result);
        return result;
    }

    private static List<String> helper(String s) {
        List<String> result = new LinkedList<>();
        int count = 0, i = 0;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == ',') {
                if (count == 0) {
                    result.add(s.substring(i, j));
                    i = j + 1;
                }
            } else if (s.charAt(j) == '{'){
                count++;
            } else if (s.charAt(j) == '}') {
                count--;
            }
        }
        result.add(s.substring(i));
        return result;
    }
}

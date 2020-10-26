package com.wz.string;

import java.util.*;

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
        return new ArrayList<>(helper(expression));
    }

    private static Set<String> helper(String s) {
        int left = s.indexOf("{");
        if (left == -1) {
            return new TreeSet<>(Arrays.asList(s.split(",")));
        }

        int right = left + 1, count = 1;
        while (right < s.length()) {
            if (s.charAt(right) == '{') {
                count++;
            } else if (s.charAt(right) == '}') {
                count--;
                if (count == 0) {
                    break;
                }
            }
            right++;
        }

        String leftPart = s.substring(0, left);
        Set<String> mid = helper(s.substring(left + 1, right));
        String rightPart = s.substring(right + 1);

        Set<String> result = new TreeSet<>();
        for (String str : mid) {
            String newStr = leftPart + str + rightPart;
            result.addAll(helper(newStr));
        }
        return result;
    }
}

package com.wz.string;

import java.util.*;

/**
 * You are given a string s that contains some bracket pairs, with each pair containing a non-empty key.
 * For example, in the string "(name)is(age)yearsold", there are two bracket pairs that contain the keys "name" and "age".
 * You know the values of a wide range of keys. This is represented by a 2D string array knowledge where each knowledge[i] = [keyi, valuei] indicates that key keyi has a value of valuei.
 * You are tasked to evaluate all of the bracket pairs. When you evaluate a bracket pair that contains some key keyi, you will:
 * 1. Replace keyi and the bracket pair with the key's corresponding valuei.
 * 2. If you do not know the value of the key, you will replace keyi and the bracket pair with a question mark "?" (without the quotation marks).
 * Each key will appear at most once in your knowledge. There will not be any nested brackets in s.
 * Return the resulting string after evaluating all of the bracket pairs.
 *
 * Example 1:
 * Input: s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
 * Output: "bobistwoyearsold"
 * Explanation:
 * The key "name" has a value of "bob", so replace "(name)" with "bob".
 * The key "age" has a value of "two", so replace "(age)" with "two".
 *
 * Example 2:
 * Input: s = "hi(name)", knowledge = [["a","b"]]
 * Output: "hi?"
 * Explanation: As you do not know the value of the key "name", replace "(name)" with "?".
 *
 * Example 3:
 * Input: s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
 * Output: "yesyesyesaaa"
 * Explanation: The same key can appear multiple times.
 * The key "a" has a value of "yes", so replace all occurrences of "(a)" with "yes".
 * Notice that the "a"s not in a bracket pair are not evaluated.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. 0 <= knowledge.length <= 10^5
 * 3. knowledge[i].length == 2
 * 4. 1 <= keyi.length, valuei.length <= 10
 * 5. s consists of lowercase English letters and round brackets '(' and ')'.
 * 6. Every open bracket '(' in s will have a corresponding close bracket ')'.
 * 7. The key in each bracket pair of s will be non-empty.
 * 8. There will not be any nested bracket pairs in s.
 * 9. keyi and valuei consist of lowercase English letters.
 * 10. Each keyi in knowledge is unique.
 */
public class EvaluateTheBracketPairsOfString {
    public static void main(String[] args) {
        System.out.println(evaluate("(name)is(age)yearsold", Arrays.asList(Arrays.asList("name", "bob"), Arrays.asList("age", "two"))));
        System.out.println(evaluate("hi(name)", Collections.singletonList(Arrays.asList("a", "b"))));
        System.out.println(evaluate("(a)(a)(a)aaa", Collections.singletonList(Arrays.asList("a", "yes"))));
    }

    /**
     * 思路与 {@link NumberOfDifferentIntegersInString} 类似
     * 先将 knowledge 转换成 map，然后遍历 s，使用 StringBuilder 收集结果，使用 start 记录 '(' 的起始位置
     * 若当前是 ')' 并且 start 不是 -1，则将 s[start...i) 对应的 value 放入结果中，同时将 start 更新为 -1
     * 若当前是 '(' 并且 start 是 -1，则将 start 更新为 i
     * 若当前不是 '(' 也不是 ')' 并且 start 是 -1，说明就是普通字符，直接则将当前字符放入结果中
     * 遍历完成后，若 start 不是 -1，则将 word[start+1...n-1] 对应的 value 放入结果中，以免遗漏最后的字符
     */
    public static String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>(knowledge.size());
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }

        StringBuilder builder = new StringBuilder();
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ')') {
                builder.append(map.getOrDefault(s.substring(start + 1, i), "?"));
                start = -1;
            } else if (cur == '(') {
                start = i;
            } else if (start == -1) {
                builder.append(cur);
            }
        }

        if (start != -1) {
            builder.append(map.getOrDefault(s.substring(start + 1), "?"));
        }

        return builder.toString();
    }
}

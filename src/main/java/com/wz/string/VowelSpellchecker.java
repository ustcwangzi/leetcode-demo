package com.wz.string;

import java.util.*;

/**
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 * 1. Capitalization: If the query matches a word in the wordlist (case-insensitive),
 *    then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * 2. Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually,
 *    it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 * 1. When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * 2. When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * 3. When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * 4. If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 *
 * Example 1:
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 *
 * Note:
 * 1. 1 <= wordlist.length <= 5000
 * 2. 1 <= queries.length <= 5000
 * 3. 1 <= wordlist[i].length <= 7
 * 4. 1 <= queries[i].length <= 7
 * 5. All strings in wordlist and queries consist only of english letters.
 */
public class VowelSpellchecker {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(spellchecker(new String[]{"KiTe", "kite", "hare", "Hare"},
                new String[]{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"})));
    }

    /**
     * 首先是精确匹配，然后是忽略大小写匹配，再接下来是忽略元音匹配
     */
    public static String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> original = new HashSet<>();
        Map<String, String> lowerCaseMap = new HashMap<>(), vowelMap = new HashMap<>();

        Arrays.stream(wordlist).forEach(world -> {
            original.add(world);
            lowerCaseMap.putIfAbsent(world.toLowerCase(), world);
            vowelMap.putIfAbsent(world.toLowerCase().replaceAll("[aeiou]", "*"), world);
        });

        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (original.contains(queries[i])) {
                result[i] = queries[i];
            } else if (lowerCaseMap.containsKey(queries[i].toLowerCase())) {
                result[i] = lowerCaseMap.get(queries[i].toLowerCase());
            } else {
                String vowelStr = queries[i].toLowerCase().replaceAll("[aeiou]", "*");
                result[i] = vowelMap.getOrDefault(vowelStr, "");
            }
        }

        return result;
    }
}

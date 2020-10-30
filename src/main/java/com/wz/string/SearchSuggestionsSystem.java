package com.wz.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests
 * at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with the searchWord. If there are more than
 * three products with a common prefix return the three lexicographically minimums products.
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 * Example 1:
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 *
 * Example 2:
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 *
 * Example 3:
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 *
 * Constraints:
 * 1. 1 <= products.length <= 1000
 * 2. There are no repeated elements in products.
 * 3. 1 <= Σ products[i].length <= 2 * 10^4
 * 4. All characters of products[i] are lower-case English letters.
 * 5. 1 <= searchWord.length <= 1000
 * 6. All characters of searchWord are lower-case English letters.
 */
public class SearchSuggestionsSystem {
    public static void main(String[] args) {
        System.out.println(suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse"));
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new LinkedList<>();
        Arrays.sort(products);
        suggestion(products, searchWord, 1, result);
        return result;
    }

    private static void suggestion(String[] products, String searchWord, int index, List<List<String>> result) {
        if (index > searchWord.length()) {
            return;
        }

        List<String> list = new LinkedList<>();
        for (int i = 0; i < products.length && list.size() < 3; i++) {
            if (products[i].startsWith(searchWord.substring(0, index))) {
                list.add(products[i]);
            }
        }
        result.add(new ArrayList<>(list));
        suggestion(products, searchWord, index + 1, result);
    }
}

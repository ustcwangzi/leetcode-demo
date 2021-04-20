package com.wz.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array items, where each items[i] = [typei, colori, namei] describes the type, color, and name of the ith item.
 * You are also given a rule represented by two strings, ruleKey and ruleValue.
 * The ith item is said to match the rule if one of the following is true:
 * 1. ruleKey == "type" and ruleValue == typei.
 * 2. ruleKey == "color" and ruleValue == colori.
 * 3. ruleKey == "name" and ruleValue == namei.
 * Return the number of items that match the given rule.
 *
 * Example 1:
 * Input: items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
 * Output: 1
 * Explanation: There is only one item matching the given rule, which is ["computer","silver","lenovo"].
 *
 * Example 2:
 * Input: items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
 * Output: 2
 * Explanation: There are only two items matching the given rule, which are ["phone","blue","pixel"] and ["phone","gold","iphone"].
 * Note that the item ["computer","silver","phone"] does not match.
 *
 * Constraints:
 * 1. 1 <= items.length <= 104
 * 2. 1 <= typei.length, colori.length, namei.length, ruleValue.length <= 10
 * 3. ruleKey is equal to either "type", "color", or "name".
 * 4. All strings consist only of lowercase letters.
 */
public class CountItemsMatchingRule {
    public static void main(String[] args) {
        List<List<String>> items = new ArrayList<>();
        items.add(Arrays.asList("phone", "blue", "pixel"));
        items.add(Arrays.asList("computer", "silver", "phone"));
        items.add(Arrays.asList("phone", "gold", "iphone"));
        System.out.println(countMatches(items, "color", "silver"));
        System.out.println(countMatches(items, "type", "phone"));
    }

    /**
     * 直接遍历 items，统计满足要求的个数即可
     */
    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index = ruleKey.equals("type") ? 0 : ruleKey.equals("color") ? 1 : 2;
        int count = 0;
        for (List<String> item : items) {
            if (item.get(index).equals(ruleValue)) {
                count++;
            }
        }
        return count;
    }
}

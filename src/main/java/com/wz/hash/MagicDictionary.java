package com.wz.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Design a data structure that is initialized with a list of different words. Provided a string,
 * you should determine if you can change exactly one character in this string to match any word in the data structure.
 * Implement the MagicDictionary class:
 * 1. MagicDictionary() Initializes the object.
 * 2. void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 * 3. bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 *
 * Example 1:
 * Input
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * Output
 * [null, null, false, true, false, false]
 * Explanation:
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // return False
 * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
 * magicDictionary.search("hell"); // return False
 * magicDictionary.search("leetcoded"); // return False
 *
 * Constraints:
 * 1. 1 <= dictionary.length <= 100
 * 2. 1 <= dictionary[i].length <= 100
 * 3. dictionary[i] consists of only lower-case English letters.
 * 4. All the strings in dictionary are distinct.
 * 5. 1 <= searchWord.length <= 100
 * 6. searchWord consists of only lower-case English letters.
 * 7. buildDict will be called only once before search.
 * 8. At most 100 calls will be made to search.
 */
public class MagicDictionary {
    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(magicDictionary.search("hello"));
        System.out.println(magicDictionary.search("hhllo"));
        System.out.println(magicDictionary.search("hell"));
        System.out.println(magicDictionary.search("leetcoded"));
    }

    private Set<String> set;

    public MagicDictionary() {
        set = new HashSet<>();
    }

    public void buildDict(String[] dictionary) {
        set.addAll(Arrays.asList(dictionary));
    }

    public boolean search(String searchWord) {
        for (String str : set) {
            if (valid(str, searchWord)) {
                return true;
            }
        }
        return false;
    }

    private static boolean valid(String s, String word) {
        if (s.length() != word.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != word.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }
}

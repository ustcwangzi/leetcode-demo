package com.wz.hash;

import java.util.*;

/**
 * Given an array of strings words, return the words that can be typed using letters of the alphabet on only one row of American keyboard like the image below.
 * In the American keyboard:
 * 1. the first row consists of the characters "qwertyuiop",
 * 2. the second row consists of the characters "asdfghjkl", and
 * 3. the third row consists of the characters "zxcvbnm".
 * @link ../../../../resource/KeyboardRow.jpg
 *
 * Example 1:
 * Input: words = ["Hello","Alaska","Dad","Peace"]
 * Output: ["Alaska","Dad"]
 *
 * Example 2:
 * Input: words = ["omk"]
 * Output: []
 *
 * Example 3:
 * Input: words = ["adsdf","sfd"]
 * Output: ["adsdf","sfd"]
 *
 * Constraints:
 * 1. 1 <= words.length <= 20
 * 2. 1 <= words[i].length <= 100
 * 3. words[i] consists of English letters (both lowercase and uppercase).
 */
public class KeyboardRow {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }

    /**
     * 把键盘的三行字符分别保存到三个 set 中，然后遍历每个单词中的字符，如果在某个集合中，就将对应的标识设置为1，统计三个标识之和就知道有几个集合参与了
     */
    public static String[] findWords(String[] words) {
        Set<Character> row1 = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p')),
                row2 = new HashSet<>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l')),
                row3 = new HashSet<>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm'));

        List<String> result = new ArrayList<>();
        for (String word : words) {
            int one = 0, two = 0, three = 0;
            for (int i = 0; i < word.length(); i++) {
                char cur = Character.toLowerCase(word.charAt(i));
                if (row1.contains(cur)) {
                    one = 1;
                }
                if (row2.contains(cur)) {
                    two = 1;
                }
                if (row3.contains(cur)) {
                    three = 1;
                }
                if (one + two + three > 1) {
                    break;
                }
            }

            if (one + two + three == 1) {
                result.add(word);
            }
        }
        return result.toArray(new String[0]);
    }
}

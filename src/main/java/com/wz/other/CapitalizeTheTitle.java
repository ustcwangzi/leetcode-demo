package com.wz.other;

/**
 * You are given a string title consisting of one or more words separated by a single space, where each word consists of English letters. Capitalize the string by changing the capitalization of each word such that:
 * 1. If the length of the word is 1 or 2 letters, change all letters to lowercase.
 * 2. Otherwise, change the first letter to uppercase and the remaining letters to lowercase.
 * Return the capitalized title.
 *
 * Example 1:
 * Input: title = "capiTalIze tHe titLe"
 * Output: "Capitalize The Title"
 * Explanation:
 * Since all the words have a length of at least 3, the first letter of each word is uppercase, and the remaining letters are lowercase.
 *
 * Example 2:
 * Input: title = "First leTTeR of EACH Word"
 * Output: "First Letter of Each Word"
 * Explanation:
 * The word "of" has length 2, so it is all lowercase.
 * The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase, and the remaining letters are lowercase.
 *
 * Constraints:
 * 1. 1 <= title.length <= 100
 * 2. title consists of words separated by a single space without any leading or trailing spaces.
 * 3. Each word consists of uppercase and lowercase English letters and is non-empty.
 */
public class CapitalizeTheTitle {
    public static void main(String[] args) {
        System.out.println(capitalizeTitle("capiTalIze tHe titLe"));
    }

    /**
     * 遍历每个 word，依次进行处理即可
     */
    public static String capitalizeTitle(String title) {
        String[] array = title.split(" ");
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            if (str.length() <= 2) {
                array[i] = str.toLowerCase();
            } else {
                array[i] = Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
            }
        }
        return String.join(" ", array);
    }
}

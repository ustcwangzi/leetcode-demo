package com.wz.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two strings first and second, consider occurrences in some text of the form "first second third",
 * where second comes immediately after first, and third comes immediately after second.
 * Return an array of all the words third for each occurrence of "first second third".
 *
 * Example 1:
 * Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
 * Output: ["girl","student"]
 *
 * Example 2:
 * Input: text = "we will we will rock you", first = "we", second = "will"
 * Output: ["we","rock"]
 *
 * Constraints:
 * 1. 1 <= text.length <= 1000
 * 2. text consists of lowercase English letters and spaces.
 * 3. All the words in text a separated by a single space.
 * 4. 1 <= first.length, second.length <= 10
 * 5. first and second consist of lowercase English letters.
 */
public class OccurrencesAfterBigram {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOcurrences("alice is a good girl she is a good student", "a", "good")));
        System.out.println(Arrays.toString(findOcurrences("we will we will rock you", "we", "will")));
    }

    /**
     * 直接进行遍历判断前两个元素是否分别时 first、second 即可
     */
    public static String[] findOcurrences(String text, String first, String second) {
        String[] array = text.split(" ");
        if (array.length < 3) {
            return new String[0];
        }
        List<String> result = new ArrayList<>();
        for (int i = 2; i < array.length; i++) {
            if (array[i - 2].equals(first) && array[i - 1].equals(second)) {
                result.add(array[i]);
            }
        }
        return result.toArray(new String[0]);
    }
}

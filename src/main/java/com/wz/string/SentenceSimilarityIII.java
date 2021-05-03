package com.wz.string;

/**
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * For example, "Hello World", "HELLO", "hello world hello world" are all sentences. Words consist of only uppercase and lowercase English letters.
 * Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary sentence (possibly empty)
 * inside one of these sentences such that the two sentences become equal. For example, sentence1 = "Hello my name is Jane"
 * and sentence2 = "Hello Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in sentence2.
 * Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.
 *
 * Example 1:
 * Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
 *
 * Example 2:
 * Input: sentence1 = "of", sentence2 = "A lot of words"
 * Output: false
 * Explanation: No single sentence can be inserted inside one of the sentences to make it equal to the other.
 *
 * Example 3:
 * Input: sentence1 = "Eating right now", sentence2 = "Eating"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.
 *
 * Constraints:
 * 1. 1 <= sentence1.length, sentence2.length <= 100
 * 2. sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
 * 3. The words in sentence1 and sentence2 are separated by a single space.
 */
public class SentenceSimilarityIII {
    public static void main(String[] args) {
        System.out.println(areSentencesSimilar("My name is Haley", "My Haley"));
        System.out.println(areSentencesSimilar("of", "A lot of words"));
        System.out.println(areSentencesSimilar("Eating right now", "Eating"));
    }

    /**
     * 双指针
     * 对两个句子分别在首、尾设置双指针，遇到相同的单词时将指针同步向中间移动。若较短的句子能够全部遍历，则说明两个句子是相似的。
     */
    public static boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] array1, array2;
        // 保证 array1 存的是较长的句子
        if (sentence2.length() >= sentence1.length()) {
            array1 = sentence2.split(" ");
            array2 = sentence1.split(" ");
        } else {
            array1 = sentence1.split(" ");
            array2 = sentence2.split(" ");
        }

        int left1 = 0, right1 = array1.length - 1, left2 = 0, right2 = array2.length - 1;
        // 首尾不等，直接返回false
        if (!array1[left1].equals(array2[left2]) && !array1[right1].equals(array2[right2])) {
            return false;
        }
        // 移动左指针
        while (left2 <= right2 && array1[left1].equals(array2[left2])) {
            left1++;
            left2++;
        }
        // 移动右指针
        while (left2 <= right2 && array1[right1].equals(array2[right2])) {
            right1--;
            right2--;
        }

        return left2 > right2;
    }
}

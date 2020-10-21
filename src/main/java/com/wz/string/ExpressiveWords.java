package com.wz.string;

/**
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".
 * In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of
 * the following extension operation: choose a group consisting of characters c, and add some number of characters c
 * to the group so that the size of the group is 3 or more.
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo",
 * but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension
 * like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy
 * because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 * Given a list of query words, return the number of words that are stretchy.
 *
 * Example:
 * Input:
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 *
 * Constraints:
 * 0 <= len(S) <= 100.
 * 0 <= len(words) <= 100.
 * 0 <= len(words[i]) <= 100.
 * S and all words in words consist only of lowercase letters
 */
public class ExpressiveWords {
    public static void main(String[] args) {
        System.out.println(expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
    }

    /**
     * 某个字母可以重复三次或以上，那么对于这种重复后的单词，称之为可拉伸的。
     * 给一个拉伸后的单词S，又给了一个单词数组，问里面有多少个单词可以拉伸成为S。
     * 关键就在于看某个字母是否被重复了三次，重复两次是不行的。遍历单词数组words中的单词，来分别和S比较。
     * 用两个指针 i 和 j 来分别指向 S 和遍历单词 word，逐个比较，如果 S[i] 和 word[j] 相等，那么j自增1，遍历下一个字母。
     * 如果不相等或者 j 已经越界，再看当前 S[i] 是否是3个重复中的中间那个，即 S[i-1]、S[i+1] 需要等于 S[i]，若是，i自增1，
     * 然后加上for循环中的自增1，相当于总共增了2个，正好跳过这个重复三连。否则的话再看是否前两个都和当前的字母相等，
     * 即 S[i-1]、S[i-2] 需要等于 S[i]，因为可能重复的个数多于3个，如果这个条件不满足的话，直接break就行了。
     * for循环结束或者跳出后，看 S 和 word 是否正好遍历完，即 i 和 j 是否分别等于 S 和 word 的长度，是的话结果加1
     */
    public static int expressiveWords(String S, String[] words) {
        int result = 0, m = S.length();
        for (String word : words) {
            int i = 0, j = 0;
            for (; i < m; ++i) {
                if (j < word.length() && S.charAt(i) == word.charAt(j)) {
                    ++j;
                } else if (i > 0 && S.charAt(i) == S.charAt(i - 1) && i + 1 < m && S.charAt(i) == S.charAt(i + 1)) {
                    ++i;
                } else if (!(i > 1 && S.charAt(i) == S.charAt(i - 1) && S.charAt(i) == S.charAt(i - 2))) {
                    break;
                }
            }
            if (i == m && j == word.length()) {
                ++result;
            }
        }
        return result;
    }
}

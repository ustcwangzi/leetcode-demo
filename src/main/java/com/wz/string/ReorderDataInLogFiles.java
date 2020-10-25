package com.wz.string;

import java.util.Arrays;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * 1. Each word after the identifier will consist only of lowercase letters, or;
 * 2. Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.
 * It is guaranteed that each log has at least one word after its identifier.
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 * The digit-logs should be put in their original order.
 * Return the final order of the logs.
 *
 * Example:
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 * Constraints:
 * 1. 0 <= logs.length <= 100
 * 2. 3 <= logs[i].length <= 100
 * 3. logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderDataInLogFiles {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(reorderLogFiles(new String[]{"\"dig1 8 1 5 1\",\"let1 art can\",\"dig2 3 6\",\"let2 own kit dig\",\"let3 art zero\""})));
    }

    /**
     * 第一个字符串是标识符，可能由字母和数字组成，后面的是日志的内容，只有两种形式的，要么都是数字的，要么都是字母的
     * 排序的规则是对于内容是字母的日志，按照字母顺序进行排序，假如内容相同，则按照标识符的字母顺序排
     * 而对于内容的是数字的日志，放到最后面，且其顺序相对于原顺序保持不变
     */
    public static String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (String o1, String o2) -> {
            String[] o1words = o1.split(" ", 2);
            String[] o2words = o2.split(" ", 2);
            // o1 内容是数字，o2 内容不是数字， o1 排在后面
            if (Character.isDigit(o1words[1].charAt(0)) && !Character.isDigit(o2words[1].charAt(0))) {
                return 1;
            }
            // o2 内容是数字，o1 内容不是数字， o2 排在后面
            if (Character.isDigit(o2words[1].charAt(0)) && !Character.isDigit(o1words[1].charAt(0))) {
                return -1;
            }
            // o1 和 o2 内容都是数字，保持原顺序
            if (Character.isDigit(o2words[1].charAt(0))) {
                return 0;
            }

            int compare = o1words[1].compareTo(o2words[1]);
            // 内容相同，按照标识符的字母顺序排
            if (compare == 0) {
                return o1words[0].compareTo(o2words[0]);
            }
            return compare;
        });
        return logs;
    }
}

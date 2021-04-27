package com.wz.string;

/**
 * You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or "(al)" in some order.
 * The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al".
 * The interpreted strings are then concatenated in the original order.
 * Given the string command, return the Goal Parser's interpretation of command.
 *
 * Example 1:
 * Input: command = "G()(al)"
 * Output: "Goal"
 * Explanation: The Goal Parser interprets the command as follows:
 * G -> G
 * () -> o
 * (al) -> al
 * The final concatenated result is "Goal".
 *
 * Example 2:
 * Input: command = "G()()()()(al)"
 * Output: "Gooooal"
 *
 * Example 3:
 * Input: command = "(al)G(al)()()G"
 * Output: "alGalooG"
 *
 * Constraints:
 * 1. 1 <= command.length <= 100
 * 2. command consists of "G", "()", and/or "(al)" in some order.
 */
public class GoalParserInterpretation {
    public static void main(String[] args) {
        System.out.println(interpret("G()(al)"));
        System.out.println(interpret("G()()()()(al)"));
        System.out.println(interpret("(al)G(al)()()G"));
    }

    /**
     * 遍历 command，根据当前字符决定结果和指针移动
     * 1. 当前字符为 G，结果为 G，指针右移一位
     * 2. 当前字符不为 G，则一定为 (，需要判断下一个字符
     * 2.1 下一个字符为 )，结果为 o，指针右移两位
     * 2.2 下一个字符不为 )，则接下来一定是 al)，结果为 al，指针右移四位
     */
    public static String interpret(String command) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < command.length()) {
            if (command.charAt(i) == 'G') {
                builder.append('G');
                i += 1;
            } else if (command.charAt(i + 1) == ')') {
                builder.append('o');
                i += 2;
            } else {
                builder.append("al");
                i += 4;
            }
        }
        return builder.toString();
    }
}

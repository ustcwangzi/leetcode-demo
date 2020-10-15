package com.wz.string;

import java.util.Stack;

/**
 * Given a string representing a code snippet, you need to implement a tag validator to parse the code and return whether it is valid.
 * A code snippet is valid if all the following rules hold:
 * 1. The code must be wrapped in a valid closed tag. Otherwise, the code is invalid.
 * 2. A closed tag (not necessarily valid) has exactly the following format : <TAG_NAME>TAG_CONTENT</TAG_NAME>.
 *    Among them, <TAG_NAME> is the start tag, and </TAG_NAME> is the end tag. The TAG_NAME in start and end tags should be the same.
 *    A closed tag is valid if and only if the TAG_NAME and TAG_CONTENT are valid.
 * 3. A valid TAG_NAME only contain upper-case letters, and has length in range [1,9]. Otherwise, the TAG_NAME is invalid.
 * 4. A valid TAG_CONTENT may contain other valid closed tags, cdata and any characters (see note1) EXCEPT unmatched <,
 *    unmatched start and end tag, and unmatched or closed tags with invalid TAG_NAME. Otherwise, the TAG_CONTENT is invalid.
 * 5. A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa. However,
 *    you also need to consider the issue of unbalanced when tags are nested.
 * 6. A < is unmatched if you cannot find a subsequent >. And when you find a < or </,
 *    all the subsequent characters until the next > should be parsed as TAG_NAME (not necessarily valid).
 * 7. The cdata has the following format : <![CDATA[CDATA_CONTENT]]>. The range of CDATA_CONTENT is defined as the
 *    characters between <![CDATA[ and the first subsequent ]]>.
 * 8. CDATA_CONTENT may contain any characters. The function of cdata is to forbid the validator to parse CDATA_CONTENT,
 *    so even it has some characters that can be parsed as tag (no matter valid or invalid), you should treat it as regular characters.
 *
 * Valid Code Examples:
 * Input: "<DIV>This is the first line <![CDATA[<div>]]></DIV>"
 * Output: True
 * Explanation:
 * The code is wrapped in a closed tag : <DIV> and </DIV>.
 * The TAG_NAME is valid, the TAG_CONTENT consists of some characters and cdata.
 * Although CDATA_CONTENT has unmatched start tag with invalid TAG_NAME, it should be considered as plain text, not parsed as tag.
 * So TAG_CONTENT is valid, and then the code is valid. Thus return true.
 *
 * Input: "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"
 * Output: True
 * Explanation:
 * We first separate the code into : start_tag|tag_content|end_tag.
 * start_tag -> "<DIV>"
 * end_tag -> "</DIV>"
 * tag_content could also be separated into : text1|cdata|text2.
 * text1 -> ">>  ![cdata[]] "
 * cdata -> "<![CDATA[<div>]>]]>", where the CDATA_CONTENT is "<div>]>"
 * text2 -> "]]>>]"
 * The reason why start_tag is NOT "<DIV>>>" is because of the rule 6.
 * The reason why cdata is NOT "<![CDATA[<div>]>]]>]]>" is because of the rule 7.
 *
 * Invalid Code Examples:
 * Input: "<A>  <B> </A>   </B>"
 * Output: False
 * Explanation: Unbalanced. If "<A>" is closed, then "<B>" must be unmatched, and vice versa.
 *
 * Input: "<DIV>  div tag is not closed  <DIV>"
 * Output: False
 *
 * Input: "<DIV>  unmatched <  </DIV>"
 * Output: False
 *
 * Input: "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>"
 * Output: False
 *
 * Input: "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>"
 * Output: False
 *
 * Input: "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>"
 * Output: False
 *
 * Note:
 * For simplicity, you could assume the input code (including the any characters mentioned above)
 * only contain letters, digits, '<','>','/','!','[',']' and ' '.
 */
public class TagValidator {
    public static void main(String[] args) {
        System.out.println(isValid("<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"));
    }

    public static boolean isValid(String code) {
        if (code == null || code.length() == 0) {
            return false;
        }

        char[] array = code.toCharArray();
        int n = array.length;
        Stack<String> stack = new Stack<>();
        StringBuilder sb;
        int i = 0;
        String startTag = "###", endTag = "$$$";
        while (i < n) {
            if (array[i] == '<') {
                if (i >= n - 1) {
                    return false;
                }
                //CDATA
                int j;
                if (array[i + 1] == '!') {
                    j = i;
                    sb = new StringBuilder();
                    while (j < n && !sb.toString().endsWith("]]>")) {
                        sb.append(array[j++]);
                    }
                    if (!isValidCharacterData(sb.toString())) {
                        return false;
                    }
                    //ending tag
                } else if (array[i + 1] == '/') {
                    j = i + 2;
                    sb = new StringBuilder();
                    while (j < n && array[j] != '>') {
                        sb.append(array[j++]);
                    }
                    j++;
                    String tag = sb.toString();
                    if (!isValidEndTag(tag, stack)) {
                        return false;
                    }
                    if (j == n) {
                        endTag = tag;
                    }
                    //starting tag
                } else {
                    j = i + 1;
                    sb = new StringBuilder();
                    while (j < n && array[j] != '>') {
                        sb.append(array[j++]);
                    }
                    j++;
                    String tag = sb.toString();
                    if (!isValidStartTag(tag, stack)) {
                        return false;
                    }
                    if (i == 0) {
                        startTag = tag;
                    }
                }
                i = j;
            } else {
                i++;
            }
        }
        return stack.isEmpty() && startTag.equals(endTag);
    }

    private static boolean isValidCharacterData(String tag) {
        return tag.startsWith("<![CDATA[");
    }

    private static boolean isValidEndTag(String tag, Stack<String> stack) {
        if (inValidTag(tag)) {
            return false;
        }
        if (stack.isEmpty() || !tag.equals(stack.peek())) {
            return false;
        }
        stack.pop();
        return true;
    }

    private static boolean isValidStartTag(String tag, Stack<String> stack) {
        if (inValidTag(tag)) {
            return false;
        }
        stack.push(tag);
        return true;
    }

    private static boolean inValidTag(String tag) {
        if (tag.length() < 1 || tag.length() > 9) {
            return true;
        }
        for (int i = 0; i < tag.length(); i++) {
            char c = tag.charAt(i);
            if (c < 'A' || c > 'Z') {
                return true;
            }
        }
        return false;
    }
}

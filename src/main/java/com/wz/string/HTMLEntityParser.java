package com.wz.string;

/**
 * HTML entity parser is the parser that takes HTML code as input and replace all the entities of the special characters by the characters itself.
 * The special characters and their entities for HTML are:
 * 1. Quotation Mark: the entity is &quot; and symbol character is ".
 * 2. Single Quote Mark: the entity is &apos; and symbol character is '.
 * 3. Ampersand: the entity is &amp; and symbol character is &.
 * 4. Greater Than Sign: the entity is &gt; and symbol character is >.
 * 5. Less Than Sign: the entity is &lt; and symbol character is <.
 * 6. Slash: the entity is &frasl; and symbol character is /.
 * Given the input text string to the HTML parser, you have to implement the entity parser.
 * Return the text after replacing the entities by the special characters.
 *
 * Example 1:
 * Input: text = "&amp; is an HTML entity but &ambassador; is not."
 * Output: "& is an HTML entity but &ambassador; is not."
 * Explanation: The parser will replace the &amp; entity by &
 *
 * Example 2:
 * Input: text = "and I quote: &quot;...&quot;"
 * Output: "and I quote: \"...\""
 *
 * Example 3:
 * Input: text = "Stay home! Practice on Leetcode :)"
 * Output: "Stay home! Practice on Leetcode :)"
 *
 * Example 4:
 * Input: text = "x &gt; y &amp;&amp; x &lt; y is always false"
 * Output: "x > y && x < y is always false"
 *
 * Example 5:
 * Input: text = "leetcode.com&frasl;problemset&frasl;all"
 * Output: "leetcode.com/problemset/all"
 *
 * Constraints:
 * 1. 1 <= text.length <= 10^5
 * 2. The string may contain any possible characters out of all the 256 ASCII characters.
 */
public class HTMLEntityParser {
    public static void main(String[] args) {
        System.out.println(entityParser("x &gt; y &amp;&amp; x &lt; y is always false"));
    }

    public static String entityParser(String text) {
        return text.replace("&quot;", "\"")
                .replace("&apos;", "'")
                .replace("&gt;", ">")
                .replace("&lt;", "<")
                .replace("&frasl;", "/")
                .replace("&amp;", "&");
    }
}

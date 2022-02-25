package com.wz.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A string s can be partitioned into groups of size k using the following procedure:
 * 1. The first group consists of the first k characters of the string,
 *    the second group consists of the next k characters of the string, and so on. Each character can be a part of exactly one group.
 * 2. For the last group, if the string does not have k characters remaining, a character fill is used to complete the group.
 * Note that the partition is done so that after removing the fill character from the last group (if it exists) and concatenating all the groups in order, the resultant string should be s.
 * Given the string s, the size of each group k and the character fill, return a string array denoting the composition of every group s has been divided into, using the above procedure.
 *
 * Example 1:
 * Input: s = "abcdefghi", k = 3, fill = "x"
 * Output: ["abc","def","ghi"]
 * Explanation:
 * The first 3 characters "abc" form the first group.
 * The next 3 characters "def" form the second group.
 * The last 3 characters "ghi" form the third group.
 * Since all groups can be completely filled by characters from the string, we do not need to use fill.
 * Thus, the groups formed are "abc", "def", and "ghi".
 *
 * Example 2:
 * Input: s = "abcdefghij", k = 3, fill = "x"
 * Output: ["abc","def","ghi","jxx"]
 * Explanation:
 * Similar to the previous example, we are forming the first three groups "abc", "def", and "ghi".
 * For the last group, we can only use the character 'j' from the string. To complete this group, we add 'x' twice.
 * Thus, the 4 groups formed are "abc", "def", "ghi", and "jxx".
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. s consists of lowercase English letters only.
 * 3. 1 <= k <= 100
 * 4. fill is a lowercase English letter.
 */
public class DivideStringIntoGroupsOfSizeK {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(divideString("abcdefghij", 3, 'x')));
    }

    /**
     * 遍历字符串，使用 StringBuilder 收集字符，每次 (i + 1) % k == 0 时说明可以组成一组
     * 遍历结束后，若字符串长度是 k 的整数倍，则说明没有剩余字符，否则计算剩余字符还差几个 fill 可以组成一组
     */
    public static String[] divideString(String s, int k, char fill) {
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            builder.append(s.charAt(i));
            if ((i + 1) % k == 0) {
                list.add(builder.toString());
                builder = new StringBuilder();
            }
        }
        if (s.length() % k == 0) {
            return list.toArray(new String[0]);
        }

        int delta = k - s.length() % k;
        for (int i = 0; i < delta; i++) {
            builder.append(fill);
        }
        list.add(builder.toString());
        return list.toArray(new String[0]);
    }
}

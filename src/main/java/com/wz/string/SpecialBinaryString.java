package com.wz.string;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Special binary strings are binary strings with the following two properties:
 * 1. The number of 0's is equal to the number of 1's.
 * 2. Every prefix of the binary string has at least as many 1's as 0's.
 * Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them.
 * (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)
 * At the end of any number of moves, what is the lexicographically largest resulting string possible?
 *
 * Example 1:
 * Input: S = "11011000"
 * Output: "11100100"
 * Explanation:
 * The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
 * This is the lexicographically largest string possible after some number of swaps.
 */
public class SpecialBinaryString {
    public static void main(String[] args) {
        System.out.println(makeLargestSpecial("11011000"));
    }

    /**
     * 需要满足两个要求，一是0和1的个数要相等，二是任何一个前缀中的1的个数都要大于等于0的个数。
     * 其实就是一个括号字符串。这里的1表示左括号，0表示右括号，那么题目中的两个限制条件其实就是限定这个括号字符串必须合法，
     * 即左右括号的个数必须相同，且左括号的个数随时都要大于等于右括号的个数。
     * 这道题让通过交换子字符串，生成字母顺序最大的特殊字符串，注意这里交换的子字符串也必须是特殊字符串，满足题目中给定的两个条件，
     * 换作括号来说就是交换的子括号字符串也必须是合法的。那么什么样的字符串是字母顺序最大的呢，根据题目中的例子可以分析得出，
     * 应该是1靠前的越多越好，那么换作括号来说就是括号嵌套多的应该放在前面。比如分析题目中的例子:
     * 11011000    ->    (()(()))
     * 11100100    ->    ((())())
     * 例子中的交换操作其实是将上面的()和(())交换了，因为(())嵌套的括号多，那么左括号就多，在前面的1就多，所以字母顺序大。
     * 所以要做的就是将中间的子串分别提取出来，然后排序，再放回即可。上面的这个例子相对简单一些，实际上可以更复杂，所以再给它们排序之前，
     * 其自身的顺序应该已经按字母顺序排好了才行，这种特点天然适合递归的思路，先递归到最里层，然后一层一层向外扩展，直至完成所有的排序。
     * 由于移动的子字符串也必须是合法的，那么利用检测括号字符串合法性的一个最常用的方法，就是遇到左括号加1，遇到右括号-1，这样得到0的时候，
     * 就是一个合法的子字符串了。用变量 start 来统计这个合法子字符串的起始位置，result 来保存这些合法的子字符串。
     * 遍历字符串S，遇到1，count自增1，否则自减1。当count为0时，将这个字串加入result，注意需要给这个字串自身也排序，
     * 不用对整个子串调用递归，因为子串的起始位置和结束位置是确定的，一定是1和0，只需对中间的调用递归即可，然后更新 start 为 i+1
     * 当将所有排序后的合法字串存入 result 中后，对 result 进行排序，将字母顺序大的放前面，最后将其连为一个字符串即可
     */
    public static String makeLargestSpecial(String S) {
        if (S.length() <= 2) {
            return S;
        }

        int start = 0, end = S.length(), count = 0;
        List<String> result = new LinkedList<>();
        for (int i = start; i < end; i++) {
            if (S.charAt(i) == '1') {
                count++;
                continue;
            }
            if (count > 0) {
                count--;
                if (count == 0) {
                    // complete one round
                    result.add("1" + makeLargestSpecial(S.substring(start + 1, i)) + "0");
                    start = i + 1;
                }
            }
        }
        result.sort(Comparator.reverseOrder());
        return String.join("", result);
    }
}

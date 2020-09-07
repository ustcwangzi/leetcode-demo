package com.wz.math;

/**
 * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
 * Note:
 * 1. Input contains only lowercase English letters.
 * 2. Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
 * 3. Input length is less than 50,000.
 *
 * Example 1:
 * Input: "owoztneoer"
 * Output: "012"
 *
 * Example 2:
 * Input: "fviefuro"
 * Output: "45"
 */
public class ReconstructOriginalDigitsFromEnglish {
    public static void main(String[] args) {
        System.out.println(originalDigits("owoztneoer"));
        System.out.println(originalDigits("fviefuro"));
    }

    /**
     * 先要统计出各个字符出现的次数，然后算出每个单词出现的次数，然后就可以重建了。
     * 由于题目中限定了输入的字符串一定是有效的，那么不会出现无法成功重建的情况。
     * 仔细观察这些表示数字的单词"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"，
     * 可以发现有些的单词的字符是独一无二的，比如z，只出现在zero中，还有w，u，x，g这四个单词，分别只出现在two，four，six，eight中，
     * 那么这五个数字的个数就可以被确定了，由于含有o的单词有zero，two，four，one，其中前三个都被确定了，那么one的个数也就知道了；
     * 由于含有h的单词有eight，three，其中eight个数已知，那么three的个数就知道了；
     * 由于含有f的单词有four，five，其中four个数已知，那么five的个数就知道了；
     * 由于含有s的单词有six，seven，其中six个数已知，那么seven的个数就知道了；
     * 由于含有i的单词有six，eight，five，nine，其中前三个都被确定了，那么nine的个数就知道了，
     * 按这个顺序"zero", "two", "four", "six", "eight", "one", "three", "five", "seven", "nine"就能找出所有的个数了
     */
    public static String originalDigits(String s) {
        int[] counts = new int[10];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'z') counts[0]++;
            if (ch == 'u') counts[4]++;
            if (ch == 'w') counts[2]++;
            if (ch == 'x') counts[6]++;
            if (ch == 'g') counts[8]++;
            if (ch == 'h') counts[3]++; // 3, 8
            if (ch == 's') counts[7]++; // 6, 7
            if (ch == 'f') counts[5]++; // 4, 5
            if (ch == 'o') counts[1]++; // 0, 1, 2, 4
            if (ch == 'i') counts[9]++; // 5, 6, 8, 9
        }

        counts[3] -= counts[8];
        counts[7] -= counts[6];
        counts[5] -= counts[4];
        counts[1] -= counts[0] + counts[2] + counts[4];
        counts[9] -= counts[5] + counts[6] + counts[8];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}

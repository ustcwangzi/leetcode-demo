package com.wz.string;

/**
 * Given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs,
 * that is, multiple frogs can croak at the same time, so multiple “croak” are mixed.
 * Return the minimum number of different frogs to finish all the croak in the given string.
 * A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially.
 * The frogs have to print all five letters to finish a croak. If the given string is not a combination of valid "croak" return -1.
 *
 * Example 1:
 * Input: croakOfFrogs = "croakcroak"
 * Output: 1
 * Explanation: One frog yelling "croak" twice.
 *
 * Example 2:
 * Input: croakOfFrogs = "crcoakroak"
 * Output: 2
 * Explanation: The minimum number of frogs is two.
 * The first frog could yell "crcoakroak".
 * The second frog could yell later "crcoakroak".
 *
 * Example 3:
 * Input: croakOfFrogs = "croakcrook"
 * Output: -1
 * Explanation: The given string is an invalid combination of "croak" from different frogs.
 *
 * Constraints:
 * 1. 1 <= croakOfFrogs.length <= 10^5
 * 2. All characters in the string are: 'c', 'r', 'o', 'a' or 'k'.
 */
public class MinimumNumberOfFrogsCroaking {
    public static void main(String[] args) {
        System.out.println(minNumberOfFrogs("crcoakroak"));
        System.out.println(minNumberOfFrogs("croakcrook"));
    }

    /**
     * 遍历 croakOfFrogs，当遇到 c 时，说明是一个 croak 的开始，当遇到 k 时，说明是一个 croak 的结束
     * 任何时候都要保持 count['c'] > count['r'] > count['o'] > count['a'] > count['k']
     * 遍历结束时，需要 count['c'] == count['r'] == count['o'] == count['a'] == count['k']
     */
    public static int minNumberOfFrogs(String croakOfFrogs) {
        int result = 0, num = 0;
        int[] count = new int[128];
        char[] array = croakOfFrogs.toCharArray();
        for (char cur : array) {
            count[cur]++;
            if (cur == 'c') {
                num++;
            } else if (cur == 'k') {
                num--;
            }
            if (count['k'] > count['a'] || count['a'] > count['o'] || count['o'] > count['r'] || count['r'] > count['c']) {
                return -1;
            }
            result = Math.max(result, num);
        }

        if (count['k'] != count['a'] || count['a'] != count['o'] || count['o'] != count['r'] || count['r'] != count['c']) {
            return -1;
        }
        return result;
    }
}

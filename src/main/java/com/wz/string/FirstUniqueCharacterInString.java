package com.wz.string;

/**
 * Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
 *
 * Examples:
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode"
 * return 2.
 *
 * Note: You may assume the string contains only lowercase English letters.
 */
public class FirstUniqueCharacterInString {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
    }

    /**
     * count[i][0] 存储每个字符出现的次数、count[i][0] 存储每个字符最后出现的下标
     * 然后遍历 count[i]，对于出现次数为 1 的字符，统计最小的下标
     */
    public static int firstUniqChar(String s) {
        int[][] count = new int[26][2];
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            count[cur - 'a'][0]++;
            count[cur - 'a'][1] = i;
        }
        int result = Integer.MAX_VALUE;
        for (int[] array : count) {
            if (array[0] == 1) {
                result = Math.min(result, array[1]);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}

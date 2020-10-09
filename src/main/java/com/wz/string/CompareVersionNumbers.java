package com.wz.string;

/**
 * Given two version numbers, version1 and version2, compare them.
 * Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros.
 * Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0,
 * the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
 * To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their
 * integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal.
 * If a version number does not specify a revision at an index, then treat the revision as 0.
 * For example, version 1.0 is less than version 1.1 because their revision 0s are the same,
 * but their revision 1s are 0 and 1 respectively, and 0 < 1.
 *
 * Return the following:
 * 1. If version1 < version2, return -1.
 * 2. If version1 > version2, return 1.
 * 3. Otherwise, return 0.
 *
 * Example 1:
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
 *
 * Example 2:
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: version1 does not specify revision 2, which means it is treated as "0".
 *
 * Example 3:
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 *
 * Constraints:
 * 1. 1 <= version1.length, version2.length <= 500
 * 2. version1 and version2 only contain digits and '.'.
 * 3. version1 and version2 are valid version numbers.
 * 4. All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 */
public class CompareVersionNumbers {
    public static void main(String[] args) {
        System.out.println(compareVersion("1.01", "1.001"));
        System.out.println(compareVersion("1.0", "1.00"));
        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
    }

    /**
     * 使用"."将原字符串分割为数组，然后遍历，将字符串转为 int 比较大小
     */
    public static int compareVersion(String version1, String version2) {
        String[] array1 = version1.split("\\."), array2 = version2.split("\\.");
        int index = Math.min(array1.length, array2.length);

        for (int i = 0; i < index; i++) {
            int num1 = Integer.parseInt(array1[i]), num2 = Integer.parseInt(array2[i]);
            if (num1 > num2) {
                return 1;
            }
            if (num1 < num2) {
                return -1;
            }
        }

        for (int i = index; i < array1.length; i++) {
            if (Integer.parseInt(array1[i]) != 0) {
                return 1;
            }
        }

        for (int i = index; i < array2.length; i++) {
            if (Integer.parseInt(array2[i]) != 0) {
                return -1;
            }
        }

        return 0;
    }
}

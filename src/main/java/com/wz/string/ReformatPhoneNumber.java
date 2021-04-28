package com.wz.string;

/**
 * You are given a phone number as a string number. number consists of digits, spaces ' ', and/or dashes '-'.
 * You would like to reformat the phone number in a certain manner. Firstly, remove all spaces and dashes.
 * Then, group the digits from left to right into blocks of length 3 until there are 4 or fewer digits. The final digits are then grouped as follows:
 * 1. 2 digits: A single block of length 2.
 * 2. 3 digits: A single block of length 3.
 * 3. 4 digits: Two blocks of length 2 each.
 * The blocks are then joined by dashes. Notice that the reformatting process should never produce any blocks of length 1 and produce at most two blocks of length 2.
 * Return the phone number after formatting.
 *
 * Example 1:
 * Input: number = "1-23-45 6"
 * Output: "123-456"
 * Explanation: The digits are "123456".
 * Step 1: There are more than 4 digits, so group the next 3 digits. The 1st block is "123".
 * Step 2: There are 3 digits remaining, so put them in a single block of length 3. The 2nd block is "456".
 * Joining the blocks gives "123-456".
 *
 * Example 2:
 * Input: number = "123 4-567"
 * Output: "123-45-67"
 * Explanation: The digits are "1234567".
 * Step 1: There are more than 4 digits, so group the next 3 digits. The 1st block is "123".
 * Step 2: There are 4 digits left, so split them into two blocks of length 2. The blocks are "45" and "67".
 * Joining the blocks gives "123-45-67".
 *
 * Example 3:
 * Input: number = "123 4-5678"
 * Output: "123-456-78"
 * Explanation: The digits are "12345678".
 * Step 1: The 1st block is "123".
 * Step 2: The 2nd block is "456".
 * Step 3: There are 2 digits left, so put them in a single block of length 2. The 3rd block is "78".
 * Joining the blocks gives "123-456-78".
 *
 * Constraints:
 * 1. 2 <= number.length <= 100
 * 2. number consists of digits and the characters '-' and ' '.
 * 3. There are at least two digits in number.
 */
public class ReformatPhoneNumber {
    public static void main(String[] args) {
        System.out.println(reformatNumber("123 4-567"));
        System.out.println(reformatNumber("123 4-5678"));
    }

    /**
     * 先将 number 中的非数字字符去除，然后遍历 number，根据剩余字符个数采取不同策略
     * 将剩余字符个数分为 大于4、等于4、等于3、等于2 四种情况，分别收集结果、移动指针
     */
    public static String reformatNumber(String number) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            if (Character.isDigit(number.charAt(i))) {
                builder.append(number.charAt(i));
            }
        }

        number = builder.toString();
        builder = new StringBuilder();

        int n = number.length(), i = 0;
        while (i < n) {
            if (n - i > 4) {
                builder.append(number, i, i + 3);
                builder.append("-");
                i += 3;
            } else if (n - i == 4) {
                builder.append(number, i, i + 2);
                i += 2;
                builder.append("-");
                builder.append(number, i, i + 2);
                i += 2;
            } else if (n - i == 3) {
                builder.append(number, i, i + 3);
                i += 3;
            } else if (n - i == 2) {
                builder.append(number, i, i + 2);
                i += 4;
            }
        }
        return builder.toString();
    }
}

package com.wz.math;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 *
 * Example 1:
 * Output: "One Hundred Twenty Three"
 *
 * Example 2:
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class IntegerToEnglishWords {
    public static void main(String[] args) {
        System.out.println(numberToWords(0));
        System.out.println(numberToWords(12345));
        System.out.println(numberToWords(1234567));
    }

    /**
     * 1. 题目首先告诉我们要3个一组的进行处理，而且限定了输入数字范围为0到2^31 - 1之间，最高只能到billion位，
     *    3个一组也只需处理四组即可，那么需要一个处理三个一组数字的函数
     * 2. 需要把 1,2,... 9,10 和 11,...,19的英文单词都列出来，分别放到2个数组里；还要把 20,30,...,90的英文单词列出来放到另一个数组里
     * 3. 比如一个三位数n
     * a) 百位数表示为n/100
     * b) 后两位数一起表示为n%100
     * c) 十位数表示为n%100/10
     * d) 个位数表示为n%10
     * 4. 然后看后两位数是否小于 10 或者小于 20，小于的话直接从数组中取出单词
     * 5. 如果大于等于 20 的话，则分别将十位和个位数字的单词从两个数组中取出来
     * 6. 然后再来处理百位上的数字，还要记得加上 Hundred，然后中间要插入"Thousand", "Million", "Billion"到对应的位置
     * 7. 最后check一下末尾是否有空格，把空格都删掉
     */
    public static String numberToWords(int num) {
        if (num < 0) {
            return null;
        }
        if (num == 0) {
            return "Zero";
        }
        return convert(num);
    }

    private static String convert(int num) {
        String[] belowTen = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] belowHundreds = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String result;
        if (num < 10) {
            result = belowTen[num];
        } else if (num < 20) {
            result = teens[num % 10];
        } else if (num < 100) {
            result = belowHundreds[num / 10] + " " + belowTen[num % 10];
        } else if (num < 1000) {
            result = belowTen[num / 100] + " Hundred " + convert(num % 100);
        } else if (num < 1000000) {
            result = convert(num / 1000) + " Thousand " + convert(num % 1000);
        } else if (num < 1000000000) {
            result = convert(num / 1000000) + " Million " + convert(num % 1000000);
        } else {
            result = convert(num / 1000000000) + " Billion " + convert(num % 1000000000);
        }
        return result.trim();
    }
}

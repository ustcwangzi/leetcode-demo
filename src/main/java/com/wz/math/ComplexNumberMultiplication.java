package com.wz.math;

/**
 * Given two strings representing two complex numbers.
 * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
 *
 * Example 1:
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 *
 * Example 2:
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 *
 * Note:
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range
 * of [-100, 100]. And the output should be also in this form.
 */
public class ComplexNumberMultiplication {
    public static void main(String[] args) {
        System.out.println(complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
    }

    /**
     * 把字符串中的实部和虚部分离开并进行运算
     * 使用加号的位置分别拆出实部虚部，进行运算后再变回字符串
     */
    public static String complexNumberMultiply(String a, String b) {
        String[] array1 = a.split("\\+");
        String[] array2 = b.split("\\+");

        int numa1 = Integer.parseInt(array1[0]);
        int numa2 = Integer.parseInt(array2[0]);

        int numb1 = Integer.parseInt(array1[1].substring(0, array1[1].length() - 1));
        int numb2 = Integer.parseInt(array2[1].substring(0, array2[1].length() - 1));

        return (numa1 * numa2 - (numb1 * numb2)) + "+" + (numa1 * numb2 + numb1 * numa2) + "i";
    }
}

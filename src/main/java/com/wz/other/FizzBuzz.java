package com.wz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return a string array answer (1-indexed) where:
 * 1. answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 * 2. answer[i] == "Fizz" if i is divisible by 3.
 * 3. answer[i] == "Buzz" if i is divisible by 5.
 * 4. answer[i] == i if non of the above conditions are true.
 *
 * Example 1:
 * Input: n = 3
 * Output: ["1","2","Fizz"]
 *
 * Example 2:
 * Input: n = 5
 * Output: ["1","2","Fizz","4","Buzz"]
 *
 * Example 3:
 * Input: n = 15
 * Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 *
 *
 * Constraints:
 * 1 <= n <= 10^4
 */
public class FizzBuzz {
    public static void main(String[] args) {
        System.out.println(fizzBuzz(3));
        System.out.println(fizzBuzz(5));
    }

    /**
     * 直接遍历 1～n，逐个进行判断即可
     */
    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 == 0) {
                result.add("FizzBuzz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else {
                result.add(String.valueOf(i));
            }
        }

        return result;
    }
}

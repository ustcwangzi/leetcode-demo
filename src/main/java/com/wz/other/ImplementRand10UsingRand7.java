package com.wz.other;

import java.util.Random;

/**
 * Given the API rand7() that generates a uniform random integer in the range [1, 7],
 * write a function rand10() that generates a uniform random integer in the range [1, 10].
 * You can only call the API rand7(), and you shouldn't call any other API. Please do not use a language's built-in random API.
 * Each test case will have one internal argument n, the number of times that your implemented function rand10() will be called while testing.
 * Note that this is not an argument passed to rand10().
 *
 * Constraints:
 * 1 <= n <= 10^5
 */
public class ImplementRand10UsingRand7 {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(rand10());
        }
    }

    /**
     * 如果由一个大的随机数产生一个小的随机数，比如 rand10() 要产生 rand5()，那么可以取余，如果选出的随机数在 1~5 则保留，否则重新选
     * 现在的问题是如何由一个小的随机数产生一个大的随机数，比如 rand7() 产生 rand10()
     * 那么先做这样的操作：result = 7*(rand7()-1) + rand7()，这样 result 可以产生 1~49 的随机数
     * 7*(rand7()-1) 可以产生 0,7,14,21,28,35,42，那么在加上随机产生的 1~7，就可以产生 1~49 的随机数
     * 类似于先把数据的间隔拉大而能刚好进行本身的线性插值，这样就可以产生一个较大的随机数
     * 能产生 1~49 的随机数，可以取其中 1~40 的数，然后再对取到的数进行取余操作即可
     */
    public static int rand10() {
        int result;
        do {
            result = 7 * (rand7() - 1) + rand7();
        } while (result > 40);
        return result % 10 + 1;
    }

    public static int rand7() {
        return new Random().nextInt(7) + 1;
    }
}

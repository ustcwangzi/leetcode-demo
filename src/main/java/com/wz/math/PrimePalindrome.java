package com.wz.math;

public class PrimePalindrome {
    public static void main(String[] args) {
        System.out.println(primePalindrome(6));
        System.out.println(primePalindrome(13));
    }

    /**
     * 给一个整数N，让找一个大于等于N的回文质数，既要求是质数，又要求是回文数
     * 对于质数来说，非常的不规则，没有太好的办法来直接组成质数，而是需要通过验证来看其是否为质数。
     * 而回文数就不一样的，非常的有特点，可以直接按规律来组成回文数，而不是对每个数字都进行验证，这样的话就相当于去掉了验证回文数的步骤，
     * 由于给了N的取值范围，可以遍历前一半的所有数字，然后翻转一下，组成后一半，两个拼起来就是回文数了。
     * 这道题用到了一个比较偏门的定理，就是所有长度为偶数的回文数字一定是 11 的倍数。通过这个定理，可以知道除了 11 之外，
     * 所有长度为偶数的回文数都不是质数，那么当N为 [8, 11] 中的数字时，才会返回11，对于其他所有的都是符合规律的。
     * 那就可以只组奇数的回文数了，由于N的范围是 [1, 1e8]，所以前一半范围是 [1, 1e5)，因为还包含了最中间的那个数字，
     * 所以在翻转之后，记得要把第一位数字去掉，因为最中间的数字只能保留一个，然后把两个数字拼接起来。
     * 此时再判断这个拼接后的数字是否大于等N，并且是否是质数，都满足的话返回这个数字即可
     */
    public static int primePalindrome(int N) {
        if (8 <= N && N <= 11) {
            return 11;
        }
        for (int x = 1; x < 1e5; ++x) {
            String s = Integer.toString(x), r = new StringBuilder(s).reverse().substring(1);
            int y = Integer.parseInt(s + r);
            if (y >= N && isPrime(y)) {
                return y;
            }
        }
        return -1;
    }

    private static boolean isPrime(int num) {
        if (num < 2 || num % 2 == 0) {
            return num == 2;
        }
        int limit = (int) Math.sqrt(num);
        for (int i = 3; i <= limit; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

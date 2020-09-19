package com.wz.math;

/**
 * A positive integer is magical if it is divisible by either A or B.
 * Return the N-th magical number.  Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: N = 1, A = 2, B = 3
 * Output: 2
 *
 * Example 2:
 * Input: N = 4, A = 2, B = 3
 * Output: 6
 *
 * Note:
 * 1 <= N <= 10^9
 * 2 <= A <= 40000
 * 2 <= B <= 40000
 */
public class NthMagicalNumber {
    public static void main(String[] args) {
        System.out.println(nthMagicalNumber(1, 2, 3));
        System.out.println(nthMagicalNumber(4, 2, 3));
    }

    /**
     * 对于 [1, n] 中的数，能整除A的有多少个，假如 n=17，A=2，那么 17 以内能整除2的就有 2，4，6，8，10，12，14，16，这八个数字，
     * 貌似正好是 n/A=17/2=8。再来看其他例子，比如 n=17，B=3，那么 17 以内能整除3的就有 3，6，9，12，15，这五个数字，
     * 貌似也是 n/B=17/3=5。那么能被A或B整除的个数呢，比如 n=17，A=2，B=3，那么 17 以内能整除2或3的数字有
     * 2，3，4，6，8，9，10，12，14，15，16，这十一个数字，并不是 n/A + n/B = 8+5 = 13，为啥呢？因为有些数字重复计算了，
     * 比如 6，12，这两个数字都加了两次，我们发现这两个数字都是既可以整除A又可以整除B的，只要把这两个数字减去 13-2=11，就是所求的了。
     * 怎么找同时能被A和B整除的数呢，其实第一个这样的数就是A和B的最小公倍数 LCM，所有能被A和B的最小公倍数整除的数字一定能同时整除A和B。
     * 那么最小公倍数 LCM 怎么算呢？就是A乘以B除以最大公约数 GCD
     * 因此，对于任意小于等于数字x的且能被A或B整除的正整数的个数为 x/A + x/B - x/lcm(A,B)。需要让这个式子等于N，然后解出x的值即为所求。
     * 直接根据式子去求解x得到的不一定是正整数，可以反其道而行之，带确定的x值进入等式，算出一个结果，然后跟N比较大小，
     * 根据这个大小来决定新的要验证的x值，这不就是典型的二分搜索法么。确定了要使用 Binary Search 后，就要来确定x值的范围了，
     * x值最小能取到A和B中的较小值，由于A和B最小能取到2，所以x的最小值也就是2。至于最大值，根据上面的等式，x能取到的最大值是 N*min(A,B)，
     * 根据题目中N和A，B的范围，可以推出最大值不会超过 1e14，这个已经超过整型最大值了，所以初始化的变量都要用长整型。
     * 然后就是进入 while 循环了，判定条件是上面写的那个等式
     */
    public static int nthMagicalNumber(int N, int A, int B) {
        long lcm = A * B / gcd(A, B), left = 2, right = (long) 1e14, M = (long) (1e9 + 7);
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (mid / A + mid / B - mid / lcm < N) left = mid + 1;
            else right = mid;
        }
        return (int) (right % M);
    }

    private static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}

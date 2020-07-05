package com.wz.lists;

/**
 * Write an iterator that iterates through a run-length encoded sequence.
 * The iterator is initialized by RLEIterator(int[] A), where A is a run-length encoding of some sequence.
 * More specifically, for all even i, A[i] tells us the number of times that the non-negative integer value A[i+1] is repeated in the sequence.
 * The iterator supports one function: next(int n), which exhausts the next n elements (n >= 1) and
 * returns the last element exhausted in this way.  If there is no element left to exhaust, next returns -1 instead.
 * For example, we start with A = [3,8,0,9,2,5], which is a run-length encoding of the sequence [8,8,8,5,5].
 * This is because the sequence can be read as "three eights, zero nines, two fives".
 *
 * Example 1:
 * Input: ["RLEIterator","next","next","next","next"], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
 * Output: [null,8,8,5,-1]
 * Explanation:
 * RLEIterator is initialized with RLEIterator([3,8,0,9,2,5]).
 * This maps to the sequence [8,8,8,5,5].
 * RLEIterator.next is then called 4 times:
 * .next(2) exhausts 2 terms of the sequence, returning 8.  The remaining sequence is now [8, 5, 5].
 * .next(1) exhausts 1 term of the sequence, returning 8.  The remaining sequence is now [5, 5].
 * .next(1) exhausts 1 term of the sequence, returning 5.  The remaining sequence is now [5].
 * .next(2) exhausts 2 terms, returning -1.  This is because the first term exhausted was 5,
 * but the second term did not exist.  Since the last term exhausted does not exist, we return -1.
 */
public class RLEIterator {
    public static void main(String[] args) {
        int[] A = new int[]{3, 8, 0, 9, 2, 5};
        RLEIterator iterator = new RLEIterator(A);
        System.out.println(iterator.next(2));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(2));
    }

    private int cur;
    private int[] nums;

    public RLEIterator(int[] A) {
        cur = 0;
        nums = A;
    }

    /**
     * 每两个数字组成一个数字对儿，前一个数字表示后面的一个数字重复出现的次数。然后有一个 next 函数，让我们返回数组的第n个数字
     * 用一个指针 cur，指向当前数字对儿的次数。那么在 next 中，首先来个 while 循环，判读假如 cur 没有越界，且当n大于当前当次数了，
     * 则n减去当前次数，cur 自增2，移动到下一个数字对儿的次数上。当 while 循环结束后，判断若此时 cur 已经越界了，则返回 -1，
     * 否则当前次数减去n，并且返回当前数字即可
     */
    public int next(int n) {
        while (cur < nums.length && n > nums[cur]) {
            n -= nums[cur];
            cur += 2;
        }
        if (cur >= nums.length) {
            return -1;
        }
        nums[cur] -= n;
        return nums[cur + 1];
    }
}

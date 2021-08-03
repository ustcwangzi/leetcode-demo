package com.wz.other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Design an iterator that supports the peek operation on a list in addition to the hasNext and the next operations.
 * Implement the PeekingIterator class:
 * 1. PeekingIterator(int[] nums) Initializes the object with the given integer array nums.
 * 2. int next() Returns the next element in the array and moves the pointer to the next element.
 * 3. bool hasNext() Returns true if there are still elements in the array.
 * 4. int peek() Returns the next element in the array without moving the pointer.
 *
 * Example 1:
 * Input
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 2, 2, 3, false]
 * Explanation
 * PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
 * peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
 * peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
 * peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
 * peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
 * peekingIterator.hasNext(); // return False
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 1000
 * 3. All the calls to next and peek are valid.
 * 4. At most 1000 calls will be made to next, hasNext, and peek.
 */
public class PeekingIterator implements Iterator<Integer> {
    public static void main(String[] args) {
        PeekingIterator peekingIterator = new PeekingIterator(Arrays.asList(1, 2, 3).iterator());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.peek());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.hasNext());
    }

    private final Iterator<Integer> iterator;
    private Integer nextValue;

    /**
     * 在普通的迭代器 Iterator 的基础上增加了 peek 功能，就是返回查看下一个值的功能，但是不移动指针，next() 才会移动指针
     * 使用变量 nextValue 专门来保存下一个值，在调用 next() 时对 nextValue 进行更新，在调用 peek() 时 nextValue 保持不变
     */
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (hasNext()) {
            nextValue = iterator.next();
        } else {
            nextValue = null;
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nextValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer value = nextValue;
        if (iterator.hasNext()) {
            nextValue = iterator.next();
        } else {
            nextValue = null;
        }
        return value;
    }

    @Override
    public boolean hasNext() {
        return nextValue != null || iterator.hasNext();
    }
}

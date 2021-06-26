package com.wz.binarysearch;

public class FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        char[] letters = new char[]{'c', 'f', 'j'};
        System.out.println(nextGreatestLetter(letters, 'a'));
        System.out.println(nextGreatestLetter(letters, 'c'));
        System.out.println(nextGreatestLetter(letters, 'j'));
    }

    /**
     * 二分查找
     * letters[mid] <= target 时，说明左侧全部不满足条件，因此将 left 赋值为 mid+1
     * 否则，说明右侧元素太大了，将 right 赋值为 mid-1，缩小范围
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left >= letters.length ? letters[0] : letters[left];
    }
}

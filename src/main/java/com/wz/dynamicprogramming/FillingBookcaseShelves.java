package com.wz.dynamicprogramming;

/**
 * We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
 * We want to place these books in order onto bookcase shelves that have total width shelf_width.
 * We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width),
 * then build another level of shelf of the bookcase so that the total height of the bookcase has increased by
 * the maximum height of the books we just put down.  We repeat this process until there are no more books to place.
 * Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.
 * For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf,
 * the third book on the second shelf, and the fourth and fifth book on the last shelf.
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 *
 * Example 1:
 * @see ../../../../resource/FillingBookcaseShelves.jpg
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * Explanation:
 * The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 *
 * Constraints:
 * 1. 1 <= books.length <= 1000
 * 2. 1 <= books[i][0] <= shelf_width <= 1000
 * 3. 1 <= books[i][1] <= 1000
 */
public class FillingBookcaseShelves {
    public static void main(String[] args) {
        int[][] books = new int[][]{{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
        System.out.println(minHeightShelves(books, 4));
    }

    /**
     * dp[i] 表示前 i 本书能够到达的最小高度
     * 求摆放前 i 本书需要的最小高度，首先需要求摆放前 i-1 书需要的最小高度，以此类推，最初需要计算的是摆放第0本书需要的最小高度，也就是0。
     * 根据前 i-1 本书求前 i 书需要的最小高度的思路是：
     * 尝试 ①将第 i 本书放在前 i-1 本书的下一层 ②将前 i-1 本书的最后几本和第i本书放在同一层两种方案
     * 看哪种方案高度更小就用哪种方案，依次求出摆放前 1,...,n 本书需要的最小高度
     * 对于第 i 本书，有两种选择
     * 1. 自己单独一层，dp[i] = dp[i-1] + h[i]
     * 2. 和前面的书放在一起， dp[i] = min{dp[j-1] + max{h[j] ～ h[i-1]}}
     */
    public static int minHeightShelves(int[][] books, int shelf_width) {
        int[] dp = new int[books.length + 1];
        dp[0] = 0;
        // 依次求摆放前i本书的最小高度
        for (int i = 1; i < dp.length; ++i) {
            int width = books[i - 1][0], height = books[i - 1][1];
            dp[i] = dp[i - 1] + height;
            // 将前 i-1本书从第 i-1 本开始放在与 i 同一层，直到这一层摆满或者所有的书都摆好
            for (int j = i - 1; j > 0 && width + books[j - 1][0] <= shelf_width; j--) {
                // 每层的高度由最高的那本书决定
                height = Math.max(height, books[j - 1][1]);
                width += books[j - 1][0];
                // 选择高度最小的方法
                dp[i] = Math.min(dp[i], dp[j - 1] + height);
            }
        }
        return dp[books.length];
    }
}

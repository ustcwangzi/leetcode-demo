package com.wz.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * There is an m x n binary grid matrix with all the values set 0 initially. Design an algorithm to randomly pick an index (i, j)
 * where matrix[i][j] == 0 and flips it to 1. All the indices (i, j) where matrix[i][j] == 0 should be equally likely to be returned.
 * Optimize your algorithm to minimize the number of calls made to the built-in random function of your language and optimize the time and space complexity.
 * Implement the Solution class:
 * 1. RandomFlipMatrix(int m, int n) Initializes the object with the size of the binary matrix m and n.
 * 2. int[] flip() Returns a random index [i, j] of the matrix where matrix[i][j] == 0 and flips it to 1.
 * 3. void reset() Resets all the values of the matrix to be 0.
 *
 * Example 1:
 * Input
 * ["Solution", "flip", "flip", "flip", "reset", "flip"]
 * [[3, 1], [], [], [], [], []]
 * Output
 * [null, [1, 0], [2, 0], [0, 0], null, [2, 0]]
 * Explanation
 * Solution solution = new Solution(3, 1);
 * solution.flip();  // return [1, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
 * solution.flip();  // return [2, 0], Since [1,0] was returned, [2,0] and [0,0]
 * solution.flip();  // return [0, 0], Based on the previously returned indices, only [0,0] can be returned.
 * solution.reset(); // All the values are reset to 0 and can be returned.
 * solution.flip();  // return [2, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
 *
 * Constraints:
 * 1. 1 <= m, n <= 10^4
 * 2. There will be at least one free cell for each call to flip.
 * 3. At most 1000 calls will be made to flip and reset.
 */
public class RandomFlipMatrix {
    public static void main(String[] args) {
        RandomFlipMatrix matrix = new RandomFlipMatrix(3, 1);
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        matrix.reset();
        System.out.println(Arrays.toString(matrix.flip()));
    }

    private final int row, col;
    private final Set<String> set;

    public RandomFlipMatrix(int m, int n) {
        this.row = m;
        this.col = n;
        this.set = new HashSet<>();
    }

    /**
     * 每次随机翻转其中的一个点，其中的隐含条件是，之前翻转过的点，下一次不能再翻转回来，而随机生成点是有可能有重复的，一旦很多点都被翻转后，很大概率会重复生成之前的点，
     * 所以需要有去重的操作，可以使用 set 保存已经翻转过的点，已存在时重新生成
     */
    public int[] flip() {
        Random random = new Random();
        int x = random.nextInt(row), y = random.nextInt(col);
        while (!set.add(x + "," + y)) {
            x = random.nextInt(row);
            y = random.nextInt(col);
        }
        return new int[]{x, y};
    }

    public void reset() {
        set.clear();
    }
}

package com.wz.string;

/**
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 * @see ../../../../resource/AlphabetBoardPath.jpg
 * We may make the following moves:
 * 1. 'U' moves our position up one row, if the position exists on the board;
 * 2. 'D' moves our position down one row, if the position exists on the board;
 * 3. 'L' moves our position left one column, if the position exists on the board;
 * 4. 'R' moves our position right one column, if the position exists on the board;
 * 5. '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
 *
 * Example 1:
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 *
 * Example 2:
 * Input: target = "code"
 * Output: "RR!DDRR!UUL!R!"
 *
 * Constraints:
 * 1. 1 <= target.length <= 100
 * 2. target consists only of English lowercase letters.
 */
public class AlphabetBoardPath {
    public static void main(String[] args) {
        System.out.println(alphabetBoardPath("leet"));
        System.out.println(alphabetBoardPath("code"));
    }

    /**
     * 求得当前字母的位置相对于前一字母的位置的x y位移即可
     * 要注意的是 z 这个字母，从它出去只能先上后右，进入它只能先做后下
     */
    public static String alphabetBoardPath(String target) {
        int n = 0, sourceRow = 0, sourceCol = 0;
        StringBuilder result = new StringBuilder("");
        while (n < target.length()) {
            int c = target.charAt(n) - 'a';
            int row = c / 5, col = c % 5;
            while (row < sourceRow) {
                result.append('U');
                sourceRow--;
            }
            while (col > sourceCol) {
                result.append('R');
                sourceCol++;
            }
            while (col < sourceCol) {
                result.append('L');
                sourceCol--;
            }
            while (row > sourceRow) {
                result.append('D');
                sourceRow++;
            }

            result.append('!');
            n++;
        }

        return result.toString();
    }
}

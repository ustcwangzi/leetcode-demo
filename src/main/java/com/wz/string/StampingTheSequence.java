package com.wz.string;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * You want to form a target string of lowercase letters.
 * At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.
 * On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with
 * the corresponding letter from the stamp.  You can make up to 10 * target.length turns.
 * For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc"
 * in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)
 * If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.
 * If the sequence is not possible to stamp, return an empty array.
 * For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2],
 * corresponding to the moves "?????" -> "abc??" -> "ababc".
 * Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.
 * Any answers specifying more than this number of moves will not be accepted.
 *
 * Example 1:
 * Input: stamp = "abc", target = "ababc"
 * Output: [0,2]
 * ([1,0,2] would also be accepted as an answer, as well as some other answers.)
 *
 * Example 2:
 * Input: stamp = "abca", target = "aabcaca"
 * Output: [3,0,1]
 *
 * Note:
 * 1. 1 <= stamp.length <= target.length <= 1000
 * 2. stamp and target only contain lowercase letters.
 */
public class StampingTheSequence {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(movesToStamp("abc", "ababc")));
        System.out.println(Arrays.toString(movesToStamp("abca", "aabcaca")));
    }

    /**
     * 找出所有盖印戳的位置，使得刚好可以盖出给定的字符串 target
     * 换一个方向思考，假如给的是 target，每次盖印章，将对应的位置变成星号，只要将 target 中所有的字符盖成星号，再把盖印章的顺序翻转一下，就是答案
     * 比如 target="aabccbc"，stamp="abc"，那么首先在 target 中找 abc，可以找到，从位置1出开始盖，target 变为 a***cbc，
     * 同时标记此时已经盖了 3 个字母，加入到 stars 变量中。然后继续找 abc，没有的话，就需要改变印戳了，开始往里面加星号，首先加一个星号，
     * 加的位置有三个，分别是 ab*, a*c, *bc，发现这三种都无法匹配，于是开始加两个星号，就有 a**，**c，其中 a** 可以成功匹配，
     * 起始位置为0，stars 加1，于是 target 变为 ****cbc，然后发现此时 **c 也可以成功匹配，起始位置为2，stars 加1，target 变为 *****bc
     * 现在并不需要给 stamp 中加三个星号，这样没有意义，要做的是再从开头找一遍，此时发现 *bc 可以匹配，起始位置为4，stars 加2，
     * 到现在位置 target 完全变为星号，当无法进行盖印戳的时候，就退出循环，需要一个 visited 的变量来标记一下是否进行了戳印
     * 循环退出后要将结果数组翻转一下，同时还要看 stars 是否等于 target 的长度，只有相等了，才说明每个字母都被戳印了，否则返回空集
     */
    public static int[] movesToStamp(String stamp, String target) {
        char[] sArray = stamp.toCharArray(), tArray = target.toCharArray();
        List<Integer> result = new LinkedList<>();
        boolean[] visited = new boolean[tArray.length];

        int stars = 0;
        while (stars < tArray.length) {
            boolean doneReplace = false;
            for (int i = 0; i <= tArray.length - sArray.length; i++) {
                if (!visited[i] && canReplace(tArray, i, sArray)) {
                    stars = doReplace(tArray, i, sArray.length, stars);
                    doneReplace = true;
                    visited[i] = true;
                    result.add(i);
                    if (stars == tArray.length) {
                        break;
                    }
                }
            }

            if (!doneReplace) {
                return new int[0];
            }
        }

        int[] resArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArray[i] = result.get(result.size() - i - 1);
        }
        return resArray;
    }

    private static boolean canReplace(char[] tArray, int p, char[] sArray) {
        for (int i = 0; i < sArray.length; i++) {
            if (tArray[i + p] != '*' && tArray[i + p] != sArray[i]) {
                return false;
            }
        }
        return true;
    }

    private static int doReplace(char[] tArray, int p, int len, int count) {
        for (int i = 0; i < len; i++) {
            if (tArray[i + p] != '*') {
                tArray[i + p] = '*';
                count++;
            }
        }
        return count;
    }
}

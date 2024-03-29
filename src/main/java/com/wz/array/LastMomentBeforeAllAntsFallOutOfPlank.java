package com.wz.array;

/**
 * We have a wooden plank of the length n units. Some ants are walking on the plank,
 * each ant moves with speed 1 unit per second. Some of the ants move to the left, the other move to the right.
 * When two ants moving in two different directions meet at some point, they change their directions
 * and continue moving again. Assume changing directions doesn't take any additional time.
 * When an ant reaches one end of the plank at a time t, it falls out of the plank imediately.
 * Given an integer n and two integer arrays left and right, the positions of the ants moving to the left and the right.
 * Return the moment when the last ant(s) fall out of the plank.
 *
 * Example 1:
 * @link ../../../../resource/LastMomentBeforeAllAntsFallOutOfPlank.jpg
 * Input: n = 4, left = [4,3], right = [0,1]
 * Output: 4
 * Explanation: In the image above:
 * -The ant at index 0 is named A and going to the right.
 * -The ant at index 1 is named B and going to the right.
 * -The ant at index 3 is named C and going to the left.
 * -The ant at index 4 is named D and going to the left.
 * Note that the last moment when an ant was on the plank is t = 4 second, after that it falls imediately out of the plank.
 *
 * Example 2:
 * Input: n = 7, left = [], right = [0,1,2,3,4,5,6,7]
 * Output: 7
 * Explanation: All ants are going to the right, the ant at index 0 needs 7 seconds to fall.
 *
 * Example 3:
 * Input: n = 7, left = [0,1,2,3,4,5,6,7], right = []
 * Output: 7
 * Explanation: All ants are going to the left, the ant at index 7 needs 7 seconds to fall.
 */
public class LastMomentBeforeAllAntsFallOutOfPlank {
    public static void main(String[] args) {
        int[] left = new int[]{4, 3};
        int[] right = new int[]{0, 1};
        System.out.println(getLastMoment(4, left, right));

        left = new int[]{};
        right = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(getLastMoment(7, left, right));

        left = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        right = new int[]{};
        System.out.println(getLastMoment(7, left, right));
    }

    /**
     * 两个蚂蚁相撞之后会互相调头，其实只要想成如果每只蚂蚁都长得一模一样，那么是不是蚂蚁碰撞的调头 就等于 穿透了？
     * 那么就可以直接让蚂蚁直接穿透爬行就好了，题目就变成了求最晚落地的蚂蚁所需时间，与碰撞无关
     * 两只蚂蚁相遇可以忽略，因为交换方向如果不考虑蚂蚁编号的话实际上等于没有交换
     * 对于向左走的蚂蚁，需要的时间等于初始的位置；对于向右走的蚂蚁，需要的时间等于长度减去初始位置。
     */
    public static int getLastMoment(int n, int[] left, int[] right) {
        int result = 0;
        for (int cur : left) {
            result = Math.max(result, cur);
        }
        for (int cur : right) {
            result = Math.max(result, n - cur);
        }
        return result;
    }
}

package com.wz.math;

/**
 * There is a special square room with mirrors on each of the four walls.
 * Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.
 * The square room has walls of length p, and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.
 * Return the number of the receptor that the ray meets first.  (It is guaranteed that the ray will meet a receptor eventually.)
 *
 * Example 1:
 * Input: p = 2, q = 1
 * Output: 2
 * Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.
 *
 * Note:
 * 1 <= p <= 1000
 * 0 <= q <= p
 */
public class MirrorReflection {
    public static void main(String[] args) {
        System.out.println(mirrorReflection(2, 1));
    }

    /**
     * 一个正方形的房间，四面都是镜子墙，然后在西南角有一个激光发射器，其余三个角都有接收装置，问最终激光会被哪个接收器接收。
     * 首先从最简单的情况开始分析，当p和q相等的时候，那么激光直接到达接收器1，当 p/q = 2 的时候，就如例子中所示，
     * 经过右边的镜面反射后到达左上角的接受器2。那么我们再来考虑下这三种情况 p/q = 3, p/q = 4, p/q = 3/2
     * 激光遇到镜面是会发生折射的，但是假如没有镜面，就会仍然沿直线前进，那么对于 p/q = 3 时，若我们在右边增加大小相同的2个房间，
     * 则激光会到达右上角，由于第二个房间和原始房间是镜面对称的，而第三个房间和第二个房间也是镜面对称的，则第三个房间和原始房间就是一样的了，
     * 那么就可以假设一下，奇数房间和原始房间的布局相同。再来 p/q = 4 时，在右边复制了三个房间，在第四个房间的时候，激光到达了右上角，
     * 而第偶数个房间的布局是跟原始房间称镜面反射的，则就是接受器2了。其实有些时候，不止要在右边复制房间，还需要在上面复制房间，
     * 比如当 p/q = 3/2 时，需要复制出一个 2x3 大小的矩阵出来，在水平方向共有三个房间，是奇数则水平方向和原始房间布局一致，
     * 但是竖直方向也复制了房间，那么竖直方向有偶数个房间，则竖直方向和原始房间成镜面反射，则最右上角为接收器0。
     * 分析到这里，已经能总结出规律如下了：
     * p为奇数，q为奇数时，到达接收器1。
     * p为奇数，q为偶数时，到达接收器0。
     * p为偶数，q为奇数时，到达接收器2。
     * 为什么没有p和q均为偶数的情况呢？比如 p = 4, q = 2，其实只要画个图就知道，这个跟 p = 2, q = 1 的情况是一摸一样的，若p和q均为偶数，
     * 那么一定可以同时除以2，其实可以先对p和q进行判断，若二者同为偶数，则同时除以2，直到不同时为偶数时，然后再带入上面归纳的三种情况求解即可
     */
    public static int mirrorReflection(int p, int q) {
        while (p % 2 == 0 && q % 2 == 0) {
            p /= 2;
            q /= 2;
        }
        if (p % 2 == 0) {
            return 2;
        }
        if (q % 2 == 0) {
            return 0;
        }
        return 1;
    }
}

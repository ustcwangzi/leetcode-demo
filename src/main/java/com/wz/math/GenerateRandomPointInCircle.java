package com.wz.math;

import java.util.Arrays;
import java.util.Random;

/**
 * Given the radius and x-y positions of the center of a circle, write a function randPoint which generates a uniform random point in the circle.
 * Note:
 * input and output values are in floating-point.
 * radius and x-y position of the center of the circle is passed into the class constructor.
 * a point on the circumference of the circle is considered to be in the circle.
 * randPoint returns a size 2 array containing x-position and y-position of the random point, in that order.
 *
 * Example 1:
 * Input:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1,0,0],[],[],[]]
 * Output: [null,[-0.72939,-0.65505],[-0.78502,-0.28626],[-0.83119,-0.19803]]
 *
 * Example 2:
 * Input:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[10,5,-7.5],[],[],[]]
 * Output: [null,[11.52438,-8.33273],[2.46992,-16.21705],[11.13430,-12.42337]]
 * Explanation of Input Syntax:
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has three arguments,
 * the radius, x-position of the center, and y-position of the center of the circle. randPoint has no arguments.
 * Arguments are always wrapped with a list, even if there aren't any.
 */
public class GenerateRandomPointInCircle {
    public static void main(String[] args) {
        GenerateRandomPointInCircle pointInCircle = new GenerateRandomPointInCircle(1, 0, 0);
        System.out.println(Arrays.toString(pointInCircle.randPoint()));
    }

    private double radius;
    private double centerX;
    private double centerY;
    private Random random;

    /**
     * 给定一个圆的半径和圆心坐标，生成圆内点的坐标
     * 1. 在圆内随机取点不好做，但是如果画出这个圆的外接正方形，在正方形里面采样就好做了
     * 2. 取两个random确定正方形内的横坐标和纵坐标即可在正方形内采样
     * 3. 如果采样到的点不在圆内，则丢弃，继续采样，当采样的点在圆内，则返回该点
     * 4. 这样采样可以视作在圆内采样的一种近似，到这里就可以把问题解决了
     */
    public GenerateRandomPointInCircle(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.centerX = x_center;
        this.centerY = y_center;
        this.random = new Random();
    }

    public double[] randPoint() {
        while (true) {
            double x = centerX - radius + random.nextDouble() * 2 * radius;
            double y = centerY - radius + random.nextDouble() * 2 * radius;
            if (Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= radius) {
                return new double[]{x, y};
            }
        }
    }
}

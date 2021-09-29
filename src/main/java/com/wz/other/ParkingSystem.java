package com.wz.other;

/**
 * Design a parking system for a parking lot. The parking lot has three kinds of parking spaces: big, medium, and small, with a fixed number of slots for each size.
 * Implement the ParkingSystem class:
 * 1. ParkingSystem(int big, int medium, int small) Initializes object of the ParkingSystem class.
 *    The number of slots for each parking space are given as part of the constructor.
 * 2. bool addCar(int carType) Checks whether there is a parking space of carType for the car that wants to get into the parking lot.
 *    carType can be of three kinds: big, medium, or small, which are represented by 1, 2, and 3 respectively.
 *    A car can only park in a parking space of its carType. If there is no space available, return false, else park the car in that size space and return true.
 *
 *
 * Example 1:
 * Input
 * ["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
 * [[1, 1, 0], [1], [2], [3], [1]]
 * Output
 * [null, true, true, false, false]
 * Explanation
 * ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
 * parkingSystem.addCar(1); // return true because there is 1 available slot for a big car
 * parkingSystem.addCar(2); // return true because there is 1 available slot for a medium car
 * parkingSystem.addCar(3); // return false because there is no available slot for a small car
 * parkingSystem.addCar(1); // return false because there is no available slot for a big car. It is already occupied.
 *
 * Constraints:
 * 1. 0 <= big, medium, small <= 1000
 * 2. carType is 1, 2, or 3
 * 3. At most 1000 calls will be made to addCar
 */
public class ParkingSystem {
    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        System.out.println(parkingSystem.addCar(1));
        System.out.println(parkingSystem.addCar(2));
        System.out.println(parkingSystem.addCar(3));
        System.out.println(parkingSystem.addCar(1));
    }

    private int big;
    private int medium;
    private int small;

    /**
     * 使用 big、medium、small 记录三种车型剩余的车位，停车时判断对应位置是否大于等于 1，即减一之后是否大于等于 0
     */
    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        switch (carType) {
            case 1:
                return --big >= 0;
            case 2:
                return --medium >= 0;
            case 3:
                return --small >= 0;
        }
        return false;
    }
}

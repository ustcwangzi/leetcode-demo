package com.wz.practice;

import java.util.concurrent.Semaphore;

public class PrintABCRotationBySemaphore {
    public static void main(String[] args) throws InterruptedException {
        PrintABC printABC = new PrintABC();
        printABC.semaphoreB.acquire();
        printABC.semaphoreC.acquire();
        for (int i = 0; i < 10; i++) {
            new Thread(printABC::printA).start();
            new Thread(printABC::printB).start();
            new Thread(printABC::printC).start();
        }
    }

    private static class PrintABC {
        Semaphore semaphoreA = new Semaphore(1), semaphoreB = new Semaphore(1), semaphoreC = new Semaphore(1);

        public void printA() {
            try {
                semaphoreA.acquire();
                System.out.print("A");
                semaphoreB.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void printB() {
            try {
                semaphoreB.acquire();
                System.out.print("B");
                semaphoreC.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void printC() {
            try {
                semaphoreC.acquire();
                System.out.print("C");
                semaphoreA.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

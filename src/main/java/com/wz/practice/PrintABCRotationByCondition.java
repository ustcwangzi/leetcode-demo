package com.wz.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCRotationByCondition {
    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();
        for (int i = 0; i < 10; i++) {
            new Thread(printABC::printA).start();
            new Thread(printABC::printB).start();
            new Thread(printABC::printC).start();
        }
    }

    private static class PrintABC {
        private final Lock lock = new ReentrantLock();
        private final Condition conditionA = lock.newCondition(), conditionB = lock.newCondition(), conditionC = lock.newCondition();
        private int num = 1;

        public void printA() {
            lock.lock();
            try {
                if (num != 1) {
                    conditionA.await();
                }
                System.out.print("A");
                num = 2;
                conditionB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printB() {
            lock.lock();
            try {
                if (num != 2) {
                    conditionB.await();
                }
                System.out.print("B");
                num = 3;
                conditionC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printC() {
            lock.lock();
            try {
                if (num != 3) {
                    conditionC.await();
                }
                System.out.print("C");
                num = 1;
                conditionA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
